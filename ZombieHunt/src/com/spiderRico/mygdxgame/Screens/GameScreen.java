package com.spiderRico.mygdxgame.Screens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.spiderRico.mygdxgame.ZombieHunt;
import com.spiderRico.mygdxgame.Logic.InputHandler;
import com.spiderRico.mygdxgame.Objects.Bullet;
import com.spiderRico.mygdxgame.Objects.Player;
import com.spiderRico.mygdxgame.Objects.Zombie;

public class GameScreen implements Screen {
	private final int NUM_ZOMBIE = 10;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Texture bgTexture;
	private Texture playerTexture;
	private Texture bloodTexture;
	private Sprite playerSprite;
	private Music bgMusic;
	private Sound evilSound;

	private Player player;

	private Texture bulletTexture;
	private ArrayList<Bullet> bullets;
	private Iterator<Bullet> bulletIterator;
	private Bullet b;

	private Zombie[] zombies = new Zombie[NUM_ZOMBIE];

	private Rectangle bulletBounds;
	private Rectangle zombieBounds;

	private Stage stage;
	private Touchpad touchpad;
	private Touchpad touchpad2;
	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;
	private float degrees = 0;
	private Circle c = new Circle();
	private BitmapFont font;
	private ZombieHunt game;
	public GameScreen(ZombieHunt game) {
		playerTexture = new Texture(
				Gdx.files.internal("data/citizenplayer_handgun.png"));
		playerSprite = new Sprite(playerTexture);
		bulletTexture = new Texture(Gdx.files.internal("data/bullet.png"));
		bgTexture = new Texture(Gdx.files.internal("data/background.png"));
		bloodTexture = new Texture(Gdx.files.internal("data/blood.png"));
		// Set the filters so that when we rotate our textures, they wont look
		playerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bulletTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		evilSound = Gdx.audio.newSound(Gdx.files.internal("data/evil.wav"));
		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("data/bgMusic.ogg"));
		font = new BitmapFont();
		this.game = game;
		bgMusic.setVolume(1f);
		bgMusic.play();
		evilSound.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = game.getBatch();

		player = new Player(800 / 2, 480 / 2);
		bullets = new ArrayList<Bullet>();
		playerSprite.setPosition(player.getPosition().x / 2,
				player.getPosition().y / 2);

