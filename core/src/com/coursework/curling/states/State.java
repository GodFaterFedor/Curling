package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.coursework.curling.Curling;
import com.coursework.curling.models.PhysicalEntity;
import com.coursework.curling.screens.GameScreen;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class State implements InputProcessor, Serializable {

    protected StateManager manager;

    public abstract void update(float dt);
    public abstract void render(float dt);

    public  State(StateManager manager) {
        this.manager = manager;
    }

    public void dispose(){

    }

    public String getName(){
        return "state";
    }

    public void setStone(PhysicalEntity stone) {
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
    public boolean scrolled(int amount) {
        return false;
    }

    public PhysicalEntity getLastStone() {
        return manager.getStones().get(manager.getStones().size() - 1);
    }
}
