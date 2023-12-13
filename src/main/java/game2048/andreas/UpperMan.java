package game2048.andreas;
import java.util.ArrayList;

import game2048.Algorithm;
import game2048.Board;
import game2048.Direction;

public class UpperMan implements Algorithm {
    public String getName() {
        return "UpperMan";
    }

    public Direction getMove(Board board) {
        ArrayList<Direction> possibleMoves = board.getPossibleMoves();
        if (possibleMoves.contains(Direction.UP))
            return Direction.UP;

        if (possibleMoves.contains(Direction.DOWN) && possibleMoves.size() > 1)
            possibleMoves.remove(Direction.DOWN);
        
        return possibleMoves.get((int) (Math.random() * possibleMoves.size()));
    }
}