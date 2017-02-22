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

    private Curling game;
    private Texture car;
    private OrthographicCamera camera;
    private Viewport viewport;

    public GameScreen (Curling game) {

        this.game = game;
        car = new Texture("back.jpg");
        camera = new OrthographicCamera();
        viewport = new FillViewport(450 , 4500, camera);
        viewport.apply();
    }


    @Override
    public void show() {
        camera.position.set(viewport.getWorldWidth() / 2 , camera.viewportHeight / 2 , 0);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(car, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);

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
