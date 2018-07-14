package com.dmitmit.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.dmitmit.game.Utils.Assets;
import com.dmitmit.game.Jumper;

public class MainMenuScreen implements Screen {

    private final static int CAMERA_WIDTH = 10;
    private final static int CAMERA_HEIGHT = 20;

    private final Jumper game;
    private ImageButton play;
    private ImageButton exit;
    private OrthographicCamera camera;


    public MainMenuScreen(Jumper game){
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Assets.mainMenuMusic.play();

    }

    private void update(){

    }

    private void draw(){

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Assets.mainMenuMusic.stop();
    }

    @Override
    public void dispose() {

    }
}
