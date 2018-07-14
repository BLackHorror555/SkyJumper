package com.dmitmit.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dmitmit.game.GameObjects.Items.Coin;
import com.dmitmit.game.GameObjects.Items.Platform;
import com.dmitmit.game.GameObjects.Player.Carl;
import com.dmitmit.game.Utils.Assets;

import java.util.Iterator;

public class WorldRenderer{
    private final static int CAMERA_WIDTH = 10;
    private final static int CAMERA_HEIGHT = 20;
    private World world;
    private SpriteBatch batch;
    private static OrthographicCamera camera;
    private static OrthographicCamera backCamera;

    private int backgroundEdge = CAMERA_HEIGHT;
    private float lastPos = CAMERA_HEIGHT / 2;
    private float back1YPos = 0;

    private int sourceY = 150;

    public WorldRenderer(World world, SpriteBatch batch) {
        this.world = world;
        this.batch = batch;
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        camera.position.set(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0);



        backCamera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        backCamera.position.set(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0);
    }


    public void render() {
        updateCamera();
        batch.begin();
        renderBackGround();
        batch.setProjectionMatrix(camera.combined);
        renderCarl();
        renderPlatforms();
        renderCoins();
        batch.end();
    }

    private void renderCoins() {
        for (Coin coin : world.coins){
            coin.incStateTime(Gdx.graphics.getDeltaTime());
            batch.draw(Assets.coin.getKeyFrame(coin.getStateTime(), true), coin.getPosition().x,
                    coin.getPosition().y, Coin.getCoinWidth(),Coin.getCoinHeight());
        }
    }

    private void renderBackGround() {
        //batch.draw(Assets.background, camera.position.x - CAMERA_WIDTH / 2,
                //camera.position.y - CAMERA_HEIGHT / 2, CAMERA_WIDTH, CAMERA_HEIGHT);
        batch.setProjectionMatrix(backCamera.combined);

        if (backCamera.position.y + CAMERA_HEIGHT / 2 >= back1YPos + 2 * CAMERA_HEIGHT)
            back1YPos = backCamera.position.y - CAMERA_HEIGHT / 2;
        batch.draw(Assets.background,0, back1YPos,CAMERA_WIDTH, CAMERA_HEIGHT);
        batch.draw(Assets.background,0, back1YPos + CAMERA_HEIGHT,CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    private void renderPlatforms() {
        Iterator<Platform> it = world.platforms.iterator();
        while (it.hasNext()) {
            Platform platform = it.next();
            batch.draw(Assets.platform, platform.getPosition().x, platform.getPosition().y,
                    platform.getSize().x, platform.getSize().y);
        }
    }

    private void renderCarl() {
        TextureRegion currTex;
        Carl carl = world.carl;
        if (carl.getVelocity().y > 0)
            currTex = Assets.carlJumping;
        else
            currTex = Assets.carlFalling;

        if (carl.getVelocity().x < 0)
            batch.draw(currTex, carl.getPosition().x + carl.getSize().x, carl.getPosition().y,
                    -carl.getSize().x, carl.getSize().y);
        else
            batch.draw(currTex, carl.getPosition().x, carl.getPosition().y,
                carl.getSize().x, carl.getSize().y);

    }

    private TextureRegion getAppropriateTexture(){
        Carl carl = world.carl;
        TextureRegion outTex;

        if (carl.getVelocity().y > 0) {
            outTex = Assets.carlJumping;
            if (carl.getVelocity().x < 0)
                outTex.flip(true, false);

        } else
            outTex = Assets.carlFalling;
        return outTex;
    }

    private void updateCamera(){
        if (world.carl.getPosition().y > camera.position.y){
            camera.position.y = world.carl.getPosition().y;
        }
        camera.update();

        if (lastPos != camera.position.y){
            backCamera.translate(0, (camera.position.y - lastPos) / 3);
            lastPos = camera.position.y;
        }
        backCamera.update();
    }

    public static int getCameraWidth() {
        return CAMERA_WIDTH;
    }

    public static int getCameraHeight() {
        return CAMERA_HEIGHT;
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }
}
