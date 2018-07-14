package com.dmitmit.game.GameObjects;

import com.badlogic.gdx.math.Vector2;

public abstract class DynamicGameObject extends GameObject {

    protected Vector2 velocity;
    protected Vector2 accel;

    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }

    public void addVelocity(float x, float y){
        this.velocity.add(x, y);
    }
    public void addVelocity(Vector2 velocity){
        this.velocity.add(velocity);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(float x, float y) {
        this.velocity = new Vector2(x, y);
    }


    public Vector2 getAccel() {
        return accel;
    }
}
