

import java.util.Scanner;
/**
 * Класс команды remove_last-Удаление последнего элемента
 */
public class RemoveLastCommand implements Command{
    private CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public RemoveLastCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("remove_last",this);
        this.coll=collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param scanner- поток
     * @param type -тип потока
     */
    @Override
    public void execute(String option, Scanner scanner,String type) {
        System.out.println("Выполняется удаление последнего элемента коллекции");
        if (coll.getSizeColl()!=0){
        coll.removeLastElement();
        System.out.println("Последний элемент коллекции удален");
    }
        else {
            System.out.println("Коллекция пуста, удаление невозможно");
        }
        }
}
