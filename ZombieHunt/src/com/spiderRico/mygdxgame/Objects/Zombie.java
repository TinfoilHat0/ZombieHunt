package com.spiderRico.mygdxgame.Objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Zombie extends Object {
	private boolean alive = false;
	private Texture zombieTexture;
	private TextureRegion zombieRegion;
	private int health;
	private double damage;

	public Zombie(float x, float y) {
		super(x, y);
		this.alive = true;
		this.health = 20;
		this.damage = .5;
		Random r = new Random();
		int roll = r.nextInt(9);
		if (roll == 0) {
			this.zombieTexture = new Texture(
					Gdx.files.internal("data/citizenzombie1.png"));
		} else if (roll == 1) {
			this.zombieTexture = new Texture(
					Gdx.files.internal("data/citizenzombie2.png"));
		} else if (roll == 2) {
			this.zombieTexture = new Texture(
					Gdx.files.internal("data/citizenzombie3.png"));
		} else if (roll == 3) {
			this.zombieTexture = new Texture(
					Gdx.files.internal("data/citizenzombie4.png"));
		} else if (roll == 4) {
			this.zombieTexture = new Texture(
					Gdx.files.internal("data/citizenzombie5.png"));
		} else if (roll == 5) {
			this.zombieTexture = new Texture(
					Gdx.files.internal("data/citizenzombie6.png"));
		} else if (roll == 6) {
			this.zombieTexture = new Texture(
					Gdx.files.internal("data/citizenzombie7.png"));
		} else if (roll == 7) {
			this.zombieTexture = new Texture(
					Gdx.files.internal("data/citizenzombie8.png"));
		} else if (roll == 8) {
			this.zombieTexture = new Texture(
					Gdx.files.internal("data/citizenzombie9.png"));
		}
		zombieTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.zombieRegion = new TextureRegion(zombieTexture);
	}

	public void startZomb(Zombie zombie) {
		if (zombie.alive == false) {
			Random r = new Random();
			if (r.nextInt(500) == 0) {
				zombie.alive = true;
			}
		}
	}

	public void update(Vector2 playerPosition, Player player) {
		velocity.set(playerPosition.x - position.x,
				playerPosition.y - position.y).nor();
		position.add(velocity);
		rotate(playerPosition.x, playerPosition.y);
		rotation.rotate(180f).nor();
		if ( Math.sqrt(Math.pow(position.x - playerPosition.x, 2.0) + Math.pow(position.y -playerPosition.y, 2)) <=30){
			player.setHealth(player.getHealth() - damage);
		}
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Texture getZombieTexture() {
		return zombieTexture;
	}

	public void setZombieTexture(Texture zombieTexture) {
		this.zombieTexture = zombieTexture;
	}

	public TextureRegion getZombieRegion() {
		return zombieRegion;
	}

	public void setZombieRegion(TextureRegion zombieRegion) {
		this.zombieRegion = zombieRegion;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getDamage() {
		return damage;
	}
	
	

}
