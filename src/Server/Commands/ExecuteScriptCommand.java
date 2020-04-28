package Server.Commands;

import Server.Commands.Command;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import Server.Collection.*;
import Server.IOInterface;
/**
 * Класс execute_script-выполнение команд из скрипта
 */
public class ExecuteScriptCommand implements Command {
    CollectWorker coll;
    ControlUnit pusk;
    /**
     * Список файлов, в которых хранятся файлы с командами
     */
    ArrayList<String> filelist = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта с определенными значениями
     *
     * @param p-          переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public ExecuteScriptCommand(ControlUnit p, CollectWorker collection) {
        p.addCommand("execute_script", this);
        this.coll = collection;
        this.pusk = p;
    }

    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param filename- имя файла с командами
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, String filename, List<String> args, IOInterface io) throws IOException {
        File file = new File (option);
        if(file.canRead()){
            FileReader fileread = new FileReader(file);
        Scanner scanfile = new Scanner(fileread);
        String currentCommand;
        while (scanfile.hasNextLine()) {
            currentCommand = scanfile.nextLine();
            try {
                if (!currentCommand.equals("")) {
                        if (currentCommand.contains(" ")) {
                            String[] lineParts = currentCommand.split(" ", 2);
                            pusk.executeCommand(lineParts[0], lineParts[1], null, option,io);
                        } else {
                            pusk.executeCommand(currentCommand, null, null, option,io);
                        }
                    }
                }
            catch (IOException e) {
                e.printStackTrace();
            }

            catch (NoSuchElementException e) {
                io.writeln("Данные для элемента коллекции введены неверно. Выполнение команды невозможно");
                break;
            } catch (NullPointerException e) {
                io.writeln("Такой команды не существует или она записана некорректно. Введите \"help\", чтобы получить список действующих команд");
            } catch (NumberFormatException e) {
                io.writeln("Данные для элемента коллекции введены неверно. Выполнение команды невозможно");
            }  catch (IndexOutOfBoundsException e) {
                io.writeln("Элемента с таким id нет. Добавьте элементы в коллекцию с помощью команды \"add\"");
            }
            catch (StackOverflowError e){
                io.writeln("Произошло зацикливание, выполнение команды дальше невозможно");
                break;
            }
        }
        io.writeln("Команда execute_script выполнена. Команды из файле "+option+" выполнены");
        }
        else {io.writeln("Чтение из файловов невозможно. Измените права.");}
    }
}

