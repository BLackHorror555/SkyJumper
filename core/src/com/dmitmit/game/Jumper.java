package com.dmitmit.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dmitmit.game.Utils.Assets;
import com.dmitmit.game.Screens.PlayScreen;

public class Jumper extends Game {

	private SpriteBatch batch;

	public Jumper(){

	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		Assets.loadTextures();
		Assets.loadSounds();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
