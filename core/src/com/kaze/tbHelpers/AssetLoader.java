package com.kaze.tbHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture;
    public static TextureRegion bg, grass;
    public static Sound dead;
    public static Sound flap;
    public static Sound coin;

    public static Animation birdAnimation;
    public static TextureRegion bird, birdDown, birdUp;
    public static BitmapFont font, shadow;
    public static TextureRegion skullUp, skullDown, bar;

    public static void load() {
    	dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
    	flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
    	coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
    	font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
    	font.getData().setScale(.25f, -.25f);
    	shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
    	shadow.getData().setScale(.25f, -.25f);
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        TextureRegion[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
        dead.dispose();
        flap.dispose();
        coin.dispose();
        font.dispose();
        shadow.dispose();
    }
}
