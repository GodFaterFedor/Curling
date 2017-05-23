package com.coursework.curling.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.coursework.curling.Curling;
import com.coursework.curling.models.PhysicalEntity;
import com.coursework.curling.models.Player;

import java.util.ArrayList;

public class WinState extends State {

    private Vector2 center = new Vector2(22.5f, 450 - 36f);
    private Player winner;
    public WinState(StateManager manager) {
        super(manager);
        Gdx.input.setInputProcessor(this);
        winner = defineWinner();
    }



    private Player defineWinner(){
        Player Winner = null;
        float minDistance = manager.getPlayers().get(0).getStones().get(0).getBody().getPosition().len2();
        float distance = 0f;
        for (Player player : manager.getPlayers()){
            for (PhysicalEntity stone: player.getStones())
                distance = (float) (Math.pow(center.x - stone.getX(), 2) + Math.pow(center.y - stone.getY(), 2));
                if (minDistance > distance){
                    minDistance = distance;
                    Winner = player;
                }
        }
        return Winner;
    }
    @Override
    public void update(float dt) {

        ArrayList<PhysicalEntity> stones = manager.getStones();

        for (PhysicalEntity stone: stones) {
            Body body = stone.getBody();
            Sprite sprite = stone.getSprite();

            sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
            sprite.setOriginCenter();
            sprite.setRotation((float) (180 * body.getAngle() / Math.PI));
        }
//        sprite.setOriginCenter();
//        sprite.setRotation((180*body.getAngle())/3.14157f);
    }

    @Override
    public void render(float dt) {
        Curling.batch.begin();
//        for (PhysicalEntity s: manager.getStones()) {
//            s.getSprite().draw(Curling.batch);
//
//        }
        if (winner != null)
            Curling.batch.draw(winner.getWinLabel(), manager.getScreen().getCamera().position.x - 10, manager.getScreen().getCamera().position.y - 5, 20, 10);
        Curling.batch.end();
    }
}
