package com.mike.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mike.game.main;
import org.lwjgl.openal.AL;

public class menuScreen implements Screen {
	private main game;
	Texture texture;
	private Stage stage;
	private Skin skin;
	private Table menuTable;
	private TextButton startButton;
	private TextButton quitButton;
	private Sound menuMusic;
	private Sound buttonClick;
	private ButtonGroup menuButtons;


	public menuScreen(main game){





		this.game = game;
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(new ScreenViewport());

		//independant pause menu  TODO
		//Window pause = new Window("PAUSE", skin);

		menuMusic = Gdx.audio.newSound(Gdx.files.internal("menuDamned.wav"));
		menuMusic.loop();
		menuMusic.play();

		buttonClick = Gdx.audio.newSound(Gdx.files.internal("buttonClick.mp3"));

		Image menuBackground = new Image(new Texture(Gdx.files.internal("menuBackground.jpg")));
		menuBackground.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		menuTable = new Table();//create then format table
		menuTable.setWidth(stage.getWidth());
		menuTable.align(Align.center|Align.top);//no idea why vertical line is needed

		menuTable.setPosition(0, Gdx.graphics.getHeight());

		startButton = new TextButton("New Game", skin);
		quitButton = new TextButton("Quit Game", skin);

		startButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("Clicked", "Yes you did");
				buttonClick.play();
				Timer.schedule(new Timer.Task() {//how to use a timer in libgdx
					@Override
					public void run() {
						for(Actor actor : stage.getActors())
						{
							actor.remove();
						}
						menuTable.remove();
						menuMusic.stop();
						/*Image gameBackground = new Image(new Texture(Gdx.files.internal("gameBackground.jpg")));
						gameBackground.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

						stage.addActor(gameBackground);*/

						//todo Launch game stage
						main mainFunctions = new main();
						mainFunctions.changeScreen(1);


					}
				},0.2f);

			}
		});


		quitButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				buttonClick.play();
				Timer.schedule(new Timer.Task() {//how to use a timer in libgdx
					@Override
					public void run() {
						AL.destroy();
						System.exit(0);

					}
				},0.2f);

			}
		});


		menuTable.padTop(30);//30 unit pad at top of table
		menuTable.add(startButton).padBottom(30);//add buttons to table with pad below
		menuTable.row();//insert row between each button addition
		menuTable.add(quitButton);

		ButtonGroup menuButtons = new ButtonGroup();
		menuButtons.add(startButton, quitButton);

		stage.addActor(menuBackground);
		stage.addActor(menuTable);//add table to stage

		Gdx.input.setInputProcessor(stage);


	}

	@Override
	public void show() {

	}

	@Override
	public void render(float v) {
		Gdx.gl.glClearColor(1,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		Gdx.app.log("cock", "cock");
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
}
