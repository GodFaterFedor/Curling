package com.coursework.curling.models;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.coursework.curling.screens.GameScreen;

import java.lang.reflect.Field;

public class PhysicalEntity extends Entity {

    private Body body;

    static public PhysicalEntity create(int x, int y, float width, float heigth, String texture, GameScreen screen) {
        Sprite sprite = new Sprite(new Texture(texture));
        sprite.setSize(width, heigth);
        PhysicalEntity stone = new PhysicalEntity(sprite);

        FixtureDef fixtureDef = new FixtureDef();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        bodyDef.linearDamping = 0.3f;//set position of sprite
        bodyDef.angularDamping = 0.7f;
        Body body;
        body = screen.getWorld().createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(sprite.getWidth() / 2);
        //shape.setPosition(new Vector2(x , y));

        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = .25f;
        body.createFixture(fixtureDef);
        stone.setBody(body);
        return stone;
    }
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
