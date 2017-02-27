package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.coursework.curling.Curling;
import com.coursework.curling.models.PhysicalEntity;

import java.util.ArrayList;

public class StateManager {

    private ArrayList<PhysicalEntity> stones;

    private Game game;
    private World world;
    private State state;

    public StateManager(Curling game, World world) {

        this.world = world;
        this.game = game;
        state = new RunState(game);
        initMainStone();
        state.setStones(stones);
    }

    private void initMainStone() {

        stones = new ArrayList<PhysicalEntity>();

        Sprite sprite = new Sprite(new Texture("stone.png"));

        PhysicalEntity stone = new PhysicalEntity(sprite);

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        Circle circle = new Circle(0, 0, 100);

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(circle.x, circle.y);

        body = world.createBody(bodyDef);

        shape.setAsBox(100,100);
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
