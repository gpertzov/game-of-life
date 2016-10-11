package net.gpdev.gameoflife;

public class GameOfLife {
    private final int xDim;
    private final int yDim;

    private Grid current;
    private Grid next;

    public GameOfLife(int xDim, int yDim) {
        this.xDim = xDim;
        this.yDim = yDim;
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
        for (int i = 0; i < xDim; i++) {
            for (int j = 0; j < yDim; j++) {
                if (current.isEmpty(i, j)) {
                    if (current.numNeighbors(i, j) == 3) {
                        next.set(i, j);
                    }
                }
            }
        }
        current = next;
    }
}
