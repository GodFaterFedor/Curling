package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.coursework.curling.Curling;
import com.coursework.curling.models.PhysicalEntity;

import java.util.ArrayList;

public abstract class State {

    protected Curling game;
    protected ArrayList<PhysicalEntity> stones;

    public abstract void update(float dt);
    public abstract void render(float dt);

    public  State(Curling game) {

        this.game = game;
    }

    public void setStones(ArrayList<PhysicalEntity> stones){
        this.stones = stones;
    }

    public ArrayList<PhysicalEntity> getStones(){
        return stones;
    }
}
