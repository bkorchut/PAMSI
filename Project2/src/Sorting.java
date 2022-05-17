import java.util.ArrayList;
import java.util.LinkedList;

public class Sorting {
    public static <E extends Comparable<E>> ArrayList<E> mergesort(ArrayList<E> list) { //sortowanie przez scalanie
        if (list.size() <= 1)
            return list;

        var left = mergesort(new ArrayList<>(list.subList(0, list.size()/2)));
        var right = mergesort(new ArrayList<>(list.subList(list.size()/2, list.size())));
        var result = new ArrayList<E>(list.size());

        var leftIter = left.iterator();
        var rightIter = right.iterator();

        var leftValue = leftIter.next();
        var rightValue = rightIter.next();

        while (true) {
            if(leftValue.compareTo(rightValue) < 0){
                result.add(leftValue);
                if (leftIter.hasNext())
                    leftValue = leftIter.next();
                else {
                    result.add(rightValue);
                    while (rightIter.hasNext())
                        result.add(rightIter.next());
                    return result;
                }
            }
            else {
                result.add(rightValue);
                if (rightIter.hasNext())
                    rightValue = rightIter.next();
                else {
                    result.add(leftValue);
                    while (leftIter.hasNext())
                        result.add(leftIter.next());
                    return result;
                }
            }
        }
    }
    public static <E extends Comparable<E>> ArrayList<E> quicksort(ArrayList<E> list) { //sortowanie szybkie
        if(list.size() < 2)
            return list;

        var random = list.get(Sorting.random(0, list.size()));
        var smaller = new ArrayList<E>(list.size());
        var bigger = new ArrayList<E>(list.size());
        E middle = null; // sluzy do zabierania jednego elementu, w celu zagwarantowania zakonczenia rekurencji

        for (var element : list){
            if (random.compareTo(element) < 0)
                bigger.add(element);
            else if (random.compareTo(element) == 0 && middle == null)
                middle = element;
            else
                smaller.add(element);
        }

        var result = new ArrayList<E>(list.size());
        result.addAll(quicksort(smaller));
        result.add(middle);
        result.addAll(quicksort(bigger));
        return result;
    }
    public static ArrayList<Movie> bucketsort(ArrayList<Movie> list){ //sortowanie kubełkowe
        var buckets = new ArrayList<LinkedList<Movie>>();
        for (int i = 0; i < 10; i++) {buckets.add(new LinkedList<>());}

        for (var movie : list) { buckets.get((int)(float)movie.rating - 1).add(movie); }

        var result = new ArrayList<Movie>(list.size());
        for (var bucket : buckets)
            result.addAll(bucket);

        return result;
    }
    private static int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
