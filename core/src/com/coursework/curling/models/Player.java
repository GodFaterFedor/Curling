package com.coursework.curling.models;

import com.coursework.curling.common.Constants;
import com.coursework.curling.screens.GameScreen;

import java.util.ArrayList;

public class Player {

    private ArrayList<PhysicalEntity> stones;
    private String color;
    private GameScreen screen;

    public Player(String color, GameScreen screen) {
        this.color = color;
        this.screen = screen;
        this.stones = new ArrayList<PhysicalEntity>();
    }

    public ArrayList<PhysicalEntity> getStones() {
        return stones;
    }

    public PhysicalEntity addStone() {

        String texture = "stone_" + color + ".png";

        stones.add(PhysicalEntity.create(22, 450, Constants.STONE_SIZE, Constants.STONE_SIZE, texture, screen));

        int last = stones.size() - 1;
        stones.get(last).getBody().setLinearVelocity(0, 0);
        stones.get(last).getBody().setTransform(22.5f, 20, 0);

        return stones.get(last);
    }
}
