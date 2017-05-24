package com.coursework.curling;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coursework.curling.screens.GameScreen;
import com.coursework.curling.screens.MenuScreen;
import com.sun.java_cup.internal.runtime.Symbol;

import java.util.Scanner;


public class Curling extends Game {

	public static SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}



}
