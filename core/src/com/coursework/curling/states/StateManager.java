package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

import java.util.ArrayList;

public class StateManager {

    private ArrayList<PhysicalEntity> stones;

    private Camera camera;
    private Game game;
    private World world;
    private State state;

    public StateManager(Curling game, World world, Camera camera) {

        this.world = world;
        this.game = game;
        this.camera = camera;
        state = new RunState(game);
        initMainStone();
        state.setStones(stones);
        state.camera = camera;

        Gdx.input.setInputProcessor(state);
    }

    private void initMainStone() {

        stones = new ArrayList<PhysicalEntity>();

        Sprite sprite = new Sprite(new Texture("stone.png"));
        sprite.setSize(100,100);
        PhysicalEntity stone = new PhysicalEntity(sprite);

        BodyDef bodyDef = new BodyDef();
        CircleShape shape = new CircleShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);

        body = world.createBody(bodyDef);

        shape.setRadius(sprite.getWidth() / 2);
        shape.setPosition(new Vector2(0,0));
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        stone.setBody(body);

        stones.add(stone);

    }

    public void render(float dt) {
        state.update(dt);
        state.render(dt);
    }
}