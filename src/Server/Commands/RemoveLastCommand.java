package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.*;
import java.io.IOException;
import java.util.List;

/**
 * Класс команды remove_last-Удаление последнего элемента
 */
public class RemoveLastCommand implements Command {
    private CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public RemoveLastCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("remove_last",this);
        this.coll=collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        if (coll.getSizeColl()!=0){
        coll.removeLastElement();
        io.writeln("Команда remove_last выполнена. Последний элемент коллекции удален");
    }
        else {
            io.writeln("Команда remove_last не выполнена. Коллекция пуста, удаление невозможно");
        }
        }
}
