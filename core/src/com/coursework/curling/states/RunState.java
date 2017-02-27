package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.coursework.curling.Curling;

public class RunState extends State {

    public RunState(Curling game) {
        super(game);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(float dt) {
        game.getBatch().begin();
        stones.get(0).getSprite().draw(game.getBatch());
        game.getBatch().end();
    }
}
