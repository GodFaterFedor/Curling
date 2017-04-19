package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.coursework.curling.Curling;
import com.coursework.curling.common.Constants;
import com.coursework.curling.models.PhysicalEntity;
import com.coursework.curling.screens.GameScreen;

import java.io.Console;
import java.util.ArrayList;

class RunState extends State {


    public RunState(StateManager manager) {
        super(manager);
        //initMainStone();
        //state.setStones(stones);
        //state.camera = camera;
        Gdx.input.setInputProcessor(this);
        //setState(new RunState(game));

    }

//    private void initMainStone() {
//
//        stones = new ArrayList<PhysicalEntity>();
//
//        Sprite sprite = new Sprite(new Texture("stone.png"));
//        sprite.setSize(100,100);
//        PhysicalEntity stone = new PhysicalEntity(sprite);
//
//        BodyDef bodyDef = new BodyDef();
//        CircleShape shape = new CircleShape();
//        FixtureDef fixtureDef = new FixtureDef();
//        Body body;
//
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(0, 0);
//
//        body = screen.getWorld().createBody(bodyDef);
//
//        shape.setRadius(sprite.getWidth() / 2);
//        shape.setPosition(new Vector2(0,0));
//        fixtureDef.shape = shape;
//        body.createFixture(fixtureDef);
//
//        stone.setBody(body);
//
//        stones.add(stone);
//    }
//
    @Override
    public void update(float dt) {
//        PhysicalEntity stone = stones.get(0);
//        Body body = stone.getBody();
//        Sprite sprite = stone.getSprite();
//
//        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
    }

    @Override
    public void render(float dt) {

//        Curling.batch.begin();
//        stones.get(0).getSprite().draw(Curling.batch);
//        Curling.batch.end();
    }
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//
//        PhysicalEntity stone = stones.get(0);
//        Vector3 coordinates = screen.getCamera().unproject(new Vector3(screenX, screenY, 0));
//
//        float deltaX = coordinates.x - stone.getCenterX();
//        float deltaY = coordinates.y - stone.getCenterY();
//
//        stone.getBody().setLinearVelocity(deltaX, deltaY);
//        stone.getBody().applyForce(-deltaX * 40, -deltaY * 40, stone.getCenterX(), stone.getCenterY(), false);
//
//        return super.touchUp(screenX, screenY, pointer, button);
//    }


}
