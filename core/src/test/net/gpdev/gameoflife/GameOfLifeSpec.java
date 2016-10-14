package net.gpdev.gameoflife;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class GameOfLifeSpec {

    private static final int X_DIM = 16;
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
    public void whenLiveCellWithOneNeighborThenCellDies() throws Exception {
        final int x = 4;
        final int y = 7;
        game.populateCell(x, y);
        game.populateCell(x, y + 1);
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
        game.populateCell(x, y + 1);

        game.tick();

        assertTrue(game.isCellPopulated(x, y));
    }

    @Test
    public void whenLiveCellWithMoreThanThreeNeighborsThenCellDies() throws Exception {
        final int x = 4;
        final int y = 2;

        final int[][] neighbors = {
                {x - 1, y},
                {x + 1, y},
                {x, y - 1},
                {x, y + 1},
                {x - 1, y - 1},
                {x - 1, y + 1},
                {x + 1, y - 1},
                {x + 1, y + 1}
        };

        for (int i = 4; i <= 8; i++) {
            game = new GameOfLife(X_DIM, Y_DIM);
            game.populateCell(x, y);
            for (int j = 0; j < i; j++) {
                game.populateCell(neighbors[j][0], neighbors[j][1]);
            }
            game.tick();

            assertFalse(
                    String.format("Cell (%d, %d) should not be populated:\n%s", x, y, game),
                    game.isCellPopulated(x, y));
        }
    }

    //
    // See:
    //  http://www.conwaylife.com/w/index.php?title=Pentadecathlon
    //
    @Test
    public void testPeriodicPentadecathlon() throws Exception {
        final int x0 = 3;
        final int y0 = 6;
        final int PERIOD = 15;

        game.populateCell(x0, y0);
        game.populateCell(x0 + 1, y0);

        game.populateCell(x0 + 2, y0 - 1);
        game.populateCell(x0 + 2, y0 + 1);

        game.populateCell(x0 + 3, y0);
        game.populateCell(x0 + 4, y0);
        game.populateCell(x0 + 5, y0);
        game.populateCell(x0 + 6, y0);

        game.populateCell(x0 + 7, y0 - 1);
        game.populateCell(x0 + 7, y0 + 1);

        game.populateCell(x0 + 8, y0);
        game.populateCell(x0 + 9, y0);

        final String originalGrid = game.toString();

        for (int i = 0; i < PERIOD; i++) {
            if (i != 0) {
                assertNotEquals("Game grid should differ from original after " + i + " generations", originalGrid, game.toString());
            }
            game.tick();
        }

        final String currentGrid = game.toString();
        assertEquals("Game grid should be equal to original after " + PERIOD + " generations", originalGrid, currentGrid);
    }
}
