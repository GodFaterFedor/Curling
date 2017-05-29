package com.coursework.curling.states;

import com.badlogic.gdx.Gdx;
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

import java.util.ArrayList;

public class StrikeState extends State {

    private PhysicalEntity stone;
    private Vector3 velocity;
    private float deltaTime;


    public StrikeState(StateManager manager) {
        super(manager);
        Gdx.input.setInputProcessor(this);

        //stone = PhysicalEntity.create(100, 100, Constants.STONE_SIZE, Constants.STONE_SIZE, "stone.png", manager.getScreen());
    }



    @Override
    public void update(float dt) {
        deltaTime = dt;
        boolean stopped = true;

        for (PhysicalEntity stone: manager.getStones()) {
            Body body = stone.getBody();
            Sprite sprite = stone.getSprite();

            if (!body.getLinearVelocity().epsilonEquals(0f, 0f, 1f)) {
                stopped = false;
            }
            sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
            sprite.setOriginCenter();
            sprite.setRotation((float) (180 * body.getAngle() / Math.PI));

        }

        if (getCurrentStone().getBody().getPosition().y < Constants.FIELD_HEIGHT - manager.getScreen().getCamera().viewportHeight / 2) {
            manager.getScreen().getCamera().position.y = getCurrentStone().getBody().getPosition().y;
            manager.getScreen().getCamera().update();
        }

        if (stopped) {
            Gdx.input.setInputProcessor(null);
            manager.addStone();
        }

//        sprite.setOriginCenter();
//        sprite.setRotation((180*body.getAngle())/3.14157f);
    }

    @Override
    public void render(float dt) {
        for (PhysicalEntity s: manager.getStones()) {
            s.getSprite().draw(Curling.batch);
        }
    }

    public void setStone(PhysicalEntity stone) {
        this.stone = stone;
    }
    @Override
    public String getName(){
        return "strike";
    }

}
