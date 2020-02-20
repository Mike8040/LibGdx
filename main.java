package com.mike.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mike.game.screens.gameScreen;
import com.mike.game.screens.menuScreen;

public class main extends Game {
	public SpriteBatch batch;
	public static int activeScreen = 0;


	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new menuScreen(this));
	}
	public void changeScreen(Integer screenNumber){
		activeScreen = screenNumber;
		if(screenNumber == 0){
			super.setScreen(new menuScreen(this));
			super.render();
		}else if(screenNumber == 1){
			super.setScreen(new gameScreen(this));
			super.render();
		}

	}


	@Override
	public void render(){
		super.render();
	}
}
