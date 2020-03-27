

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Класс для хранения и запуска команд
 */
public class ControlUnit {
    /** Коллекция Map для хранения комманд*/
    private Map<String,Command>Commands=new HashMap<>();
    /** Список последних восьми команд*/
    private ArrayList<String> lastCommands=new ArrayList<>();
    private int numberCommand=0;
    /**
     * Функция добавления комманды в Map
     * @param key- ключ комманды
     * @param command- комманда
     */
    public void addCommand(String key,Command command){
        Commands.put(key,command);
    }
    /**
     * Функция исполения команды
     * @param key- ключ комманды
     * @param option- параметр комманды
     * @param scanner- поток
     */
    public void executeCommand(String key, String option,Scanner scanner,String type) throws IOException {
        try{
            Commands.get(key).execute(option,scanner,type);
            lastCommands.remove(numberCommand);
        lastCommands.add(numberCommand,key);
        numberCommand++;
        if (numberCommand == 8) {
            numberCommand = 0;
        }
        }
        catch(IndexOutOfBoundsException e){
            lastCommands.add(numberCommand,key);
            numberCommand++;
            if (numberCommand == 8) {
                numberCommand = 0;
            }
        }
    }

    /**
     * Функция вывода последних восьми комманд
     */
    public void getListCommand() {
        System.out.println("Вывод последних восьми использованных команд:");
        if (lastCommands.size() != 8) {
            System.out.println("Вы использовали меньше 8 команд");
        }
        else {
            for (String commands : lastCommands) {
                System.out.println(commands);
            }
        }
    }
}
