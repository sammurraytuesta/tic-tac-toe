import java.util.Scanner;

public class TicTacToe {
    private Scanner input = new Scanner(System.in);
    private GameBoard gb = new GameBoard();
    private int player;
    private int row;
    private int col;

    public void play(){
        int mode;
        int turn = 1;

        while(true){
            System.out.println("=================================================\n");
            System.out.println("Select the AI's opponent:\n [1] Human \n [2] AI");
            System.out.print("==>");
            mode = input.nextInt();

            if (mode == 1){
                while(true){
                    if (turn % 2 == 0) {
                        player = 2;
                    } else {
                        player = 1;
                    }

                    System.out.println("\n=================================================\n");
                    gb.print();
                    //player turn
                    System.out.println("\nPlayer " + player + "'s turn!");
                    //row
                    System.out.print("Enter row [0 to 2]: ");
                    row = input.nextInt();
                    //col
                    System.out.print("Enter col [0 to 2]: ");
                    col = input.nextInt();

                    if (gb.tryPlacePiece(player, row, col)){
                        if (gb.checkWin()){
                            System.out.println("Player " + player + " wins!");
                            break;
                        }
                        turn++;
                    }
                }
            }
            break;
        }
    }
}
