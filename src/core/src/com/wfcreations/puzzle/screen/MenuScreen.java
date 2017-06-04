package com.wfcreations.puzzle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.wfcreations.puzzle.Assets;
import com.wfcreations.puzzle.Constants;
import com.wfcreations.puzzle.Puzzle;

public class MenuScreen extends BaseScreen {

	Puzzle game;
	Stage stage;
	Skin skin;
	private Button playButton;
	private Button creditsButton;

	public MenuScreen(final Puzzle puzzle) {
		super(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

		this.game = puzzle;

		this.stage = new Stage(viewport, batch);
		
		this.stage.clear();

		Gdx.input.setInputProcessor(stage);

		skin = new Skin();
		skin.addRegions(Assets.uiAtlas);

		Button.ButtonStyle playButtonStyle = new Button.ButtonStyle(skin.getDrawable(Assets.UI_BUTTON_PLAY_UP), skin.getDrawable(Assets.UI_BUTTON_PLAY_DOWN), skin.getDrawable(Assets.UI_BUTTON_PLAY_DOWN));
		Button.ButtonStyle creditsButtonStyle = new Button.ButtonStyle(skin.getDrawable(Assets.UI_BUTTON_CREDITS_UP), skin.getDrawable(Assets.UI_BUTTON_CREDITS_DOWN), skin.getDrawable(Assets.UI_BUTTON_CREDITS_DOWN));

		playButton = new Button(playButtonStyle);
		
		playButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				MenuScreen.this.game.setScreen(new GameScreen(game, 1, 60000));
			}
		});
		
		creditsButton = new Button(creditsButtonStyle);

		creditsButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				MenuScreen.this.game.setScreen(new CreditsScreen(game));
			}
		});
		
		stage.addActor(playButton);
		stage.addActor(creditsButton);
	}


	@Override
	public void show() {
		Assets.musicTheme.play();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255f / 255f, 255f / 255f, 255f / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playButton.setPosition(Constants.SCREEN_WIDTH / 2 - playButton.getWidth() / 2, 330);
		
		creditsButton.setPosition(Constants.SCREEN_WIDTH / 2 - creditsButton.getWidth() / 2, 190);
		
		this.stage.act(delta);
		
		this.batch.begin();
		this.batch.draw(Assets.bgMenu, 0, 0);
		this.batch.end();
		
		this.stage.draw();
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
		super.dispose();
		stage.dispose();
		skin.dispose();
	}
	
}
