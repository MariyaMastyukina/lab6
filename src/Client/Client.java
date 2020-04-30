package Client;

import Server.Collection.CollectWorker;
import Server.MapFromServer;

import java.io.*;
import java.util.Scanner;

/**
 * @author Мастюкина Мария
 * Класс, работающий в качестве клиента
 */
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        IOInterfaceStream ioClient = new IOTerminal(System.in, System.out);
        ServerConnection serverConnection = new ServerConnection();
        if (args.length == 2) {
            serverConnection.connection(args[0], args[1]);
            ioClient.writeln("Здравстуйте! Введите \"help\", чтобы увидеть список доступных команд");
        } else {
            ioClient.writeln("введите корректный хост и порт");
            System.exit(0);
        }

        IOInterfaceStream ioServer = new IOTerminal(serverConnection.getInputStream(), serverConnection.getOutputStream());
        while (!ioServer.ready()) {
        }
        MapFromServer map = (MapFromServer) ioServer.readObj();
        TransferObject transferObject = new TransferObject(ioServer, map, ioClient, serverConnection);
        ioClient.writeln(ioServer.readLine());
        ioClient.writeln("Введите команду");
        String line = ioClient.readLine();
        CommandObject command;
        while (true) {
            command = new CommandObject(line, map, null);
            if (command.getChecker()) {
                try {
                    transferObject.transfer(command);
                } catch (StackOverflowError e) {
                    ioClient.writeln("Произошла зацикливнаие команды execute_script. Выполнение команды прекращено");
                }
                ioClient.writeln("Введите команду");
                line = ioClient.readLine();
            }
        }
    }
}