import java.util.Scanner;


public class Main {
    public static int BOARD_SIZE = 0;
    public static int WIN_LENGTH = 0;

    static void endGame(Board board, char player, char oposPlayer) {
        var eval = Engine.evaluationTotal(board.content, player, oposPlayer);
        board.print();

        if (Math.abs(1.2 * eval) < Math.pow(10, Main.WIN_LENGTH - 1) )
            System.out.println("...::: Remis :::...");
        else if (eval > 0)
            System.out.println("...::: Wygrales :::...");
        else
            System.out.println("...::: Przegrales :::...");

        System.exit(0);
    }

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        while(BOARD_SIZE > 10 || BOARD_SIZE < 3) {
            System.out.print("Rozmiar planszy: ");
            BOARD_SIZE = scanner.nextInt();
            if(BOARD_SIZE > 10)
                System.out.println("Rozmiar planszy pownienien byc nie wiekszy niz 10!");
            else if (BOARD_SIZE < 3)
                System.out.println("Rozmiar planszy pownienien byc nie mniejszy niz 3!");
        }

        while (WIN_LENGTH > BOARD_SIZE || WIN_LENGTH < 3) {
            System.out.print("Wygrywajaca dlugosc znakow: ");
            WIN_LENGTH = scanner.nextInt();
            if(WIN_LENGTH > BOARD_SIZE)
                System.out.println("Wygrywajaca dlugosc powinna byc krotsza od rozmiaru planszy!");
            else if (WIN_LENGTH < 3)
                System.out.println("Wygrywajaca dlugosc powinna byc nie mniejsza niz 3!");
        }

        var start = false;
        System.out.print("Gracz wykonuje ruch jako pierwszy(Y/any): ");
        if(scanner.next().equals("Y"))
            start = true;

        var board = new Board();

        while(!Engine.hasEnded(board.content, 'X', 'O') && !Engine.isFull(board.content)){

            var x = 0;
            var y = 0;

            if(start){
                board.print();

                do {
                    System.out.print("Kolumna: ");
                    x = scanner.nextInt() - 1;
                    if (x >= BOARD_SIZE || x < 0) {
                        System.out.println("Kolumna poza zakresem!");
                        continue;
                    }

                    System.out.print("Wiersz: ");
                    y = scanner.nextInt() - 1;
                    if (y >= BOARD_SIZE || y < 0) {
                        System.out.println("Wiersz poza zakresem!");
                        continue;
                    }


                    if (Engine.possibleMove(board.content, y, x))
                        break;

                    System.out.println("Wybrane pole jest zajete!");
                    System.out.println("Wybierz inne!");
                }
                while (true);

                board.content[y][x] = 'X';
            }
            else start = true;

            if(Engine.hasEnded(board.content, 'X', 'O') || Engine.isFull(board.content))
                endGame(board, 'X' , 'O');

            int[] bestMove = new int[2];
            int bestEstimation = Integer.MIN_VALUE;
            for (var move : Engine.possibleMoves(board.content)) {

                var child = Engine.cloneArray(board.content);
                child[move[0]][move[1]] = 'O';

                var estimation = Engine.minimax(child, 2, Integer.MIN_VALUE, Integer.MAX_VALUE, false, 'O', 'X');
                if (bestEstimation < estimation){
                    bestEstimation = estimation;
                    bestMove = move;
                }
            }
            board.content[bestMove[0]][bestMove[1]] = 'O';
        }
        endGame(board, 'X' , 'O');
    }
}