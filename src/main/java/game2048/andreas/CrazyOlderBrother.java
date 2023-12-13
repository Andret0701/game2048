package game2048.andreas;

import java.util.ArrayList;

import game2048.Algorithm;
import game2048.Board;
import game2048.Direction;

public class CrazyOlderBrother implements Algorithm {
    int count = 0;
    public String getName() {
        return "CrazyOlderBrother";
    }

    public Direction getMove(Board board) {
        ArrayList<Direction> possibleMoves = board.getPossibleMoves();

        if ((count%4<2 || !possibleMoves.contains(Direction.LEFT)) && possibleMoves.contains(Direction.UP)) {
            count++;
            return Direction.UP;
        }
        else if(possibleMoves.contains(Direction.LEFT))
        {
            count++;
            return Direction.LEFT;
        }
        
        if (possibleMoves.contains(Direction.DOWN))
            return Direction.DOWN;
        return Direction.RIGHT;
    }
}