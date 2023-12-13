package game2048.andreas;

import java.util.ArrayList;

import game2048.Algorithm;
import game2048.Board;
import game2048.Direction;

public class NoUpAlgorithm implements Algorithm {
    public String getName() {
        return "NoUpAlgorithm";
    }

    public Direction getMove(Board board) {
        ArrayList<Direction> possibleMoves = board.getPossibleMoves();
        if (possibleMoves.contains(Direction.UP) && possibleMoves.size() > 1)
            possibleMoves.remove(Direction.UP);   
        
        return possibleMoves.get((int) (Math.random() * possibleMoves.size()));
    }
}
