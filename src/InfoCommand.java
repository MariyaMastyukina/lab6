

import java.util.Scanner;
/**
 * Класс команды info-вывод информации о коллекции
 */
public class InfoCommand implements Command{
    private CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public InfoCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("info",this);
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
        System.out.println("Информация о коллекции (тип, дата инициализации, количество элементов)");
        System.out.println(coll.getTypeColl());
        System.out.println(coll.getDataColl());
        System.out.println(coll.getSizeColl());
    }
}
