package com.mike.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mike.game.main;
import com.mike.game.main.*;

public class gameScreen implements Screen, InputProcessor {
	private main game;
	Texture texture;
	boolean movingRight = false;
	boolean movingLeft = false;
	Stage gameStage;
	Viewport viewport;
	public Image gameBackground1;
	public Image gameBackground2;
	Group gameBackgroundGroup;
	private int score;
	private String scoreString;
	Label text;
	Label.LabelStyle textStyle;
	Integer scoreHeight = 2;
	Integer scoreWidth = 2;
	private int scoreX = 0;

	public gameScreen(main game){
		this.game = game;
		gameStage = new Stage(new ScreenViewport());
		gameBackgroundGroup = new Group();

		Image gameBackground1 = new Image(new Texture(Gdx.files.internal("gameBackground.jpg")));
		Image gameBackground2 = new Image(new Texture(Gdx.files.internal("gameBackground.jpg")));

		gameBackground1.setName("b1");
		gameBackground2.setName("b2");

		gameBackgroundGroup.addActor(gameBackground1);
		gameBackgroundGroup.addActor(gameBackground2);
		gameBackground1.setPosition(0, 0);
		gameBackground2.setPosition(0 + gameBackground1.getWidth(), 0);

		gameStage.addActor(gameBackgroundGroup);

		score = 0;
		scoreString = Integer.toString(score);

		BitmapFont font = new BitmapFont();
		textStyle = new Label.LabelStyle();
		textStyle.font = font;

		text = new Label(scoreString,textStyle);
		text.setBounds(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() * 0.9f,scoreWidth,scoreHeight);
		text.setFontScale(scoreWidth,scoreHeight);
		gameStage.addActor(text);

		/*textureAtlas = new TextureAtlas(Gdx.files.internal("characterMan/manSpritesheet/Manimations.atlas"));
		textureRegion = textureAtlas.findRegion("0032");

		//textureRegion = textureAtlas.findRegion(String.format("%04d",currentFrame));

		Image manman = new Image(textureRegion);
		manman.setName("manAnimation");

		gameStage.addActor(manman);*/


		InputMultiplexer im = new InputMultiplexer(gameStage, this);//combines both input processors in order of priority
		Gdx.input.setInputProcessor(im);
	}
	public static Vector2 getStageLocation(Actor actor) {
		return actor.localToStageCoordinates(new Vector2(0, 0));
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float v) {
		Gdx.app.log("penis", "penis");
		Gdx.gl.glClearColor(1,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Image bg1 = (Image) gameBackgroundGroup.findActor("b1");
		Image bg2 = (Image) gameBackgroundGroup.findActor("b2");
		//Image man = (Image)gameBackgroundGroup.findActor("manAnimation");

		if (movingRight) {
			if(scoreX < 0) {
				scoreX = scoreX + 1;
			}
			if(scoreX >= 0){
				score = score + 1;
				scoreString = Integer.toString(score);
				text.setText(scoreString);
			}
			/*currentFrame++;
			if (currentFrame < minWalkFrame) {
				currentFrame = maxWalkFrame;
			}*/
			//man.setDrawable(new SpriteDrawable(new Sprite(textureAtlas.findRegion(String.format("%04d", currentFrame)))));
			gameBackgroundGroup.setPosition(gameBackgroundGroup.getX() - 10, gameBackgroundGroup.getY());
			//Gdx.app.log("bg1 location", String.valueOf(getStageLocation(bg1).x + bg1.getWidth()));

			if ((getStageLocation(bg1).x + bg1.getWidth()) < 0) {
				//Gdx.app.log("position", "bg1 off screen");
				bg1.setPosition(bg2.getX() + bg2.getWidth(), 0);
			}
			if ((getStageLocation(bg2).x + bg2.getWidth()) < 0) {
				//Gdx.app.log("position", "bg2 off screen");
				bg2.setPosition(bg1.getX() + bg1.getWidth(), 0);
			}
		}

		if (movingLeft) {
			scoreX = scoreX - 1;
			//score = score - 1;
			//scoreString = Integer.toString(score);
			//text.setText(scoreString);
			gameBackgroundGroup.setPosition(gameBackgroundGroup.getX() + 10, gameBackgroundGroup.getY());
			if (getStageLocation(bg1).x > gameStage.getWidth()) {
				//Gdx.app.log("position", "bg1 off screen");
				bg1.setPosition(bg2.getX() - bg2.getWidth(), 0);
			}
			if (getStageLocation(bg2).x > gameStage.getWidth()) {
				//Gdx.app.log("position", "bg2 off screen");
				bg2.setPosition(bg1.getX() - bg1.getWidth(), 0);
			}
		}
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameStage.act(Gdx.graphics.getDeltaTime());
		gameStage.draw();
	}

	@Override
	public void resize(int i, int i1) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.LEFT)
			movingLeft = true;
		if (keycode == Input.Keys.RIGHT)
			movingRight = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT)
			movingLeft = false;
		if (keycode == Input.Keys.RIGHT)
			movingRight = false;
		return false;
	}

	@Override
	public boolean keyTyped(char c) {
		return false;
	}

	@Override
	public boolean touchDown(int i, int i1, int i2, int i3) {
		return false;
	}

	@Override
	public boolean touchUp(int i, int i1, int i2, int i3) {
		return false;
	}

	@Override
	public boolean touchDragged(int i, int i1, int i2) {
		return false;
	}

	@Override
	public boolean mouseMoved(int i, int i1) {
		return false;
	}

	@Override
	public boolean scrolled(int i) {
		return false;
	}
}
