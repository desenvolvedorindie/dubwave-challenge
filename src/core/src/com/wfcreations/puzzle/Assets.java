package com.wfcreations.puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Assets {

	// UI
	public static final String ASSET_UI_PACK = "ui.atlas";
	
	//MUSIC
	public static final String ASSET_MUSIC_SOUNDTRACK = "music/soundtrack.ogg";
	
	//BG
	public static final String ASSET_BG_SPLASH = "bg/splash.png";
	public static final String ASSET_BG_GAME = "bg/game.png";
	public static final String ASSET_BG_MENU = "bg/menu.png";
	public static final String ASSET_BG_CREDITS = "bg/credits.png";
	public static final String ASSET_BG_GAMEOVER = "bg/gameover.png";
	public static final String ASSET_BG_WINNER = "bg/winner.png";
	public static final String ASSET_BG_NEXT_STAGE = "bg/next_stage.png";
	
	public static final String UI_BUTTON_NEXT_DOWN = "button_next_down";
	public static final String UI_BUTTON_NEXT_UP = "button_next_up";
	public static final String UI_BUTTON_PREV_DOWN = "button_prev_down";
	public static final String UI_BUTTON_PREV_UP = "button_prev_up";

	public static final String UI_BUTTON_PLAY_DOWN = "button_play_down";
	public static final String UI_BUTTON_PLAY_UP = "button_play_up";

	public static final String UI_BUTTON_CREDITS_DOWN = "button_credits_down";
	public static final String UI_BUTTON_CREDITS_UP = "button_credits_up";
	
	public static final String UI_BUTTON_NEXT_STAGE_DOWN = "button_next_stage_down";
	public static final String UI_BUTTON_NEXT_STAGE_UP = "button_next_stage_up";

	public static final String UI_BUTTON_BACK_TO_MENU_DOWN = "button_back_to_menu_down";
	public static final String UI_BUTTON_BACK_TO_MENU_UP = "button_back_to_menu_up";

	public static final String UI_BUTTON_MUSIC_OFF = "button_music_off";
	public static final String UI_BUTTON_MUSIC_ON = "button_music_on";

	public static final String UI_BUTTON_SOUND_OFF = "button_sound_off";
	public static final String UI_BUTTON_SOUND_ON = "button_sound_on";

	//Music
	public static Music musicTheme;
	
	public static final AssetManager assetManager = new AssetManager();

	private static final String ASSET_FONT_CORDELINA = "fonts/bebasneue.ttf";
	
	private static boolean initLoaded = false;
	private static boolean allLoaded = false;

	public static Texture bgSplash;
	public static Texture bgMenu;
	public static Texture bgGame;
	public static Texture bgCredits;
	public static Texture bgGameOver;
	public static Texture bgWinner;
	public static Texture bgNextStage;
	
	public static BitmapFont font;
	
	public static TextureAtlas uiAtlas;
	
	private static void loadBefore() {
		//Music
		assetManager.load(ASSET_MUSIC_SOUNDTRACK, Music.class);
		
		//BG
		assetManager.load(ASSET_BG_SPLASH, Texture.class);
		assetManager.load(ASSET_BG_MENU, Texture.class);
		assetManager.load(ASSET_BG_GAME, Texture.class);
		assetManager.load(ASSET_BG_CREDITS, Texture.class);
		assetManager.load(ASSET_BG_GAMEOVER, Texture.class);
		assetManager.load(ASSET_BG_NEXT_STAGE, Texture.class);
		assetManager.load(ASSET_BG_WINNER, Texture.class);
		
		assetManager.finishLoading();
		
		//Font
		FreeTypeFontGenerator cordelinaGenerator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.ASSET_FONT_CORDELINA));

		FreeTypeFontParameter parameterCordelina22 = new FreeTypeFontParameter();
		parameterCordelina22.size = 58;

		font = cordelinaGenerator.generateFont(parameterCordelina22);
		
		//PACKS
		uiAtlas = new TextureAtlas(Gdx.files.internal(ASSET_UI_PACK));
		
		//BG
		bgSplash = assetManager.get(ASSET_BG_SPLASH, Texture.class);
		bgMenu = assetManager.get(ASSET_BG_MENU, Texture.class);
		bgGame = assetManager.get(ASSET_BG_GAME, Texture.class);
		bgCredits = assetManager.get(ASSET_BG_CREDITS, Texture.class);
		bgGameOver = assetManager.get(ASSET_BG_GAMEOVER, Texture.class);
		bgNextStage = assetManager.get(ASSET_BG_NEXT_STAGE, Texture.class);
		bgWinner = assetManager.get(ASSET_BG_WINNER, Texture.class);
		
		//MUSIC
		musicTheme = assetManager.get(ASSET_MUSIC_SOUNDTRACK, Music.class);
		musicTheme.setLooping(true);
	}
	
	public static void loadAll() {
		if (!initLoaded) {
			loadBefore();
		}

		allLoaded = true;
	}

	public static float getProgress() {
		// Get the progress percentage from the asset manager
		float progress = assetManager.getProgress();
		assetManager.update();
		// Check if it is all loaded, then
		if (progress == 1 && allLoaded) {

		}

		return progress;
	}

	public static void destroy(boolean destroy) {
		assetManager.clear();

		if (destroy) {
			assetManager.dispose();
		}
	}
	
}
