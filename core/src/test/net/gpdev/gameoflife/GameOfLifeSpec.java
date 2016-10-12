package net.gpdev.gameoflife;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void whenLiveCellWithNoNeighborsThenCellDies() throws Exception {
        final int x = 4;
        final int y = 7;
        game.populateCell(x, y);
        game.tick();
        assertFalse(game.isCellPopulated(x, y));
    }

    @Test
    public void whenEmptyCellWithThreeNeighborsThenCellBecomesPopulated() throws Exception {
        final int x = 6;
        final int y = 5;
        game.populateCell(x - 1, y);
        game.populateCell(x + 1, y);
        game.populateCell(x + 1, y + 1);

        game.tick();

        assertTrue(game.isCellPopulated(x, y));
    }

    @Test
    public void whenLiveCellWithTwoNeighborsThenCellSurvives() throws Exception {
        final int x = 3;
        final int y = 9;
        game.populateCell(x, y);

        game.populateCell(x + 1, y);
        game.populateCell(x - 1, y);

        game.tick();

        assertTrue(game.isCellPopulated(x, y));
    }

    @Test
    public void whenLiveCellWithThreeNeighborsThenCellSurvives() throws Exception {
        final int x = 3;
        final int y = 9;
        game.populateCell(x, y);

        game.populateCell(x + 1, y);
        game.populateCell(x - 1, y);
        game.populateCell(x , y + 1);

        game.tick();

        assertTrue(game.isCellPopulated(x, y));
    }
}
