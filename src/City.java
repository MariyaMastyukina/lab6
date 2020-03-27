
import java.io.Serializable;
import java.time.LocalDate;
/**
 * Класс города со свойствами id, name, coordiantes, creationDate, area, population, metersAboveSeaLevel, capital, climate, government, governor.
 */
public class City implements Comparable<City>, Serializable {
    /** Поле id*/
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /** Поле название*/
    private String name; //Поле не может быть null, Строка не может быть пустой
    /** Поле координаты*/
    private Coordinates coordinates; //Поле не может быть null
    /** Поле дата создания*/
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /** Поле площадь*/
    private Double area; //Значение поля должно быть больше 0, Поле не может быть null
    /** Поле население*/
    private Integer population; //Значение поля должно быть больше 0, Поле не может быть null
    /** Поле метры на уровнем моря*/
    private Integer metersAboveSeaLevel;
    /** Поле столица*/
    private Boolean capital; //Поле может быть null
    /** Поле климат*/
    private Climate climate; //Поле может быть null
    /** Поле правительство*/
    private Government government; //Поле может быть null
    /** Поле губернатор*/
    private Human governor; //Поле может быть null
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name- название
     * @param coordinates- координаты
     * @param population- население
     * @param area- площадь
     * @param metersAboveSeaLevel- метры над уровнем моря
     * @param capital- столица
     * @param government- правительство
     * @param governor- губернатор
     * @param climate- климат
     * @see City#City(int, String, Coordinates, Double, Integer, Integer, Boolean, Climate, Government, Human)
     */
    public City(String name, Coordinates coordinates, Double area,Integer population,Integer metersAboveSeaLevel,Boolean capital,Climate climate,Government government,Human governor ) {
        this.id=(int)(Math.random() * 1000.0D);
        this.name=name;
        this.population = population;
        this.coordinates=coordinates;
        this.creationDate = LocalDate.now();
        this.area=area;
        this.metersAboveSeaLevel=metersAboveSeaLevel;
        this.capital=capital;
        this.government=government;
        this.governor=governor;
        this.climate=climate;
    }
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param id-id
     * @param name- название
     * @param coordinates- координаты
     * @param population- население
     * @param area- площадь
     * @param metersAboveSeaLevel- метры над уровнем моря
     * @param capital- столица
     * @param government- правительство
     * @param governor- губернатор
     * @param climate- климат
     * @see City#City(String, Coordinates, Double, Integer, Integer, Boolean, Climate, Government, Human)
     */
    public City(int id,String name, Coordinates coordinates, Double area,Integer population,Integer metersAboveSeaLevel,Boolean capital,Climate climate,Government government,Human governor ) {
        this.id=id;
        this.name=name;
        this.population = population;
        this.coordinates=coordinates;
        this.creationDate = LocalDate.now();
        this.area=area;
        this.metersAboveSeaLevel=metersAboveSeaLevel;
        this.capital=capital;
        this.government=government;
        this.governor=governor;
        this.climate=climate;
    }
    /**
     * Функция получения значения поля {@link City#id}
     * @return возвращает id города
     */
    public long getIdOfCity(){
        return id;
    }
    /**
     * Функция получения значения поля {@link City#metersAboveSeaLevel}
     * @return возвращает метры над уровнем моря
     */
    public Integer getMetersAboveSeaLevel(){
        return metersAboveSeaLevel;
    }
    /**
     * Функция переопределения метода toString
     * @return объект в строковом представлении
     */
    @Override
    public String toString() {
        return "City:id=" + this.id + ", name='" + this.name + '\'' + ", coordinates=" + this.coordinates + ", creationDate=" + this.creationDate + ", area=" + this.area +" , population="+this.population+ ", metersAboveSeaLevel=" + this.metersAboveSeaLevel + ", capital=" + this.capital + ", government=" + this.government + ", governor=" + this.governor + ", climate=" + this.climate;
    }
    /**
     * Функция получения значения поля {@link City#population}
     * @return возвращает население города
     */
    public Integer getPopulation(){
        return population;
    }
    /**
     * Функция переопределения сравнения значения поля {@link City#population}
     * @return число
     */
    @Override
    public int compareTo(City anotherCity) {
        if (this.population==anotherCity.population){
            return 0;
        }
        else{
            if (this.population>anotherCity.population){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

}

