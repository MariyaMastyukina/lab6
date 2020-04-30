package Server.Collection;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class NameComparator implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return city1.getNameCity().compareTo(city2.getNameCity());
    }
}
