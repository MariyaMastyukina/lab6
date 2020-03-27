

import java.util.Scanner;
/**
 * Класс команды clear-очистка коллекции
 */
public class ClearCommand implements Command{
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public ClearCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("clear",this);
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
        System.out.println("Выполняется команда очищения коллекции");
        if (coll.getSizeColl()==0){
            System.out.println("Коллекция пуста, невозможно очистить");
        }
        else {
            coll.clearList();
            System.out.println("Коллекция очищена");
        }
    }
}
