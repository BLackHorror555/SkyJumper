package com.dmitmit.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Assets {
    private static Texture items;
    public static TextureRegion coinSheet;
    public static TextureRegion[] coinFrames;

    public static Texture background;
    public static TextureRegion carlJumping;
    public static TextureRegion carlFalling;
    public static TextureRegion carlHitting;
    public static TextureRegion platform;
    public static TextureRegion barBackground;
    public static TextureRegion blueBar;
    public static TextureRegion redBar;
    public static Animation<TextureRegion> coin;

    public static Texture gameOver;

    public static Sound simpleJumpSound;
    public static Sound coinPickUp;
    public static Music gameMusic;
    public static Music mainMenuMusic;

    public static BitmapFont font;

    public static void loadTextures(){
        items = new Texture("text.png");

        background = new Texture("background\\61.jpg");
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        carlJumping = new TextureRegion(items, 32, 0, 14, 13);
        carlFalling = new TextureRegion(items, 10, 0, 10, 14);
        carlHitting = new TextureRegion(items, 47, 0, 13, 13);

        platform = new TextureRegion(items, 61, 0, 27, 7);
        barBackground = new TextureRegion(items, 0, 53, 15, 71);
        blueBar = new TextureRegion(items, 19, 53, 11, 67);
        redBar = new TextureRegion(items, 34, 52, 13, 78);
        gameOver = new Texture("losing.png");

        font = new BitmapFont(Gdx.files.internal("font.fnt"),Gdx.files.internal("font.png"), false);
        font.setColor(Color.BLACK);

        createAnimation();
    }

    private static void createAnimation() {
        //creating coin animation
        coinSheet = new TextureRegion(items, 0, 34, 127, 16);
        TextureRegion[][] tmp = coinSheet.split(16, coinSheet.getRegionHeight());
        coinFrames = new TextureRegion[7];
        System.arraycopy(tmp[0], 0, coinFrames, 0, tmp[0].length);
        coin = new Animation<TextureRegion>(0.15f, coinFrames);
    }

    public static void loadSounds(){
        simpleJumpSound = Gdx.audio.newSound(Gdx.files.internal("Sounds\\jump3.wav"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds\\gameMusic.wav"));
        mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds\\menuMusic.wav"));
        coinPickUp = Gdx.audio.newSound(Gdx.files.internal("Sounds\\coin4.wav"));

        gameMusic.setLooping(true);
        gameMusic.setVolume(0.8f);
        mainMenuMusic.setLooping(true);
        mainMenuMusic.setVolume(0.8f);
    }

    public static void playSound(Sound sound){
        sound.play();
    }

    public static void playSound(Sound sound, float volume){
        sound.play(volume);
    }

    public static void playMusic(Music music){
            music.play();
    }

    public static void dispose(){
        simpleJumpSound.dispose();
    }
}
