package com.dmitmit.game.World;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dmitmit.game.GameObjects.Items.Coin;
import com.dmitmit.game.GameObjects.Player.Carl;
import com.dmitmit.game.GameObjects.Items.Platform;
import com.dmitmit.game.GameObjects.Items.Spring;

import java.util.concurrent.CopyOnWriteArrayList;

public class World {

    Vector2 worldGravity;
    final static int WORLD_WIDTH = 10;
    final static int WORLD_HEIGHT = 20 * 20;
    CopyOnWriteArrayList<Platform> platforms;
    CopyOnWriteArrayList<Coin> coins;
    CopyOnWriteArrayList<Spring> springs;
    Carl carl;

    //physics
    static float gravity = -0.4f;

    public World(){
        worldGravity = new Vector2(0, -1);
        platforms = new CopyOnWriteArrayList<Platform>();
        coins = new CopyOnWriteArrayList<Coin>();
        springs = new CopyOnWriteArrayList<Spring>();
        carl = new Carl(4.2f, 2);

        //test
        platforms.add(new Platform(Platform.PlatformMode.STATIC, 4, 0));

    }

    public void Update(){

    }

    public static int getWorldWidth() {
        return WORLD_WIDTH;
    }

    public static int getWorldHeight() {
        return WORLD_HEIGHT;
    }

    public void changeGravity(Vector2 newGravity){
        this.worldGravity = newGravity;
    }

}