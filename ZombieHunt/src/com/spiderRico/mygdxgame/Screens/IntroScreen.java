package com.spiderRico.mygdxgame.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.spiderRico.mygdxgame.ZombieHunt;

public class IntroScreen implements Screen {

        final ZombieHunt game;
        private Texture bgTexture;

        OrthographicCamera camera;

        public IntroScreen(final ZombieHunt game) {
                this.game = game;
                camera = new OrthographicCamera();
                camera.setToOrtho(false, 800, 480);
                bgTexture = new Texture(Gdx.files.internal("data/StartScreen.png"));
        }

        @Override
        public void render(float delta) {
                Gdx.gl.glClearColor(0, 0, 0.2f, 1);
                Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();
                game.batch.draw(bgTexture, 0, 0);
                /*game.font.draw(game.batch, "Use Left Stick to Move ", 100, 400);
                game.font.draw(game.batch, "Use Right Stick to Rotate", 100, 300);
                game.font.draw(game.batch, "Touch anywhere on the screen to shoot", 100, 350);*/
                
                game.batch.end();

                if (Gdx.input.isTouched()) {
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


        //...Rest of class omitted for succinctness.

}