package game2048.andreas;

import java.util.ArrayList;

import game2048.Algorithm;
import game2048.Board;
import game2048.Direction;

public class CrazySpiner implements Algorithm {
    private Direction lastMove = Direction.UP;
    public String getName() {
        return "CrazySpiner";
    }

    public Direction getMove(Board board) {
        ArrayList<Direction> possibleMoves = board.getPossibleMoves();
        if (possibleMoves.size()==1) return possibleMoves.get(0);
        if (!possibleMoves.contains(Direction.DOWN)  &&  !possibleMoves.contains(Direction.RIGHT))
        {
            if (possibleMoves.contains(Direction.LEFT)) return Direction.LEFT;
            else return Direction.UP;
        }


        Direction move = getNextMove();
        lastMove = move;
        if (possibleMoves.contains(move)) 
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
                return Direction.RIGHT;
            case LEFT:
                return Direction.RIGHT;
        }
        return Direction.UP;
    }
}