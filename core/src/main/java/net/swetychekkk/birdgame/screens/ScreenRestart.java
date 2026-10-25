package net.swetychekkk.birdgame.screens;

import static net.swetychekkk.birdgame.Main.SCR_HEIGHT;
import static net.swetychekkk.birdgame.Main.batch;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import net.swetychekkk.birdgame.Main;
import net.swetychekkk.birdgame.characters.Bird;
import net.swetychekkk.birdgame.elements.PointCounter;
import net.swetychekkk.birdgame.elements.TextButton;
import net.swetychekkk.birdgame.elements.back;

public class ScreenRestart implements Screen {

    Main main;

    back back;
    TextButton buttonRestart;

    public ScreenRestart(Main main) {
        this.main = main;

        back = new back(new Texture("restartBackground.png"), 20);
        buttonRestart = new TextButton(100, 400, "Restart");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        back.draw(main.batch);
        buttonRestart.draw(main.batch);
        main.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
