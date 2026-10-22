package net.swetychekkk.birdgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private SpriteBatch batch;
    public OrthographicCamera camera;
    Texture birdTexture;
    ScreenGame screenGame;

    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;

    int birdX = 0;
    int birdY = 0;
    int birdSpeed = 5;
    Bird bird;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        screenGame = new ScreenGame(this);
        bird = new Bird(0,0,birdTexture,birdSpeed);
        setScreen(screenGame);
    }

    @Override
    public void render() {
        if (Gdx.input.justTouched()) {
            bird.onClick();
            System.out.println("Jump!");
        }
        bird.fly();

        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        bird.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        birdTexture.dispose();
    }
}
