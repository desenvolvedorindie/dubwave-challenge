package com.wfcreations.puzzle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.wfcreations.puzzle.Assets;
import com.wfcreations.puzzle.Constants;
import com.wfcreations.puzzle.Puzzle;

public class SplashScreen extends BaseScreen {

	Puzzle game;
	float elapsed = 0;
	
	public SplashScreen(final Puzzle puzzle) {
		super(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		this.game = puzzle;
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255f / 255f, 255f / 255f, 255f / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		elapsed += delta;
		
		this.batch.begin();
		this.batch.draw(Assets.bgSplash, 0, 0);
		this.batch.end();
		
		if (elapsed > 6.0) {
			game.setScreen(new MenuScreen(game));
		}		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}	

}
