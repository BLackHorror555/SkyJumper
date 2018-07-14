package com.dmitmit.game.GameObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public abstract class GameObject {
    protected final Rectangle bounds;
    protected float stateTime = 0;
    //protected Vector2 position;

    public GameObject(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
        //position = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return bounds.getPosition(new Vector2());
    }

    public void addPosition(Vector2 position) {
        bounds.setPosition(bounds.getPosition(new Vector2()).add(position));
    }

    public void setPosition(Vector2 position) {
        bounds.setPosition(position);
    }

    public void setPosition(float x, float y) {
        bounds.setPosition(x, y);
    }


    public Vector2 getSize() {
        return bounds.getSize(new Vector2());
    }

    public void setYPosition(float y) {
        bounds.setY(y);
    }

    public void setXPosition(float x) {
        bounds.setX(x);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void incStateTime(float time) {
        stateTime += time;
    }
}