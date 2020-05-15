package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды remove_all_by_meters_above_sea_level-удаление элементов коллекции с данным полем metersAboveSeaLevel
 */
public class RemoveAllBYMetersAboveSeaLevelCommand implements Command {
    CollectWorker coll;
    static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public RemoveAllBYMetersAboveSeaLevelCommand(CollectWorker collection, ControlUnit p){
        p.addCommand("remove_all_by_meters_above_sea_level",this);
        this.coll=collection;
        LOGGER=Logger.getLogger(RemoveAllBYMetersAboveSeaLevelCommand.class.getName());
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        LOGGER.log(Level.INFO,"Отправка результата выполнения команды на сервер");
        io.writeln(coll.delete(Integer.parseInt(option)));
    }
}
