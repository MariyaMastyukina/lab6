

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
     *
     * @param filename- параметр команды
     * @param scanner-  поток
     * @param type -тип потока
     */
    @Override
    public void execute(String filename, Scanner scanner,String type) throws FileNotFoundException {
        File file = new File (filename);
        FileReader fileread = new FileReader(file);
        if(!file.canRead()){
            file.setReadable(true);
        }
        if(!file.canWrite()){
            file.setWritable(true);
        }
        if(!file.canExecute()){
            file.setExecutable(true);
        }
        Scanner scanfile = new Scanner(fileread);
        filelist.add(filename);
        Boolean checker;
        String currentCommand;
        while (scanfile.hasNextLine()) {
            currentCommand = scanfile.nextLine();
            try {
                if (!currentCommand.equals("")) {
                    if (currentCommand.equals("exit")) break;
                    else {
                        if (currentCommand.contains(" ")) {
                            String[] lineParts = currentCommand.split(" ", 2);
                            if (lineParts[0].equals("execute_script")) {
                                checker = true;
                                for (String files : filelist) {
                                    if (files.equals(lineParts[1])) {
                                        checker = false;
                                        break;
                                    }
                                }
                                if (checker) {
                                    filelist.add(lineParts[1]);
                                    pusk.executeCommand(lineParts[0], lineParts[1], scanfile,"file");
                                } else {
                                    System.out.println("Зацикливание execute_script. Дальнейшее выполнение команды невозможно");
                                    break;
                                }
                            } else {
                                pusk.executeCommand(lineParts[0], lineParts[1], scanfile,"file");
                            }
                        } else {
                            pusk.executeCommand(currentCommand, null, scanfile,"file");
                        }
                    }
                } else {
                    System.out.println("Пустая строка");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Данные для элемента коллекции введены неверно. Выполнение команды невозможно");
                break;
            } catch (NullPointerException e) {
                System.out.println("Такой команды не существует или она записана некорректно. Введите \"help\", чтобы получить список действующих команд");
            } catch (NumberFormatException e) {
                System.out.println("Данные для элемента коллекции введены неверно. Выполнение команды невозможно");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Элемента с таким id нет. Добавьте элементы в коллекцию с помощью команды \"add\"");
            }
        }
    }
}

