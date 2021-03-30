package Server.Commands;

import Server.IOServer.IOInterfaceChannel;
import Server.Launch.ControlUnit;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды history-вывод последних 8 команд
 */
public class HistoryCommand implements Command {
    static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     */
    private ControlUnit pusk;
    public HistoryCommand(ControlUnit p){
        p.addCommand("history",this);
        this.pusk=p;
        LOGGER=Logger.getLogger(HistoryCommand.class.getName());
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        LOGGER.log(Level.INFO,"Отправка результата выполнения команды на сервер");
        io.writeln(pusk.getListCommand());
    }
}
