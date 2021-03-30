package Server.Commands;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Server.IOServer.IOInterfaceChannel;
import Server.Launch.*;

/**
 * Класс команды clear-очистка коллекции
 */
public class ClearCommand implements Command {
    CollectWorker coll;
    static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public ClearCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("clear",this);
        this.coll=collection;
        LOGGER=Logger.getLogger(ClearCommand.class.getName());
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        LOGGER.log(Level.INFO,"Отправка результата выполнения команды на сервер");
        if (coll.getSizeColl()==0){
            io.writeln("Команда clear не выполнена. Коллекция не содержит элементов, очищение невозможно");
        }
        else {
            coll.clearList();
            io.writeln("Команда clear выполнена. Коллекция очищена");
        }
    }
}
