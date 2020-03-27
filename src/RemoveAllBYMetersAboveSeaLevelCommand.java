

import java.util.Scanner;
/**
 * Класс команды remove_all_by_meters_above_sea_level-удаление элементов коллекции с данным полем metersAboveSeaLevel
 */
public class RemoveAllBYMetersAboveSeaLevelCommand implements Command{
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public RemoveAllBYMetersAboveSeaLevelCommand(CollectWorker collection,ControlUnit p){
        p.addCommand("remove_all_by_meters_above_sea_level",this);
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
        System.out.println("Выполняется удаление элементов из коллекции, значение поля metersAboveSeaLevel которых эквивалентно "+Integer.parseInt(option));
        int k=0;
        for (int i=0;i<coll.getSizeColl();i++) {
            if (coll.getElement(i).getMetersAboveSeaLevel() == Integer.parseInt(option)) {
                coll.removeElement(i);
                k++;
            } else {
            }
        }
           if(k>0) {
               System.out.println("Удалены из коллекции все элементы, значение поля metersAboveSeaLevel которых эквивалентно "+Integer.parseInt(option));
           }
           else{
               System.out.println("В коллекции нет элементов, значение поля metersAboveSeaLevel которых эквивалентно "+Integer.parseInt(option));
           }

    }
}
