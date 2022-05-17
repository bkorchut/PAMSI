import java.util.List;

public class Other {
    public static double average(List<Movie> list) { // funkcja wyznaczajaca wartosc srednia rankingu
        var sum = 0.0;
        for (var movie : list) {sum += movie.rating;}
        return sum/list.size();
    }
    public static double median(List<Movie> list) { // funkcja wyznaczajaca mediane rankingu
        if (list.size() < 1)
            return 0;

        var iter = list.iterator();
        for (int i = 0; i < (list.size() - 1) / 2; i++)
            iter.next();

        if (list.size() % 2 == 1)
            return iter.next().rating;
        return (iter.next().rating + iter.next().rating) / 2;
    }
}