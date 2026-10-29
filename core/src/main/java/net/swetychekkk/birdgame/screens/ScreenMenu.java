package net.swetychekkk.birdgame.screens;

import static net.swetychekkk.birdgame.Main.SCR_WIDTH;
import static net.swetychekkk.birdgame.Main.batch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import net.swetychekkk.birdgame.Main;
import net.swetychekkk.birdgame.elements.TextButton;
import net.swetychekkk.birdgame.elements.back;

public class ScreenMenu implements Screen {
    Main main;
    TextButton playButton;
    TextButton exitButton;
    Texture logo;

    BitmapFont font;
    back back;

    String text;

    public ScreenMenu(Main main) {
        this.main = main;

        font = new BitmapFont(Gdx.files.internal("operator.fnt"));
        font.getData().scale(1f);
        font.setColor(Color.WHITE);

        playButton = new TextButton(SCR_WIDTH/2, 200, "Play");
        exitButton = new TextButton(SCR_WIDTH/2, 25, "Exit");
        logo = new Texture("logo.png");

        back = new back(new Texture("background-game.png"), 5);
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

            if (playButton.isHit((int) touch.x, (int) touch.y)) {
                main.setScreen(main.screenGame);
                return;
            } else if (exitButton.isHit((int) touch.x, (int) touch.y)) {
                Gdx.app.exit();
                return;
            }
        }
        ScreenUtils.clear(0,0,0,1);
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();

        back.draw(batch);
        back.move(delta);
        playButton.draw(main.batch);
        exitButton.draw(main.batch);
        batch.draw(logo, SCR_WIDTH/2-logo.getWidth()*0.25f, 270, logo.getWidth()*0.5f, logo.getHeight()*0.5f);

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
