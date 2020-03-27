
import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
/**
 * Класс, работающий с коллекцией объектов класса City со свойствами date, collection, file, creationDate, area, population, metersAboveSeaLevel, capital, climate, government, governor.
 */
public class CollectWorker{
    /** Поле дата создания коллекции*/
    private static Date date;
    /** Статический блок инициализации*/
    static {
        date = new Date();
    }
    /** Поле файл, содержащий элементы коллекции*/
    private File file;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param file- имя человека
     */
    public CollectWorker(File file) throws FileNotFoundException {
        this.file=file;
    }
    /** Связанная коллекция экземпляров класса City*/
    private static LinkedList<City> collection = new LinkedList<>();
    /**
     * Функция добавления элемента в коллекцию
     * @param city- объект класса City
     */
    public void addElement(City city) {
        collection.add(city);
    }
    /**
     * Функция добавления элемента в коллекцию с данным id
     * @param city- объект класса City
     * @param index- id города
     */
    public void addElementWithId(City city,int index){
        collection.add(index,city);
    }
    /**
     * Функция очистки коллекции
     */
    public void clearList() {
        collection.clear();
    }
    /**
     * Функция удаления последнего элемента коллекции
     */
    public void removeLastElement() {
        collection.removeLast();
    }
    /**
     * Функция удаления определенного элемента
     * @param index- индекс элемента
     */
    public void removeElement(int index) {
        collection.remove(index);
    }
    /**
     * Функция получения определенного элемента коллекции
     * @param index- индекс элемента
     * @return объект класса City
     */
    public City getElement(int index) {
        return collection.get(index);
    }
    /**
     * Функция группировки коллекции по полю population
     */
    public void grouping(){
        Map<Integer, Long> collectionPerPopulation = collection.stream().collect(groupingBy(City::getPopulation,counting()));
        System.out.println(collectionPerPopulation);
    }
    /**
     * Функция заполнения коллекции данными из файла
     * @throws  IOException if <code>filereader</code> does not exist
     */
    public void fromFileToColl() throws IOException {
        try{
            Gson gson=new Gson();
            if(!file.canRead()){
                file.setReadable(true);
            }
            FileReader filereader=new FileReader(file);
            Scanner scanFile=new Scanner(filereader) ;
            while(scanFile.hasNextLine()){
                collection.add(gson.fromJson(scanFile.nextLine(),City.class));
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
    public void saveCollection() throws IOException {
        Gson gson=new Gson();
        if(!file.canWrite()){
            file.setWritable(true);
        }
        FileOutputStream filewriter=new FileOutputStream(file);
        for (City city:collection) {
            filewriter.write(gson.toJson(city).getBytes());
            filewriter.write("\n".getBytes());
        }
        filewriter.close();
    }
    /**
     * Функция получения определенного элемента
     * @param id- id элемента
     * @return индекс элемента
     */
    public int getElementById(int id){
        City element = null;
        for (City city:collection){
            if (city.getIdOfCity()==id){
                element=city;
            }
        }
        return collection.indexOf(element);
    }
    /**
     * Функция получения всех элементов коллекции
     */
    public void getAllElement() throws NullPointerException {
        for (City city : collection) {
            System.out.println(city.toString());
        }
    }
    /**
     * Функция удаления определенного элемента
     * @return тип коллекции
     */
    public String getTypeColl() {
        return collection.getClass().getTypeName();
    }
    /**
     * Функция определения размера коллекции
     * @return длину коллекции
     */
    public int getSizeColl() {
        return collection.size();
    }
    /**
     * Функция определения даты создания коллекции
     * @return дату создания коллекции
     */
    public String getDataColl() {
        return date.toString();
    }
    /**
     * Функция сортировки коллекции по полю population
     */
    public void sortRise() {
        Collections.sort(collection, new CityComparator());
    }
    /**
     * Функция строчного представления элемента коллекции
     */
    public String getElementToString(int id){
        return collection.get(id).toString();
}
}