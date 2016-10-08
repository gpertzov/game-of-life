package net.gpdev.gameoflife;

public class GameOfLife {
    private Grid current;
    private Grid next;

    public GameOfLife(int xDim, int yDim) {
        current = new Grid(xDim, yDim);
        next = new Grid(xDim, yDim);
    }

    public void populateCell(int x, int y) {
        current.set(x, y);
    }

    public boolean isCellPopulated(int x, int y) {
        return !current.isEmpty(x, y);
    }

    public void tick() {

    }
}
