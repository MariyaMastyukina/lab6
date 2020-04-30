package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.*;
import java.io.IOException;
import java.util.List;

/**
 * Класс команды remove_by_id-Удаление элементов по id
 */

public class RemoveByIdCommand implements Command {
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public RemoveByIdCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("remove_by_id",this);
        this.coll=collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws NullPointerException, IOException {
        try {
            coll.removeElement(Integer.parseInt(coll.getElementById(Integer.parseInt(option))));
            io.writeln("Команда remove_by_id выполнена. Элемент из коллекции с id " + Integer.parseInt(option) + " удален");
        }
        catch(IndexOutOfBoundsException | IOException e){
            io.writeln("Команда remove_by_id не выполнена. Элемента с таким id нет. Посмотреть элементы и их id можно, введя команду \"show\"");
        }
        catch (NumberFormatException e){
            io.writeln("Элемента с таким id нет. Введите команду \"show\", чтобы увидеть элементы коллекции и их id.");
        }
    }
}
