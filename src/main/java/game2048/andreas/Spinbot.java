package game2048.andreas;

import game2048.Algorithm;
import game2048.Board;
import game2048.Direction;

public class Spinbot implements Algorithm {
    private Direction lastMove = Direction.UP;
    public String getName() {
        return "Spinbot";
    }

    public Direction getMove(Board board) {
        Direction move = getNextMove();
        lastMove = move;
        if (board.getPossibleMoves().contains(move)) 
            return move;
        
        return getMove(board);
    }

    private Direction getNextMove() {
        switch (lastMove) {
            case UP:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.DOWN;
            case DOWN:
                return Direction.LEFT;
            case LEFT:
                return Direction.UP;
        }
        return Direction.UP;
    }
}