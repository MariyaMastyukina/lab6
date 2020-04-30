package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.*;
import Server.IOInterfaceChannel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExitCommand implements Command {
    CollectWorker coll;
    public ExitCommand(ControlUnit p, CollectWorker collection) {
        this.coll=collection;
        p.addCommand("exit",this);
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        io.writeln( coll.saveCollection(new File("Collection.json")));
        io.writeln("Команда exit выполняется. Завершение работы программы");
        io.writeln("exit");
        io.close();
        System.exit(0);
    }
}
