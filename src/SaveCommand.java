

import java.io.IOException;
import java.util.Scanner;
/**
 * Класс команды save-сохранение коллекции в файл
 */
public class SaveCommand implements Command{
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public SaveCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("save",this);
        this.coll=collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param scanner- поток
     * @param type -тип потока
     */
    @Override
    public void execute(String option, Scanner scanner,String type) throws IOException {
        System.out.println("Выполняется сохранение коллекции в файл");
        coll.saveCollection();
        System.out.println("Коллекция сохранена в файл");
    }
}
