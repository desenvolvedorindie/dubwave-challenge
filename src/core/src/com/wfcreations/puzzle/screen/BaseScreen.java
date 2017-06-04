package com.wfcreations.puzzle.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class BaseScreen implements Screen {

	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	protected Viewport viewport;
    
    public BaseScreen(int width, int height) {
		batch = new SpriteBatch();
        camera = new OrthographicCamera(width, height);
        camera.position.set(width * 0.5f, height * 0.5f, 0);
        camera.update();
        viewport = new FillViewport(width, height, camera);
        batch.setProjectionMatrix(camera.combined);
	}
    
    public void resize (int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }
    
	public void dispose() {
		batch.dispose();
	}
	
}
