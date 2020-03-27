

import java.util.Scanner;
/**
 * Класс команды history-вывод последних 8 команд
 */
public class HistoryCommand implements Command {
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     */
    private ControlUnit pusk;
    public HistoryCommand(ControlUnit p){
        p.addCommand("history",this);
        this.pusk=p;
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param scanner- поток
     * @param type -тип потока
     */
    @Override
    public void execute(String option, Scanner scanner, String type) {
        System.out.println("Выполняется команда вывода последних восьми использованных команд");
        pusk.getListCommand();
    }
}
