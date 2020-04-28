package Server.Commands;

import Server.Commands.Command;

import java.io.IOException;
import java.util.List;
import Server.Collection.*;
import Server.IOInterface;
/**
 * Класс команды info-вывод информации о коллекции
 */
public class InfoCommand implements Command {
    private CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public InfoCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("info",this);
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
        io.writeln("Информация о коллекции (тип, дата инициализации, количество элементов):\n"+coll.getTypeColl()+coll.getDataColl()+coll.getSizeColl());
    }
}
