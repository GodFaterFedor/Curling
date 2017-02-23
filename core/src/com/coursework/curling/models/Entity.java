package com.coursework.curling.models;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity {

    protected Sprite sprite;

    public Entity(Sprite sprite) {
        this.sprite = sprite;
    }

    public Entity(Sprite sprite, float width, float height) {
        this.sprite = sprite;
        sprite.setSize(width, height);
    }

    public Entity(Sprite sprite, float width, float height, float x, float y) {
        this.sprite = sprite;
        sprite.setSize(width, height);
        sprite.setPosition(x,y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSize(float width, float height) {
        sprite.setSize(width, height);
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x,y);
    }

    public void setCenter(float x, float y) {
        sprite.setCenter(x,y);
    }

    public void move(float dx, float dy) {
        sprite.setX(sprite.getX() + dx);
        sprite.setY(sprite.getY() + dy);

    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getCenterX() {
        return sprite.getX() + sprite.getWidth() / 2;
    }

    public float getCenterY() {
        return sprite.getY() + sprite.getHeight() / 2;
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
