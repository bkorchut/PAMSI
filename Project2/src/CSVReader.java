import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static List<Movie> readFile(String path) { //funkcja odczytujaca informacje z pliku
        var list = new LinkedList<Movie>();
        try {
            Scanner scanner = new Scanner(new File(path));

            if (scanner.hasNextLine())
                scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                var elements = line.split("\"");
                if (elements.length > 1){
                    try {list.add(new Movie(elements[1], Float.parseFloat(elements[2].split(",")[1])));}
                    catch (Exception ignored) {}
                }
                else {
                    try {
                        elements = line.split(",");
                        list.add(new Movie(elements[1], Float.parseFloat(elements[2])));
                    }
                    catch (Exception ignored) {}
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }
}
