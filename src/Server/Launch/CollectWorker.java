package Server.Launch;

import Server.Data.City;
import Server.Comparators.CityComparator;
import Server.Comparators.NameComparator;
import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
/**
 * Класс, работающий с коллекцией объектов класса Server.Server.Commands.AddCommand.City со свойствами date, collection, file, creationDate, area, population, metersAboveSeaLevel, capital, climate, government, governor.
 */
public class CollectWorker {
    /** Поле дата создания коллекции*/
    private static Date date;
    /** Статический блок инициализации*/
    static {
        date = new Date();
    }
    /** Связанная коллекция экземпляров класса Server.Server.Commands.AddCommand.City*/
    private static List<City> collection = new LinkedList<>();

    /**
     * Функция добавления элемента в коллекцию
     * @param city- объект класса Server.Server.Commands.AddCommand.City
     */
    public void addElement(City city) {
        collection.add(city);
    }//для add
    /**
     * Функция добавления элемента в коллекцию с данным id
     * @param city- объект класса Server.Server.Commands.AddCommand.City
     * @param index- id города
     */
    public void addElementWithId(City city, int index){
        collection.add(index,city);
    }//для update
    /**
     * Функция очистки коллекции
     */
    public void clearList() {
        collection.clear();
    }//для clear
    /**
     * Функция удаления последнего элемента коллекции
     */
    public void removeLastElement() {
        collection.remove(collection.size()-1);
    }//для remove_last
    /**
     * Функция удаления определенного элемента
     * @param index- индекс элемента
     */
    public void removeElement(int index) {
        collection.remove(index);
    }//для update, remove_all_by_meters_above_sea_level,remove_by_id
    /**
     * Функция получения определенного элемента коллекции
     * @param meters_above_sea_level- индекс элемента
     * @return объект класса Server.Server.Commands.AddCommand.City
     */
    public String delete(int meters_above_sea_level) {
        List <Long> IdDelete=collection.stream().filter(city->city.getMetersAboveSeaLevel()==meters_above_sea_level).map(City::getIdOfCity).collect(Collectors.toList());
        if (IdDelete.size()==0){
            return "Команда remove_all_by_meters_above_sea_level не выполнена. В коллекции нет элементов, значение поля metersAboveSeaLevel которых эквивалентно "+meters_above_sea_level;
        }
        else {
            try {
                IdDelete.forEach(id -> removeElement(getElementById(id)));
                return "Команда remove_all_by_meters_above_sea_level выполнена. Удалены из коллекции все элементы, значение поля metersAboveSeaLevel которых эквивалентно " + meters_above_sea_level;
            }
            catch (NumberFormatException e){
                return "Элемента с таким id нет. Введите команду \"show\", чтобы увидеть элементы коллекции и их id.";
            }        }
    }//for remove_all_by_meters_above_sea_level,remove_by_id
    /**
     * Функция группировки коллекции по полю population
     */
    public String grouping(){
        Map<Integer, Long> collectionPerPopulation = collection.stream().collect(groupingBy(city->city.getPopulation(),counting()));
        return collectionPerPopulation.toString();
    }//for group_counting_by_population
    /**
     * Функция заполнения коллекции данными из файла
     * @throws  IOException if <code>filereader</code> does not exist
     */
    public void fromFileToColl(File file) throws IOException {
        try {
            Gson gson = new Gson();
            FileReader filereader = new FileReader(file);
            Scanner scanFile = new Scanner(filereader);
            while (scanFile.hasNextLine()) {
                collection.add(gson.fromJson(scanFile.nextLine(), City.class));
            }
            filereader.close();
        }
        catch(IOException e){
            System.out.println("Файл не существует, заполнение коллекции невозможно, поэтому коллекция пуста");
        }

    }
    /**
     * Функция сохранения коллекции в файл
     */
    public String saveCollection(File file) throws IOException {
        Gson gson=new Gson();
        if(file.canWrite()) {
            FileOutputStream filewriter = new FileOutputStream(file);
            collection.forEach(city -> {
                try {
                    filewriter.write(gson.toJson(city).getBytes());
                    filewriter.write("\n".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } );
            filewriter.close();
            return"Коллекция сохранена в файл"+file.getName();
        }
        else{
            return"Запись файла невозможна. Поменяйте права доступа";
        }
    }//for save
    /**
     * Функция получения определенного элемента
     * @param id- id элемента
     * @return индекс элемента
     */
    public int getElementById(long id){
        City city=null;
        for (City c:collection){
            if (c.getIdOfCity()==id){
                city=c;
            }
        }
        return collection.indexOf(city);
    }//for remove_by_id,update
    /**
     * Функция получения всех элементов коллекции
     */
    public void setCollection(LinkedList<City> newColl){
        collection=newColl;
    }//show
    /**
     * Функция удаления определенного элемента
     * @return тип коллекции
     */
    public String getTypeColl() {
        return collection.getClass().getTypeName();
    }//for info
    /**
     * Функция определения размера коллекции
     * @return длину коллекции
     */
    public int getSizeColl() {
        return collection.size();
    }// for clear,group_counting_by_population,info,print_ascending,remove_all_by_meters_above_sea_level,remove_by_id,remove_last,show,sort
    /**
     * Функция определения даты создания коллекции
     * @return дату создания коллекции
     */
    public String getDataColl() {
        return date.toString();
    }//for info
    /**
     * Функция сортировки коллекции по полю population
     */
    public LinkedList<City> sortRise() {
//        Collections.sort(collection, new CityComparator());
        LinkedList<City> newList=collection.stream().sorted(new CityComparator()).collect(Collectors.toCollection(LinkedList::new));
        return newList;
    }//for print_ascending,sort
    /**
     * Функция сортировки коллекции по полю name
     */
    public LinkedList<City> sortByName(){
//        Collections.sort(collection,new NameComparator());
        LinkedList<City> newList=collection.stream().sorted(new NameComparator()).collect(Collectors.toCollection(LinkedList::new));
        return newList;
    }

}
