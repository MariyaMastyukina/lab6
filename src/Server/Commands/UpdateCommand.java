package Server.Commands;

import Server.Collection.*;
import Server.*;
import Server.IOInterfaceChannel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды update-обновление элемента по id
 */
public class UpdateCommand implements Command {
    CollectWorker coll;
static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param p-          переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public UpdateCommand(ControlUnit p, CollectWorker collection) {
        p.addCommand("update", this);
        this.coll = collection;
    }

    /**
     * Функция выполнения команды
     *
     * @param option-   параметр команды
     * @param args      -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        try {
            Integer index = coll.getElementById(Integer.parseInt(option));
            coll.removeElement(index);
                coll.addElementWithId(new City(args),index);
                io.writeln("Команда update выполнена. Значение элемента коллекции с id " + Integer.parseInt(option) + " обновлено, введите команду \"show\", чтобы увидеть содержимое коллекции");
        }
        catch (IndexOutOfBoundsException e){
            io.writeln("Элемента с таким id нет. Введите команду \"show\", чтобы увидеть элементы коллекции и их id.");
        }
    }
}