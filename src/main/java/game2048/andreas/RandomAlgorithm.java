package game2048.andreas;

import game2048.Algorithm;
import game2048.Board;
import game2048.Direction;

public class RandomAlgorithm implements Algorithm {
    public String getName() {
        return "RandomAlgorithm";
    }

    public Direction getMove(Board board) {
        return board.getPossibleMoves().get((int) (Math.random() * board.getPossibleMoves().size()));
    }
}