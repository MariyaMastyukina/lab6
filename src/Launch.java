

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * Класс, который обрабатывает ввод команд с консоли
 */
public class Launch {
    private File file;
    public Launch(File file){
        this.file=file;
    }
    /**
     * Функция, запускающая обработку вводимых с консоли программ
     */
public void beginProgramm() throws IOException{
    System.out.println("Здравстуйте! Введите \"help\", чтобы увидеть список доступных команд");
    Scanner terminalScanner=new Scanner(System.in);
    ControlUnit pusk = new ControlUnit();
    CollectWorker collection=new CollectWorker(file);
    collection.fromFileToColl();
    Command execute_script=new ExecuteScriptCommand(pusk,collection);
    Command save=new SaveCommand(pusk,collection);
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
        terminalScanner.locale();
    System.out.println("Введите команду");
    String line=terminalScanner.nextLine();
        while(!line.equals("exit")) {
                if (!line.trim().equals("")) {
                    try {
                        if (line.contains(" ")) {
                            String[] lineParts = line.split(" ", 2);
                            pusk.executeCommand(lineParts[0], lineParts[1],terminalScanner,"console");
                        } else {
                            pusk.executeCommand(line, null,terminalScanner,"console");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Такой команды не существует или она записана некорректно. Введите \"help\", чтобы получить список действующих команд");
                    } catch (NumberFormatException e) {
                        System.out.println("Выполнение команды невозможно. Введите корректный id, metersAboveSeaLevel или корректное имя файла");
                    }
                }
                line = terminalScanner.nextLine();
            }
        }
}

