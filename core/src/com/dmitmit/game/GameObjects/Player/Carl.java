package com.dmitmit.game.GameObjects.Player;

import com.dmitmit.game.GameObjects.DynamicGameObject;

public class Carl extends DynamicGameObject {

    public enum CarlStates{
        JUMPING, FALLING, HITTING
    }
    private final static float CARL_WIDTH = 0.8f;
    private final static float CARL_HEIGHT = 1.1f;
    private CarlStates state;
    private final static float CARL_JUMP_VELOCITY = 0.24f;
    private final static float CARL_MOVE_VELOCITY = 0.16f;
    private final static float SUPER_JUMP_VELOCITY = 0.6f;

    public Carl(float x, float y) {
        super(x, y, CARL_WIDTH, CARL_HEIGHT);
        state = CarlStates.JUMPING;
    }
    public void update(){}

    public void hit(){
        state = CarlStates.HITTING;
    }

    public void fall(){
        state = CarlStates.FALLING;
    }

    public static float getCarlWidth() {
        return CARL_WIDTH;
    }

    public static float getCarlHeight() {
        return CARL_HEIGHT;
    }

    public CarlStates getState() {
        return state;
    }

    public void changeState(CarlStates state){this.state = state; }

    public static float getCarlJumpVelocity() { return CARL_JUMP_VELOCITY; }

    public static float getCarlMoveVelocity() {
        return CARL_MOVE_VELOCITY;
    }

    public static float getSuperJumpVelocity() {
        return SUPER_JUMP_VELOCITY;
    }
}
