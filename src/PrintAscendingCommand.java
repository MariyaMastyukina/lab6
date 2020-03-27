

import java.util.Scanner;
/**
 * Класс команды print_ascending-вывод отсортированной коллекции
 */
public class PrintAscendingCommand implements Command{
    CollectWorker coll;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public PrintAscendingCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("print_ascending",this);
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
        System.out.println("Коллекция, отсортированная по возрастанию поля-население города:");
        if (coll.getSizeColl()!=0){
            coll.sortRise();
            for (int i = 0; i < coll.getSizeColl(); i++) {
                System.out.println(coll.getElementToString(i));
            }
        }
        else{
            System.out.println("Коллекция пуста, сортировка невозможна");
        }
    }
}
