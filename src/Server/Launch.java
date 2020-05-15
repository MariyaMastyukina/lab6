package Server;

import Server.Collection.CollectWorker;
import Server.Collection.ControlUnit;
import Server.Commands.*;
import Server.Server;
import Client.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Server.Collection.*;
import Server.*;
/**
 * Класс, который обрабатывает ввод команд с консоли
 */
public class Launch {
    private ControlUnit pusk = new ControlUnit();
    private CollectWorker collection = new CollectWorker();
    /**
     * Конструктор создания команд
     */
    public Launch() {
        Command exit = new ExitCommand(pusk, collection);
        Command group_counting_by_population = new GroupCountingByPopulationCommand(collection, pusk);
        Command help = new HelpCommand(pusk);
        Command sort = new SortCommand(pusk, collection);
        Command remove_by_id = new RemoveByIdCommand(pusk, collection);
        Command remove_all_by_meters_above_sea_level = new RemoveAllBYMetersAboveSeaLevelCommand(collection, pusk);
        Command clear = new ClearCommand(pusk, collection);
        Command info = new InfoCommand(pusk, collection);
        Command show = new ShowCommand(pusk, collection);
        Command history = new HistoryCommand(pusk);
        Command remove_last = new RemoveLastCommand(pusk, collection);
        Command print_ascending = new PrintAscendingCommand(pusk, collection);
        Command add = new AddCommand(pusk, collection);
        Command update = new UpdateCommand(pusk, collection);
        Command save=new SaveCommand(collection);
    }

    /**
     * Функция получения списка существующих команд
     * @return список существующих команд
     */
    public List<String> getCommands() {
        return pusk.getCommandsList();
    }
    /**
     * Функция, запускающая обработку вводимых с консоли программ
     */
    public void beginProgramm(CommandObject command, IOInterfaceChannel io) throws IOException {
        pusk.executeCommand(command.getNameCommand(), command.getOption(), command.getArgs(), io);
    }
}


