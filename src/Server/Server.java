package Server;

import Client.DataUtils.CommandObject;
import Client.IOClient.IOInterfaceStream;
import Client.IOClient.IOTerminal;
import Server.Data.MapFromServer;
import Server.IOServer.IOClient;
import Server.IOServer.IOInterfaceChannel;
import Server.Launch.*;

import java.io.*;
import java.net.BindException;
import java.net.ConnectException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import Server.Commands.SaveCommand;

/**
 * @author Мастюкина Мария
 * Класс, работающий в качестве сервера
 */
public class Server {
    private static boolean checker;
    static Logger LOGGER;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientConnection clientConnection = null;
        CommandObject currentCommand = null;
        IOInterfaceChannel ioClient = null;
        IOInterfaceStream ioServer=new IOTerminal(System.in,System.out);
        LOGGER=Logger.getLogger(Server.class.getName());
        Integer PORT=Integer.parseInt(args[0]);
        try{
            LOGGER.log(Level.INFO,"Подключение к клиенту");
            clientConnection=new ClientConnection();
            clientConnection.connect(PORT);
        }
        catch(IndexOutOfBoundsException e){
            LOGGER.log(Level.WARNING,"Ошибка в подключении к клиенту, не указан порт");
            System.out.println("Нужно указать порт");
            System.exit(0);
        }
        catch (NumberFormatException e){
            LOGGER.log(Level.WARNING,"Ошибка в подключении к клиенту, формат порта указан неверно");
            System.out.println("Формат порта не верен");
            System.exit(0);
        }
        catch (BindException e){
            PORT++;
            clientConnection.connect(PORT);
        }
        Launch launch=new Launch();
        MapFromServer map=new MapFromServer(launch.getCommands());


        while(true) {
            try {
                if (ioServer.ready()) {
                    if (ioServer.readLine().equals("save")) {
                        new SaveCommand(new CollectWorker()).execute(args[1], null, null);
                    }
                    if (ioServer.readLine().equals("help")) {
                        System.out.println("help:вывести справку по доступным командам\n" +
                                "info:вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                                "show:вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                                "add:добавить новый элемент в коллекцию\n" +
                                "update id :обновить значение элемента коллекции, id которого равен заданному\n" +
                                "remove_by_id id:удалить элемент из коллекции по его id\n" +
                                "clear:очистить коллекцию\n" +
                                "save:сохранить коллекцию в файл\n" +
                                "execute_script file_name:считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                                "exit:завершить программу (без сохранения в файл)\n" +
                                "remove_last:удалить последний элемент из коллекции\n" +
                                "sort:отсортировать коллекцию в естественном порядке\n" +
                                "history:вывести последние 8 команд (без их аргументов)\n" +
                                "remove_all_by_meters_above_sea_level metersAboutSeaLevel:удалить из коллекции все элементы, значение поля metersAboveSeaLevel которого эквивалентно заданному\n" +
                                "group_counting_by_population:сгруппировать элементы коллекции по значению поля population, вывести количество элементов в каждой группе\n" +
                                "print_ascending:вывести элементы коллекции в порядке возрастания");
                    }

                }
            }catch(NullPointerException e){
                System.out.println("Завершение работы сервера");
                System.exit(1);
            }
            //к чему готов канал, получаем доступ к селектору
            clientConnection.getSelector().selectNow();
            Iterator iterator = clientConnection.getSelector().selectedKeys().iterator();
            if (iterator.hasNext()) {
                LOGGER.log(Level.INFO, "Получение текущего селектора");
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                iterator.remove();
                try {
                    LOGGER.log(Level.INFO, "Проверка ключа селектора");
                    if (!selectionKey.isValid()) {
                        continue;
                    }
                    if (selectionKey.isAcceptable()) {
                        LOGGER.log(Level.INFO, "Разрешение подключение клиента к севреру");
                        clientConnection.acceptConnection(selectionKey);
                        checker = false;

                    }
                    if (selectionKey.isWritable()) {
                        if (!checker) {
                            ioClient = new IOClient((SocketChannel) selectionKey.channel());
                            LOGGER.log(Level.INFO, "Отправка списка команд серверу");
                            ioClient.writeObj(map);
                            try {
                                LOGGER.log(Level.INFO, "Загрузка содержимого файла Collection.json в коллекцию ");
                                File file = new File(args[1]);
                                if (file.canRead()) {
                                    CollectWorker collection = new CollectWorker();
                                    collection.fromFileToColl(file);
                                    ioClient.writeln("Содержимое файла Collection.json записано в коллекцию");
                                } else {
                                    LOGGER.log(Level.INFO, "Ошибка в правах файла");
                                    ioClient.writeln("Чтение содержимого коллекции из файла Collection.json невозможно, коллекция пуста. Поменяйте права на данный файл");
                                }
                            } catch (FileNotFoundException e) {
                                ioClient.writeln("Collection.json файла не существует, загрузка коллекции невозможно, коллекция пуста");
                            }
                            checker = true;
                        } else {
                            LOGGER.log(Level.INFO, "Запуск полученный от клиента команды");
                            launch.beginProgramm(currentCommand, ioClient);
                        }

                        selectionKey.interestOps(SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()) {
                        LOGGER.log(Level.INFO, "Чтение полученной от клиента команды");
                        ioClient = new IOClient((SocketChannel) selectionKey.channel());
                        currentCommand = (CommandObject) ioClient.readObj();
                        selectionKey.interestOps(SelectionKey.OP_WRITE);

                    }
                } catch (ConnectException e) {
                    System.out.println(e.getMessage());
                    checker = false;
                }
                catch (CancelledKeyException e){
                    ioServer.writeln("Работа с текущем клиентом закончена. Ожидается подключение нового клиента");
                    clientConnection.sscClose();
                    clientConnection.connect(Integer.parseInt(args[0]));
                }
            }
        }
    }
}
