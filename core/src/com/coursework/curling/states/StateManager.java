package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;
import com.coursework.curling.Curling;
import com.coursework.curling.common.Constants;
import com.coursework.curling.models.Entity;
import com.coursework.curling.models.PhysicalEntity;
import com.coursework.curling.models.Player;
import com.coursework.curling.models.SavedObject;
import com.coursework.curling.screens.GameScreen;
import com.sun.corba.se.impl.orb.ParserTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class StateManager {

    private transient GameScreen screen;
    private Stack<State> states;
    private ArrayList<Player> players;
    private int nextPlayer = 0;
    private PhysicalEntity currentStone;
    private Texture pauseTexture;
    private Texture buttonTexture;

    Texture getPauseTexture(){
        return pauseTexture;
    }

    public StateManager(GameScreen screen, int numberOfPlayers) {
        this.screen = screen;
        this.states = new Stack<State>();
        pauseTexture = new Texture("pause_screen.png");

        players = new ArrayList<Player>();

        ArrayList<String> colors = new ArrayList<String>();
        colors.add("red");
        colors.add("yellow");
        colors.add("green");
        colors.add("blue");


        for(int i = 0; i < numberOfPlayers; i ++ ) {
            players.add(new Player(colors.get(i), screen));
        }

        addStone();

    }

    public void addStone(){
        if (!this.states.empty())
            this.states.pop();
        if (getStones().size() == players.size() * Constants.STONES_PER_PLAYER)
            this.states.push(new WinState(this));
        else {
            FirstState state = new FirstState(this);
            Player player = players.get((nextPlayer++) % players.size());

            currentStone = player.addStone();
            state.setStone(currentStone);

            this.states.push(state);
        }
    }

    public void addState(State state){
        this.states.push(state);
    }

    public void deleteState(){
        this.states.pop();
        Gdx.input.setInputProcessor(states.peek());
    }

    public void setState(State state){
        this.states.pop();
        state.setStone(getStones().get(getStones().size() - 1));
        this.states.push(state);
    }
    public State getState(){
        return this.states.peek();
    }

    public void render(float dt) {
        states.peek().update(dt);
        states.peek().render(dt);
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void setRunState() {
        this.setState(new RunState(this));
    }

    public void setStrikeState() {
        this.setState(new StrikeState(this));
    }

    public GameScreen getScreen() {
        return screen;
    }

    public ArrayList<PhysicalEntity> getStones(){
        ArrayList<PhysicalEntity> stones = new ArrayList<PhysicalEntity>();
        for (Player player: players) {
            stones.addAll(player.getStones());
        }
        return stones;
    }

    public int getSpeed() {
        float x = getCurrentStone().getBody().getLinearVelocity().x;
        float y = getCurrentStone().getBody().getLinearVelocity().y;

        return (int)Math.sqrt(x*x + y*y);
    }

    public PhysicalEntity getCurrentStone() {
        return currentStone;
    }
}
