package com.kaze.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Tuberia extends Scrollable {
	private Random r;
	private boolean isScored = false;

	private Rectangle skullUp, skullDown, barUp, barDown;
	private float groundY;
	public static final int VERTICAL_GAP = 45;
	public static final int SKULL_WIDTH = 24;
	public static final int SKULL_HEIGHT = 11;

	public Tuberia(float x, float y, int width, int height, float scrollSpeed, float groundY) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		skullUp = new Rectangle();
		skullDown = new Rectangle();
		barUp = new Rectangle();
		barDown = new Rectangle();
		this.groundY = groundY;
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		barUp.set(position.x, position.y, width, height);
		barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
				groundY - (position.y + height + VERTICAL_GAP));
		skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height - SKULL_HEIGHT, SKULL_WIDTH,
				SKULL_HEIGHT);
		skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y, SKULL_WIDTH, SKULL_HEIGHT);

	}

	public boolean collides(Pajaro bird) {
		if (position.x < bird.getX() + bird.getWidth()) {
			return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
					|| Intersector.overlaps(bird.getBoundingCircle(), barDown)
					|| Intersector.overlaps(bird.getBoundingCircle(), skullUp)
					|| Intersector.overlaps(bird.getBoundingCircle(), skullDown));
		}
		return false;
	}

	@Override
	public void reset(float newX) {
		super.reset(newX);
		height = r.nextInt(90) + 15;
	    isScored = false;

	}

	public Rectangle getSkullUp() {
		return skullUp;
	}
	
	public void onRestart(float x, float scrollSpeed) {
	    velocity.x = scrollSpeed;
	    reset(x);
	}
	
	public boolean isScored() {
	    return isScored;
	}

	public void setScored(boolean b) {
	    isScored = b;
	}

	public Rectangle getSkullDown() {
		return skullDown;
	}

	public Rectangle getBarUp() {
		return barUp;
	}

	public Rectangle getBarDown() {
		return barDown;
	}
}
