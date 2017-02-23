package com.coursework.curling.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coursework.curling.Curling;
import com.coursework.curling.models.Entity;
import com.coursework.curling.models.PhysicalEntity;


public class GameScreen implements Screen, InputProcessor {

    private static final float FIELD_WIDTH = 450;
    private static final float FIELD_HEIGHT = 4500;

    private Curling game;
    private OrthographicCamera camera;
    private Entity background;
    private PhysicalEntity car;
    private Vector3 velocity;

    public GameScreen (Curling game) {

        this.game = game;

        Sprite backgroundSprite = new Sprite(new Texture("back.jpg"));
        Sprite carSprite = new Sprite(new Texture("car.jpg"));
        Gdx.input.setInputProcessor(this);
        background = new Entity(backgroundSprite, FIELD_WIDTH, FIELD_HEIGHT);
        car = new PhysicalEntity(carSprite);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        car.applyVelocity(delta);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        background.getSprite().draw(game.batch);
        car.getSprite().draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        float ratio = (float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight();

        camera = new OrthographicCamera(FIELD_WIDTH, FIELD_WIDTH / ratio);
        camera.position.set(FIELD_WIDTH / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        car.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 coordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        velocity = new Vector3(0,0,0);
        car.setCenter(coordinates.x,coordinates.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        Vector3 coordinates = camera.unproject(new Vector3(screenX, screenY, 0));

        car.setVelocity(new Vector2(velocity.x * 2, velocity.y * 2));

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 coordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        velocity = new Vector3(coordinates.x - car.getCenterX(),  coordinates.y - car.getCenterY(), 0);

        car.setCenter(coordinates.x,coordinates.y);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
