package code;

import java.util.Scanner;

public class Main {

    private static String readNotEmptyString(){ //czyta input dopoki nie bedzie pusty
        Scanner input = new Scanner(System.in);
        String result;
        while(true){
            result = input.nextLine();
            if(!result.equals("")){
                return result;
            }
            System.out.println("Podaj niepusta wiadomosc!");
        }
    }
    private static int readInt(){ //czyta input dopóki użytkownik nie poda liczby
        Scanner input = new Scanner(System.in);
        int result;
        while(true){
            String line = input.nextLine();
            try{
                result = Integer.parseInt(line);
                return result;
            }
            catch (NumberFormatException e){
                System.out.println("Podaj liczbe!");
            }
        }
    }
    public static void main(String[] args) throws Exception {

        MyList<String> lastList = new MyList<String>();
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Menu:");
            System.out.println("    1.Podanie i dzielenie informacji na pakiety");
            System.out.println("    2.Zmiana kolejnosci pakietow");
            System.out.println("    3.Zobacz liste");
            System.out.println("    4.Scalanie wiadomosci");
            System.out.println("    5.Wyjscie");
            System.out.print("Opcja: ");

            int choice = readInt();

            switch (choice) {
                case 1: //Przyjmij informacje, podziel ja na pakiety i zapisz do listy
                    System.out.print("Informacja: ");
                    String information = readNotEmptyString();
                    System.out.print("Ilosc pakietow: ");
                    int partitionNumber = readInt();
                    lastList = Engine.partition(information, partitionNumber);
                    System.out.println(lastList);
                    break;
                case 2: //Wymieszanie pakietow w liscie
                    lastList = lastList.shuffle();
                    System.out.println(lastList);
                    break;
                case 3: //Wyswietla liste
                    System.out.println(lastList);
                    break;
                case 4: //Przywroc wiadomosc do pierwotnego stanu
                    System.out.println(Engine.merge(lastList));
                    break;
                case 5: //Wyjscie z menu
                    isRunning = false;
                    break;
                default:
                    System.out.println(("Podaj cyfre z przedzialu od 1 do 5!"));
                    break;
            }
            System.out.println("\n");
        }
    }
}
