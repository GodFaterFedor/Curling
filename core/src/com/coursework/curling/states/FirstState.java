package com.coursework.curling.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.coursework.curling.Curling;
import com.coursework.curling.common.Constants;
import com.coursework.curling.models.PhysicalEntity;
import com.coursework.curling.screens.GameScreen;

import java.io.Console;

public class FirstState extends State {

    private PhysicalEntity stone;
    private Vector3 velocity;
    private float deltaTime;

    public FirstState(StateManager manager) {
        super(manager);
        Gdx.input.setInputProcessor(this);

        //stone = PhysicalEntity.create(100, 100, Constants.STONE_SIZE, Constants.STONE_SIZE, "stone.png", manager.getScreen());
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 coordinates = manager.getScreen().getCamera().unproject(new Vector3(screenX, screenY, 0));

        velocity = new Vector3(0, 0, 0);

        stone.getBody().setTransform(coordinates.x, coordinates.y, stone.getBody().getAngle());
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        Vector3 coordinates = manager.getScreen().getCamera().unproject(new Vector3(screenX, screenY, 0));

        float deltaX = (coordinates.x - stone.getCenterX()) / deltaTime;
        float deltaY = (coordinates.y - stone.getCenterY()) / deltaTime;

        velocity.x = deltaX * Constants.SPEED_MULTIPLIER;
        velocity.y = deltaY * Constants.SPEED_MULTIPLIER;

        stone.getBody().setLinearVelocity(0, 0);
        stone.getBody().setTransform(coordinates.x, coordinates.y, stone.getBody().getAngle());

        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        Vector3 coordinates = manager.getScreen().getCamera().unproject(new Vector3(screenX, screenY, 0));

        stone.getBody().setLinearVelocity(velocity.x, velocity.y);
        //stone.getBody().applyForce(-deltaX * 40, -deltaY * 40, stone.getCenterX(), stone.getCenterY(), false);

        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public void update(float dt) {
        deltaTime = dt;
        Body body = stone.getBody();
        Sprite sprite = stone.getSprite();

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);


    }

    @Override
    public void render(float dt) {

        Curling.batch.begin();
        stone.getSprite().draw(Curling.batch);
        Curling.batch.end();
    }

    public void setStone(PhysicalEntity stone) {
        this.stone = stone;
    }
}
