package game2048.andreas;

import java.util.Scanner;

import game2048.Board;
import game2048.Direction;

public class UserAlgorithm extends RandomAlgorithm {
    public String getName() {
        return "UserAlgorithm";
    }

    public Direction getMove(Board board) {
        board.print();
        System.out.println("Enter move: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("w")) {
            if (board.getPossibleMoves().contains(Direction.UP))
                return Direction.UP;
        } else if (input.equals("a")) {
            if (board.getPossibleMoves().contains(Direction.LEFT))
                return Direction.LEFT;
        } else if (input.equals("s")) {
            if (board.getPossibleMoves().contains(Direction.DOWN))
                return Direction.DOWN;
        } else if (input.equals("d")) {
            if (board.getPossibleMoves().contains(Direction.RIGHT))
                return Direction.RIGHT;
        }
        System.out.println("Invalid input");
        return getMove(board);
    }
}
