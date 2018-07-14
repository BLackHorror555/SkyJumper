package com.dmitmit.game.GameObjects.Items;

import com.badlogic.gdx.math.Vector2;
import com.dmitmit.game.GameObjects.DynamicGameObject;

public class Platform extends DynamicGameObject {
    public enum PlatformMode {
        STATIC, MOVING_HORIZONTAL, MOVING_VERTICAL
    }
    private static final float PLATFORM_WIDTH= 2.2f;
    private static final float PLATFORM_HEIGHT = 0.5f;
    private static final float PLATFORM_VELOCITY = 0.07f;
    private static final float PLATFORM_VERTICAL_VELOCITY = 0.05f;
    private static final float VERTICAL_MOVING_RADIUS = 4f;
    private final Vector2 centerPosition;
    private PlatformMode mode;

    public Platform(PlatformMode mode, float x, float y) {
       super (x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
       centerPosition = new Vector2(x, y);
       this.mode = mode;
    }

    public void setMode(PlatformMode mode){
        this.mode = mode;
    }

    public static float getPlatformWidth() {
        return PLATFORM_WIDTH;
    }

    public static float getPlatformHeight() {
        return PLATFORM_HEIGHT;
    }

    public static float getPlatformVelocity() {
        return PLATFORM_VELOCITY;
    }

    public static float getPlatformVerticalVelocity() {
        return PLATFORM_VERTICAL_VELOCITY;
    }

    public PlatformMode getMode() {
        return mode;
    }

    public static float getVerticalMovingRadius() {
        return VERTICAL_MOVING_RADIUS;
    }

    public Vector2 getCenterPosition() {
        return centerPosition;
    }
}
