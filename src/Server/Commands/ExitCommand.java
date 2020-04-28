package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.IOInterface;
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
     * @param filename- имя файла с командами
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, String filename, List<String> args, IOInterface io) throws IOException {
        io.writeln( coll.saveCollection(new File("Collection.json")));
        io.writeln("Команда exit выполняется. Завершение работы программы");
        System.exit(0);
    }
}
