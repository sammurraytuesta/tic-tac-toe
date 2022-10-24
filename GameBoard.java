public class GameBoard {
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char EMPTY = ' ';
    private char[][] gameBoard = {{EMPTY,EMPTY,EMPTY}
                                ,{EMPTY,EMPTY,EMPTY}
                                ,{EMPTY,EMPTY,EMPTY}};

    public boolean tryPlacePiece(int player, int row, int column) {
        //checks that the given coords are inbounds and not already occupied
        //and places a piece (X for player 1, O for player 2)
        //returns true if a piece placed successfully, or false otherwise
        if(row < 0 || row > 2) {
            System.out.println("Out of bounds!");
            return false;
        }
        else if (gameBoard[row][column] != EMPTY){
            System.out.println("That space is already occupied, try again.");
            return false;
        }

        if (player == 1){
            gameBoard[row][column] = X;
        }
        else if (player == 2){
            gameBoard[row][column] = O;
        }
        return true;
    }

    public boolean checkDraw() {
        int count = 0;
        if (!checkWin()){
            for (char[] square : gameBoard){
                for (char s : square){
                    if (s == EMPTY){
                        count++;
                    }
                }
            }
            System.out.println(count);
            return false;
        }
        return true;
    }

    public boolean checkWin() {
        //Checks if there is a winner in the current state
        //If there were more variable (X, O, etc.) I would have passed them through as arguments instead to avoid copying the same solution over and over. However since there are only two this way is okay too.
        boolean win = false;

        /* ===== win state for X ===== */
        
        //first diagonal
        int col = 0;
        for (int i = 0; i < gameBoard.length; i++){
            if (gameBoard[i][col] != X){
                win = false;
                break;
            }
            if (gameBoard[i][col] == X){
                win = true;
            }
            col++;
        }
        if (win == true){
            return true;
        }

        //second diagonal
        col = 0;
        for (int j = gameBoard.length - 1; j >= 0; j--){
            if (gameBoard[j][col] != X){
                win = false;
                break;
            }
            if (gameBoard[j][col] == X){
                win = true;
            }
            col++;
        }
        if (win == true){
            return true;
        }

        //rows
        for (int i = 0; i < gameBoard.length; i ++){
            for (int j = 0; j < gameBoard[0].length; j++){
                if (gameBoard[i][j] != X){
                    win = false;
                    break;
                }
                if (gameBoard[i][j] == X){
                    win = true;
                }
            }
            if (win == true){
                return true;
            }
        }

        //columns
        for (int i = 0; i < gameBoard.length; i ++){
            for (int j = 0; j < gameBoard[0].length; j++){
                if (gameBoard[j][i] != X){
                    win = false;
                    break;
                }
                if (gameBoard[j][i] == X){
                    win = true;
                }
            }
            if (win == true){
                return true;
            }
        }

        /* ===== win state for O ===== */
        
        //first diagonal
        col = 0;
        for (int i = 0; i < gameBoard.length; i++){
            if (gameBoard[i][col] != O){
                win = false;
                break;
            }
            if (gameBoard[i][col] == O){
                win = true;
            }
            col++;
        }
        if (win == true){
            return true;
        }

        //second diagonal
        col = 0;
        for (int j = gameBoard.length - 1; j >= 0; j--){
            if (gameBoard[j][col] != O){
                win = false;
                break;
            }
            if (gameBoard[j][col] == O){
                win = true;
            }
            col++;
        }
        if (win == true){
            return true;
        }

        //rows
        for (int i = 0; i < gameBoard.length; i ++){
            for (int j = 0; j < gameBoard[0].length; j++){
                if (gameBoard[i][j] != O){
                    win = false;
                    break;
                }
                if (gameBoard[i][j] == O){
                    win = true;
                }
            }
            if (win == true){
                return true;
            }
        }

        //columns
        for (int i = 0; i < gameBoard.length; i ++){
            for (int j = 0; j < gameBoard[0].length; j++){
                if (gameBoard[j][i] != O){
                    win = false;
                    break;
                }
                if (gameBoard[j][i] == O){
                    win = true;
                }
            }
            if (win == true){
                return true;
            }
        }

        return win;
    }

    public int evaluate() {
        //Returns higher numbers if player 1 is at an advantage
        //or lower numbers if player 2 is at an advantage
        int x1 = 0;
        int x2 = 0;
        int o1 = 0;
        int o2 = 0;

        /* ===== evaluation for X ===== */
        
        //first diagonal
        int count = 0;
        int col = 0;
        for (int i = 0; i < gameBoard.length; i++){
            if (gameBoard[i][col] != X){
                if (gameBoard[i][col] == O){
                    count = 0;
                    break;
                }
            }
            if (gameBoard[i][col] == X){
                count++;
            }
            col++;
        }
        if (count == 1){
            x1++;
        }
        else if (count == 2){
            x2++;
        }

        //second diagonal
        count = 0;
        col = 0;
        for (int j = gameBoard.length - 1; j >= 0; j--){
            if (gameBoard[j][col] != X){
                if (gameBoard[j][col] == O){
                    count = 0;
                    break;
                }
            }
            if (gameBoard[j][col] == X){
                count++;
            }
            col++;
        }
        if (count == 1){
            x1++;
        }
        else if (count == 2){
            x2++;
        }

        //rows
        for (int i = 0; i < gameBoard.length; i ++){
            count = 0;
            for (int j = 0; j < gameBoard[0].length; j++){
                if (gameBoard[i][j] != X){
                    if (gameBoard[i][j] == O){
                        count = 0;
                        break;
                    }
                }
                if (gameBoard[i][j] == X){
                    count++;
                }
            }
            if (count == 1){
                x1++;
            }
            else if (count == 2){
                x2++;
            }
        }

        //columns
        for (int i = 0; i < gameBoard.length; i ++){
            count = 0;
            for (int j = 0; j < gameBoard[0].length; j++){
                if (gameBoard[j][i] != X){
                    if (gameBoard[j][i] == O){
                        count = 0;
                        break;
                    }
                }
                if (gameBoard[j][i] == X){
                    count++;
                }
            }
            if (count == 1){
                x1++;
            }
            else if (count == 2){
                x2++;
            }
        }

        /* ===== win state for O ===== */
        
        //first diagonal
        count = 0;
        col = 0;
        for (int i = 0; i < gameBoard.length; i++){
            if (gameBoard[i][col] != O){
                if (gameBoard[i][col] == X){
                    count = 0;
                    break;
                }
            }
            if (gameBoard[i][col] == O){
                count++;
            }
            col++;
        }
        if (count == 1){
            o1++;
        }
        else if (count == 2){
            o2++;
        }

        //second diagonal
        count = 0;
        col = 0;
        for (int j = gameBoard.length - 1; j >= 0; j--){
            if (gameBoard[j][col] != O){
                if (gameBoard[j][col] == X){
                    count = 0;
                    break;
                }
            }
            if (gameBoard[j][col] == O){
                count++;
            }
            col++;
        }
        if (count == 1){
            o1++;
        }
        else if (count == 2){
            o2++;
        }

        //rows
        for (int i = 0; i < gameBoard.length; i ++){
            count = 0;
            for (int j = 0; j < gameBoard[0].length; j++){
                if (gameBoard[i][j] != O){
                    if (gameBoard[i][j] == X){
                        count = 0;
                        break;
                    }
                }
                if (gameBoard[i][j] == O){
                    count++;
                }
            }
            if (count == 1){
                o1++;
            }
            else if (count == 2){
                o2++;
            }
        }

        //columns
        for (int i = 0; i < gameBoard.length; i ++){
            count = 0;
            for (int j = 0; j < gameBoard[0].length; j++){
                if (gameBoard[j][i] != O){
                    if (gameBoard[j][i] == X){
                        count = 0;
                        break;
                    }
                }
                if (gameBoard[j][i] == O){
                    count++;
                }
            }
            if (count == 1){
                o1++;
            }
            else if (count == 2){
                o2++;
            }
        }

        //heuristic
        return (3*x2) + x1 - ((3*o2) + o1);
    }

    public void print(){
        //Prints out the formatted game board
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[0].length; j++){
                System.out.print(" "+ gameBoard[i][j] +" ");
                if(j != 2){
                    System.out.print("|");
                }
            }
            if (i != 2){
                System.out.println();
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    public GameBoard clone(){
        //Creates and returns a copy of this game board
        GameBoard clone = new GameBoard();
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[0].length; j++){
                clone.gameBoard[i][j] = this.gameBoard[i][j];
            }
        }
        return clone;
    }
}
