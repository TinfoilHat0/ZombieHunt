package com.spiderRico.mygdxgame;



import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spiderRico.mygdxgame.Screens.GameScreen;
import com.spiderRico.mygdxgame.Screens.IntroScreen;

public class ZombieHunt extends Game {
	private FPSLogger fps;
	public BitmapFont font;
	// SpriteBatch will be handling drawing stuff to the screen.
	// It's quite a heavy object so we really want to have only 1 of them,
	// which we can pass later to the screens.
	public SpriteBatch batch;
	
	@Override
	public void create() {		
		fps = new FPSLogger();
		batch = new SpriteBatch();
		font = new BitmapFont();
        this.setScreen(new IntroScreen(this));
		
	}
	
	public SpriteBatch getBatch() { return batch; }

	@Override
	public void dispose() {
		// remember dispose the current screen
		getScreen().dispose();
		
		batch.dispose();
		super.dispose();
		
		// You can use this function to print stuff to the console. 
		// It's very useful to use to track what's happening in your game.
		Gdx.app.log("AngryShooter", "Disposed");
	}

	@Override
	public void render() {		
		super.render();
		//fps.log();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}


