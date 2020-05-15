package Server.Commands;

import Server.Collection.CityObjects;
import Server.Collection.*;
import Server.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды print_ascending-вывод отсортированной коллекции
 */
public class PrintAscendingCommand implements Command {
    private CityObjects send=new CityObjects();

    CollectWorker coll;
    static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public PrintAscendingCommand(ControlUnit p, CollectWorker collection){
        p.addCommand("print_ascending",this);
        this.coll=collection;
        LOGGER=Logger.getLogger(PrintAscendingCommand.class.getName());
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        LOGGER.log(Level.INFO,"Отправка результата выполнения команды на сервер");
        if (coll.getSizeColl()!=0){
            send.setMessage("Команда print_ascending выполнена. Коллекция, отсортированная по возрастанию поля-население города:\n");
            coll.sortRise().forEach(city -> {
                try {
                    send.setObject(city);
                    io.writeObj(send);
                    send.setMessage("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
        else{
            send.setMessage("Команда print_ascending не выполнена. Коллекция пуста, сортировка невозможна");
            send.setObject(null);
            io.writeObj(send);
        }
    }
}
