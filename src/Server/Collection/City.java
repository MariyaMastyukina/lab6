package Server.Collection;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Класс города со свойствами id, name, coordiantes, creationDate, area, population, metersAboveSeaLevel, capital, climate, government, governor.
 */
public class City  {
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
    /** Список аргументов класса City */
    List<String> args;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param args -список аргументов определенного объекта
     */
    public City(List<String> args) {
        this.args=args;
        if (args.size()==10) {
            this.id = (long) (Math.random() * 1000.0D);
            this.name = args.get(0);
            this.coordinates = new Coordinates(args.subList(1, 3));
            this.creationDate = LocalDate.now();
            this.area = Double.parseDouble(args.get(3));
            this.population = Integer.parseInt(args.get(4));
            this.metersAboveSeaLevel = Integer.parseInt(args.get(5));
            this.capital = Boolean.parseBoolean(args.get(6));
            if (args.get(7) == null) this.climate = null;
            else this.climate = Climate.valueOf(args.get(7));
            if (args.get(8) == null) this.government = null;
            else this.government = Government.valueOf(args.get(8));
            this.governor = new Human(args.get(9));
        }
        else if (args.size()==11){
            this.id=Long.parseLong(args.get(0));
            this.name = args.get(1);
            this.coordinates = new Coordinates(args.subList(2, 4));
            this.creationDate = LocalDate.now();
            this.area = Double.parseDouble(args.get(4));
            this.population = Integer.parseInt(args.get(5));
            this.metersAboveSeaLevel = Integer.parseInt(args.get(6));
            this.capital = Boolean.parseBoolean(args.get(7));
            if (args.get(8) == null) this.climate = null;
            else this.climate = Climate.valueOf(args.get(8));
            if (args.get(9) == null) this.government = null;
            else this.government = Government.valueOf(args.get(9));
            this.governor = new Human(args.get(10));
        }
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
     * Функция получения значения поля {@link City#name}
     * @return имя города
     */
    public String getNameCity(){
        return name;
    }
}
