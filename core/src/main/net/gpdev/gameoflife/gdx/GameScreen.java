package net.gpdev.gameoflife.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.gpdev.gameoflife.GameOfLife;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class GameScreen extends ScreenAdapter {

    // Game board dimensions in world units
    private static final int GAME_WIDTH = 40;
    private static final int GAME_HEIGHT = 30;

    // Colors
    private static final Color BG_COLOR = Color.GRAY;
    private static final Color GRID_COLOR = Color.WHITE;
    private static final Color CELL_COLOR = Color.GREEN;

    private GameOfLife game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    @Override
    public void show() {
        super.show();

        game = new GameOfLife(GAME_WIDTH, GAME_HEIGHT);
        camera = new OrthographicCamera();
        shapeRenderer = new ShapeRenderer();

        // Set-up "Y Down" camera
        final int width = Gdx.graphics.getWidth();
        final int height = Gdx.graphics.getHeight();
        final float aspectRatio = (float) height / width;
        final float viewportWidth = GAME_WIDTH;
        final float viewportHeight = viewportWidth * aspectRatio;
        camera.setToOrtho(true, viewportWidth, viewportHeight);

        game.populateCell(0, 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Clear buffer
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, BG_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Apply camera projection
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Render grid lines
        shapeRenderer.begin(Line);
        shapeRenderer.setColor(GRID_COLOR);
        for (int y = 0; y < GAME_HEIGHT; y++) {
            for (int x = 0; x < GAME_WIDTH; x++) {
                shapeRenderer.rect(x, y, 1, 1);
            }
        }
        shapeRenderer.end();

        // Render game board
        shapeRenderer.begin(Filled);
        shapeRenderer.setColor(CELL_COLOR);
        for (int y = 0; y < GAME_HEIGHT; y++) {
            for (int x = 0; x < GAME_WIDTH; x++) {
                if (game.isCellPopulated(x, y)) {
                    shapeRenderer.rect(x, y, 1, 1);
                }
            }
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        super.dispose();

        shapeRenderer.dispose();
    }
}
