package com.spiderRico.mygdxgame.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.spiderRico.mygdxgame.Screens.GameScreen;

public abstract class Object {

	public Vector2 position;
	public Vector2 rotation;
	public Vector2 velocity;

	public Object(float x, float y) {
		this.position = new Vector2(x, y);
		this.rotation = new Vector2();
		this.velocity = new Vector2();
	}

	public void update() {
		position.add(velocity); // vektör formatýnda toplama yapýyor
		if (position.x <= 0 - 71/2)
			position.x = 0 - 71/2;
		else if (position.x >= 800 -  71)
			position.x = 800 - 71; 
		if (position.y <= 0 - 71/2)
			position.y = 0 - 71/2;
		else if (position.y >= 480 - 71/2)
			position.y = 480 - 71/2;

	}



	public void rotate(float x, float y) {
		rotation.set(position);
		rotation.sub(x, y);

	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public void start(Player player, int screenX, int screenY) {

	}
	public Vector2 getRotation() {
		return rotation;
	}

	public void setRotation(Vector2 rotation) {
		this.rotation = rotation;
	}

}
