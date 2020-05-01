package Server.Commands;

import Server.Commands.Command;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Server.Collection.*;
import Server.*;
import Server.IOInterfaceChannel;

/**
 * Класс команды info-вывод информации о коллекции
 */
public class InfoCommand implements Command {
    private CollectWorker coll;
    static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public InfoCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("info",this);
        this.coll=collection;
        LOGGER=Logger.getLogger(InfoCommand.class.getName());
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        LOGGER.log(Level.INFO,"Отправка результата выполнения команды на сервер");

        io.writeln("Информация о коллекции (тип, дата инициализации, количество элементов):\n"+coll.getTypeColl()+"\n"+coll.getDataColl()+"\n"+coll.getSizeColl());
    }
}
