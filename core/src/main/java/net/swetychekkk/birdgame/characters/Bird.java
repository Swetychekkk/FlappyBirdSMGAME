package net.swetychekkk.birdgame.characters;

import static net.swetychekkk.birdgame.Main.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import net.swetychekkk.birdgame.Main;

import java.util.Random;

public class Bird {
    public int x, y;
    int speed;
    Texture texture;
    int jumpHeight;
    final int maxHeightOfJump = 200;
    boolean jump;
    int frameCounter;
    public boolean underControl;
    float velocityY = 0;
    public int gravity = -1500;
    float JUMP_FORCE = 600; // Подбери значение, например -1 или -2
    float scale = 0.5f;
    public float width = (389*scale);
    public float height = (290*scale);
    public boolean canFly = true;
    Random random = new Random();


    Texture[] framesArray = new Texture[]{
        new Texture("birdTiles/bird0.png"),
            new Texture("birdTiles/bird1.png"),
            new Texture("birdTiles/bird2.png"),
            new Texture("birdTiles/bird1.png"),
    };

    Texture deadTexture = new Texture("birdTiles/bird_dead.png");

    public Bird(int x, int y, Texture texture, int speed){
        this.x = x;
        this.y = (int) (height*random.nextInt((int) Math.floor(SCR_HEIGHT/height)));
        this.texture = texture;
        this.speed = speed;
        underControl = true;
    }

    public void fly(float delta) {
        if (canFly) {
            velocityY += gravity * delta;
            y += velocityY * delta;
        }

//        if (y < 0) {
//            y = 0;
//            velocityY = 0;
//        }
    }
    public void draw(Batch batch) {
        int frameMultiplier = 10;
        if (underControl) {
            batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        } else {
            batch.draw(deadTexture, x, y, width, height);
        }
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

    public boolean isInField() {
        return (y < Main.SCR_HEIGHT && y + height > 0);
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
        deadTexture.dispose();
    }

    public void onClick() {
        if (underControl) velocityY = JUMP_FORCE;
    }
}
