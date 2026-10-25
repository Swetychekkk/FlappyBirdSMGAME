package net.swetychekkk.birdgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.swetychekkk.birdgame.screens.ScreenGame;
import net.swetychekkk.birdgame.screens.ScreenRestart;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static SpriteBatch batch;
    public OrthographicCamera camera;
    ScreenGame screenGame;
    public ScreenRestart screenRestart;

    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;


    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        screenGame = new ScreenGame(this);
        screenRestart = new ScreenRestart(this);
        setScreen(screenGame);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
