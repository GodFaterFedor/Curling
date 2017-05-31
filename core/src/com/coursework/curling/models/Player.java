package com.coursework.curling.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.coursework.curling.common.Constants;
import com.coursework.curling.screens.GameScreen;

import java.util.ArrayList;

public class Player {

    private ArrayList<PhysicalEntity> stones;
    private String color;
    private GameScreen screen;
    private Texture winLabel;
    private int score = Constants.MAX_INT;

    public Player(String color, GameScreen screen) {
        this.color = color;
        winLabel = new Texture(color + "_player_wins.png");
        this.screen = screen;
        this.stones = new ArrayList<PhysicalEntity>();
    }

    public Texture getWinLabel(){
        return winLabel;
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
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getColor() {
        return color;
    }
}
