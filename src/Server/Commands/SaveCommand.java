package Server.Commands;

import Server.Launch.*;

import Server.IOServer.IOInterfaceChannel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaveCommand implements Command{
    private CollectWorker collection;
    public SaveCommand(CollectWorker coll){
        collection=coll;
    }

    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        System.out.println(collection.saveCollection(new File(option)));
    }
}
