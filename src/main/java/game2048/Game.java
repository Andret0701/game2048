package game2048;

import java.util.ArrayList;

public class Game implements Board {
    private int[] board;
    private int width;
    private int height;
    public Game(int width, int height) {
        board = new int[width * height];
        this.width = width;
        this.height = height;

        addRandomTile();
        addRandomTile();
    }

    private Game(int width, int height, int[] board) {
        this.width = width;
        this.height = height;
        this.board= new int[width * height];
        for (int i = 0; i < board.length; i++)
            this.board[i] = board[i];
    }

    private boolean isInside(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
            return false;
        return true;
    }

    private void setValue(int x, int y, int value) {
        if (!isInside(x, y))
            throw new IllegalArgumentException("Invalid position");
        board[y * getWidth() + x] = value;
    }

    @Override
    public int getValue(int x, int y) {
        if (!isInside(x, y))
            throw new IllegalArgumentException("Invalid position");
        return board[y * getWidth() + x];
    }

    @Override
    public int getWidth() {
        return width;
    }
    
    @Override
    public int getHeight() {
        return height;
    }

    private void moveLeft() { 
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int value = getValue(x, y);
                if (value == 0)
                    continue;
                
                int newX = x;
                while (newX > 0 && getValue(newX - 1, y) == 0)
                    newX--;
                
                if (newX-1>=0 && getValue(newX - 1, y) == value) {
                    setValue(newX - 1, y, getValue(newX - 1, y) * 2);
                    setValue(x, y, 0);
                } else if (newX != x) {
                    setValue(newX, y, value);
                    setValue(x, y, 0);
                }
                
                
            }
        }
    }

    private void moveRight() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = getWidth() - 1; x >= 0; x--) {
                int value = getValue(x, y);
                if (value == 0)
                    continue;
                
                int newX = x;
                while (newX < getWidth() - 1 && getValue(newX + 1, y) == 0)
                    newX++;
                
                if (newX+1<getWidth() && getValue(newX + 1, y) == value) {
                    setValue(newX + 1, y, getValue(newX + 1, y) * 2);
                    setValue(x, y, 0);
                } else if (newX != x) {
                    setValue(newX, y, value);
                    setValue(x, y, 0);
                }
                
                
            }
        }
    }

    private void moveUp() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int value = getValue(x, y);
                if (value == 0)
                    continue;
                
                int newY = y;
                while (newY > 0 && getValue(x, newY - 1) == 0)
                    newY--;
                
                if (newY-1>=0 && getValue(x, newY - 1) == value) {
                    setValue(x, newY - 1, getValue(x, newY - 1) * 2);
                    setValue(x, y, 0);
                } else if (newY != y) {
                    setValue(x, newY, value);
                    setValue(x, y, 0);
                }
                
                
            }
        }
    }

    private void moveDown() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = getHeight() - 1; y >= 0; y--) {
                int value = getValue(x, y);
                if (value == 0)
                    continue;
                
                int newY = y;
                while (newY < getHeight() - 1 && getValue(x, newY + 1) == 0)
                    newY++;
                
                if (newY+1<getHeight() && getValue(x, newY + 1) == value) {
                    setValue(x, newY + 1, getValue(x, newY + 1) * 2);
                    setValue(x, y, 0);
                } else if (newY != y) {
                    setValue(x, newY, value);
                    setValue(x, y, 0);
                }
                
                
            }
        }
    }





    @Override
    public ArrayList<Direction> getPossibleMoves() {
        ArrayList<Direction> result = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            if (canMove(direction))
                result.add(direction);
        }
        return result;
    }

    public boolean canMove(Direction direction) {
        Game copy = new Game(getWidth(), getHeight());
        copy.board = board.clone();
        switch (direction) {
            case UP:
                copy.moveUp();
                break;
            case DOWN:
                copy.moveDown();
                break;
            case LEFT:
                copy.moveLeft();
                break;
            case RIGHT:
                copy.moveRight();
                break;
        }
        return !copy.equals(this);
    }

    private void move(Direction direction, boolean addRandomTile) {
        if (!canMove(direction))
            throw new IllegalArgumentException("Invalid move");
        
        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
        if (addRandomTile)
            addRandomTile();
    }

    public void move(Direction direction) {
        move(direction, true);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Game))
            return false;
        Game other = (Game) obj;
        if (getWidth() != other.getWidth() || getHeight() != other.getHeight())
            return false;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != other.board[i])
                return false;
        }
        return true;
    }

    private int getRandomEmptyCell() {
        ArrayList<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0)
                emptyCells.add(i);
        }
        if (emptyCells.isEmpty())
            return -1;
        return emptyCells.get((int) (Math.random() * emptyCells.size()));
    }

    public void addRandomTile() {
        int cell = getRandomEmptyCell();
        if (cell == -1)
            return;
        board[cell] = Math.random() < 0.9 ? 2 : 4;
    }

    public boolean isGameOver() {
        if (getRandomEmptyCell() != -1)
            return false;
        for (Direction direction : Direction.values()) {
            if (canMove(direction))
                return false;
        }
        return true;
    }

    public int getScore() {
        int score = 0;
        for (int value : board) {
            score += value;
        }
        return score;
    }

    public int getHighestTile() {
        int highest = 0;
        for (int value : board) {
            highest = Math.max(highest, value);
        }
        return highest;
    }

    public void print() {
        int longest = 0;
        for (int value : board) 
            longest = Math.max(longest, Integer.toString(value).length());
        
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++)
                System.out.printf("%" + longest + "d ", getValue(x, y));
            System.out.println();
        }
    }

    @Override
    public Board testMove(Direction direction) {
        Game copy = copy();
        copy.move(direction, false);
        return copy;
    }

    public Game copy() {
        return new Game(getWidth(), getHeight(), board);
    }
}
