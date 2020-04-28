package Server.Commands;

import Server.IOInterface;

import java.io.IOException;
import java.util.List;
/**
 * {@code Server.Server.Commands.Command} содержит команду выполнения команд
 */
public interface Command {
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param filename- имя файла с командами
     * @param args -список аргументов
     */
    void execute(String option, String filename, List<String> args, IOInterface io) throws IOException;
}
