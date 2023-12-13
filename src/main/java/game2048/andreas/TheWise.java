package game2048.andreas;

import game2048.Algorithm;
import game2048.Board;
import game2048.Direction;
import game2048.Score;
import javafx.util.Pair;

public class TheWise implements Algorithm {
    public String getName() {
        return "TheWise";
    }

    public Direction getMove(Board board) {
        Pair<Direction,Score> move = getBestMove(board, 2);
        Direction bestMove = move.getKey();
        return bestMove;
    }

    private Pair<Direction,Score> getBestMove(Board board, int depth)
    {
        double bestScore = 0;
        Direction bestMove = null;
        for(Direction direction : board.getPossibleMoves())
        {
            Board newBoard = board.testMove(direction);
            double score = getScore(newBoard);
            if (depth > 0)
            {
                Pair<Direction,Score> move = getBestMove(newBoard, depth - 1);
                score=Math.max(score,move.getValue().score);
            }
            if(score > bestScore)
            {
                bestScore = score;
                bestMove = direction;
            }
        }
        return new Pair<Direction,Score>(bestMove,new Score(bestScore,0));
    }

    private int getScore(Board board)
    {
        int score = 0;
        for(int y = 0; y < board.getHeight(); y++)
        {
            for(int x = 0; x < board.getWidth(); x++)
            {
                score += Math.pow(board.getValue(x, y), 2);
            }
        }
        return score;
    }
}
