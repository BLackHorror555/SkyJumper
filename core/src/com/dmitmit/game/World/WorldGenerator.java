package com.dmitmit.game.World;

import com.dmitmit.game.GameObjects.Items.Coin;
import com.dmitmit.game.GameObjects.Items.Platform;
import com.dmitmit.game.Screens.PlayScreen;

import java.util.Random;

public class WorldGenerator implements Runnable{

    private World world;
    private float highEdge = 0;
    private static float PLATFORM_SPACING = 3.5f;
    private Random rand;

    private Platform.PlatformMode prevMode;

    public WorldGenerator(World world){
        this.world = world;
        rand = new Random();
    }

    @Override
    public void run() {
        while(PlayScreen.getGameState() != PlayScreen.GameState.LOSE) {
            if (world.platforms.size() < 10)
                generateObjects();
        }
    }

    private void generateObjects(){
        generatePlatform();
        generateCoins();
    }

    private void generateCoins() {
        if (rand.nextFloat() > 0.6f && world.carl.getPosition().y > 10) {
            float xPos = rand.nextInt(World.WORLD_WIDTH);
            float yPos = highEdge + Platform.getPlatformHeight();
            world.coins.add(new Coin(xPos, yPos));
        }
    }

    private void generatePlatform(){
        float x = rand.nextInt(World.WORLD_WIDTH - (int)Platform.getPlatformWidth());
        highEdge += PLATFORM_SPACING;

        Platform platform;

        //generate horizontal or vertical moving platform
        //number of such platforms increases with game progress
        if (rand.nextFloat() > 0.7 - rand.nextInt((int)(world.carl.getPosition().y) / 10 + 1) / 100
                && world.carl.getPosition().y > 10){
            //horizontal
            if (rand.nextFloat() > 0.5f || prevMode == Platform.PlatformMode.MOVING_VERTICAL) {
                platform = new Platform(Platform.PlatformMode.MOVING_HORIZONTAL, x, highEdge);
                platform.addVelocity(Platform.getPlatformVelocity()
                        + rand.nextInt((int) (world.carl.getPosition().y) / 10 + 1) / 100, 0);
            }
            //vertical
            else{
                platform = new Platform(Platform.PlatformMode.MOVING_VERTICAL, x,
                        highEdge + Platform.getVerticalMovingRadius() * 0.85f);
                highEdge += Platform.getVerticalMovingRadius() * 0.85f * 2;
                platform.addVelocity(0, Platform.getPlatformVerticalVelocity()
                        + rand.nextInt((int) (world.carl.getPosition().y) / 10 + 1) / 100);
            }

        }
        else
            platform = new Platform(Platform.PlatformMode.STATIC, x, highEdge);
        world.platforms.add(platform);
        prevMode = platform.getMode();
    }
}


