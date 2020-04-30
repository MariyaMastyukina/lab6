package Server.Commands;

import java.io.IOException;
import java.util.List;
import Server.Collection.*;
import Server.*;
import Server.IOInterfaceChannel;

/**
 * Класс команды clear-очистка коллекции
 */
public class ClearCommand implements Command {
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public ClearCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("clear",this);
        this.coll=collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        if (coll.getSizeColl()==0){
            io.writeln("Команда clear не выполнена. Коллекция не содержит элементов, очищение невозможно");
        }
        else {
            coll.clearList();
            io.writeln("Команда clear выполнена. Коллекция очищена");
        }
    }
}
