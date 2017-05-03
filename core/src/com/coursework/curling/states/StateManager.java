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

    private transient GameScreen screen;
    private State state;
    private ArrayList<PhysicalEntity> stones;

    public StateManager(GameScreen screen) {
        stones = new ArrayList<PhysicalEntity>();
        this.screen = screen;
        FirstState state = new FirstState(this);
        loadFromFile();

        if (stones.size() == 0) {
            stones.add(PhysicalEntity.create(100, 100, Constants.STONE_SIZE, Constants.STONE_SIZE, "stone.png", screen));
        }
        state.setStone(stones.get(0));
        this.state = state;

        //initMainStone();
        //state.setStones(stones);
        //state.camera = camera;
        //Gdx.input.setInputProcessor(state);
        //setState(new RunState(game));
    }

    public void loadFromFile() {
        ArrayList<SavedObject> data = new ArrayList<SavedObject>();

        try {
            String path = Gdx.files.getLocalStoragePath();
            File file = new File(path, "/" + "manager.cer");
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (ArrayList<SavedObject>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
        }catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }

        ArrayList<PhysicalEntity> stones = new ArrayList<PhysicalEntity>();

        for (SavedObject dataObject: data) {

            PhysicalEntity stone = PhysicalEntity.create((int)dataObject.x, (int)dataObject.y, Constants.STONE_SIZE, Constants.STONE_SIZE, "stone.png", this.screen);
            stone.getBody().setLinearVelocity(dataObject.speedX, dataObject.speedY);
            stones.add(stone);
        }

        this.stones = stones;
    }

    public void saveToFile() {

        ArrayList<SavedObject> data = new ArrayList<SavedObject>();

        for (PhysicalEntity stone: stones) {

            SavedObject dataObject = new SavedObject();
            dataObject.x = stone.getCenterX();
            dataObject.y = stone.getCenterY();
            dataObject.speedX = stone.getBody().getLinearVelocity().x;
            dataObject.speedY = stone.getBody().getLinearVelocity().y;
            data.add(dataObject);
        }

        try {
            String path = Gdx.files.getLocalStoragePath();
            File file = new File(path, "/" + "manager.cer");
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();

        }catch(IOException i) {
            i.printStackTrace();
        }
    }

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
        state.update(dt);
        state.render(dt);
    }

    public GameScreen getScreen() {
        return screen;
    }
    public void setScreen(GameScreen screen) { this.screen = screen; }
    public ArrayList<PhysicalEntity> getStones() {
        return stones;
    }

    public void setStones(ArrayList<PhysicalEntity> stones) {
        this.stones = stones;
    }
}
