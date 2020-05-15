package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды remove_by_id-Удаление элементов по id
 */

public class RemoveByIdCommand implements Command {
    CollectWorker coll;
    static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public RemoveByIdCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("remove_by_id",this);
        this.coll=collection;
        LOGGER=Logger.getLogger(RemoveByIdCommand.class.getName());
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws NullPointerException, IOException {
        try {
            if (coll.getSizeColl()!=0) {
                LOGGER.log(Level.INFO, "Отправка результата выполнения команды на сервер");
                coll.removeElement(coll.getElementById(Integer.parseInt(option)));
                io.writeln("Команда remove_by_id выполнена. Элемент из коллекции с id " + Integer.parseInt(option) + " удален");
            }
            else{
                io.writeln("Команда remove_by_id не выполнена. Коллекция пуста");
            }
            }
        catch (IndexOutOfBoundsException e){
            io.writeln("Элемента с таким id нет. Введите команду \"show\", чтобы увидеть элементы коллекции и их id.");
        }
    }
}
