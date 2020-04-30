package Server.Commands;

import Server.Commands.Command;
import Server.Collection.*;
import Server.*;
import java.io.IOException;
import java.util.List;

/**
 * Класс команды sort-сортировка коллекции по возрастанию поля population
 */
public class SortCommand implements Command {
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public SortCommand(ControlUnit p, CollectWorker collection){
            p.addCommand("sort",this);
            this.coll=collection;
        }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        if(coll.getSizeColl()!=0){
            coll.sortRise();
            io.writeln("Команда sort выполнена. Коллекция отсортирована. Чтобы увидеть отсортированную коллекцию, введите команду \"print_ascending\"");
        }
        else{
            io.writeln("Команда sort не выполнена. Коллекция пуста, сортировка невозможна");
        }
    }

}
