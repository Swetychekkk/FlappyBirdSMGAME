package net.swetychekkk.birdgame.screens;

import static net.swetychekkk.birdgame.Main.SCR_HEIGHT;
import static net.swetychekkk.birdgame.Main.batch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import net.swetychekkk.birdgame.Main;
import net.swetychekkk.birdgame.characters.Tube;
import net.swetychekkk.birdgame.characters.Bird;
import net.swetychekkk.birdgame.elements.PointCounter;
import net.swetychekkk.birdgame.elements.back;

import java.util.Random;

public class ScreenGame implements Screen {
    net.swetychekkk.birdgame.Main Main;
    Random random = new Random();

    Texture birdTexture;
    Bird bird;
    net.swetychekkk.birdgame.elements.back back;
    PointCounter pointCounter;
    final int pointCounterMarginTop = 30;
    final int pointCounterMarginLeft = 10;
    int tubeCount = 3;
    Tube[] tubes;
    int birdX = 0;
    int birdY = 0;
    int birdSpeed = 3;
    double tubeSpeed = 200;
    double maxTubeSpeed = 600;
    boolean isGameOver;


    private boolean screenTransition;
    private float timerToSwitch = 2.5f;
    int points;
    float deathFade = 0f;   // 0 = нет затемнения, 0.7 = затемнено

    public ScreenGame(Main main) {
        this.Main = main;
        initTubes();
        back = new back(new Texture("background-game.png"), 20);
        bird = new Bird(20,0,birdTexture,birdSpeed);
        pointCounter = new PointCounter(0+pointCounterMarginLeft, SCR_HEIGHT - pointCounterMarginTop);
    }

    @Override
    public void show() {
        isGameOver = false;
        points = 0;
    }

    @Override
    public void render(float delta) {
        if (screenTransition) {
            if (bird.y+bird.height<=0) {
                Main.setScreen(Main.screenRestart);
                return;
            }
        }
        if (isGameOver) {
            bird.underControl = false;
            bird.gravity = -700;
            screenTransition = true;
        }
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
        bird.fly(delta);
        if (!bird.isInField()) {
            isGameOver = true;

        }
        back.move(delta);

        if (tubeSpeed < maxTubeSpeed) tubeSpeed += 2*delta;
        for (Tube tube : tubes) {
            tube.move(delta);
            tube.speed = (int) tubeSpeed;
            if (tube.isHit(bird)) isGameOver = true; else if (tube.isPointAdd(bird)) points++;
        }


        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        back.draw(batch);
        bird.draw(batch);
        for (Tube tube : tubes) tube.draw(Main.batch);
        pointCounter.draw(batch, points);
        batch.end();
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
        birdTexture.dispose();
        for (int i=0; i<tubeCount; i++) {
            tubes[i].dispose();
        }
    }
    void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i, 200);
        }
    }
}
