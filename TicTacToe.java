import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private Scanner input = new Scanner(System.in);
    public GameBoard gb = new GameBoard();
    private GameTreeNode gt = new GameTreeNode();
    public int player;
    public int AI;
    public int row;
    public int col;

    public void play(){
        int mode;
        int turn = 1;

        while(true){
            System.out.println("=================================================\n");
            System.out.println("Select the AI's opponent:\n [1] Human \n [2] AI");
            System.out.print("==>");
            mode = input.nextInt();

            Random rand = new Random();
            this.AI = rand.nextInt(3-1) +1;

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
                    
                    if (player != AI){
                        //row
                        System.out.print("Enter row [0 to 2]: ");
                        row = input.nextInt();
                        //col
                        System.out.print("Enter col [0 to 2]: ");
                        col = input.nextInt();
                    }
                    else if (player == AI){
                        gt.gameBoard = gb.clone();
                        gt.AI = this.AI;
                        gt.expandChildren(3);
                        GameTreeNode node = gt.runMiniMax(true);
                        row = node.row;
                        col = node.col;
                        System.out.println("["+row+","+col+"]");
                    }
                    if (gb.tryPlacePiece(player, row, col)){
                        if (gb.checkWin()){
                            System.out.println("\n=================================================\n");
                            gb.print();
                            System.out.println("\nPlayer " + player + " wins!");
                            break;
                        }
                        else if (gb.checkDraw()){
                            System.out.println("\n=================================================\n");
                            gb.print();
                            System.out.println("\nDraw!!");
                            break;
                        }
                        turn++;
                    }
                }
            }
            else if (mode == 2){
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

                    if (player != AI){
                        gt.gameBoard = gb.clone();
                        gt.AI = this.player;
                        gt.expandChildren(3);
                        GameTreeNode node = gt.runMiniMax(true);
                        row = node.row;
                        col = node.col;
                        System.out.println("["+row+","+col+"]");
                    }
                    else if (player == AI){
                        //same thing since two AI's
                        gt.gameBoard = gb.clone();
                        gt.AI = this.AI;
                        gt.expandChildren(3);
                        GameTreeNode node = gt.runMiniMax(true);
                        row = node.row;
                        col = node.col;
                        System.out.println("["+row+","+col+"]");
                    }

                    if (gb.tryPlacePiece(player, row, col)){
                        if (gb.checkWin()){
                            System.out.println("\n=================================================\n");
                            gb.print();
                            System.out.println("\nPlayer " + player + " wins!");
                            break;
                        }
                        else if (gb.checkDraw()){
                            System.out.println("\n=================================================\n");
                            gb.print();
                            System.out.println("\nDraw!!");
                            break;
                        }
                        turn++;
                    }
                }
            }
            else{
                System.out.println("Please enter a valid repsonse!");
            }
            break;
        }
    }
}
