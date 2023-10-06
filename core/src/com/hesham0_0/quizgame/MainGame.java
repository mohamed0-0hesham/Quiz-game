package com.hesham0_0.quizgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hesham0_0.quizgame.models.Button;

import java.util.ArrayList;
import java.util.List;

public class MainGame extends ApplicationAdapter implements InputProcessor {
	private OrthographicCamera camera;
	private final float virtualWidth = 720;
	private float virtualHeight = 1280;
	float aspectRation=1;
	private SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	GameScreen gameScreen = GameScreen.START_SCREEN;
	Texture img;
	private final List<Button> buttons = new ArrayList<>();

	@Override
	public void create () {
		Box2D.init();
		batch = new SpriteBatch();
		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();
		aspectRation =(float) Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight();
		virtualHeight = virtualWidth / aspectRation;

		camera = new OrthographicCamera(virtualWidth, virtualHeight);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
		Viewport viewport = new FitViewport(virtualWidth, virtualHeight, camera);
		viewport.apply();
		batch.setProjectionMatrix(camera.combined);

		batch = new SpriteBatch();
		int buttonWidth = Gdx.graphics.getWidth() * 4 / 5;
		int buttonHeight = Gdx.graphics.getHeight() / 16;
		buttons.add(new Button(virtualWidth / 2, 150, buttonWidth, buttonHeight, "a"));
		buttons.add(new Button(virtualWidth / 2, 300, buttonWidth, buttonHeight, "b"));
		buttons.add(new Button(virtualWidth / 2, 450, buttonWidth, buttonHeight, "c"));
		buttons.add(new Button(virtualWidth / 2, 600, buttonWidth, buttonHeight, "d"));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.25f, 0.25f, 1f, 1);
		batch.begin();
		for (Button button: buttons) {
			button.draw(batch,1);
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// Update the viewport when the screen is resized
		camera.viewportWidth = virtualWidth;
		camera.viewportHeight = virtualHeight;
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
		Viewport viewport = new FitViewport(virtualWidth, virtualHeight, camera);
		viewport.update(width, height);
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
		float worldX = worldCoordinates.x;
		float worldY = worldCoordinates.y;
		for (Button btn:buttons) {
			if (btn.isClicked(worldX,worldY)){
				btn.setLabel("isClicked");
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
