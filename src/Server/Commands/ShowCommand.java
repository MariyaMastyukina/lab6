package Server.Commands;

import Server.IOServer.IOInterfaceChannel;
import Server.Data.CityObjects;
import Server.Launch.*;


import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс команды show-вывод элементов коллекции
 */
public class ShowCommand implements Command {
    private CityObjects send=new CityObjects();
    CollectWorker coll;
    static Logger LOGGER;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param p- переменная для управления командами
     * @param collection- переменнаяи для работы с коллекцией
     */
    public ShowCommand(ControlUnit p, CollectWorker collection) {
        p.addCommand("show", this);
        this.coll = collection;
        LOGGER=Logger.getLogger(ShowCommand.class.getName());
    }
    /**
     * Функция выполнения команды
     * @param option- параметр команды
     * @param args -список аргументов
     */
    @Override
    public void execute(String option, List<String> args, IOInterfaceChannel io) throws IOException {
        LOGGER.log(Level.INFO,"Отправка результата выполнения команды на сервер");
        if (coll.getSizeColl()==0){
            send.setMessage("Команда show не выполнена. Коллекция пустая");
            send.setObject(null);
            io.writeObj(send);}
        else{
            coll.sortByName();
            send.setMessage("Команда show выполнена. Список элементов коллекции:\n");
            coll.sortByName().forEach(city -> {
                try {
                    send.setObject(city);
                    io.writeObj(send);
                    send.setMessage("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
