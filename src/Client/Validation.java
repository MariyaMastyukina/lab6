package Client;

import Server.Commands.Command;
import Server.MapFromServer;

import java.util.List;
import java.util.Map;

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
        if (option!=null && !command.equals("execute_script")){
            try{
                Long.parseLong(option);
            }
            catch(NumberFormatException e){
                System.out.println("Выполнение команды невозможно. Введите корректный id, metersAboveSeaLevel");
                checker=false;
            }
        }
        return checker;
    }
}
