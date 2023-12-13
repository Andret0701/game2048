package game2048;

import java.util.ArrayList;

public interface Board {
    public int getValue(int x, int y);
    public int getWidth();
    public int getHeight();
    public ArrayList<Direction> getPossibleMoves();
    public Board testMove(Direction direction);
    public void print();
}
