package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.*;
import Server.IOInterfaceChannel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExitCommand implements Command {
    CollectWorker coll;
    static Logger LOGGER;
    public ExitCommand(ControlUnit p, CollectWorker collection) {
        this.coll=collection;
        p.addCommand("exit",this);
        LOGGER=Logger.getLogger(ClearCommand.class.getName());
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        LOGGER.log(Level.INFO,"Отправка результата выполнения команды на сервер");
        io.writeln( coll.saveCollection(new File("Collection.json")));
        io.writeln("Команда exit выполняется. Завершение работы программы");
        io.writeln("exit");
        io.close();
        LOGGER.log(Level.INFO,"Завершение работы приложения");
        System.exit(0);
    }
}
