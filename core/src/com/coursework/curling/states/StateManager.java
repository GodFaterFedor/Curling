package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class StateManager {

    private static StateManager instance;

    private transient GameScreen screen;
    private State state;
    private ArrayList<Player> players;
    private int nextPlayer = 0;
    private PhysicalEntity currentStone;
    private int numberOfStones = 0;

    public StateManager(GameScreen screen) {
        this.screen = screen;
        players = new ArrayList<Player>();

        int numberOfPlayers = 2;

        ArrayList<String> colors = new ArrayList<String>();
        colors.add("red");
        colors.add("yellow");

        for(int i = 0; i < numberOfPlayers; i ++ ) {
            players.add(new Player(colors.get(i), screen));
        }

        addStone();

    }

    public void addStone(){
        if (getStones().size() == players.size())
            this.state = new WinState(this);
        else {
            FirstState state = new FirstState(this);
            Player player = players.get((nextPlayer++) % players.size());

            currentStone = player.addStone();
            state.setStone(currentStone);

            this.state = state;
        }
    }

    public void setState(State state){
        state.setStone(getStones().get(getStones().size() - 1));
        this.state = state;
    }

//    public static synchronized StateManager getInstance() {
//
//        if (instance == null) {
//            instance = new StateManager();
//        }
//        return instance;
//    }

//    public void loadFromFile() {
//        ArrayList<SavedObject> data = new ArrayList<SavedObject>();
//
//        try {
//            String path = Gdx.files.getLocalStoragePath();
//            File file = new File(path, "/" + "manager.cer");
//            FileInputStream fileIn = new FileInputStream(file);
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            data = (ArrayList<SavedObject>) in.readObject();
//            in.close();
//            fileIn.close();
//        }catch(IOException i) {
//            i.printStackTrace();
//        }catch(ClassNotFoundException c) {
//            System.out.println("Employee class not found");
//            c.printStackTrace();
//        }
//
//        ArrayList<PhysicalEntity> stones = new ArrayList<PhysicalEntity>();
//
//        for (SavedObject dataObject: data) {
//
//            PhysicalEntity stone = PhysicalEntity.create((int)dataObject.x, (int)dataObject.y, Constants.STONE_SIZE, Constants.STONE_SIZE, "stone.png", this.screen);
//            stone.getBody().setLinearVelocity(dataObject.speedX, dataObject.speedY);
//            stones.add(stone);
//        }
//
//        this.stones = stones;
//    }

//    public void saveToFile() {
//
//        ArrayList<SavedObject> data = new ArrayList<SavedObject>();
//
//        for (PhysicalEntity stone: stones) {
//
//            SavedObject dataObject = new SavedObject();
//            dataObject.x = stone.getCenterX();
//            dataObject.y = stone.getCenterY();
//            dataObject.speedX = stone.getBody().getLinearVelocity().x;
//            dataObject.speedY = stone.getBody().getLinearVelocity().y;
//            data.add(dataObject);
//        }
//
//        try {
//            String path = Gdx.files.getLocalStoragePath();
//            File file = new File(path, "/" + "manager.cer");
//            FileOutputStream fileOut = new FileOutputStream(file);
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(data);
//            out.close();
//            fileOut.close();
//
//        }catch(IOException i) {
//            i.printStackTrace();
//        }
//    }

    public void render(float dt) {

//        for (int i = 0; i < stones.size(); i ++) {
//            PhysicalEntity stone = stones.get(i);
//            if (stone.getBody() == null) {
//                PhysicalEntity entity = PhysicalEntity.create((int)stone.getX(), (int)stone.getY(), stone.getWidth(), stone.getHeight(), "stone.png", screen);
//                stones.remove(i);
//                stones.add(i, entity);
//            }
//        }
//        for (PhysicalEntity entity: stones) {
//            if (entity.getSprite() == null) {
//                entity.setSprite(new Sprite(new Texture("stone.png")));
//            }
//
//            if (entity.getBody() == null) {
//                entity = PhysicalEntity.create((int)entity.getX(), (int)entity.getY(), entity.getWidth(), entity.getHeight(), "stone.png", screen);
//            }
//        }
//        if ((stones.get(stones.size() - 1).getBody().getPosition().y < 380) && (stones.get(stones.size() - 1).getBody().getPosition().y > 40) && (this.state.getName() != "run")) {
//            this.setState(new RunState(this));
//        }
//        if ((stones.get(stones.size() - 1).getBody().getPosition().y < 40 && (stones.get(stones.size() - 1).getBody().getPosition().y > 0) && (this.state.getName() != "strike"))){
//            this.setState(new StrikeState(this));
//        }
//        if (stones.get(stones.size() - 1).getBody().getLinearVelocity().epsilonEquals(0f, 0f, 1f) && (this.state.getName() != "first")) {
//            addStone();
//        }
        state.update(dt);
        state.render(dt);
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

    public void setScreen(GameScreen screen) {
        this.screen = screen;
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
