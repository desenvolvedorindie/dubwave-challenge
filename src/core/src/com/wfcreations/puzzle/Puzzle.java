package com.wfcreations.puzzle;

import com.wfcreations.puzzle.screen.SplashScreen;

public class Puzzle extends com.badlogic.gdx.Game {
	
	
	@Override
	public void create () {
		Assets.loadAll();
        Assets.assetManager.update();
        
        setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		Assets.destroy(true);
	}

	
}
