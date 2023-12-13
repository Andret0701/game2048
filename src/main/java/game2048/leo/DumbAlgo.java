package game2048.leo;

import java.util.ArrayList;

import game2048.Algorithm;
import game2048.Board;
import game2048.Direction;

public class DumbAlgo implements Algorithm {

    @Override
    public String getName() {
        return "DuErDumAlgoritme";
    }

    @Override
    public Direction getMove(Board board) {
        ArrayList<Direction> options = board.getPossibleMoves();
        if (options.contains(Direction.DOWN)) {
            return Direction.DOWN;
        }
        if (options.contains(Direction.RIGHT)) {
            return Direction.RIGHT;
        }
        if (options.contains(Direction.LEFT)) {
            return Direction.LEFT;
        }
        
        return Direction.DOWN;
    }

}
