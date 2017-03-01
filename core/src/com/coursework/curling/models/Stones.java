package com.coursework.curling.models;


import com.badlogic.gdx.graphics.g2d.Sprite;

public class Stones extends PhysicalEntity {
    public Stones(Sprite sprite) {
        super(sprite);
    }

    public Stones(Sprite sprite, float width, float height) {
        super(sprite, width, height);
    }

    public Stones(Sprite sprite, float width, float height, float x, float y) {
        super(sprite, width, height, x, y);
    }
}
