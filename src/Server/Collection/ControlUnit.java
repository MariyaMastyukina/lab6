package Server.Collection;

import Server.Commands.Command;
import Server.IOInterface;

import java.io.IOException;
import java.util.*;

/**
 * Класс для хранения и запуска команд
 */
public class ControlUnit {
    /** Коллекция Map для хранения комманд*/
    private Map<String,Command>Commands=new HashMap<>();;
    /** Список последних восьми команд*/
    private List<String> lastCommands=new ArrayList<>();
    private List<String> commandsList=new ArrayList<>();
    private int numberCommand=0;
    /**
     * Функция добавления комманды в Map
     * @param key- ключ комманды
     * @param command- комманда
     */

    public void addCommand(String key, Command command){
        Commands.put(key,command);
        commandsList.add(key);
    }

    public List<String> getCommandsList() {return commandsList;}
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param filename- имя файла с командами
     * @param args -список аргументов
     */

    public void executeCommand(String key, String option, List<String> args, String filename, IOInterface io) throws IOException {
        try {
            lastCommands.remove(numberCommand);
            Commands.get(key).execute(option, filename, args,io);
            lastCommands.add(numberCommand, key);
            numberCommand++;
            if (numberCommand == 8) {
                numberCommand = 0;
            }

        } catch (IndexOutOfBoundsException e) {
            Commands.get(key).execute(option, filename, args,io);
            lastCommands.add(numberCommand, key);
            numberCommand++;
            if (numberCommand == 8) {
                numberCommand = 0;
            }
        }
    }

    /**
     * Функция вывода последних восьми комманд
     */
    public String getListCommand() {
    StringBuilder stringBuilder=new StringBuilder();
    if (lastCommands.size() != 8) {
            return"Команда history не выполнена. Вы использовали меньше 8 команд";
        }
        else {
            lastCommands.forEach(commands->stringBuilder.append(commands+"\n"));
            }
            return "Команда history выполнена.\n"+"Вывод последних восьми использованных команд:\n"+stringBuilder.toString();
        }
    }