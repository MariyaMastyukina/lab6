
package Client.DataUtils;

import java.util.List;

import Client.Client;
import Server.Data.MapFromServer;

/**
 * Класс, проверяющий валидность вводимых команд
 */
public class Validation {
    List<String> list;
    String command;
    String option;

    /**
     * Конструктор класса, принимающий имя команды и ее аргумент
     * @param command
     * @param option
     */
    public Validation(String command,String option, MapFromServer commands){
        this.list=commands.getMap();
        list.add("execute_script");
        this.command=command;
        this.option=option;
    }
    /**
     * Функция проверки валидности вводимой команды
     */
    public Boolean isValidation(){
        boolean checker=false;
        for(String c:list){
            if (c.equals(command)) checker=true;
        }
        if (!checker){
            System.out.println("Такой команды не существует или она записана некорректно. Введите \"help\", чтобы получить список действующих команд");
            return checker;
        }
        if (command.equals("remove_by_id") || command.equals("update")){
            try{
                Long.parseLong(option);
            }
            catch(NumberFormatException e){
                System.out.println("Выполнение команды невозможно. Введен некорректный id, повторите ввод команды");
                checker=false;
            }
        }
        if (command.equals("remove_all_by_meters_above_sea_level")){
            try {
                Integer.parseInt(option);
            }
            catch(NumberFormatException e){
                System.out.println("Выполнение команды невозможно. Введен некорректный meters_above_sea_level, повторите ввод команды");
                checker=false;
            }
        }
        return checker;
    }
}
