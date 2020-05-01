package Server;

import Server.Collection.CollectWorker;
import Server.Collection.ControlUnit;

import java.io.*;
import java.net.ConnectException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import Client.*;
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
        LOGGER=Logger.getLogger(Server.class.getName());
        try{
            LOGGER.log(Level.INFO,"Подключение к клиенту");
            clientConnection=new ClientConnection(Integer.parseInt(args[0]));
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
        Launch launch=new Launch();
        MapFromServer map=new MapFromServer(launch.getCommands());
        while(true){
            clientConnection.getSelector().select();
            Iterator iterator= clientConnection.getSelector().selectedKeys().iterator();
            while(iterator.hasNext()){
                LOGGER.log(Level.INFO,"Получение текущего селектора");
                SelectionKey selectionKey=(SelectionKey) iterator.next();
                iterator.remove();
                try{
                    LOGGER.log(Level.INFO,"Проверка ключа селектора");
                    if (!selectionKey.isValid()){
                        continue;
                    }
                    if (selectionKey.isAcceptable()){
                        LOGGER.log(Level.INFO,"Разрешение подключение клиента к севреру");
                        clientConnection.acceptConnection();
                        checker=false;
                    }
                    if (selectionKey.isWritable()){
                        if (!checker){
                            ioClient=new IOClient((SocketChannel) selectionKey.channel());
                            LOGGER.log(Level.INFO,"Отправка списка команд серверу");
                            ioClient.writeObj(map);
                            try {
                                LOGGER.log(Level.INFO,"Загрузка содержимого файла Collection.json в коллекцию ");
                                File file = new File("Collection.json");
                                if (file.canRead()) {
                                    CollectWorker collection = new CollectWorker();
                                    collection.fromFileToColl(file);
                                    ioClient.writeln("Содержимое файла Collection.json записано в коллекцию");
                                } else {
                                    LOGGER.log(Level.INFO,"Ошибка в правах файла");
                                    ioClient.writeln("Чтение содержимого коллекции из файла Collection.json невозможно, коллекция пуста. Поменяйте права на данный файл");
                                }
                            } catch (FileNotFoundException e) {
                                LOGGER.log(Level.WARNING,"Файл не существует");
                                ioClient.writeln("Collection.json файла не существует, загрузка коллекции невозможно, коллекция пуста");
                            }
                            checker=true;
                        }
                        else{
                            LOGGER.log(Level.INFO,"Запуск полученный от клиента команды");
                            launch.beginProgramm(currentCommand,ioClient);
                        }
                        selectionKey.interestOps(SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()){
                        LOGGER.log(Level.INFO,"Чтение полученной от клиента команды");
                        ioClient= new IOClient((SocketChannel) selectionKey.channel());
                        currentCommand=(CommandObject) ioClient.readObj();
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                    }
                }
                catch(ConnectException e){
                    LOGGER.log(Level.WARNING,"Ошибка в соединении с клиентом",e);
                    System.out.println(e.getMessage());
                    checker=false;
                }
            }
        }
    }
}
