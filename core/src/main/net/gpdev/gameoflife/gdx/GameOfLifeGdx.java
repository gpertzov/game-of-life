package net.gpdev.gameoflife.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class GameOfLifeGdx extends Game {

    private final Screen gameScreen = new GameScreen();

    @Override
    public void create() {
        setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        gameScreen.dispose();
    }
}
