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
    private Vector3 lastTouchPosition;


    public RunState(StateManager manager) {
        super(manager);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public String getName(){
        return "run";
    }

    private boolean isStoneInRange(PhysicalEntity stone) {

        return stone.getBody().getPosition().y < Constants.RUN_STATE_BORDER;
    }

    private boolean isGoodSpeed(PhysicalEntity stone) {
        return stone.getBody().getLinearVelocity().x > Constants.MINIMAL_RUN_STATE_SPEED ||
                stone.getBody().getLinearVelocity().y > Constants.MINIMAL_RUN_STATE_SPEED;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 coordinates = manager.getScreen().getCamera().unproject(new Vector3(screenX, screenY, 0));
        lastTouchPosition = coordinates;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        lastTouchPosition = null;
        getLastStone().getBody().setLinearDamping(Constants.LINEAR_DAMPING);

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        Vector3 coordinates = manager.getScreen().getCamera().unproject(new Vector3(screenX, screenY, 0));
        if ((Math.abs(getLastStone().getBody().getPosition().x - coordinates.x)) < 10 &&
                coordinates.y - getLastStone().getBody().getPosition().y < 30 &&
                coordinates.y - getLastStone().getBody().getPosition().y > 0) {

            getLastStone().getBody().setLinearDamping(0);
        } else {
            getLastStone().getBody().setLinearDamping(Constants.LINEAR_DAMPING);
        }

            return false;

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
            getLastStone().getBody().setLinearDamping(Constants.LINEAR_DAMPING);
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
