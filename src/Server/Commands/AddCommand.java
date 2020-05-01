package Server.Commands;

import Server.Collection.*;
import Server.*;
import Server.IOInterfaceChannel;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды add-добавление объекта в коллекцию
 */
public class AddCommand implements Command {
    CollectWorker coll;
    static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param p-          переменная для управления командами
     * @param collection- переменная для работы с коллекцией
     */
    public AddCommand(ControlUnit p, CollectWorker collection) {
        p.addCommand("add", this);
        this.coll = collection;
        LOGGER=Logger.getLogger(AddCommand.class.getName());
    }

    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
            coll.addElement(new City(args));
        LOGGER.log(Level.INFO,"Отправка результата выполнения команды на сервер");
        io.writeln("Команда add выполнена. Элемент добавлен в коллекцию, введите команду \"show\", чтобы увидеть содержимое коллекции");

    }
}
