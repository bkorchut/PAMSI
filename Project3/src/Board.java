public class Board {

    public char[][] content = new char[Main.BOARD_SIZE][Main.BOARD_SIZE];

    public Board() {
        for(int row = 0; row < Main.BOARD_SIZE; row++)
            for(int cell = 0; cell < Main.BOARD_SIZE; cell++)
                content[row][cell] = ' ';
    }

    public void print() {

        //for(int i = 0; i < 30; i++)
        //    System.out.println();

        System.out.print(" ");
        for(int i = 0; i < Main.BOARD_SIZE; i++)
            System.out.print("   " + (i+1));
        System.out.println();

        System.out.print("  |--");
        for(int i = 0; i < Main.BOARD_SIZE - 1; i++)
            System.out.print("-|--");
        System.out.println("-|");

        for(int row = 0; row < Main.BOARD_SIZE; row++){
            if(row != 0){
                System.out.print("  |--");
                for(int i = 0; i < Main.BOARD_SIZE - 1; i++)
                    System.out.print("-|--");
                System.out.println("-|");
            }
            System.out.print((row + 1) + " |");
            for(int cell = 0; cell < Main.BOARD_SIZE; cell++){
                System.out.print(" " + content[row][cell] + " |");
            }
            System.out.println();
        }
        System.out.print("  |--");
        for(int i = 0; i < Main.BOARD_SIZE - 1; i++)
            System.out.print("-|--");
        System.out.println("-|");
    }
}
