package com.coursework.curling.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.coursework.curling.Curling;
import com.coursework.curling.common.Constants;

import javafx.scene.control.Tab;


public class MenuScreen implements Screen {

    private TextureAtlas atlas;
    private Skin skin;

    private Stage stage;
    private Table table;
    private Texture background;
    private OrthographicCamera camera;


    @Override
    public void show() {

        float aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        float width = Constants.FIELD_WIDTH;
        float height = Constants.FIELD_WIDTH / aspect;

        this.camera = new OrthographicCamera(width, height);
        this.camera.position.set(this.camera.viewportWidth / 2, this.camera.viewportHeight / 2,0);
        this.camera.update();

        background = new Texture("menu_background.png");

        atlas = new TextureAtlas("menu.pack");
        skin = new Skin(atlas);

        stage = new Stage(new StretchViewport(camera.viewportWidth, camera.viewportHeight, camera), Curling.batch);
        table = new Table(skin);
        table.setBounds(0, 0, width, height);
        table.setFillParent(true);

        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();


        table.row().fillX();

        addButton("players_label", "players_label", 10, 4, null).expandX().left().pad(15,2,1,0);
        table.row();
        table.add();
        addButton("players_label", "players_label", 5, 4, null).pad(0,0,1,0);
        addButton("players_label", "players_label", 5, 4, null).pad(0,0,1,0);
        addButton("players_label", "players_label", 5, 4, null).pad(0,0,1,0);
        addButton("players_label", "players_label", 5, 4, null).pad(0,0,1,2);


        table.row();
        addButton("difficulty_label", "difficulty_label", 14, 4, null).expandX().left().pad(0,2,1,0);
        table.row();
        table.add();
        addButton("easy_blue_button", "easy_red_button", 8, 4, null).colspan(2);
        addButton("hard_blue_button", "hard_red_button", 8, 4, null).colspan(2).pad(0,0,0,2);

        table.row();
        addButton("play_button", "play_button", 10, 5, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        }).colspan(5).expandY().bottom().pad(0,0,5,0);


        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        Curling.batch.setProjectionMatrix(camera.combined);

        Curling.batch.begin();
        Curling.batch.draw(background, 0, 0, Constants.FIELD_WIDTH, Constants.FIELD_WIDTH * 3);
        Curling.batch.end();

        stage.setDebugAll(true);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private Cell<ImageButton> addButton(String normal, String selected, float width, float height, ClickListener listener) {

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable(normal);
        style.down = skin.getDrawable(selected);

        ImageButton button = new ImageButton(style);

        if (listener != null) button.addListener(listener);
        return table.add(button).width(width).height(height);
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
}
