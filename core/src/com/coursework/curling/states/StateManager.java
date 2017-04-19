package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.coursework.curling.Curling;
import com.coursework.curling.models.PhysicalEntity;
import com.coursework.curling.screens.GameScreen;

import java.util.ArrayList;

public class StateManager {

    private GameScreen screen;
    private State state;
    private ArrayList<PhysicalEntity> stones;

    public StateManager(GameScreen screen) {
        stones = new ArrayList<PhysicalEntity>();
        this.screen = screen;
        this.state = new FirstState(this);
        //initMainStone();
        //state.setStones(stones);
        //state.camera = camera;
        //Gdx.input.setInputProcessor(state);
        //setState(new RunState(game));
    }

    public void render(float dt) {
        state.update(dt);
        state.render(dt);
    }

    public GameScreen getScreen() {
        return screen;
    }

    public ArrayList<PhysicalEntity> getStones() {
        return stones;
    }
}
