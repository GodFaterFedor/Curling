package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.coursework.curling.Curling;
import com.coursework.curling.models.PhysicalEntity;

class RunState extends State {

    public RunState(Curling game) {
        super(game);
    }

    @Override
    public void update(float dt) {
        PhysicalEntity stone = stones.get(0);
        Body body = stone.getBody();
        Sprite sprite = stone.getSprite();

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
    }

    @Override
    public void render(float dt) {

        game.getBatch().begin();
        stones.get(0).getSprite().draw(game.getBatch());
        game.getBatch().end();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        PhysicalEntity stone = stones.get(0);
        Vector3 coordinates = camera.unproject(new Vector3(screenX, screenY, 0));

        float deltaX = coordinates.x - stone.getCenterX();
        float deltaY = coordinates.y - stone.getCenterY();

        stone.getBody().applyLinearImpulse(deltaX, deltaY, stone.getCenterX(), stone.getCenterY(), true);
        stone.getBody().applyForce(-deltaX * 40, -deltaY * 40, stone.getCenterX(), stone.getCenterY(), true);



        return super.touchUp(screenX, screenY, pointer, button);
    }
}
