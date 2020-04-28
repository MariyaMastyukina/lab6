package Server.Commands;

import Server.Collection.*;
import Server.IOInterface;
import java.io.IOException;
import java.util.List;

/**
 * Класс команды print_ascending-вывод отсортированной коллекции
 */
public class PrintAscendingCommand implements Command {
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public PrintAscendingCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("print_ascending",this);
        this.coll=collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param filename- имя файла с командами
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, String filename, List<String> args, IOInterface io) throws IOException {
        String listElement=null;
        if (coll.getSizeColl()!=0){
            coll.sortRise();
            io.writeln("Команда print_ascending выполнена. Коллекция, отсортированная по возрастанию поля-население города:\n"+ coll.getAllElement());
        }
        else{
            io.writeln("Команда print_ascending не выполнена. Коллекция пуста, сортировка невозможна");
        }
    }
}
