package game2048.leo;

import java.util.ArrayList;

import game2048.Algorithm;
import game2048.Board;
import game2048.Direction;

public class SmarterAlgo implements Algorithm {

    @Override
    public String getName() {
        return "LeoFørstePlass";
    }

    @Override
    public Direction getMove(Board board) {
        if (canJoinDir(true, board)) {
            return Direction.DOWN;
        } else if (canJoinDir(false, board)) {
            return Direction.RIGHT;
        }
        return worseGetMove(board);

    }

    private Direction worseGetMove(Board board) {
        ArrayList<Direction> options = board.getPossibleMoves();
        if (options.contains(Direction.DOWN)) {
            return Direction.DOWN;
        }
        if (options.contains(Direction.RIGHT)) {
            return Direction.LEFT;
        }
        if (options.contains(Direction.RIGHT)) {
            return Direction.RIGHT;
        } else {
            return Direction.DOWN;
        }
    }

    private boolean canJoinDir(boolean vertical, Board board) {
        if (vertical) {
            for (int col = 0; col < 4; col++) {
                Integer curr = null;
                for (int row = 0; row < 4; row++) {
                    if (board.getValue(col, row) > 0) {
                        if (board.getValue(col, row) == curr)
                            return true;
                        curr = board.getValue(col, row);
                    }

                }
            }
            return false;
        }

        else {
            for (int row = 0; row < 4; row++) {
                Integer curr = null;
                for (int col = 0; col < 4; col++) {
                    if (board.getValue(col, row) > 0) {
                        if (board.getValue(col, row) == curr)
                            return true;
                        curr = board.getValue(col, row);
                    }

                }
            }
            return false;
        }

    }

}
