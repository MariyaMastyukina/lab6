
import java.util.Scanner;
/**
 * Класс команды remove_by_id-Удаление элементов по id
 */

public class RemoveByIdCommand implements Command{
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public RemoveByIdCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("remove_by_id",this);
        this.coll=collection;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param scanner- поток
     * @param type -тип потока
     */
    @Override
    public void execute(String option,Scanner scanner,String type) throws NullPointerException{
        System.out.println("Выполняется поиск элемета с id" + Integer.parseInt(option));
        try {
            coll.removeElement(coll.getElementById(Integer.parseInt(option)));
            System.out.println("Элемент из коллекции с id " + Integer.parseInt(option) + " удален");
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Элемента с таким id нет. Добавьте элементы в коллекцию с помощью команды \"add\"");
        }
    }
}
