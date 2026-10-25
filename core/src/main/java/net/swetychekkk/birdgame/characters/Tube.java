package net.swetychekkk.birdgame.characters;

import static net.swetychekkk.birdgame.Main.SCR_HEIGHT;
import static net.swetychekkk.birdgame.Main.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {
    Texture textureUpperTube;
    Texture textureDownTube;

    Random random;

    int x, gapY;
    int distanceBetweenTubes;

    public int speed;
    final int width = 200;
    final int height = 700;
    int gapHeight = 350;
    int padding = 100;

    private boolean canClaimPoint = true;
    public Tube(int tubeCount, int tubeIdx, int speed) {
        random = new Random();
        this.speed = speed;

        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distanceBetweenTubes = (SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;

        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");
    }

    public void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }

    public void move(float delta) {
        x -= speed*delta;
        if (x < -width) {
            canClaimPoint=true;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }

    public void dispose() {
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }

    public boolean isHit(Bird bird) {
        return ((bird.y <= gapY - gapHeight  / 2  || bird.y + bird.height >= gapY + gapHeight / 2) && bird.x + bird.width >= x && bird.x <= x + width);
    }

    public boolean isPointAdd(Bird bird) {
        if (bird.x>x+width && canClaimPoint) {
            canClaimPoint = false;
            return true;
        }
        return false;
    }

}