		for (int i = 0; i < NUM_ZOMBIE; i++) {
			Random x = new Random();
			int roll2 = x.nextInt(4);
			if (roll2 == 0) {
				zombies[i] = new Zombie(0, x.nextInt(480));

			} else if (roll2 == 1) {
				zombies[i] = new Zombie(x.nextInt(800), 480);

			} else if (roll2 == 2) {
				zombies[i] = new Zombie(800, x.nextInt(480));

			} else if (roll2 == 3) {
				zombies[i] = new Zombie(x.nextInt(480), 0);

			}

			touchpadSkin = new Skin();
			touchpadSkin.add("touchBackground", new Texture(
					"data/touchBackground.png"));
			touchpadSkin.add("touchKnob", new Texture("data/touchKnob.png"));
			touchpadStyle = new TouchpadStyle();
			touchBackground = touchpadSkin.getDrawable("touchBackground");
			touchKnob = touchpadSkin.getDrawable("touchKnob");
			touchpadStyle.background = touchBackground;
			touchpadStyle.knob = touchKnob;
			touchpad = new Touchpad(10, touchpadStyle);
			touchpad2 = new Touchpad(10, touchpadStyle);
			touchpad.setBounds(15, 40, 200, 200);
			touchpad2.setBounds(600, 40, 200, 200);
			stage = new Stage(800, 480, true, batch);
			stage.addActor(touchpad);
			stage.addActor(touchpad2);

			InputMultiplexer multiplexer = new InputMultiplexer();
			multiplexer.addProcessor(stage);
			multiplexer.addProcessor(new InputHandler(player, camera, bullets));
			Gdx.input.setInputProcessor(multiplexer);

			// Create the collision boxes
			bulletBounds = new Rectangle();
			zombieBounds = new Rectangle();
			c.set(player.position.x, player.position.y, 32);
		}
	}

	@Override
	public void render(float delta) {
		// Clear the screen.
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();
		player.setTime(player.getTime() + 1);
		degrees = (float) Math.atan2((touchpad2.getKnobPercentY()),
				touchpad2.getKnobPercentX());
		if (degrees != 0)
			player.setDegree(degrees);
		// Move blockSprite with TouchPad
		player.position.x += touchpad.getKnobPercentX() * player.getSpeed();
		player.position.y += touchpad.getKnobPercentY() * player.getSpeed();
		player.update();

		// Draw stuff to the screen.
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bgTexture, 0, 0);

		/*
		 * batch.draw(crossTexture, player.position.x + c.radius*-(float)
		 * Math.cos(Math.toDegrees(player.getDegree())+5* Math.PI / 180f),
		 * player.position.y + c.radius*-(float)
		 * Math.sin(Math.toDegrees(player.getDegree())+5* Math.PI / 180f));
		 */
		for (int i = 0; i < NUM_ZOMBIE; i++) {
			if (zombies[i].isAlive() == false) {
				batch.draw(bloodTexture, zombies[i].position.x,
						zombies[i].position.y);
			}
		}
		// Draw&Update the zombies
		for (int i = 0; i < NUM_ZOMBIE; i++) {
			if (zombies[i].isAlive() == true) {
				zombies[i].update(player.position, player);
				if (player.getHealth() <= 0) {
					game.setScreen(new EndScreen(game, player));
					bgMusic.stop();
				}
				batch.draw(zombies[i].getZombieRegion(), zombies[i]
						.getPosition().x, zombies[i].getPosition().y,
						zombies[i].getZombieTexture().getWidth() / 2,
						zombies[i].getZombieTexture().getHeight() / 2,
						zombies[i].getZombieTexture().getWidth(), zombies[i]
								.getZombieTexture().getHeight(), 1, 1,
						zombies[i].rotation.angle());

			} else if (zombies[i].isAlive() == false) {
				Random r = new Random();
				if (r.nextInt(500) == 0) {
					zombies[i].setAlive(true);
					zombies[i].setHealth(20);
					int roll = r.nextInt(4);
					if (roll == 0) {
						zombies[i].position.x = 0;
						zombies[i].position.y = r.nextInt(480);
					} else if (roll == 1) {
						zombies[i].position.x = 800;
						zombies[i].position.y = r.nextInt(480);
					} else if (roll == 2) {
						zombies[i].position.x = r.nextInt(800);
						zombies[i].position.y = 0;
					} else if (roll == 3) {
						zombies[i].position.x = r.nextInt(800);
						zombies[i].position.y = 480;
					}
				}
			}
		}

		for (bulletIterator = bullets.iterator(); bulletIterator.hasNext();) {
			b = bulletIterator.next();

			// Update its state
			b.update();

			// Draw the bullet
			batch.draw(bulletTexture, b.position.x, b.position.y);
			// Delete the bullet if it's too far
			if (b.position.x > 800 || b.position.x < 0 || b.position.y > 480
					|| b.position.y < 0) {
				bulletIterator.remove();

				// The current, deleted bullet isn't going to hit any zombies so
				// move on to the next one
				break;
			}
		}

		checkCollisions();
		playerSprite.setPosition(player.position.x, player.position.y);
		playerSprite.setRotation(((float) Math.toDegrees(player.getDegree())));
		playerSprite.draw(batch);
		font.draw(batch, "Score:" + player.getScore(), 20, 470);
		font.draw(batch, "Health:" + (int) player.getHealth(), 80, 470);
		font.draw(batch, "Time:" + player.getTime()/60, 740, 470);
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		playerTexture.dispose();
		bulletTexture.dispose();
		bgTexture.dispose();
		Gdx.app.log("GameScreen", "Disposed");
	}

	public void checkCollisions() {

		// check for collisions
		for (bulletIterator = bullets.iterator(); bulletIterator.hasNext();) {
			b = bulletIterator.next();

			for (int i = 0; i < NUM_ZOMBIE; i++) {
				// Update the rectangles
				if (zombies[i].isAlive() == true) {
					bulletBounds
							.set(b.position.x, b.position.y,
									bulletTexture.getWidth(),
									bulletTexture.getHeight());
					zombieBounds.set(zombies[i].position.x,
							zombies[i].position.y, zombies[i]
									.getZombieTexture().getWidth(), zombies[i]
									.getZombieTexture().getHeight());

					// Check for collisions
					if (bulletBounds.overlaps(zombieBounds)) {
						// An collision has happened, remove both the bullet and
						// the
						// zombie
						bulletIterator.remove();
						zombies[i].setHealth(zombies[i].getHealth() - 10);
						if (zombies[i].getHealth() <= 0) {
							player.setScore(player.getScore() + 1);
							zombies[i].setAlive(false);
						}
						break;
					}
				}

			}
		}
	}
}
