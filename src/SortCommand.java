

import java.util.Scanner;
/**
 * Класс команды sort-сортировка коллекции по возрастанию поля population
 */
public class SortCommand implements Command{
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public SortCommand(ControlUnit p, CollectWorker collection){
            p.addCommand("sort",this);
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
        if(coll.getSizeColl()!=0){
            System.out.println("Начинается сортировка коллекции по возрастанию поля-население города");
            coll.sortRise();
            System.out.println("Коллекция отсортирована. Чтобы увидеть отсортированную коллекцию, введите команду \"print_ascending\"");
        }
        else{
            System.out.println("Коллекция пуста, сортировка невозможна");
        }
    }

}
