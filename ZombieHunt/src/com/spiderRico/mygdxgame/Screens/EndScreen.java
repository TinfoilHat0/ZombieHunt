package com.spiderRico.mygdxgame.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.spiderRico.mygdxgame.ZombieHunt;
import com.spiderRico.mygdxgame.Objects.Player;

public class EndScreen implements Screen {

        final ZombieHunt game;
        private Texture bgTexture;
        Player player;
        OrthographicCamera camera;
        private Sound gameOverSound;
        private int timeCount;
        
        public EndScreen(final ZombieHunt game, Player player) {
                this.game = game;
                camera = new OrthographicCamera();
                camera.setToOrtho(false, 800, 480);
                this.player = player;
                this.timeCount = 0;
                bgTexture = new Texture(Gdx.files.internal("data/EndScreen.png"));
                gameOverSound =  Gdx.audio.newSound(Gdx.files.internal("data/GameOver.mp3"));
                gameOverSound.play(1);
        }

        @Override
        public void render(float delta) {
                Gdx.gl.glClearColor(0, 0, 0.2f, 1);
                Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
                
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);
                timeCount++;
                game.batch.begin();
                game.batch.draw(bgTexture, 0, 0);
                int x = (int)player.getTime()/60;
                game.font.draw(game.batch, " " +  player.getScore() * x , 550, 270);
                game.font.draw(game.batch, "( " + player.getScore() + " )", 280, 200);
                game.font.draw(game.batch, "( " + player.getTime()/60 + " )", 430, 200);
                
                game.batch.end();
                
                if (Gdx.input.isTouched() && timeCount/60 >= 2) {
                        game.setScreen(new GameScreen(game));
                        dispose();
                }
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
			// TODO Auto-generated method stub
			
		}
}