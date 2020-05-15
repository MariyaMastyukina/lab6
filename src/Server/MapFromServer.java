package Server;

import Server.Commands.Command;

import java.io.Serializable;
import java.util.*;

/**
 * Класс содержащий список команд, полученного с сервера
 */
public class MapFromServer implements Serializable {
    /**
     * Список существующих команд
     */
    private List<String> commands;
    public MapFromServer(List<String> commands){
        this.commands=commands;
    }

    /**
     * Функция получения списка команд
     * @return список команд
     */
    public List<String> getMap(){
        return commands;
    }
}
