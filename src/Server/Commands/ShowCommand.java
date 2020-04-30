package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.IOInterface;
import java.io.IOException;
import java.util.List;

/**
 * Класс команды show-вывод элементов коллекции
 */
public class ShowCommand implements Command {
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public ShowCommand(ControlUnit p, CollectWorker collection) {
        p.addCommand("show", this);
        this.coll = collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param filename- имя файла с командами
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, String filename, List<String> args, IOInterface io) throws IOException {
            if (coll.getSizeColl()==0){
                io.writeln("Команда show не выполнена. Коллекция пустая");}
                        else{
                            coll.sortByName();
                io.writeln("Команда show выполнена. Список элементов коллекции:\n"+coll.getAllElement());
            }
    }
}
