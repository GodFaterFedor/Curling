package com.coursework.curling.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.coursework.curling.Curling;
import com.coursework.curling.common.Constants;


public class MenuScreen implements Screen {

    private TextureAtlas atlas;
    private Skin skin;

    private Stage stage;
    private Table table;
    private Texture background;
    private OrthographicCamera camera;

    private ButtonGroup<ImageButton> difficultyGroup;
    private ButtonGroup<ImageButton> numberOfPlayersGroup;


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

        addButton("players_label", "players_label", 12, 4, null).expandX().left().pad(15,2,1,0);
        table.row();
        table.add();
        ImageButton onePlayerButton = addButton("1_blue", "1_red", 2, 4, null).pad(0,1,1,1).getActor();
        ImageButton twoPlayerButton = addButton("2_blue", "2_red", 3, 4, null).pad(0,1,1,1).getActor();
        ImageButton threePlayerButton = addButton("3_blue", "3_red", 3, 4, null).pad(0,1,1,1).getActor();
        ImageButton fourPlayerButton = addButton("4_blue", "4_red", 3, 4, null).pad(0,1,1,2).getActor();


        table.row();
        addButton("difficulty_label", "difficulty_label", 16, 4, null).expandX().left().pad(0,2,1,0);
        table.row();
        table.add();
        final ImageButton easyButton = addButton("easy_blue_button", "easy_red_button", 8, 4, null).colspan(2).pad(0,1,0,1).getActor();
        ImageButton hardButton = addButton("hard_blue_button", "hard_red_button", 8, 4, null).colspan(2).pad(0,1,0,2).getActor();

        table.row();
        addButton("play_blue_button", "play_red_button", 10, 5, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameScreen.Difficulty difficulty = easyButton.isChecked() ? GameScreen.Difficulty.Easy: GameScreen.Difficulty.Hard;
                int numberOfPlayers = numberOfPlayersGroup.getCheckedIndex() + 1;
                startGame(difficulty, numberOfPlayers);
            }
        }).colspan(5).expandY().bottom().pad(0,0,5,0);


        stage.addActor(table);

        difficultyGroup = new ButtonGroup<ImageButton>(easyButton, hardButton);
        difficultyGroup.setMaxCheckCount(1);
        difficultyGroup.setMinCheckCount(1);
        difficultyGroup.setUncheckLast(true);
        easyButton.setChecked(true);

        numberOfPlayersGroup = new ButtonGroup<ImageButton>(onePlayerButton, twoPlayerButton, threePlayerButton, fourPlayerButton);
        numberOfPlayersGroup.setMaxCheckCount(1);
        numberOfPlayersGroup.setMinCheckCount(1);
        numberOfPlayersGroup.setUncheckLast(true);
        twoPlayerButton.setChecked(true);

        Gdx.input.setInputProcessor(stage);
    }

    private void startGame(GameScreen.Difficulty difficulty, int numberOfPlayers) {

        ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(difficulty, numberOfPlayers));
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

        //stage.setDebugAll(true);
        stage.draw();
    }

    @Override
    public void dispose() {

        stage.dispose();
        background.dispose();
        atlas.dispose();
    }

    private Cell<ImageButton> addButton(String normal, String selected, float width, float height, ClickListener listener) {

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable(normal);
        style.checked = skin.getDrawable(selected);


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
