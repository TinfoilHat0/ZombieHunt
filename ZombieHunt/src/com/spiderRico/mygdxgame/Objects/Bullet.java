package com.spiderRico.mygdxgame.Objects;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Bullet extends Object {


	public Bullet(float x, float y, Vector2 velocity) {
		super(x, y);
		this.velocity = velocity;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		
		position.add(velocity.x/3, velocity.y/3);
	}

}
