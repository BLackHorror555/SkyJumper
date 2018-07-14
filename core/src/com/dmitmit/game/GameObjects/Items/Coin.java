package com.dmitmit.game.GameObjects.Items;

import com.badlogic.gdx.graphics.Texture;
import com.dmitmit.game.GameObjects.GameObject;

public class Coin extends GameObject {
    private static float COIN_WIDTH = 0.9f;
    private static float COIN_HEIGHT = 0.9f;

    public Coin(float x, float y) {
        super(x, y, COIN_WIDTH, COIN_HEIGHT);
    }

    public static float getCoinWidth() {
        return COIN_WIDTH;
    }

    public static float getCoinHeight() {
        return COIN_HEIGHT;
    }
}
