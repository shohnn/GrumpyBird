package com.kaze.monjoc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.kaze.gameobjects.Cesped;
import com.kaze.gameobjects.Pajaro;
import com.kaze.gameobjects.ScrollHandler;
import com.kaze.gameobjects.Tuberia;
import com.kaze.tbHelpers.AssetLoader;

public class GameRenderer {
	private MonJoc mon;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;
	private int gameHeight;

	private Pajaro pajaro;

	private ScrollHandler scroller;
	private Cesped frontGrass, backGrass;
	private Tuberia pipe1, pipe2, pipe3;

	private TextureRegion bg, grass;
	private Animation birdAnimation;
	private TextureRegion birdMid, birdDown, birdUp;
	private TextureRegion skullUp, skullDown, bar;

	public GameRenderer(MonJoc mon, int gameHeight, int midPointY) {
		this.mon = mon;
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 136, gameHeight);
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(camera.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		initGameObjects();
		initAssets();
	}

	public void render(float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeType.Filled);
		// Dibujar el color de fondo
		shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY + 66);
		// Dibujar el cesped
		shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 66, 136, 11);
		// Dibujar misc
		shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 77, 136, 52);
		shapeRenderer.end();
		batcher.begin();
		batcher.disableBlending();
		batcher.draw(bg, 0, midPointY + 23, 136, 43);

		drawGrass();
		drawPipes();

		batcher.enableBlending();
		drawSkulls();

		if (pajaro.shouldntFlap()) {
			batcher.draw(birdMid, pajaro.getX(), pajaro.getY(), pajaro.getWidth() / 2.0f, pajaro.getHeight() / 2.0f,
					pajaro.getWidth(), pajaro.getHeight(), 1, 1, pajaro.getRotation());

		} else {
			batcher.draw(birdAnimation.getKeyFrame(runTime), pajaro.getX(), pajaro.getY(), pajaro.getWidth() / 2.0f,
					pajaro.getHeight() / 2.0f, pajaro.getWidth(), pajaro.getHeight(), 1, 1, pajaro.getRotation());
		}

		if (mon.isReady()) {
			AssetLoader.shadow.draw(batcher, "   Tocar", (136 / 2) - (42), 76);
			AssetLoader.font.draw(batcher, "   Tocar", (136 / 2) - (42 - 1), 75);
		} else {

			if (mon.isGameOver()) {
				AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
				AssetLoader.font.draw(batcher, "Game Over", 24, 55);

				AssetLoader.shadow.draw(batcher, "Otra  vez?", 23, 76);
				AssetLoader.font.draw(batcher, "Otra  vez?", 24, 75);

			}
		}

		String score = mon.getScore() + "";
		AssetLoader.shadow.draw(batcher, "" + mon.getScore(), (136 / 2) - (3 * score.length()), 12);
		AssetLoader.font.draw(batcher, "" + mon.getScore(), (136 / 2) - (3 * score.length() - 1), 11);

		batcher.end();

	}

	private void initGameObjects() {
		pajaro = mon.getBird();
		scroller = mon.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		birdAnimation = AssetLoader.birdAnimation;
		birdMid = AssetLoader.bird;
		birdDown = AssetLoader.birdDown;
		birdUp = AssetLoader.birdUp;
		skullUp = AssetLoader.skullUp;
		skullDown = AssetLoader.skullDown;
		bar = AssetLoader.bar;
	}

	private void drawGrass() {
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
		batcher.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
	}

	private void drawSkulls() {

		batcher.draw(skullUp, pipe1.getX() - 1, pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
		batcher.draw(skullDown, pipe1.getX() - 1, pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

		batcher.draw(skullUp, pipe2.getX() - 1, pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
		batcher.draw(skullDown, pipe2.getX() - 1, pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

		batcher.draw(skullUp, pipe3.getX() - 1, pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
		batcher.draw(skullDown, pipe3.getX() - 1, pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
	}

	private void drawPipes() {
		batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(), pipe1.getHeight());
		batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45, pipe1.getWidth(),
				midPointY + 66 - (pipe1.getHeight() + 45));

		batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(), pipe2.getHeight());
		batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45, pipe2.getWidth(),
				midPointY + 66 - (pipe2.getHeight() + 45));

		batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(), pipe3.getHeight());
		batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45, pipe3.getWidth(),
				midPointY + 66 - (pipe3.getHeight() + 45));
	}

}
