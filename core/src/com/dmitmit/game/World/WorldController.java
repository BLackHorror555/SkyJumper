package com.dmitmit.game.World;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.dmitmit.game.GameObjects.Items.Coin;
import com.dmitmit.game.GameObjects.Items.Platform;
import com.dmitmit.game.GameObjects.Player.Carl;
import com.dmitmit.game.Utils.Assets;
import com.dmitmit.game.Screens.PlayScreen;

import java.util.ArrayList;
import java.util.Random;

public class WorldController implements InputProcessor{
    private World world;
    private Carl carl;
    private Random rand;

    private int prevPlatformCollision = 0;

    public WorldController(World world) {
        this.world = world;
        this.carl = world.carl;
        Gdx.input.setInputProcessor(this);
        rand = new Random();
    }

    public void update(float delta){
        updatePlatforms();
        if (Gdx.app.getType() == Application.ApplicationType.Android)
            checkAcceleration();
        updateCarl(delta);
        updateScores();
        checkPlatformCollisions();
        checkCoinCollisions();
    }

    private void updatePlatforms(){
        ArrayList<Platform> delPlatforms = new ArrayList<Platform>();
        for ( Platform pl : world.platforms){
            if (pl.getPosition().y + pl.getBounds().getHeight()
                    < carl.getPosition().y - WorldRenderer.getCameraHeight() / 2) {
                delPlatforms.add(pl);
                continue;
            }
            if (pl.getMode() == Platform.PlatformMode.MOVING_HORIZONTAL){
                if (pl.getPosition().x <= 0){
                    pl.getVelocity().x = Platform.getPlatformVelocity();
                }
                if (pl.getPosition().x >= World.WORLD_WIDTH - Platform.getPlatformWidth()){
                    pl.getVelocity().x = -Platform.getPlatformVelocity();
                }                pl.addPosition(pl.getVelocity());
            }
            else if (pl.getMode() == Platform.PlatformMode.MOVING_VERTICAL){
                if (pl.getPosition().y <= pl.getCenterPosition().y - Platform.getVerticalMovingRadius()){
                    pl.getVelocity().y = Platform.getPlatformVelocity();
                }
                if (pl.getPosition().y >= pl.getCenterPosition().y + Platform.getVerticalMovingRadius()){
                    pl.getVelocity().y = -Platform.getPlatformVelocity();
                }
                pl.addPosition(pl.getVelocity());
            }
        }
        world.platforms.removeAll(delPlatforms);
    }

    private void checkPlatformCollisions(){
        for (int i = 0; i < world.platforms.size(); i++){
            if (world.platforms.get(i).getBounds().overlaps(world.carl.getBounds())
                && world.carl.getPosition().y >= world.platforms.get(i).getPosition().y
                    && carl.getVelocity().y <= 0)
            {
                world.carl.setVelocity(new Vector2(world.carl.getVelocity().x
                        , Carl.getCarlJumpVelocity() + world.platforms.get(i).getVelocity().y));
                Assets.simpleJumpSound.play();
                //remove lucky platform
                if (rand.nextFloat() > 0.7 && world.platforms.get(i).getMode() != Platform.PlatformMode.MOVING_VERTICAL
                        && world.platforms.get(i + 1).getMode() != Platform.PlatformMode.MOVING_VERTICAL
                        && carl.getPosition().y > 10)
                    world.platforms.remove(i);

                if (prevPlatformCollision != i)
                    PlayScreen.incJumpPoints(5);
                prevPlatformCollision = i;
                break;
            }
        }
    }

    private void checkCoinCollisions(){
        for (Coin coin : world.coins){
            if (coin.getBounds().overlaps(world.carl.getBounds())){
                world.coins.remove(coin);
                PlayScreen.incCoins(1);
                Assets.playSound(Assets.coinPickUp, 0.8f);
                PlayScreen.incJumpPoints(10);
                break;
            }
        }
    }

    private void updateCarl(float delta){
        Carl carl = world.carl;
        switch (carl.getState()){
            case JUMPING:
                carl.addVelocity(0, World.gravity * delta);

        }

        carl.addPosition(carl.getVelocity());
        if (carl.getPosition().x + Carl.getCarlWidth() <= 0){
            carl.setXPosition(10);
        }
        if (carl.getPosition().x >= World.WORLD_WIDTH && carl.getVelocity().x > 0){
            carl.setXPosition(0 - Carl.getCarlWidth());
        }
        if (carl.getPosition().y + Carl.getCarlHeight()
                < WorldRenderer.getCamera().position.y - WorldRenderer.getCameraHeight()){
            PlayScreen.setGameState(PlayScreen.GameState.LOSE);
        }
    }

    private void updateScores(){
        if (PlayScreen.getScores() < carl.getPosition().y / 1.5)
            PlayScreen.setScores((int)(carl.getPosition().y / 1.5));
    }

    private void checkAcceleration(){
        float accX = Gdx.input.getAccelerometerX();
        world.carl.getVelocity().x = -accX * Carl.getCarlMoveVelocity() / 4;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT){
            world.carl.getVelocity().x = -Carl.getCarlMoveVelocity();
        }
        if (keycode == Input.Keys.RIGHT){
            world.carl.getVelocity().x = Carl.getCarlMoveVelocity();
        }
        if (PlayScreen.getGameState() == PlayScreen.GameState.WAITING
                || PlayScreen.getGameState() == PlayScreen.GameState.LOSE){
            PlayScreen.setGameState(PlayScreen.GameState.RUNNING);
        }
        if (keycode == Input.Keys.SPACE && PlayScreen.getGameState() == PlayScreen.GameState.RUNNING
                && PlayScreen.isJumpReady()) {
            world.carl.setVelocity(carl.getVelocity().x, Carl.getSuperJumpVelocity());
            PlayScreen.setJumpPoints(0);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT){
            world.carl.getVelocity().x = 0;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (PlayScreen.getGameState() == PlayScreen.GameState.RUNNING
                && PlayScreen.isJumpReady()) {
            world.carl.setVelocity(carl.getVelocity().x, Carl.getSuperJumpVelocity());
            PlayScreen.setJumpPoints(0);
        }
        /*if (screenX <= 300)
            world.carl.getVelocity().x = -Carl.getCarlMoveVelocity();
        else
            world.carl.getVelocity().x = Carl.getCarlMoveVelocity();*/
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //world.carl.getVelocity().x = 0;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}