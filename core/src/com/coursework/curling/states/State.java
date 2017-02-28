package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.coursework.curling.Curling;
import com.coursework.curling.models.PhysicalEntity;

import java.util.ArrayList;

public abstract class State implements InputProcessor {

    protected Camera camera;
    protected Curling game;
    protected ArrayList<PhysicalEntity> stones;

    public abstract void update(float dt);
    public abstract void render(float dt);

    public  State(Curling game) {

        this.game = game;
    }

    public void setStones(ArrayList<PhysicalEntity> stones){
        this.stones = stones;
    }

    public ArrayList<PhysicalEntity> getStones(){
        return stones;
    }

    private void setCamera(Camera camera) {
        this.camera = camera;
    }

    private Camera getCamera() {
        return camera;
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
}
