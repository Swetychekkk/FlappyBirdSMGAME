package net.swetychekkk.birdgame.elements;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import net.swetychekkk.birdgame.Main;

public class back {
    Texture backgroundFirst;
    Texture backgroundNext;
    float x = 0;
    float sx;
    int speedMod;

    // Fade transition state
    private boolean transitioning = false;
    private float transitionProgress = 0f; // 0 = start/current, 1 = done
    private float transitionDuration = 1f; // seconds

    public back(Texture texture, int speedMod) {
        this.backgroundFirst = texture;
        this.backgroundNext = null;
        this.speedMod = speedMod;
    }

    /**
     * Запускает плавный переход на новый фон за заданное время (в секундах).
     * Если duration <= 0 — переключает мгновенно.
     */
    public void changeBackground(Texture texture, float duration) {
        if (texture == null) return;
        if (duration <= 0f) {
            backgroundFirst = texture;
            backgroundNext = null;
            transitioning = false;
            transitionProgress = 0f;
        } else {
            backgroundNext = texture;
            transitioning = true;
            transitionProgress = 0f;
            transitionDuration = duration;
        }
    }

    /** Мгновенно устанавливает текстуру без перехода (для совместимости) */
    public void setTexture(Texture texture) {
        backgroundFirst = texture;
        backgroundNext = null;
        transitioning = false;
        transitionProgress = 0f;
    }

    public void draw(Batch batch) {
        // Обновляем прогресс перехода
        if (transitioning) {
            transitionProgress += Gdx.graphics.getDeltaTime() / transitionDuration;
            if (transitionProgress >= 1f) {
                transitionProgress = 0f;
                // Завершаем переход: новый становится текущим
                backgroundFirst = backgroundNext;
                backgroundNext = null;
                transitioning = false;
            }
        }

        // Рисуем текущий фон с альфой (затухание)
        float alphaOld = 1f - transitionProgress;
        if (alphaOld > 0f) {
            batch.setColor(1f, 1f, 1f, alphaOld);
            batch.draw(backgroundFirst, x, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            batch.draw(backgroundFirst, sx, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
        }

        // Рисуем следующий фон с нарастающей альфой
        if (transitioning && backgroundNext != null) {
            float alphaNew = transitionProgress;
            if (alphaNew > 0f) {
                batch.setColor(1f, 1f, 1f, alphaNew);
                batch.draw(backgroundNext, x, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
                batch.draw(backgroundNext, sx, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
            }
        }

        // Сброс цвета
        batch.setColor(1f, 1f, 1f, 1f);
    }

    public void move(float delta) {
        x -= speedMod * delta;
        if (x < 0 - Main.SCR_WIDTH) {
            x = 0;
            System.out.println("OOS");
        }
        sx = x + Main.SCR_WIDTH;
    }

    public void dispose() {
        backgroundFirst.dispose();
        if (backgroundNext != null && !backgroundNext.equals(backgroundFirst)) {
            backgroundNext.dispose();
        }
    }
}