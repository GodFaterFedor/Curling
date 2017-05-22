package com.coursework.curling.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coursework.curling.common.Constants;

public class Hud {

    private Stage stage;
    private Viewport viewPort;
    private Label speedLabel;

    public Hud(SpriteBatch spriteBatch) {

        float aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        float width = Constants.FIELD_WIDTH;
        float height = Constants.FIELD_WIDTH / aspect;

        stage = new Stage();
        stage.getViewport().setCamera(new OrthographicCamera(width, height));
        speedLabel = new Label("Text", new Label.LabelStyle(new BitmapFont(), Color.BLUE));

        stage.addActor(speedLabel);
    }

    public void draw() {
        stage.draw();
    }

}
