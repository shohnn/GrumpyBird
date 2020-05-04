package com.kaze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kaze.monjoc.GameRenderer;
import com.kaze.monjoc.MonJoc;
import com.kaze.tbHelpers.InputHandler;

public class GameScreen implements Screen {
	
	private MonJoc mon;
	private GameRenderer renderer;
	private float runTime = 0;
	
	public GameScreen(){
        Gdx.app.log("GameScreen", "Attached");
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int)(gameHeight / 2);
        mon = new MonJoc(midPointY);
        renderer = new GameRenderer(mon, (int) gameHeight, midPointY);
        
        Gdx.input.setInputProcessor(new InputHandler(mon));
        
	}
	@Override
	public void show() {
        Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void render(float delta) {
		   runTime += delta;
		   mon.update(delta);
		   renderer.render(runTime);
        
	}

	@Override
	public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
		
	}

	@Override
	public void pause() {
        Gdx.app.log("GameScreen", "pause called");        
		
	}

	@Override
	public void resume() {
        Gdx.app.log("GameScreen", "resume called");       
		
	}

	@Override
	public void hide() {
        Gdx.app.log("GameScreen", "hide called");     
		
	}

	@Override
	public void dispose() {
		
	}

}
