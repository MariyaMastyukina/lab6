package Server.Collection;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
/**
 * Класс сравнение объектов класса Server.Server.Commands.AddCommand.City по имени, реализующий интерфейс Comparator
 */
public class NameComparator implements Comparator<City> {
    /**
     * Функция переопределения метода compare
     * @return число
     */
    @Override
    public int compare(City city1, City city2) {
        return city1.getNameCity().compareTo(city2.getNameCity());
    }
}
