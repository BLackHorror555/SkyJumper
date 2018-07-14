package com.dmitmit.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dmitmit.game.Utils.Assets;
import com.dmitmit.game.Jumper;
import com.dmitmit.game.UI.UIRenderer;
import com.dmitmit.game.World.World;
import com.dmitmit.game.World.WorldController;
import com.dmitmit.game.World.WorldGenerator;
import com.dmitmit.game.World.WorldRenderer;

public class PlayScreen implements Screen {
    public enum GameState{
        RUNNING, PAUSE, LOSE, WAITING
    }
    private static GameState state;
    private final Jumper game;
    private World world;
    private WorldRenderer worldRenderer;
    private WorldController worldController;
    private WorldGenerator worldGenerator;
    private static int scores = 0;
    private static int coins = 0;
    private static final int POINTS_FOR_JUMP = 100;
    private static int jumpPoints = 0;
    private SpriteBatch batch;
    private Thread generation;
    private UIRenderer uiRenderer;

    public PlayScreen(final Jumper game){
        this.game = game;
        batch = new SpriteBatch();
        world = new World();
        worldRenderer = new WorldRenderer(world, batch);
        worldController = new WorldController(world);
        worldGenerator = new WorldGenerator(world);
        generation = new Thread(worldGenerator);
        generation.start();

        uiRenderer = new UIRenderer(batch);

        Assets.gameMusic.play();
        state = GameState.WAITING;
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void resize(int width, int height) {
        uiRenderer.resize(width,height);
    }

    private void update(float delta){
        switch(state){
            case WAITING:
                updateWaiting();
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            case LOSE:
                updateLose();
                break;
        }

    }

    private void updateWaiting(){
        if (Gdx.input.justTouched()){
            setGameState(GameState.RUNNING);
        }
    }

    private void updateRunning(float delta){
        worldController.update(delta);
    }

    private void updateLose(){
        if (Gdx.input.isTouched()){
            newGame();
        }

    }

    private void newGame(){
        jumpPoints = 0;
        world = new World();
        worldRenderer = new WorldRenderer(world, batch);
        worldController = new WorldController(world);
        worldGenerator = new WorldGenerator(world);
        generation = new Thread(worldGenerator);
        generation.start();
        setGameState(GameState.WAITING);
    }

    public void draw(){
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().enableBlending();

        worldRenderer.render();
        uiRenderer.render();
    }

    public static int getScores(){
        return scores;
    }

    public static int getCoins() {
        return coins;
    }

    public static void setScores(int newScores){
        scores = newScores;
    }

    public static void incJumpPoints(int points){
        jumpPoints += points;
        if (jumpPoints > POINTS_FOR_JUMP)
            jumpPoints = POINTS_FOR_JUMP;
    }

    public static int getPointsForJump() {
        return POINTS_FOR_JUMP;
    }

    public static int getJumpPoints() {
        return jumpPoints;
    }

    public static void setJumpPoints(int points){
        jumpPoints = points;
    }

    public static void incCoins(int coins){
        PlayScreen.coins += coins;
    }

    public static boolean isJumpReady(){
        return PlayScreen.getJumpPoints() == PlayScreen.getPointsForJump();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        Assets.dispose();
    }

    public static GameState getGameState() {
        return state;
    }

    public static void setGameState(GameState state) {
        PlayScreen.state = state;
    }
}
