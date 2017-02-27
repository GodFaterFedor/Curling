package com.coursework.curling.states;


import com.badlogic.gdx.Game;

public abstract class State {

    private Game game;

    public abstract void update(float dt);
    public abstract void render(float dt);

    public  State(Game game) {

        this.game = game;
    }
}
