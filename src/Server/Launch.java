package Server;

import Server.Collection.CollectWorker;
import Server.Collection.ControlUnit;
import Server.Commands.*;
import Server.Server;
import Client.*;

import java.io.IOException;
import java.util.*;
import Server.Collection.*;
import Server.IOInterface;
/**
 * Класс, который обрабатывает ввод команд с консоли
 */
public class Launch {
    private ControlUnit pusk = new ControlUnit();
    private CollectWorker collection=new CollectWorker();
    public Launch(){
        Command execute_script=new ExecuteScriptCommand(pusk,collection);
        Command exit=new ExitCommand(pusk,collection);
        Command group_counting_by_population=new GroupCountingByPopulationCommand(collection,pusk);
        Command help=new HelpCommand(pusk);
        Command sort=new SortCommand(pusk,collection);
        Command remove_by_id=new RemoveByIdCommand(pusk,collection);
        Command remove_all_by_meters_above_sea_level=new RemoveAllBYMetersAboveSeaLevelCommand(collection,pusk);
        Command clear=new ClearCommand(pusk,collection);
        Command info=new InfoCommand(pusk,collection);
        Command show=new ShowCommand(pusk,collection);
        Command history=new HistoryCommand(pusk);
        Command remove_last=new RemoveLastCommand(pusk,collection);
        Command print_ascending=new PrintAscendingCommand(pusk,collection);
        Command add=new AddCommand(pusk,collection);
        Command update=new UpdateCommand(pusk,collection);
    }
    /**
     * Функция, запускающая обработку вводимых с консоли программ
     */
    public List<String> getCommands(){
        return pusk.getCommandsList();
    }
public void beginProgramm(CommandObject command, IOInterface io) throws IOException{
    pusk.executeCommand(command.getNameCommand(),command.getOption(),command.getArgs(),null,io);


}
}
//    System.out.println("Введите команду");
//    String line=terminalScanner.nextLine();
//        while(!line.equals("exit")) {
//                if (!line.trim().equals("")) {
//                    try {
//                        if (line.contains(" ")) {
//                            String[] lineParts = line.split(" ", 2);
//                            pusk.executeCommand(lineParts[0], lineParts[1],terminalScanner,"console");
//                        } else {
//                            pusk.executeCommand(line, null,terminalScanner,"console");
//                        }
//                    } catch (NullPointerException e) {
//                        System.out.println("Такой команды не существует или она записана некорректно. Введите \"help\", чтобы получить список действующих команд");
//                    } catch (NumberFormatException e) {
//                        System.out.println("Выполнение команды невозможно. Введите корректный id, metersAboveSeaLevel или корректное имя файла");
//                    }
//                }
//                line = terminalScanner.nextLine();
//            }
//        }
//}

