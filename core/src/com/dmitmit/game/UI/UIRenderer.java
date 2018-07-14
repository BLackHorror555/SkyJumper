package com.dmitmit.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dmitmit.game.Utils.Assets;
import com.dmitmit.game.Screens.PlayScreen;

public class UIRenderer {

    private OrthographicCamera uiCamera;
    private SpriteBatch batch;

    public UIRenderer(SpriteBatch batch){
        uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.batch = batch;
    }
    public void render(){
        batch.setProjectionMatrix(uiCamera.combined);
        batch.begin();
        renderScores();
        renderCoins();
        renderBar();
        switch (PlayScreen.getGameState()){
            case LOSE:
                renderGameOver();
            case WAITING:
                renderWaiting();
        }
        batch.end();
    }

    private void renderBar() {
        batch.draw(Assets.barBackground, -Gdx.graphics.getWidth() / 2,
                -Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth() / 14, Gdx.graphics.getHeight() / 7);

        TextureRegion currBar = new TextureRegion(PlayScreen.isJumpReady() ? Assets.redBar : Assets.blueBar,
                0, 0, Assets.blueBar.getRegionWidth(),
                Assets.blueBar.getRegionHeight() * PlayScreen.getJumpPoints() / PlayScreen.getPointsForJump());
        batch.draw(currBar,
                -Gdx.graphics.getWidth() / 2 + 5,
                -Gdx.graphics.getHeight() / 2 + 5,
                Gdx.graphics.getWidth() / 14 - 10,
                (Gdx.graphics.getHeight() / 7 - 10) * PlayScreen.getJumpPoints() / PlayScreen.getPointsForJump());
    }

    private void renderCoins() {
        Assets.font.setColor(Color.ORANGE);
        Assets.font.getData().setScale(Gdx.graphics.getWidth() * 0.9f/ 360);
        Assets.font.draw(batch, "Coins: " + Integer.toString(PlayScreen.getCoins()),
                Gdx.graphics.getWidth() / 12,
                Gdx.graphics.getHeight() / 2 - Assets.font.getXHeight());
    }

    private void renderWaiting(){
        Assets.font.setColor(Color.BLUE);
        Assets.font.getData().setScale(Gdx.graphics.getWidth() * 1.5f / 360);
        Assets.font.draw(batch, "Are you ready?",
                -Gdx.graphics.getWidth() / 2 + 10,
                0);
    }

    private void renderScores(){
        Assets.font.setColor(Color.BLACK);
        Assets.font.getData().setScale(Gdx.graphics.getWidth() * 0.9f / 360);
        Assets.font.draw(batch, "Scores: " + Integer.toString(PlayScreen.getScores()),
                -Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - Assets.font.getXHeight());
    }

    private void renderGameOver(){
        batch.draw(Assets.gameOver,
                -Gdx.graphics.getWidth() / 2, 0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight() / 3);

    }

    public void resize(int width, int height){
        uiCamera = new OrthographicCamera(width, height);
    }

}
