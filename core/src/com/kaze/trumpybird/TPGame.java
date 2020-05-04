package com.kaze.trumpybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kaze.screens.GameScreen;
import com.kaze.tbHelpers.AssetLoader;

public class TPGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("ZBGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());

	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
