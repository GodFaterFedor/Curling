package com.coursework.curling.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.coursework.curling.Curling;
import com.coursework.curling.common.Constants;
import com.coursework.curling.models.Font;
import com.coursework.curling.states.StateManager;

public class GameScreen implements Screen {

    public enum Difficulty {
        Easy,
        Hard
    }

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private StateManager stateManager;
    private OrthographicCamera camera;
    private Texture background;
    private Difficulty difficulty = Difficulty.Easy;
    private int numberOfPlayers = 2;

    public GameScreen(Difficulty difficulty, int numberOfPlayers) {

        this.difficulty = difficulty;
        this.numberOfPlayers = numberOfPlayers;

        background = new Texture("background.png");

        world = new World(new Vector2(0,0), false);

        float aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        float width = Constants.FIELD_WIDTH;
        float height = Constants.FIELD_WIDTH / aspect;

        this.camera = new OrthographicCamera(width, height);
        this.camera.position.set(this.camera.viewportWidth / 2, 370 + this.camera.viewportHeight / 2,0);
        this.camera.update();

        stateManager = new StateManager(this, numberOfPlayers);

        this.debugRenderer = new Box2DDebugRenderer();

        addBorder(-10, 0, 10, Constants.FIELD_HEIGHT);
        addBorder(Constants.FIELD_WIDTH + 10, 0, 10, Constants.FIELD_HEIGHT);
        addBorder(0, -10, Constants.FIELD_WIDTH, 10);
        addBorder(0, Constants.FIELD_HEIGHT + 10, Constants.FIELD_WIDTH, 10);

    }

    public void addBorder(float x, float y, float width, float heigth) {
        FixtureDef fixtureDef = new FixtureDef();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        Body body;
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, heigth);

        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
    }

    public World getWorld(){
        return world;
    }

    public OrthographicCamera getCamera(){
        return camera;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(dt, 8, 3);
        debugRenderer.render(world, camera.combined);
        Curling.batch.setProjectionMatrix(camera.combined);

        Font text = new Font(stateManager.getSpeed());


        Curling.batch.begin();
        Curling.batch.draw(background, 0, 0, Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);


        this.stateManager.render(dt);
        if (difficulty == Difficulty.Easy) {

            int i = 5;
            for(Texture texture: text.images()) {

                Curling.batch.draw(texture, i, camera.position.y + camera.viewportHeight / 2 - 10, 3, 4);
                i += 5;
            }
            Curling.batch.end();
        }


    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int  getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

        //stateManager.saveToFile();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
