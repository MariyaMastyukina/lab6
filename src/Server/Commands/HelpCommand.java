package Server.Commands;

import Server.Commands.Command;

import java.io.IOException;
import java.util.List;
import Server.Collection.*;
import Server.*;
import Server.IOInterfaceChannel;

/**
 * Класс команды help-список команд
 */
public class HelpCommand implements Command {
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     */
    public HelpCommand(ControlUnit p){
        p.addCommand("help",this);
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        io.writeln("help:вывести справку по доступным командам\n" +
                "info:вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show:вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add:добавить новый элемент в коллекцию\n" +
                "update id :обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id:удалить элемент из коллекции по его id\n" +
                "clear:очистить коллекцию\n" +
                "save:сохранить коллекцию в файл\n" +
                "execute_script file_name:считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit:завершить программу (без сохранения в файл)\n" +
                "remove_last:удалить последний элемент из коллекции\n" +
                "sort:отсортировать коллекцию в естественном порядке\n" +
                "history:вывести последние 8 команд (без их аргументов)\n" +
                "remove_all_by_meters_above_sea_level metersAboutSeaLevel:удалить из коллекции все элементы, значение поля metersAboveSeaLevel которого эквивалентно заданному\n" +
                "group_counting_by_population:сгруппировать элементы коллекции по значению поля population, вывести количество элементов в каждой группе\n" +
                "print_ascending:вывести элементы коллекции в порядке возрастания");
    }

}
