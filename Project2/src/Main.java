import java.util.ArrayList;
import java.util.List;

public class Main {
    static long time (String funcName, List<Movie> list) { //funkcja obliczajaca czas sortowania w mikrosekundach
        long time = 0;

        try {
            var method = Sorting.class.getMethod(funcName, ArrayList.class);

            long current = System.nanoTime() / 1000; //dzielenie nanosekund przez 1000 w celu otrzymania mikrosekund
            method.invoke(null, list);
            time = System.nanoTime() / 1000 - current;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return time;
    }

    public static void main(String[] args) {

        var list = new ArrayList<>(CSVReader.readFile("projekt2_dane.csv"));
        var list700Tho = new ArrayList<>(list.subList(0, 700000));
        var list500Tho = new ArrayList<>(list.subList(0, 500000));
        var list300Tho = new ArrayList<>(list.subList(0, 300000));
        var list100Tho = new ArrayList<>(list.subList(0, 100000));
        var list70Tho = new ArrayList<>(list.subList(0, 70000));
        var list50Tho = new ArrayList<>(list.subList(0, 50000));
        var list30Tho = new ArrayList<>(list.subList(0, 30000));
        var list10Tho = new ArrayList<>(list.subList(0, 10000));

        System.out.println("|-------------------------|");
        System.out.println("|   FULL LIST = " + list.size()+ "    |");
        System.out.println("|-------------------------|");
        System.out.println("| List length | Sort time |");
        System.out.println("|-------------------------|");
        System.out.println("|         Mergesort       |");
        System.out.println("|-------------------------|");
        System.out.println("       10 000 | " + time("mergesort", list10Tho));
        System.out.println("       30 000 | " + time("mergesort", list30Tho));
        System.out.println("       50 000 | " + time("mergesort", list50Tho));
        System.out.println("       70 000 | " + time("mergesort", list70Tho));
        System.out.println("      100 000 | " + time("mergesort", list100Tho));
        System.out.println("      300 000 | " + time("mergesort", list300Tho));
        System.out.println("      500 000 | " + time("mergesort", list500Tho));
        System.out.println("      700 000 | " + time("mergesort", list700Tho));
        System.out.println("    FULL LIST | " + time("mergesort", list));
        System.out.println("|-------------------------|");
        System.out.println("|         QUICKSORT       |");
        System.out.println("|-------------------------|");
        System.out.println("       10 000 | " + time("quicksort", list10Tho));
        System.out.println("       30 000 | " + time("quicksort", list30Tho));
        System.out.println("       50 000 | " + time("quicksort", list50Tho));
        System.out.println("       70 000 | " + time("quicksort", list70Tho));
        System.out.println("      100 000 | " + time("quicksort", list100Tho));
        System.out.println("      300 000 | " + time("quicksort", list300Tho));
        System.out.println("      500 000 | " + time("quicksort", list500Tho));
        System.out.println("      700 000 | " + time("quicksort", list700Tho));
        System.out.println("    FULL LIST | " + time("quicksort", list));
        System.out.println("|-------------------------|");
        System.out.println("|        BUCKETSORT       |");
        System.out.println("|-------------------------|");
        System.out.println("       10 000 | " + time("bucketsort", list10Tho));
        System.out.println("       30 000 | " + time("bucketsort", list30Tho));
        System.out.println("       50 000 | " + time("bucketsort", list50Tho));
        System.out.println("       70 000 | " + time("bucketsort", list70Tho));
        System.out.println("      100 000 | " + time("bucketsort", list100Tho));
        System.out.println("      300 000 | " + time("bucketsort", list300Tho));
        System.out.println("      500 000 | " + time("bucketsort", list500Tho));
        System.out.println("      700 000 | " + time("bucketsort", list700Tho));
        System.out.println("    FULL LIST | " + time("bucketsort", list));
        System.out.println("|-------------------------|");
        System.out.println("| List length |  Average  |");
        System.out.println("|-------------------------|");
        System.out.println("       10 000 | " + Other.average(list10Tho));
        System.out.println("       30 000 | " + Other.average(list30Tho));
        System.out.println("       50 000 | " + Other.average(list50Tho));
        System.out.println("       70 000 | " + Other.average(list70Tho));
        System.out.println("      100 000 | " + Other.average(list100Tho));
        System.out.println("      300 000 | " + Other.average(list300Tho));
        System.out.println("      500 000 | " + Other.average(list500Tho));
        System.out.println("      700 000 | " + Other.average(list700Tho));
        System.out.println("    FULL LIST | " + Other.average(list));
        System.out.println("|-------------------------|");
        System.out.println("| List length |   Median  |");
        System.out.println("|-------------------------|");
        System.out.println("       10 000 | " + Other.median(Sorting.bucketsort(list10Tho)));
        System.out.println("       30 000 | " + Other.median(Sorting.bucketsort(list30Tho)));
        System.out.println("       50 000 | " + Other.median(Sorting.bucketsort(list50Tho)));
        System.out.println("       70 000 | " + Other.median(Sorting.bucketsort(list70Tho)));
        System.out.println("      100 000 | " + Other.median(Sorting.bucketsort(list100Tho)));
        System.out.println("      300 000 | " + Other.median(Sorting.bucketsort(list300Tho)));
        System.out.println("      500 000 | " + Other.median(Sorting.bucketsort(list500Tho)));
        System.out.println("      700 000 | " + Other.median(Sorting.bucketsort(list700Tho)));
        System.out.println("    FULL LIST | " + Other.median(Sorting.bucketsort(list)));

    }
}
