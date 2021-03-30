package Server.Commands;

import Server.IOServer.IOInterfaceChannel;

import java.io.IOException;
import java.util.List;
/**
 * {@code Server.Server.Commands.Command} содержит команду выполнения команд
 */
public interface Command {
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException;
}
