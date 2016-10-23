package net.gpdev.gameoflife;

import java.util.Random;

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

    public void randomize(final long seed) {
        Random rand = new Random(seed);
        for (int x = 0; x < xDim; x++) {
            for (int y = 0; y < yDim; y++) {
                if (rand.nextBoolean()) {
                    populateCell(x, y);
                }
            }
        }
    }

    public void populateCell(int x, int y) {
        current.set(x, y);
    }

    public boolean isCellPopulated(int x, int y) {
        return !current.isEmpty(x, y);
    }

    public void tick() {
        for (int x = 0; x < xDim; x++) {
            for (int y = 0; y < yDim; y++) {
                if (current.isEmpty(x, y)) {
                    if (current.numNeighbors(x, y) == 3) {
                        next.set(x, y);
                    } else {
                        next.unset(x, y);
                    }
                } else {
                    final int numNeighbors = current.numNeighbors(x, y);
                    if (numNeighbors == 2 || numNeighbors == 3) {
                        next.set(x, y);
                    } else {
                        next.unset(x, y);
                    }
                }
            }
        }

        final Grid prev = current;
        current = next;
        next = prev;
    }

    @Override
    public String toString() {
        return current.toString();
    }
}
