

import java.util.Scanner;
/**
 * Класс команды group_counting_by_population-группировка коллекции
 */
public class GroupCountingByPopulationCommand implements Command{
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public GroupCountingByPopulationCommand(CollectWorker collection,ControlUnit p){
        p.addCommand("group_counting_by_population",this);
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
        System.out.println("Выполняется команда группировки коллекции");
        if (coll.getSizeColl()!=0) {
            System.out.println("Количество элементов полученных групп элементов коллекции");
            coll.grouping();
        }
        else{
            System.out.println("Коллекция пуста, групировка элементов невозможна");
        }
    }
}
