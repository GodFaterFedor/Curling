package com.coursework.curling.states;


import com.badlogic.gdx.Game;

public class StateManager {

    private Game game;
    private State state;

    public StateManager(Game game) {

        this.game = game;
        state = new RunState(game);
    }

    public void render(float dt) {
        state.update(dt);
        state.render(dt);
    }
}
