package Server;

import Server.Commands.Command;

import java.io.Serializable;
import java.util.*;

public class MapFromServer implements Serializable {
    private List<String> commands;
    public MapFromServer(List<String> commands){
        this.commands=commands;
    }
    public List<String> getMap(){
        return commands;
    }
}
