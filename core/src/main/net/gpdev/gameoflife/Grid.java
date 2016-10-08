package net.gpdev.gameoflife;

public class Grid {
    private final int xDim;
    private final int yDim;
    private boolean grid[][];

    public Grid(int xDim, int yDim) {
        this.xDim = xDim;
        this.yDim = yDim;
        this.grid = new boolean[xDim][yDim];
    }

    public boolean isEmpty(int x, int y) {
        throwIfOutOfBounds(x, y);
        return !grid[x][y];
    }

    public void set(int x, int y) {
        throwIfOutOfBounds(x, y);
        grid[x][y] = true;
    }

    public void unset(int x, int y) {
        throwIfOutOfBounds(x, y);
        grid[x][y] = false;
    }

    private void throwIfOutOfBounds(int x, int y) {
        if (isOutOfBounds(x, y)) {
            throw new IllegalArgumentException(String.format("Position (%d, %d) is out of grid bounds", x, y));
        }
    }

    private boolean isOutOfBounds(int xPos, int yPos) {
        if (xPos < 0 || xPos >= xDim) {
            return true;
        }
        if (yPos < 0 || yPos >= yDim) {
            return true;
        }
        return false;
    }

    public int numNeighbors(int x, int y) {
        int neighbors = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                final int x1 = x + i;
                final int y1 = y + j;

                // Skip position of the tested cell itself
                if (x1 == x && y1 == y) {
                    continue;
                }

                // Skip out of bounds
                if (isOutOfBounds(x1, y1)) {
                    continue;
                }

                // Increase neighbor count if not empty
                if (!isEmpty(x1, y1)) {
                    neighbors++;
                }
            }
        }

        return neighbors;
    }
}
