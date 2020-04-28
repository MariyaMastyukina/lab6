package Server.Commands;

import Server.Collection.*;
import Server.IOInterface;
import java.io.IOException;
import java.util.List;

/**
 * Класс команды history-вывод последних 8 команд
 */
public class HistoryCommand implements Command {
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     */
    private ControlUnit pusk;
    public HistoryCommand(ControlUnit p){
        p.addCommand("history",this);
        this.pusk=p;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param filename- имя файла с командами
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, String filename, List<String> args, IOInterface io) throws IOException {
        io.writeln(pusk.getListCommand());
    }
}
