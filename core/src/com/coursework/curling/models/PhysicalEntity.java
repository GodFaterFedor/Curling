package com.coursework.curling.models;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class PhysicalEntity extends Entity {

    private Body body;

    public PhysicalEntity(Sprite sprite) {
        super(sprite);
    }

    public PhysicalEntity(Sprite sprite, float width, float height) {
        super(sprite, width, height);

    }

    public PhysicalEntity(Sprite sprite, float width, float height, float x, float y) {
        super(sprite, width, height, x, y);

    }

    public void setBody(Body body) {

        this.body = body;
    }

    public Body getBody() {
        return body;
    }

}
