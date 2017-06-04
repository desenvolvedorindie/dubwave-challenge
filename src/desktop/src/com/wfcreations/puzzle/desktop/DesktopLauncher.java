package com.wfcreations.puzzle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.wfcreations.puzzle.Puzzle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 405;
		config.height = 720;
		config.resizable = false;
		config.useGL30 = false;
		config.title = "Dub Wave Challenge";
		new LwjglApplication(new Puzzle(), config);
	}
}
