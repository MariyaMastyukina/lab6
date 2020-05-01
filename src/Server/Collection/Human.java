package Server.Collection;

import java.io.Serializable;

/**
 * Класс человека со свойством name.
 */

public class Human {
    /** Поле имя человека*/
    private String name; //Поле не может быть null, Строка не может быть пустой
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name- имя человека
     */
    public Human(String name){
        this.name=name;
    }
    /**
     * Функция переопределения метода toString
     * @return объект в строковом представлении
     */
    @Override
    public String toString(){
        return this.name;
    }
}
