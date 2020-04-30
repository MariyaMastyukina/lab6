package Client;

import Server.Collection.CollectWorker;
import Server.MapFromServer;

import java.io.*;

/**
 * @author Мастюкина Мария
 * Класс, работающий в качестве клиента
 */
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        IOInterface ioClient = new IOTerminal(System.in, System.out);
        Validation validator;
        ServerConnection serverConnection = new ServerConnection();
        if (args.length == 2) {
            serverConnection.connection(args[0], args[1]);
            ioClient.writeln("Здравстуйте! Введите \"help\", чтобы увидеть список доступных команд");
        } else {
            ioClient.writeln("введите корректный хост и порт");
            System.exit(0);
        }
        try {
            File file = new File("Collection.json");
            if (file.canRead()) {
                CollectWorker collection = new CollectWorker();
                collection.fromFileToColl(file);
            } else {
                ioClient.writeln("Чтение файла невозможно. Поменяйте права");
            }
        } catch (FileNotFoundException e) {
            ioClient.writeln("Такого файла не существует");
        }
        IOInterface ioServer = new IOTerminal(serverConnection.getInputStream(), serverConnection.getOutputStream());
        while (!ioServer.ready()) {
        }
        MapFromServer map = (MapFromServer) ioServer.readObj();
        ioClient.writeln("Введите команду");
        String line = ioClient.readLine();
        CommandObject command = null;
        while(true) {
            command=new CommandObject(line,map);
                if (command.getChecker()) {
                    ioServer.writeObj(command);
                    while (!ioServer.ready()){

                    }
                    while (ioServer.ready()){
                        ioClient.writeln(ioServer.readLine());
                    }
//                    TransferObject transferObject = new TransferObject(ioServer);
//                    transferObject.transfer(command,ioClient);
                }
                ioClient.writeln("Введите команду");
                line=ioClient.readLine();
            }
        }
    }