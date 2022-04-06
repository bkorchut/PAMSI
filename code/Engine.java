package code;

public class Engine {// m-dlugosc informacji n-liczba pakietow

    public static MyList<String> partition(String information, int partitionsNumber) throws Exception { // O(n^2)

        if (partitionsNumber < 1) throw new Exception("Za mala liczba pakietow!");
        if (information.equals("")) throw new Exception("Nie podano informacji!");
        MyList<String> partitions = new MyList<String>();
        for (int i = 0; i < partitionsNumber; i++) //pętla konstruuje liste czesci powstalych z podzielenia informacji, partisionsNumber oznacza liczbe pakietow // O(n*m)
            partitions.add(intToString(i + 1) + intToString(partitionsNumber) + information.substring(i * information.length() / partitionsNumber,// substring O(m)
                            (i + 1) * information.length() / partitionsNumber));
        return partitions.shuffle(); // O(n^2)
    }

    public static String merge(MyList<String> partitions) throws Exception { //funkcja przywraca informacje w pierwotnej kolejnosci  // O(m*n)
        if (partitions.size() < 1) throw new Exception("Brak pakietow do zlaczenia!");

        int partitionsNumber = Integer.parseInt(partitions.get(0).substring(8, 16)); //liczba wyslanych pakietow // parseInt O(1), bo ma stała długosc // get(0) O(1) // substring O(1), ze stalej długosci stringa
        if (partitionsNumber != partitions.size()) throw new Exception("Nie odebrano wszystkich pakietow!");// O(1)

        String[] sorted = new String[partitionsNumber]; //rezerwacja miejsca o dlugosci calej wiadomosci
        for (String partition : partitions)// O(n)
            sorted[Integer.parseInt(partition.substring(0, 8)) - 1] = partition.substring(16); //wstawianie zawartosci pakietu w deklarowana przez niego pozycje w informacji
        StringBuffer buffer = new StringBuffer();
        for (String str : sorted)// O(n*m)
            buffer.append(str); //scalenie wynikowej tablicy w pojedynczy string //

        return buffer.toString(); // O(m)
    }



    private static String intToString (int number) { //funkcja dodaje 0 na początek liczby, aby cały napis miał długość 8 znakow // O(1)

        String result = Integer.toString(number);
        while (result.length() < 8)
            result = "0" + result;
        return result;
    }
}