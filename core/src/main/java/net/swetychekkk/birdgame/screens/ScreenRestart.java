package net.swetychekkk.birdgame.screens;

import static net.swetychekkk.birdgame.Main.SCR_HEIGHT;
import static net.swetychekkk.birdgame.Main.SCR_WIDTH;import static net.swetychekkk.birdgame.Main.batch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
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
    TextButton mainMenu;
    PointCounter pointCounter;
    int gamePoints;

    public ScreenRestart(Main main) {
        this.main = main;

        pointCounter = new PointCounter(750, 530);
        back = new back(new Texture("restartBackground.png"), 20);
        buttonRestart = new TextButton(SCR_WIDTH/2, 250, "Restart");
        mainMenu = new TextButton(SCR_WIDTH/2, 25, "Menu");
        pointCounter.setXByWidth(SCR_WIDTH/2, gamePoints);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {
            main.camera.update();

            Vector3 touch = main.camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );

            if (buttonRestart.isHit((int) touch.x, (int) touch.y)) {
                System.out.println("SSCCCREEENNN");
                main.setScreen(main.screenGame);
                return;
            } else if (mainMenu.isHit((int) touch.x, (int) touch.y)) {
                System.out.println("Main menu");
                main.setScreen(main.screenMenu);
                return;
            }
        }

        ScreenUtils.clear(0,0,0,1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        back.draw(main.batch);
        buttonRestart.draw(main.batch);
        mainMenu.draw(main.batch);
        pointCounter.draw(Main.batch, gamePoints);

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
