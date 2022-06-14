import java.util.Arrays;
import java.util.LinkedList;

public class Engine {

    public static int evaluationTotal(char[][] board, char player, char oposPlayer) {
        return evaluation(board, player, oposPlayer) - evaluation(board, oposPlayer, player);
    }
    private static int evaluation(char[][] board, char player, char oposPlayer) {
        int sum = 0;


        for(int row = 0; row < Main.BOARD_SIZE; row++)
            sum += pointsAdder(board, player, oposPlayer, 0, row, 1, 0, new int[]{0, 0}, 0);

        for(int column = 0; column < Main.BOARD_SIZE; column++)
            sum += pointsAdder(board, player, oposPlayer, column, 0, 0, 1, new int[]{0, 0}, 0);


        for(int row = 0; row < Main.BOARD_SIZE; row++)
            sum += pointsAdder(board, player, oposPlayer, 0, row, 1, 1, new int[]{0, 0}, 0);

        for(int column = 1; column < Main.BOARD_SIZE; column++)
            sum += pointsAdder(board, player, oposPlayer, column, 0, 1, 1, new int[]{0, 0}, 0);


        for(int row = 0; row < Main.BOARD_SIZE; row++)
            sum += pointsAdder(board, player, oposPlayer, 0, row, 1, -1, new int[]{0, 0}, 0);

        for(int column = 1; column < Main.BOARD_SIZE; column++)
            sum += pointsAdder(board, player, oposPlayer, column, 0, 1, -1, new int[]{0, 0}, 0);

        return sum;
    }

    //[entire, onlyPlayer]
    private static int pointsAdder(char[][] board, char player, char oposPlayer, int startX, int startY, int x, int y, int[] pair, int currentMax) {

        if(board[startY][startX] != oposPlayer)
            pair = new int[]{pair[0] + 1, pair[1] + ((board[startY][startX] == player) ? 1 : 0)};
        else
            pair = new int[]{0, 0};

        currentMax = Math.max(currentMax, calculatePoints(pair));

        if(startX + x < 0 ||startX + x >= Main.BOARD_SIZE || startY + y < 0 || startY + y >= Main.BOARD_SIZE)
            return currentMax;
        else
            return pointsAdder(board, player, oposPlayer, startX + x, startY + y, x, y, pair, currentMax);
    }
    private static int calculatePoints(int[] tuple){
        if(tuple[0] < Main.WIN_LENGTH)
            return 0;
        return (int) Math.pow(10, tuple[1] - 1);
    }
    public static boolean isFull(char[][] board) {
        for(var row : board)
            for(var cell : row)
                if(cell == ' ')
                    return false;
        return true;
    }
    public static LinkedList<int[]> possibleMoves(char[][] board) {
        var moves = new LinkedList<int[]>();

        for(int y = 0; y < Main.BOARD_SIZE; y++)
            for(int x = 0; x < Main.BOARD_SIZE; x++)
                if(board[y][x] == ' ')
                    moves.add(new int[]{y, x});
        return moves;
    }
    public static boolean possibleMove(char[][] board, int y, int x){
        var moves = possibleMoves(board);
        for(var move : moves)
            if(Arrays.equals(new int[]{y, x}, move))
                return true;
        return false;
    }
    public static char[][] cloneArray(char[][] board) {
        char[][] clone = new char[Main.BOARD_SIZE][Main.BOARD_SIZE];
        for(int y = 0; y < Main.BOARD_SIZE; y++)
            for(int x = 0; x < Main.BOARD_SIZE; x++)
                clone[y][x] = board[y][x];
        return clone;
    }
    public static boolean hasEnded (char[][] board, char player, char oposPlayer) {
        return Math.abs(1.2 * evaluationTotal(board, player, oposPlayer)) >= Math.pow(10, Main.WIN_LENGTH - 1);
    }


    public static int minimax(char[][] board, int depth, int alpha, int beta, boolean maximizingPlayer, char player, char oposPlayer) {
        var total = evaluationTotal(board, player, oposPlayer);
        if (depth == 0 || isFull(board) || Math.abs(1.2 * total) >= Math.pow(10, Main.WIN_LENGTH - 1))
            return total;

        var moves = possibleMoves(board);

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;

            for (var move : moves) {
                var child = cloneArray(board);
                child[move[0]][move[1]] = player;

                var eval = minimax(child, depth - 1, alpha, beta, false, player, oposPlayer);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha)
                    break;
            }
            return maxEval;
        }
        else {
            int minEval = Integer.MAX_VALUE;

            for (var move : moves) {
                var child = cloneArray(board);
                child[move[0]][move[1]] = oposPlayer;

                var eval = minimax(child, depth - 1, alpha, beta, true, player, oposPlayer);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha)
                    break;
            }
            return minEval;
        }
    }
}
