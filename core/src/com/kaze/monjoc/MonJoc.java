package com.kaze.monjoc;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kaze.gameobjects.Pajaro;
import com.kaze.gameobjects.ScrollHandler;
import com.kaze.tbHelpers.AssetLoader;

public class MonJoc {
	private Pajaro bird;
	private ScrollHandler scroller;
	private Rectangle suelo;
	private int score = 0;
	private GameState currentState;
    private int midPointY;


	public MonJoc(int midPointY) {
		currentState = GameState.READY;
        this.midPointY = midPointY;

		bird = new Pajaro(33, midPointY - 5, 17, 12);
		scroller = new ScrollHandler(this, midPointY + 66);
		suelo = new Rectangle(0, midPointY + 66, 136, 11);

	}

	public enum GameState {

		READY, RUNNING, GAMEOVER

	}

	public void update(float delta) {

		switch (currentState) {
		case READY:
			updateReady(delta);
			break;

		case RUNNING:
		default:
			updateRunning(delta);
			break;
		}

	}

	private void updateReady(float delta) {
		
	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}
		bird.update(delta);
		scroller.update(delta);
		if (bird.isAlive() && scroller.collides(bird)) {
			// Clean up on game over
			scroller.stop();
			bird.die();
			AssetLoader.dead.play();
		}
		if (Intersector.overlaps(bird.getBoundingCircle(), suelo)
				&& currentState == GameState.RUNNING) {
			scroller.stop();
			bird.die();
			bird.decelerate();
			currentState = GameState.GAMEOVER;

		}

	}

	public Pajaro getBird() {
		return bird;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}
	public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

}
