package com.coursework.curling.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.coursework.curling.Curling;


public class MenuScreen implements Screen {

    private TextureAtlas atlas;
    private Skin skin;

    private Stage stage;
    private Table table;

    @Override
    public void show() {

        atlas = new TextureAtlas("menu.pack");
        skin = new Skin(atlas);

        stage = new Stage();
        table = new Table(skin);
        //table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.setFillParent(true);
        addButton("easy_blue_button", 100, 50, 1, Align.top, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        addButton("easy_blue_button", 100, 50, 1, Align.top, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        table.row();
        addButton("easy_blue_button", 100, 50, 2, Align.left, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        Curling.batch.begin();
        stage.draw();
        Curling.batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();

    }

    private void addButton(String text, float width, float height, int span, int align, ClickListener listener) {

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("easy_blue_button");
        style.down = skin.getDrawable("easy_red_button");

        ImageButton button = new ImageButton(style);

        if (listener != null) button.addListener(listener);
        table.add(button).width(width).height(30).colspan(span).align(align);
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
