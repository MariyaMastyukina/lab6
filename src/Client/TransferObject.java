package Client;

import Client.CommandObject;
import com.sun.tools.internal.ws.wsdl.document.PortType;

import java.io.IOException;
import java.io.Serializable;
import java.net.ConnectException;

public class TransferObject implements Serializable {
    private IOInterface ioServer;
    private String PORT;
    private String host;
    public TransferObject(IOInterface ioServer) {
        this.ioServer=ioServer;

    }
    public void transfer(CommandObject currentCommand, IOInterface ioClient) throws IOException {
        ioServer.writeObj(currentCommand);
        long timeStart =System.currentTimeMillis();
//        while (!ioServer.ready()){
////            long timeEnd=System.currentTimeMillis();
////            if (timeEnd-timeStart>10000){
////                ioClient.writeln("Сервер недоступен. Введите через пробел имя хоста и порт заново");
////                String line=ioClient.readLine();
////                String [] words=line.split(" ",2);
////                ServerConnection serverConnection=new ServerConnection();
////                serverConnection.connection(words[0],words[1]);
////            }
//        }
        while(!ioServer.ready()){

        }
        while (ioServer.ready()){
            ioClient.writeln(ioServer.readLine());
        }
    }
    }
