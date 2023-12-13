package game2048;

import java.util.ArrayList;

import javafx.util.Pair;

public class Arena {
   
    public static Score doGame(Algorithm algorithm, boolean print) {
        Game game = new Game(4, 4);
        while (!game.isGameOver()) {
            try {
                Direction move = algorithm.getMove(game);
                if (move==null || !game.canMove(move))
                    return new Score(0,0);
                game.move(move);
            } catch (Exception e) {
                return new Score(0,0);
            }
        }

        if (print)
            game.print();

        return new Score(game.getScore(), game.getHighestTile());
    }

    public static Score doGame(Algorithm algorithm) {
        return doGame(algorithm, false);
    }

    public static Score doGames(Algorithm algorithm, int n) {
        int totalScore = 0;
        int highestTile = 0;
        for (int i = 0; i < n; i++) {
            Score score = doGame(algorithm);
            totalScore += score.score;
            highestTile = Math.max(highestTile, score.highestTile);
        }
        return new Score(totalScore / n, highestTile);
    }

    public static Score evaluate(Algorithm algorithm) {
        Score score = doGames(algorithm, 10000);
        System.out.println("Evaluation of " + algorithm.getName() + ":");
        System.out.println("    Average score: " + score.score);
        System.out.println("    Highest tile: " + score.highestTile);
        return score;
    }

    public static void compete(Algorithm... algorithms) {
        ArrayList<Pair<Algorithm, Score>> scores = new ArrayList<>();
        for (Algorithm algorithm : algorithms) {
            Score score = doGames(algorithm, 500);
            scores.add(new Pair<>(algorithm, score));
        }
        scores.sort((a, b) -> Double.compare(b.getValue().score,a.getValue().score));

        System.out.println("Competition:");
        int rank = 1;
        for (Pair<Algorithm, Score> pair : scores) {
            System.out.println("  " + rank+". "+ pair.getKey().getName() + ":  score " + pair.getValue().score + ", highest tile " + pair.getValue().highestTile);
            rank++;
        }

    }
}
