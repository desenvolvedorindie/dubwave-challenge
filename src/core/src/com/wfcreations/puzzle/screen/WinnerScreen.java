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

public class WinnerScreen extends BaseScreen {

    Puzzle game;
    Stage stage;
    Skin skin;
    private Button backToMenuButton;

    public WinnerScreen(final Puzzle puzzle) {
        super(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        this.game = puzzle;
        
        this.stage = new Stage(viewport, batch);
        
        stage.clear();
        
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin();
		skin.addRegions(Assets.uiAtlas);
		
		Button.ButtonStyle backToMenuButtonStyle = new Button.ButtonStyle(skin.getDrawable(Assets.UI_BUTTON_BACK_TO_MENU_UP), skin.getDrawable(Assets.UI_BUTTON_BACK_TO_MENU_DOWN), skin.getDrawable(Assets.UI_BUTTON_BACK_TO_MENU_DOWN));
		
		backToMenuButton = new Button(backToMenuButtonStyle);
		
		backToMenuButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				WinnerScreen.this.game.setScreen(new MenuScreen(game));
			}
		});
		
		stage.addActor(backToMenuButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(255f / 255f, 255f / 255f, 255f / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	
    	backToMenuButton.setPosition(Constants.SCREEN_WIDTH - backToMenuButton.getWidth() - 40, 30);
    	
    	this.stage.act(delta);
		
		this.batch.begin();
		this.batch.draw(Assets.bgWinner, 0, 0);
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
