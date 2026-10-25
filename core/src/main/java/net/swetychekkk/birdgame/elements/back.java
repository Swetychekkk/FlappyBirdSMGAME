package net.swetychekkk.birdgame.elements;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import net.swetychekkk.birdgame.Main;

public class back {
    Texture backgroundFirst;
    float x = 0;
    float sx;
    int speedMod;
    public back(Texture texture, int speedMod) {
        this.backgroundFirst = texture;
        this.speedMod = speedMod;
    }
    public void draw(Batch batch) {
        batch.draw(backgroundFirst, x,0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        batch.draw(backgroundFirst, sx,0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
    }
    public void move(float delta) {
        x-=speedMod*delta;
        if (x<0-Main.SCR_WIDTH) {
            x= 0;
            System.out.println("OOS");
        }
        sx=x+Main.SCR_WIDTH;
    }

    public void dispose() {
        backgroundFirst.dispose();
    }
}
