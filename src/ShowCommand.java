

import java.util.Scanner;
/**
 * Класс команды show-вывод элементов коллекции
 */
public class ShowCommand implements Command {
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public ShowCommand(ControlUnit p, CollectWorker collection) {
        p.addCommand("show", this);
        this.coll = collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param scanner- поток
     * @param type -тип потока
     */
    @Override
    public void execute(String option, Scanner scanner,String type) {
            if (coll.getSizeColl()==0){
                System.out.println("Коллекция пустая");}
                        else{
                System.out.println("Список элементов коллекции:");
                coll.getAllElement();
            }
    }
}
