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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coursework.curling.Curling;
import com.coursework.curling.common.Constants;
import com.coursework.curling.models.Entity;
import com.coursework.curling.models.PhysicalEntity;
import com.coursework.curling.states.StateManager;


public class GameScreen implements Screen {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Curling game;
    private StateManager stateManager;
    private OrthographicCamera camera;


    public GameScreen(Curling game) {

        this.game = game;
        world = new World(new Vector2(0,0), false);

        this.stateManager = new StateManager(this.game, this.world);
        this.debugRenderer = new Box2DDebugRenderer();
        this.camera = new OrthographicCamera();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        debugRenderer.render(world, camera.combined);
        this.stateManager.render(dt);

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
