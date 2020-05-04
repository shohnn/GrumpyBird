package com.kaze.gameobjects;

import com.kaze.monjoc.MonJoc;
import com.kaze.tbHelpers.AssetLoader;

public class ScrollHandler {
	private Cesped frontGrass, backGrass;
	private Tuberia tuberia1, tuberia2, tuberia3;
	private MonJoc monjoc;

	public static final int SCROLL_SPEED = -30;//-59
	public static final int PIPE_GAP = 49;

	public ScrollHandler(MonJoc monjoc, float yPos) {
		this.monjoc = monjoc;
		frontGrass = new Cesped(0, yPos, 143, 11, SCROLL_SPEED);
		backGrass = new Cesped(frontGrass.getTailX(), yPos, 143, 11, SCROLL_SPEED);

		tuberia1 = new Tuberia(210, 0, 22, 60, SCROLL_SPEED, yPos);
		tuberia2 = new Tuberia(tuberia1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED, yPos);
		tuberia3 = new Tuberia(tuberia2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED, yPos);
	}

	public void update(float delta) {
		// Update our objects
		frontGrass.update(delta);
		backGrass.update(delta);
		tuberia1.update(delta);
		tuberia2.update(delta);
		tuberia3.update(delta);

		// Check if any of the pipes are scrolled left,
		// and reset accordingly
		if (tuberia1.isScrolledLeft()) {
			tuberia1.reset(tuberia3.getTailX() + PIPE_GAP);
		} else if (tuberia2.isScrolledLeft()) {
			tuberia2.reset(tuberia1.getTailX() + PIPE_GAP);

		} else if (tuberia3.isScrolledLeft()) {
			tuberia3.reset(tuberia2.getTailX() + PIPE_GAP);
		}

		// Same with grass
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());

		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());

		}
	}

	public void stop() {
		frontGrass.stop();
		backGrass.stop();
		tuberia1.stop();
		tuberia2.stop();
		tuberia3.stop();
	}

	public boolean collides(Pajaro bird) {
		if (!tuberia1.isScored() && tuberia1.getX() + (tuberia1.getWidth() / 2) < bird.getX() + bird.getWidth()) {
			addScore(1);
			tuberia1.setScored(true);
			AssetLoader.coin.play();
		} else if (!tuberia2.isScored()
				&& tuberia2.getX() + (tuberia2.getWidth() / 2) < bird.getX() + bird.getWidth()) {
			addScore(1);
			tuberia2.setScored(true);
			AssetLoader.coin.play();

		} else if (!tuberia3.isScored()
				&& tuberia3.getX() + (tuberia3.getWidth() / 2) < bird.getX() + bird.getWidth()) {
			addScore(1);
			tuberia3.setScored(true);
			AssetLoader.coin.play();

		}

		return (tuberia1.collides(bird) || tuberia2.collides(bird) || tuberia3.collides(bird));
	}

	private void addScore(int increment) {
		monjoc.addScore(increment);
	}

	public void onRestart() {
		frontGrass.onRestart(0, SCROLL_SPEED);
		backGrass.onRestart(frontGrass.getTailX(), SCROLL_SPEED);
		tuberia1.onRestart(210, SCROLL_SPEED);
		tuberia2.onRestart(tuberia1.getTailX() + PIPE_GAP, SCROLL_SPEED);
		tuberia3.onRestart(tuberia2.getTailX() + PIPE_GAP, SCROLL_SPEED);
	}

	public Cesped getFrontGrass() {
		return frontGrass;
	}

	public Cesped getBackGrass() {
		return backGrass;
	}

	public Tuberia getPipe1() {
		return tuberia1;
	}

	public Tuberia getPipe2() {
		return tuberia2;
	}

	public Tuberia getPipe3() {
		return tuberia3;
	}
}
