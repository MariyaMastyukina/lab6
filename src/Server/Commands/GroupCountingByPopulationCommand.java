package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды group_counting_by_population-группировка коллекции
 */
public class GroupCountingByPopulationCommand implements Command {
    CollectWorker coll;
    static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public GroupCountingByPopulationCommand(CollectWorker collection, ControlUnit p){
        p.addCommand("group_counting_by_population",this);
        this.coll=collection;
        LOGGER=Logger.getLogger(GroupCountingByPopulationCommand.class.getName());
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        LOGGER.log(Level.INFO,"Отправка результата выполнения команды на сервер");
        if (coll.getSizeColl()!=0) {
            io.writeln("Команда group_counting_by_population выполнена. Количество элементов полученных групп элементов коллекции:\n"+coll.grouping());
        }
        else{
            io.writeln("Команда group_counting_by_population не выполнена. Коллекция пуста, групировка элементов невозможна");
        }
    }
}
