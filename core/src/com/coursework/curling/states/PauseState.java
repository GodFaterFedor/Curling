package com.coursework.curling.states;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.coursework.curling.Curling;
import com.coursework.curling.common.Constants;
import com.coursework.curling.models.PhysicalEntity;
import com.coursework.curling.models.Player;
import com.coursework.curling.screens.GameScreen;
import com.coursework.curling.screens.MenuScreen;

import java.util.ArrayList;

public class PauseState extends State {

    private ArrayList<PhysicalEntity> stones;

    public PauseState(StateManager manager) {

        super(manager);
        manager.getScreen().isSleep(true);
        stones = manager.getStones();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(float dt) {
        Vector3 cameraPosition = manager.getScreen().getCamera().position;
        float width = Constants.FIELD_WIDTH * 0.8f;
        float heigth = manager.getPauseTexture().getHeight() / (float)manager.getPauseTexture().getWidth() * width;
        for (PhysicalEntity s: manager.getStones()) {
            s.getSprite().draw(Curling.batch);
        }
        Curling.batch.draw(manager.getPauseTexture(), cameraPosition.x - width / 2, cameraPosition.y - heigth / 2, width, heigth);

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        float width = Gdx.graphics.getWidth() * 0.8f / 2;
        float heigth = manager.getPauseTexture().getHeight() / (float)manager.getPauseTexture().getWidth() * width / 2 + 10;
        float middleX = Gdx.graphics.getWidth() / 2f;
        float middleY = Gdx.graphics.getHeight() / 2f;

        Rectangle repeatButtonRect = new Rectangle(middleX - width, middleY + 20, width, heigth);
        if (repeatButtonRect.contains(screenX, screenY)) {
            resumeButtonTap();
        }
        Rectangle menuButtonRect = new Rectangle(middleX, middleY + 20, width, heigth);
        if (menuButtonRect.contains(screenX, screenY)) {
            menuButtonTap();
        }


        return false;
    }

    private void resumeButtonTap() {
        manager.getScreen().isSleep(false);
        manager.deleteState();
    }

    private void menuButtonTap() {
        ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
    }
}
