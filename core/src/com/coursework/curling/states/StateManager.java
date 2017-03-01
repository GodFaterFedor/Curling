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

    public StateManager(GameScreen screen) {
        this.screen = screen;
        this.state = new RunState(screen);
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
}
