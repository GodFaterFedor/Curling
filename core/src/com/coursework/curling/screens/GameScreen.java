package com.coursework.curling.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coursework.curling.Curling;


public class GameScreen implements Screen {

    private static final float FIELD_WIDTH = 450;
    private static final float FIELD_HEIGHT = 4500;

    private Curling game;
    private Texture car;
    private OrthographicCamera camera;

    public GameScreen (Curling game) {

        this.game = game;
        car = new Texture("back.jpg");



    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(camera.position.x, camera.position.y + 2, 0);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(car, 0, 0, FIELD_WIDTH, FIELD_HEIGHT);
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
        car.dispose();
    }
}
