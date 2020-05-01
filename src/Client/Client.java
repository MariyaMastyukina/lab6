package Client;

import Server.Collection.CollectWorker;
import Server.MapFromServer;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Мастюкина Мария
 * Класс, работающий в качестве клиента
 */
public class Client {
    static Logger LOGGER;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LOGGER=Logger.getLogger(Client.class.getName());
        IOInterfaceStream ioClient = new IOTerminal(System.in, System.out);
        ServerConnection serverConnection = new ServerConnection();
        LOGGER.log(Level.INFO,"Начало работы клиента");
        if (args.length == 2) {
            LOGGER.log(Level.INFO,"Подключение к серверу");
            serverConnection.connection(args[0], args[1]);
            ioClient.writeln("Здравстуйте! Введите \"help\", чтобы увидеть список доступных команд");
        } else {
            LOGGER.log(Level.INFO,"Ошибка в соединении с сервером, неверный хост, порт");
            ioClient.writeln("введите корректный хост и порт");
            System.exit(0);
        }

        IOInterfaceStream ioServer = new IOTerminal(serverConnection.getInputStream(), serverConnection.getOutputStream());
        LOGGER.log(Level.INFO,"Ждем готовности сервера");
        while (!ioServer.ready()) {
        }
        LOGGER.log(Level.INFO,"Получаем спсиок команд с сервера");
        MapFromServer map = (MapFromServer) ioServer.readObj();
        TransferObject transferObject = new TransferObject(ioServer, map, ioClient, serverConnection);
        ioClient.writeln(ioServer.readLine());
        ioClient.writeln("Введите команду");
        LOGGER.log(Level.INFO,"Считываем команду с консоли");
        String line = ioClient.readLine();
        CommandObject command;
        while (true) {
            LOGGER.log(Level.INFO, "Создаем объект текущей команды");
            command = new CommandObject(line, map, null);
            if (command.getChecker()) {
                try {
                    LOGGER.log(Level.INFO, "Запускаем метод отправки командв на севрер");
                    transferObject.transfer(command);
                } catch (StackOverflowError e) {
                    LOGGER.log(Level.WARNING, "Запускаем метод отправки командв на севрер", e);
                    ioClient.writeln("Произошла зацикливнаие команды execute_script. Выполнение команды прекращено");
                }
            }
                ioClient.writeln("Введите команду");
                LOGGER.log(Level.INFO,"Считываем команду с консоли");
                line = ioClient.readLine();

        }
    }
}