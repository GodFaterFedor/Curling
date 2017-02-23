package com.coursework.curling.models;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PhysicalEntity extends Entity {

    private Vector2 velocity;

    public PhysicalEntity(Sprite sprite) {
        super(sprite);
        velocity = new Vector2(0,0);
    }

    public PhysicalEntity(Sprite sprite, float width, float height) {
        super(sprite, width, height);
        velocity = new Vector2(0,0);

    }

    public PhysicalEntity(Sprite sprite, float width, float height, float x, float y) {
        super(sprite, width, height, x, y);
        velocity = new Vector2(0,0);

    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void applyVelocity(float delta) {


        float dx = velocity.x * delta;
        float dy = velocity.y * delta;

        move(dx, dy);


    }
}
