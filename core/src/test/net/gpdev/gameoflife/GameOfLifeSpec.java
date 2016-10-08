package net.gpdev.gameoflife;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GameOfLifeSpec {

    private static final int X_DIM = 10;
    private static final int Y_DIM = 12;

    private GameOfLife game;

    @Before
    public void setUp() throws Exception {
        game = new GameOfLife(X_DIM, Y_DIM);
    }

    @After
    public void tearDown() throws Exception {
        game = null;

    }

    @Test
    public void WhenLiveCellWithNoNeighborsThenCellDies() throws Exception {
        final int x = 4;
        final int y = 7;
        game.populateCell(x, y);
        game.tick();
        assertFalse(game.isCellPopulated(x, y));
    }
}
