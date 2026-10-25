package net.swetychekkk.birdgame.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PointCounter {
    int x,y;
    BitmapFont font;

    public PointCounter(int x, int y) {
        this.x =x;
        this.y = y;

        font = new BitmapFont(Gdx.files.internal("operator.fnt"));
        font.getData().setScale(1.5f);
        font.setColor(Color.WHITE);
    }
    public void draw(Batch batch, int countOfPoints) {
        font.draw(batch, "Count: " + countOfPoints, x, y);
    }

    void dispose() {
        font.dispose();
    }

}
