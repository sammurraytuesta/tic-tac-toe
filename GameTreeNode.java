import java.util.*;
import java.util.Stack;

public class GameTreeNode {
    private ArrayList<GameTreeNode> children = new ArrayList<GameTreeNode>();
    public GameBoard gameBoard;
    public int row;
    public int col;
    public int AI;
    private int minimaxValue;
    private static final int MAX_DEPTH = 3;

    public void expandChildren(int depthLimit) {
        // Expands game tree to the given depth limit
        Stack<GameTreeNode> s = new Stack<GameTreeNode>();
        int depth = 0;

        this.minimaxValue = gameBoard.evaluate(AI);
        s.push(this);
        while (!s.isEmpty()) {
            if (depth <= depthLimit && depthLimit <= MAX_DEPTH) {
                GameTreeNode node = s.pop();
                for (int i = 0; i < node.gameBoard.gameBoard.length; i++) {
                    for (int j = 0; j < node.gameBoard.gameBoard[0].length; j++) {
                        GameTreeNode child = new GameTreeNode();
                        child.gameBoard = node.gameBoard.clone();
                        if (AI == 1 && node.gameBoard.gameBoard[i][j] == ' ') {
                            child.gameBoard.gameBoard[i][j] = 'X';
                            child.row = i;
                            child.col = j;
                            child.minimaxValue = child.gameBoard.evaluate(AI);
                            node.children.add(child);
                        } else if (AI == 2 && node.gameBoard.gameBoard[i][j] == ' ') {
                            child.gameBoard.gameBoard[i][j] = 'O';
                            child.row = i;
                            child.col = j;
                            child.minimaxValue = child.gameBoard.evaluate(AI);
                            node.children.add(child);
                        }
                    }
                    depth++;
                }

            } else {
                break;
            }
        }

    }

    public GameTreeNode runMiniMax(boolean max) {
        // Runs MINIMAX on the game tree rooted at this node
        // max is true if the MAX result is desired
        // max is false if the MIN result is desired
        // Returns the child node that the maximizes or minimizes the result
        int temp_max = Integer.MIN_VALUE;
        int temp_min = Integer.MAX_VALUE;

        GameTreeNode minimax = new GameTreeNode();

        if (children.size() == 0) {
            // set the minimax value
            minimaxValue = gameBoard.evaluate(AI);
            return null;
        }

        if (max && children.size() > 0) {
            for (GameTreeNode child : children) {
                child.runMiniMax(!max);
                if (child.minimaxValue > temp_max) {
                    temp_max = child.minimaxValue;
                    minimax = child;
                    minimax.gameBoard = child.gameBoard.clone();
                    minimax.minimaxValue = child.gameBoard.evaluate(AI);
                }
            }
            return minimax;
        } else {
            for (GameTreeNode child : children) {
                child.runMiniMax(!max);
                if (child.minimaxValue < temp_min) {
                    temp_min = child.minimaxValue;
                    minimax = child;
                    minimax.gameBoard = child.gameBoard.clone();
                    minimax.minimaxValue = child.gameBoard.evaluate(AI);
                }
            }
            return minimax;
        }
    }

}
