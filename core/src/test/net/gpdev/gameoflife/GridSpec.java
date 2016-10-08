package net.gpdev.gameoflife;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GridSpec {

    private static final int X_DIM = 4;
    private static final int Y_DIM = 6;
    private Grid grid;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        grid = new Grid(X_DIM, Y_DIM);
    }

    @After
    public void tearDown() throws Exception {
        grid = null;
    }

    @Test
    public void whenGridInitThenAllCellsAreEmpty() throws Exception {
        for (int i = 0; i < X_DIM; i++) {
            for (int j = 0; j < Y_DIM; j++) {
                assertTrue(grid.isEmpty(i, j));
            }
        }
    }

    @Test
    public void whenCellIsSetThenCellIsNotEmpty() throws Exception {
        for (int i = 0; i < X_DIM; i++) {
            for (int j = 0; j < Y_DIM; j++) {
                grid.set(i, j);
                assertFalse(grid.isEmpty(i, j));
            }
        }
    }

    @Test
    public void whenCellIsUnsetThenCellIsEmpty() throws Exception {
        for (int i = 0; i < X_DIM; i++) {
            for (int j = 0; j < Y_DIM; j++) {
                grid.set(i, j);
                grid.unset(i, j);
                assertTrue(grid.isEmpty(i, j));
            }
        }
    }

    @Test
    public void whenXBelowBoundsThenThrowException() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("out of grid bounds");

        grid.set(-1, 0);
    }

    @Test
    public void whenXAboveBoundsThenThrowException() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("out of grid bounds");

        grid.set(X_DIM + 1, 0);
    }

    @Test
    public void whenYBelowBoundsThenThrowException() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("out of grid bounds");

        grid.set(0, -1);
    }

    @Test
    public void whenYAboveBoundsThenThrowException() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("out of grid bounds");

        grid.set(0, Y_DIM + 1);
    }

    @Test
    public void whenGridInitThenNoNeighbors() throws Exception {
        for (int i = 0; i < X_DIM; i++) {
            for (int j = 0; j < Y_DIM; j++) {
                assertEquals(0, grid.numNeighbors(i, j));
            }
        }
    }

    @Test
    public void whenNoAdjacentCellAreSetThenNoNeighbors() throws Exception {
        final int xPos = 1;
        final int yPos = 2;
        assertEquals("No cell is set", 0, grid.numNeighbors(xPos, yPos));

        grid.set(xPos, yPos);
        assertEquals("Only cell under test is set", 0, grid.numNeighbors(xPos, yPos));
    }

    @Test
    public void whenOneAdjacentCellIsSetThenOneNeighbor() throws Exception {
        final int xPos = 2;
        final int yPos = 3;
        for (int i = xPos - 1; i <= xPos + 1; i++) {
            for (int j = yPos - 1; j <= yPos + 1; j++) {
                if (i == xPos && j == yPos) {
                    continue;
                }
                grid.set(i, j);
                assertEquals(String.format("Cell (%d, %d) is set", i, j), 1, grid.numNeighbors(xPos, yPos));
                grid.unset(i, j);
            }
        }
    }

    @Test
    public void whenAllAdjacentCellsAreSetThenEightNeighbors() throws Exception {
        final int xPos = 2;
        final int yPos = 3;

        grid.set(xPos - 1, yPos - 1);
        grid.set(xPos, yPos - 1);
        grid.set(xPos + 1, yPos - 1);

        grid.set(xPos - 1, yPos);
        grid.set(xPos + 1, yPos);

        grid.set(xPos - 1, yPos + 1);
        grid.set(xPos, yPos + 1);
        grid.set(xPos + 1, yPos + 1);

        assertEquals(8, grid.numNeighbors(xPos, yPos));
    }
}
