package Server;

import Server.Collection.ControlUnit;

import java.io.*;
import java.net.ConnectException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import Client.*;
/**
 * @author Мастюкина Мария
 * Класс, работающий в качестве сервера
 */
public class Server {
    private static boolean checker;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientConnection clientConnection = null;
        Launch launch=new Launch();
        CommandObject currentCommand = null;
        IOInterface ioClient = null;
        try{
            clientConnection=new ClientConnection(Integer.parseInt(args[0]));
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Нужно указать порт");
            System.exit(0);
        }
        catch (NumberFormatException | IOException e){
            System.out.println("Формат порта не верен");
            System.exit(0);
        }
        MapFromServer map=new MapFromServer(launch.getCommands());
        while(true){
            clientConnection.getSelector().select();
            Iterator iterator= clientConnection.getSelector().selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey=(SelectionKey) iterator.next();
                iterator.remove();
                try{
                    if (!selectionKey.isValid()){
                        continue;
                    }
                    if (selectionKey.isAcceptable()){
                        clientConnection.acceptConnection();
                        checker=false;
                    }
                    if (selectionKey.isWritable()){
                        if (!checker){
                            ioClient=new IOClient((SocketChannel) selectionKey.channel());
                            ioClient.writeObj(map);
                            checker=true;
                        }
                        else{
                            System.out.println("byb");
                            launch.beginProgramm(currentCommand,ioClient);
                        }
                        selectionKey.interestOps(SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()){
                        System.out.println("byb");
                        ioClient= new IOClient((SocketChannel) selectionKey.channel());
                        System.out.println("byb");
                        currentCommand=(CommandObject) ioClient.readObj();
                        System.out.println("byb");
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                    }
                }
                catch(ConnectException e){
                    System.out.println(e.getMessage());
                    checker=false;
                }
            }
        }
    }
}
