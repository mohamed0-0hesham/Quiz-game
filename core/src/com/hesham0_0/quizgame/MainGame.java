package com.hesham0_0.quizgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hesham0_0.quizgame.models.Button;
import com.hesham0_0.quizgame.models.Question;
import com.hesham0_0.quizgame.models.QuestionBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainGame extends ApplicationAdapter implements InputProcessor {
	private OrthographicCamera camera;
	private final float virtualWidth = Utils.VIRTUAL_WIDTH;
	private float virtualHeight = Utils.VIRTUAL_HEIGHT;
	float aspectRation = 1;
	private SpriteBatch batch;
	private GameScreen gameScreen = GameScreen.START_SCREEN;
	private final List<Button> startButtons = new ArrayList<>();

	private final List<Button> vialsButtons = new ArrayList<>();
	private final List<Button> questionButtons = new ArrayList<>();
	private final List<Button> gameOverButtons = new ArrayList<>();
	private QuestionBody questionBody;
	private Texture background;
	private Question question;
	private Question lastQuestion;
	private int questionIndex = 0;
	private int score = 0;


	@Override
	public void create () {
		Box2D.init();
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		aspectRation =(float) Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight();
		virtualHeight = virtualWidth / aspectRation;
		camera = new OrthographicCamera(virtualWidth/2, virtualHeight/2);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
		Viewport viewport = new FitViewport(virtualWidth, virtualHeight, camera);
		viewport.apply();
		batch.setProjectionMatrix(camera.combined);
		batch = new SpriteBatch();
		background= new Texture("background.png");
		initToStartScreen();

	}

	private void updateQuestion(Question question){
		questionBody = new QuestionBody(
				virtualWidth / 2,
				virtualHeight * 3 / 4,
				virtualWidth * 11 / 12,
				virtualHeight  / 3,
				question.getQuestion());
		float buttonWidth = virtualWidth * 11 / 12;
		questionButtons.clear();

		for (int i = 0; i < Utils.positionsPortrait.length; i++) {
			String answer = question.getAnswers()[i];
			if (Gdx.graphics.getWidth() < Gdx.graphics.getHeight()) {
				questionButtons.add(new Button(
						Utils.positionsPortrait[i].x,
						Utils.positionsPortrait[i].y,
						buttonWidth,
						answer));
			} else {
				questionButtons.add(new Button(
						Utils.positionsLandscape[i].x,
						Utils.positionsLandscape[i].y,
						buttonWidth,
						answer)
				);
			}
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.9f, 0.9f, 0.9f, 1);
		batch.begin();
		batch.draw(background, 0, 0, virtualWidth, virtualHeight);
		batch.end();
		switch (gameScreen) {
			case START_SCREEN:
				renderStartScreen();
				break;
			case VIALS_SCREEN:
				renderVialsScreen();
				break;
			case PLAYING_SCREEN:
				renderPlayingScreen();
				break;
			case GAME_OVER_SCREEN:
				renderGameOverScreen();
				break;
		}
	}

	public void initToStartScreen() {
		startButtons.add(
				new Button(virtualWidth / 2,
						virtualHeight / 3,
						virtualWidth,
						"Start")
		);
	}

	public void initToVialsScreen() {
		for (int i = 0; i < Utils.vialsDrawable.size(); i++) {
			if (Gdx.graphics.getWidth() < Gdx.graphics.getHeight()) {
				vialsButtons.add(
						new Button(
								Utils.positionsPortrait[i].x,
								Utils.positionsPortrait[i].y,
								virtualWidth,
								Utils.vialsDrawable.get(i)
						)
				);
			} else {
				vialsButtons.add(
						new Button(
								Utils.positionsLandscape[i].x,
								Utils.positionsLandscape[i].y,
								virtualWidth,
								Utils.vialsDrawable.get(i)
						)
				);
			}
		}
	}

	public void initToPlayingScreen() {
		questionIndex = 0;
		score = 0;
		question = Utils.questions.get(questionIndex);
	}

	public void initToGameOverScreen() {
		gameOverButtons.add(
				new Button(virtualWidth / 2,
						virtualHeight / 3,
						virtualWidth,
						"score = "+score)
		);
	}
	public void renderStartScreen(){
		batch.begin();
		for (Button button : startButtons) {
			button.draw(batch, 1);
		}
		batch.end();
	}

	public void renderVialsScreen(){
		batch.begin();
		for (Button button : vialsButtons) {
			button.draw(batch, 1);
		}
		batch.end();
	}

	public void renderPlayingScreen() {
		if (lastQuestion == null || question != lastQuestion) {
			updateQuestion(question);
			lastQuestion = question;
		}

		batch.begin();
		questionBody.draw(batch, 1);
		batch.end();

		batch.begin();
		for (Button button : questionButtons) {
			button.draw(batch, 1);
		}
		batch.end();
	}

	public void renderGameOverScreen(){
		batch.begin();
		for (Button button : gameOverButtons) {
			button.draw(batch, 1);
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
		switch (gameScreen) {
			case START_SCREEN:
				onClickStartScreen(worldX,worldY);
				break;
			case VIALS_SCREEN:
				onClickVialsScreen(worldX,worldY);
				break;
			case PLAYING_SCREEN:
				onClickPlayingScreen(worldX,worldY);
				break;
			case GAME_OVER_SCREEN:
				onClickGameOverScreen(worldX,worldY);
				break;
		}
		return true;
	}
	public void onClickStartScreen(float worldX, float worldY){
		for (Button btn : startButtons) {
			if (btn.isClicked(worldX,worldY)){
				initToVialsScreen();
				gameScreen = GameScreen.VIALS_SCREEN;
			}
		}
	}
	public void onClickVialsScreen(float worldX, float worldY){
		for (Button btn : vialsButtons) {
			if (btn.isClicked(worldX,worldY)){
				initToPlayingScreen();
				gameScreen = GameScreen.PLAYING_SCREEN;
			}
		}
	}
	public void onClickPlayingScreen(float worldX, float worldY){
		for (Button btn : questionButtons) {
			if (btn.isClicked(worldX,worldY)){
				if (Objects.equals(btn.getText(), question.getCorrectAnswer())){
					score++;
				}
				questionIndex += 1;
				if (Utils.questions.size() > questionIndex) {
					question = Utils.questions.get(questionIndex);
				}else {
					gameScreen = GameScreen.GAME_OVER_SCREEN;
					initToGameOverScreen();
				}
			}
		}
	}
	public void onClickGameOverScreen(float worldX, float worldY){
		for (Button btn : gameOverButtons) {
			if (btn.isClicked(worldX,worldY)){
				initToStartScreen();
				gameScreen = GameScreen.START_SCREEN;
			}
		}
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
