package com.wfcreations.puzzle.screen;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.wfcreations.puzzle.Assets;
import com.wfcreations.puzzle.Constants;
import com.wfcreations.puzzle.Puzzle;
import com.wfcreations.puzzle.function.Function;
import com.wfcreations.puzzle.function.Sin;

public class GameScreen extends BaseScreen {

	public InputListener inputPrevListener = new InputListener() {
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			return true;
		}

		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			int index = GameScreen.this.prevButtons.indexOf(event.getTarget());
			phases.set(index, phases.get(index) - Constants.PHASE_INCREMENT);
			GameScreen.this.checkNextStage();
		}
	};
	
	public InputListener inputNextListener = new InputListener() {
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			return true;
		}

		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			int index = GameScreen.this.nextButtons.indexOf(event.getTarget());
			phases.set(index, phases.get(index) + Constants.PHASE_INCREMENT);
			GameScreen.this.checkNextStage();
		}
	};

	Puzzle game;
	Stage stage;
	Skin skin;
	ShapeRenderer shape;
	ArrayList<Function> functions = new ArrayList<Function>(4);
	ArrayList<Function> functionsAnswer = new ArrayList<Function>(4);
	ArrayList<Float> phases = new ArrayList<Float>();
	ArrayList<Button> prevButtons = new ArrayList<Button>();
	ArrayList<Button> nextButtons = new ArrayList<Button>();

	private Button backToMenuButton;
	
	private int waves;
	
	private float time = 60000 * 2;

	private Label scoreLabel;
	
	public GameScreen(final Puzzle puzzle, int waves, float time) {
		super(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		this.waves = waves;
		this.time = time;
		
		this.game = puzzle;
		stage = new Stage(viewport, batch);
		stage.clear();
		
		Gdx.input.setInputProcessor(stage);
		
		shape = new ShapeRenderer();
		
		shape.setProjectionMatrix(camera.combined);
		
		functions.add(new Sin((float) Math.PI / (Constants.TONE_WIDTH / 0.2f) * 2));
		functions.add(new Sin((float) Math.PI / (Constants.TONE_WIDTH / 0.3f) * 2));
		functions.add(new Sin((float) Math.PI / (Constants.TONE_WIDTH / 0.4f) * 2));
		functions.add(new Sin((float) Math.PI / (Constants.TONE_WIDTH / 0.5f) * 2));
		
		phases.add(0f);
		phases.add(0f);
		phases.add(0f);
		phases.add(0f);
		
		functionsAnswer.add(new Sin((float) Math.PI / (Constants.TONE_WIDTH / 0.2f) * 2));
		functionsAnswer.add(new Sin((float) Math.PI / (Constants.TONE_WIDTH / 0.3f) * 2));
		functionsAnswer.add(new Sin((float) Math.PI / (Constants.TONE_WIDTH / 0.4f) * 2));
		functionsAnswer.add(new Sin((float) Math.PI / (Constants.TONE_WIDTH / 0.5f) * 2));
		
		skin = new Skin();
		skin.addRegions(Assets.uiAtlas);
		
		Button.ButtonStyle nextButtonStyle = new Button.ButtonStyle(skin.getDrawable(Assets.UI_BUTTON_NEXT_DOWN), skin.getDrawable(Assets.UI_BUTTON_NEXT_UP), skin.getDrawable(Assets.UI_BUTTON_NEXT_DOWN));
		Button.ButtonStyle prevButtonStyle = new Button.ButtonStyle(skin.getDrawable(Assets.UI_BUTTON_PREV_DOWN), skin.getDrawable(Assets.UI_BUTTON_PREV_UP), skin.getDrawable(Assets.UI_BUTTON_PREV_DOWN));
		
		do {		
			for(int i = 0; i < waves; i++) {
				int rand = this.randomNumber(0, 5);
				phases.set(i, rand * Constants.PHASE_INCREMENT);
			}			
		} while(checkAnswer());
		
		for(int i = 0; i < waves; i++){			
			Button prev = new Button(prevButtonStyle);
			Button next = new Button(nextButtonStyle);
			
			prev.setPosition(38, 567 - 118 * i);
			next.setPosition(617, 567 - 118 * i);
			
			prev.addListener(inputPrevListener);
			next.addListener(inputNextListener);
			
			prevButtons.add(prev);
			nextButtons.add(next);
			
			stage.addActor(prev);
			stage.addActor(next);
		}
		
		Button.ButtonStyle backToMenuButtonStyle = new Button.ButtonStyle(skin.getDrawable(Assets.UI_BUTTON_BACK_TO_MENU_UP), skin.getDrawable(Assets.UI_BUTTON_BACK_TO_MENU_DOWN), skin.getDrawable(Assets.UI_BUTTON_BACK_TO_MENU_DOWN));
		
		backToMenuButton = new Button(backToMenuButtonStyle);
		
		backToMenuButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				GameScreen.this.game.setScreen(new MenuScreen(game));
			}
		});
		
		stage.addActor(backToMenuButton);
		
		Label.LabelStyle scoreLabelStyle = new Label.LabelStyle(Assets.font, Color.WHITE);

		scoreLabel = new Label("TIME 00:00", scoreLabelStyle);
		stage.addActor(scoreLabel);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255f / 255f, 255f / 255f, 255f / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.stage.act(delta);
		
		backToMenuButton.setPosition(Constants.SCREEN_WIDTH - backToMenuButton.getWidth() - 40, 30);
		
		this.batch.begin();
		this.batch.draw(Assets.bgGame, 0, 0);
		this.batch.end();
		
		this.stage.draw();	
		
		this.shape.begin(ShapeType.Line);
		this.shape.setColor(1, 1, 1, 1);
		
		int width = Constants.TONE_WIDTH;
		int height = Constants.TONE_HEIGHT;
		
		scoreLabel.setPosition(25, 1180);
		
		time -= Gdx.graphics.getDeltaTime() * 1000;
		
		scoreLabel.setText(String.format("TIME %02d:%02d", TimeUnit.MILLISECONDS.toMinutes((int) time), TimeUnit.MILLISECONDS.toSeconds((int) time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((int) time))));
		
		float widthResult = Constants.SCREEN_WIDTH;
		
		float initXGraph1 = 148;
		float initYGraph1 = 603;
		
		float initYGraphResult = 893;
		
		float distance = 118;
		
		float totalCurrentY, totalNextY, totalCurrentYAnswer, totalNextYAnswer;
		
		for(int x = 0; x < width - 1; x++) {
			totalCurrentY = 0;
			totalNextY = 0;
			totalCurrentYAnswer = 0;
			totalNextYAnswer = 0;
			
			for(int i = 0; i < waves; i++) {
				Function function = functions.get(i);
				
				function.calculate(x, phases.get(i));
				totalCurrentY += function.getCurrentY();
				totalNextY += function.getNextY();
				this.shape.setColor(1, 1, 1, 1);
				this.shape.line(initXGraph1 + function.getCurrentX(), initYGraph1 + function.getCurrentY() * height - i * distance, initXGraph1 + function.getNextX(), initYGraph1 + function.getNextY() * height - i * distance);
				
				Function functionAnswer = functionsAnswer.get(i);
				functionAnswer.calculate(x, 0);
				totalCurrentYAnswer += functionAnswer.getCurrentY();
				totalNextYAnswer += functionAnswer.getNextY();
			}
			
			this.shape.setColor(0.5f, 0.5f, 0.5f, 1);
			this.shape.line(x * (widthResult/width), initYGraphResult + totalCurrentY * height, (x + 1) * (widthResult/width) , initYGraphResult + totalNextY * height);
			
			this.shape.setColor(1f, 0f, 0f, 1);
			this.shape.line(x * (widthResult/width), initYGraphResult + totalCurrentYAnswer * height, (x + 1) * (widthResult/width) , initYGraphResult + totalNextYAnswer * height);
		}
		
		this.shape.end();
		
		if(time < 0) {
			this.game.setScreen(new GameOverScreen(game));
		}
	}
	
	private boolean checkAnswer() {
		boolean equals = true;
		
		for (int i = 0; i < waves; i++) {
			equals = equals && functionsAnswer.get(i).equal(functions.get(i), phases.get(i));
		}
		
		return equals;
	}
	
	private void checkNextStage() {
		if(this.checkAnswer()) {
			if(waves < 4) {
				this.game.setScreen(new NextStageScreen(game, waves, time));	
			} else {
				this.game.setScreen(new WinnerScreen(game));
			}
		}
	}
	
	private int randomNumber(int minimum, int maximum) {
		return minimum + (int)(Math.random() * maximum); 
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
	
	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
		skin.dispose();
		shape.dispose();
	}
}
