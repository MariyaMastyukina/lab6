import java.io.Serializable;

/**
 * Класс координат со свойствами x y.
 */

public class Coordinates  implements Serializable {
    /** Поле координата X*/
    private float x; //Значение поля должно быть больше -375
    /** Поле координата Y*/
    private int y; //Значение поля должно быть больше -966
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param x- координата Х
     * @param y- координата Y
     */
    public Coordinates(float x,int y){
        this.x = x;
        this.y = y;
    }
    /**
     * Функция получения значения поля {@link Coordinates#x}
     * @return возвращает id города
     */
    public Float getX(){
        return this.x;
    }
    /**
     * Функция получения значения поля {@link Coordinates#y}
     * @return возвращает id города
     */
    public Integer getY(){
        return this.y;
    }
    /**
     * Функция переопределения метода toString
     * @return объект в строковом представлении
     */
    @Override
    public String toString() {
        return "x=" + this.getX() + ", y=" + this.getY();
    }
}
