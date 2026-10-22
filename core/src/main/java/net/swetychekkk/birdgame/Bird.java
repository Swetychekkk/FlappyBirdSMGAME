package net.swetychekkk.birdgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    int x, y;
    int speed;
    Texture texture;
    int jumpHeight;
    final int maxHeightOfJump = 200;
    boolean jump;
    int frameCounter;
    int velocityY = 0;
    int gravity = -1; // Подбери значение, например -1 или -2

    Texture[] framesArray = new Texture[]{
        new Texture("birdTiles/bird0.png"),
            new Texture("birdTiles/bird1.png"),
            new Texture("birdTiles/bird2.png"),
            new Texture("birdTiles/bird1.png"),
    };

    public Bird(int x, int y, Texture texture, int speed){
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.speed = speed;
    }

    public void fly() {
        // Применяем гравитацию (постоянно тянем вниз)
        velocityY += gravity;

        // Обновляем позицию
        y += velocityY;

        // Ограничение по нижней границе экрана (пол)
        if (y < 0) {
            y = 0;
            velocityY = 0;
        }
    }
    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

    public void dispose() {
        texture.dispose();
    }

    void onClick() {
        velocityY = 15;
    }
}
