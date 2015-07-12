package com.spiderRico.mygdxgame.Objects;

public class Player extends Object{
	private float speed;
	private int score = 0;
	private double health;
	private float degree;
	private int time;
	
	public Player(float x, float y) {
		super(x, y);
		this.health = 100;
		this.speed = 3;
		this.score = 0;
		this.time = 0;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getDegree() {
		return degree;
	}

	public void setDegree(float degree) {
		this.degree = degree;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double d) {
		this.health = d;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	
	

}
