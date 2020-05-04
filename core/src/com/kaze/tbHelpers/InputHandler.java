package com.kaze.tbHelpers;

import com.badlogic.gdx.InputProcessor;
import com.kaze.gameobjects.Pajaro;
import com.kaze.monjoc.MonJoc;

public class InputHandler implements InputProcessor {
	private Pajaro trmp;
	MonJoc mon;
	
	public InputHandler(MonJoc mon){
		this.mon = mon;
		this.trmp = mon.getBird();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		 if (mon.isReady()) {
			 mon.start();
	        }

	        trmp.onClick();

	        if (mon.isGameOver()) {
	        	mon.restart();
	        }

	        return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
