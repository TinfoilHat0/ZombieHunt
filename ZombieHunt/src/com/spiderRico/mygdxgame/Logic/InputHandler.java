package com.spiderRico.mygdxgame.Logic;

import java.util.ArrayList;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.spiderRico.mygdxgame.Objects.Bullet;
import com.spiderRico.mygdxgame.Objects.Player;

public class InputHandler implements InputProcessor {
	// We want a direct access to the player object so let's fetch it from the GameScreen
	Player player;
	float degrees;
	// This vector is needed for rotating the player
	Vector3 mouseposition;
	private Sound shotSound;
	// This vector is needed for firing the bullet towards the right direction
	Vector3 bulletposition;
	
	// This circle is needed to set the right spot for the bullet
	Circle c = new Circle();

	// We also need the camera to convert from window coordinates to world space
	OrthographicCamera camera;
	// This time we don't need an iterator since we won't be going through the bullets in this class
	ArrayList< Bullet > bullets;
	
	
	public InputHandler(Player player, OrthographicCamera camera, ArrayList< Bullet > bullets) {
		this.player = player;
		this.camera = camera;
		this.bullets = bullets;
		
		shotSound = Gdx.audio.newSound(Gdx.files.internal("data/GunFire.wav"));
		mouseposition = new Vector3();
		bulletposition = new Vector3();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		// Let's move our player
		switch (keycode) {
		case Keys.W:
			player.velocity.y = -2;
			break;
		case Keys.A:
			player.velocity.x = -2;
			break;
		case Keys.S:
			player.velocity.y = 2;
			break;
		case Keys.D:
			player.velocity.x = 2;
			break;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		// Let's stop our player
		switch (keycode) {
		case Keys.W:
			// We don't want a laggy movement, hence the if statements
			// You can try taking off them and see what happens
			if (player.velocity.y < 0) player.velocity.y = 0;
			break;
		case Keys.S:
			if (player.velocity.y > 0) player.velocity.y = 0;
			break;
		case Keys.A:
			if (player.velocity.x < 0) player.velocity.x = 0;
			break;
		case Keys.D:
			if (player.velocity.x > 0) player.velocity.x = 0;
			break;
		}
		
		return false;
	}

	// Let's move this method up here where we can see it easier
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		/*mouseposition.set(screenX, screenY, 0);
		camera.unproject(mouseposition);
		player.rotate(mouseposition.x, mouseposition.y);*/
		
		return false;
	}
	
	@Override
	
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	
		// Change coordinates from window to world 
		camera.unproject(bulletposition);
		// You might have to tweak these numbers depending on the size and position of your player in your texture
		c.set(player.position.x , player.position.y, 32);
		float[] radius = getRadius(c.radius, (float)( Math.toDegrees(player.getDegree()) - 3f));
		bullets.add(new Bullet(player.position.x + radius[0] + 30,player.position.y + radius[1] + 31, new Vector2(radius[0], radius[1])));
		shotSound.play(0.1f);
		
		return false;
	}
	
	// This method will figure out the right position for the bullet
	public float[] getRadius(float radius, float rotation) {
		float posX = radius * (float) Math.cos(rotation * Math.PI / 180f);
		float posY = radius * (float) Math.sin(rotation * Math.PI / 180f);
		
		return new float[] { posX, posY };
	}
	
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}