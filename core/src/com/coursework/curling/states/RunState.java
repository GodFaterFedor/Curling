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

    private PhysicalEntity stone;
    private Vector3 velocity;
    private float deltaTime;


    public RunState(StateManager manager) {
        super(manager);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public String getName(){
        return "run";
    }

    private boolean isStoneInRange(PhysicalEntity stone) {

        return stone.getBody().getPosition().y < 410;
    }

    private boolean isGoodSpeed(PhysicalEntity stone) {
        return stone.getBody().getLinearVelocity().x > Constants.MINIMAL_RUN_STATE_SPEED ||
                stone.getBody().getLinearVelocity().y > Constants.MINIMAL_RUN_STATE_SPEED;
    }

    @Override
    public void update(float dt) {

        ArrayList<PhysicalEntity> stones = manager.getStones();
        deltaTime = dt;
        for (PhysicalEntity stone: stones) {
            Body body = stone.getBody();
            Sprite sprite = stone.getSprite();

            sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
            sprite.setOriginCenter();
            sprite.setRotation((float) (180 * body.getAngle() / Math.PI));
        }

        manager.getScreen().getCamera().position.y = getLastStone().getBody().getPosition().y;
        manager.getScreen().getCamera().update();

        if (!isStoneInRange(getLastStone()) || !isGoodSpeed(getLastStone())) {
            manager.setStrikeState();
        }



    }

    @Override
    public void render(float dt) {

        Curling.batch.begin();
        for (PhysicalEntity s: manager.getStones()) {
            s.getSprite().draw(Curling.batch);
        }
        Curling.batch.end();
    }

    public void setStone(PhysicalEntity stone) {
        this.stone = stone;
    }
}
