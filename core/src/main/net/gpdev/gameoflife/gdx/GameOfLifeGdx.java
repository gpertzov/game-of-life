package net.gpdev.gameoflife.gdx;

import com.badlogic.gdx.Game;

public class GameOfLifeGdx extends Game {


    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
