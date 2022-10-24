import java.util.*;
import java.util.Stack;
import java.util.List;

public class GameTreeNode {
    private List<GameTreeNode> children;
    private GameBoard gameBoard;
    private int minimaxValue;
    private static final int MAX_DEPTH = 3;

    public void expandChildren(int depthLimit){
        //Expands game tree to the given depth limit
        //DFS but with a depthLimit
        Stack<GameTreeNode> s = new Stack<GameTreeNode>();
        ArrayList<GameTreeNode> visited = new ArrayList<GameTreeNode>();
        int depth = 0;

        s.push(children.get(0));
        while(!s.isEmpty()){
            if(depth <= depthLimit){
                GameTreeNode node = s.pop();
                visited.add(node);
                s.addAll(node.children);
                depth++;
            }
            else{
                break;
            }
        }
        
    }

    public GameTreeNode runMiniMax(boolean max) {
        //Runs MINIMAX on the game tree rooted at this node
        //max is true if the MAX result is desired
        //max is false if the MIN result is desired
        //Returns the child node that the maximizes or minimizes the result
        int temp_max = Integer.MIN_VALUE;
        int temp_min = Integer.MAX_VALUE;

        GameTreeNode minimax = new GameTreeNode();

        if (max){
            for (GameTreeNode child : children){
                if (child.minimaxValue > temp_max){
                    temp_max = child.minimaxValue;
                    minimax = child;
                }
            }
            return minimax;
        }
        else{
            for (GameTreeNode child : children){
                if (child.minimaxValue < temp_min){
                    temp_min = child.minimaxValue;
                    minimax = child;
                }
            }
            return minimax;
        }
    }

}
