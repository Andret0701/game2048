package game2048;

public interface Algorithm {
    public String getName();
    public Direction getMove(Board board);
}
