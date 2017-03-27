package com.coursework.curling.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.coursework.curling.Curling;
import com.coursework.curling.models.PhysicalEntity;
import com.coursework.curling.screens.GameScreen;

import java.util.ArrayList;

public class StrikeState extends State {

    public StrikeState(GameScreen screen) {
        super(screen);
        initMainStone();
        //state.setStones(stones);
        //state.camera = camera;
        Gdx.input.setInputProcessor(this);
        //setState(new RunState(game));

    }

    private void initMainStone() {
        int size = 5;
        stones = new ArrayList<PhysicalEntity>();
        for(int i = 0; i <= size; i++)
            stones.add(initStone(i*20 + 40, 40));
    }

    private PhysicalEntity initStone(int x, int y){
        Sprite sprite = new Sprite(new Texture("stone.png"));
        sprite.setSize(20,20);
        PhysicalEntity stone = new PhysicalEntity(sprite);

        FixtureDef fixtureDef = new FixtureDef();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        bodyDef.linearDamping = 0.5f;//set position of sprite
        Body body;
        body = screen.getWorld().createBody(bodyDef);


        CircleShape shape = new CircleShape();
        shape.setRadius(sprite.getWidth() / 2);
        //shape.setPosition(new Vector2(x , y));

        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = .25f;
        body.createFixture(fixtureDef);

        stone.setBody(body);
        return stone;
    }

    @Override
    public void update(float dt) {
        for (PhysicalEntity stone: stones) {
            //PhysicalEntity stone = s;
            Body body = stone.getBody();
            Sprite sprite = stone.getSprite();

            sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
        }
    }

    @Override
    public void render(float dt) {

        Curling.batch.begin();
        for (PhysicalEntity stone: stones)
            stone.getSprite().draw(Curling.batch);
        Curling.batch.end();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        PhysicalEntity stone = stones.get(0);
        Vector3 coordinates = screen.getCamera().unproject(new Vector3(screenX, screenY, 0));

        float deltaX = coordinates.x - stone.getCenterX();
        float deltaY = coordinates.y - stone.getCenterY();

        stone.getBody().setLinearVelocity(deltaX, deltaY);
        //stone.getBody().applyForce(-deltaX * 40, -deltaY * 40, stone.getCenterX(), stone.getCenterY(), false);

        return super.touchUp(screenX, screenY, pointer, button);
    }
}
