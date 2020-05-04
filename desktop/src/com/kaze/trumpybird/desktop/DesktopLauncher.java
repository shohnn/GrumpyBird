package com.kaze.trumpybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kaze.trumpybird.TPGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "TrumpyBird";
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new TPGame(), config);
	}
}
