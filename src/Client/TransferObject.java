package Client;

import Client.CommandObject;

import java.io.IOException;
import java.io.Serializable;

public class TransferObject implements Serializable {
    private IOInterface ioServer;
    public TransferObject(IOInterface ioServer){
        this.ioServer=ioServer;
    }
    public void transfer(CommandObject currentCommand, IOInterface ioClient) throws IOException {
        System.out.println("byb");
        ioServer.writeObj(currentCommand);
        long timeStart =System.currentTimeMillis();
        while (!ioServer.ready()){
            long timeEnd=System.currentTimeMillis();
            if (timeEnd-timeStart>10000){
                throw new IOException();
            }
        }
        while (ioServer.ready()){
            ioClient.writeln(ioServer.readLine());
        }
    }
    }
