package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import game.model.PathToNecromancer;
import game.model.sprites.ObjectSprites;
import game.model.sprites.player.Player;

/**
 * The in-game menu screen. Contains stages for the character's stats, the
 * settings screen, the controls, and the save and quit menu.
 * 
 * @author enigma-phi
 *
 */
public class Menu implements Screen {
    /**
     * The PlayScreen for the main game
     */
    private PlayScreen screen;
    /**
     * The main game class
     */
    private PathToNecromancer game;
    /**
     * The current stage for the menu
     */
    private Stage stage;
    /**
     * The SpriteBatch used to render sprites
     */
    private SpriteBatch batch;
    /**
     * The player character
     */
    private Player player;
    /**
     * The settings button
     */
    private TextButton settings;
    /**
     * The TextButtonStyle for the settings button
     */
    private TextButtonStyle settingsStyle;
    /**
     * The button that returns the user to the main game
     */
    private TextButton toGame;
    /**
     * The TextButtonStyle for the button that returns the user to the main game
     */
    private TextButtonStyle toGameStyle;
    /**
     * The button that takes the user to the save and quit menu
     */
    private TextButton saveQuit;
    /**
     * The TextButtonStyle for the button that takes the user to the save and quit
     * menu
     */
    private TextButtonStyle saveQuitStyle;
    /**
     * The button that saves the user's current game state
     */
    private TextButton saveButton;
    /**
     * The TextButtonStyle for the button that saves the user's current game state
     */
    private TextButtonStyle saveButtonStyle;
    /**
     * The button that quits the game
     */
    private TextButton quitButton;
    /**
     * The TextButtonStyle that quits the game
     */
    private TextButtonStyle quitButtonStyle;
    /**
     * The button that takes the user to the menu with the character's information
     */
    private TextButton character;
    /**
     * The TextButtonStyle for the button that takes the user to the menu with the
     * character's information
     */
    private TextButtonStyle characterStyle;
    /**
     * A TextureAtlas of all of the images for the buttons
     */
    private TextureAtlas buttonAtlas;
    /**
     * If the user hits escape to enter the menu, without this boolean the menu will
     * automatically return to the main game. This ensures that this does not happen
     */
    private boolean stillPressed;
    /**
     * The font used in the menus
     */
    private BitmapFont font;
    /**
     * The left dragon texture for the save and quit stage
     */
    private ObjectSprites dragon1;
    /**
     * The right dragon texture for the save and quit stage
     */
    private ObjectSprites dragon2;
    /**
     * A boolean used to make sure the dragons are only rendered in the save and
     * quit stage.
     */
    private boolean isSaveQuit;

    /**
     * Create the menu screen
     * 
     * @param screen
     *            is the PlayScreen the user is on
     * @param game
     *            is the main game class
     */
    public Menu(PlayScreen screen, PathToNecromancer game) {
        this.screen = screen;
        this.game = game;
        this.batch = game.getBatch();
        this.player = screen.getPlayer();
        // set up the font
        this.font = new BitmapFont(Gdx.files.internal("MagicCardFont.fnt"), false);
        this.font.getData().setScale(0.6f);
        // set the buttons for the menus
        this.setButtons();
        // set the main menu stage up
        this.mainMenu();
        this.stillPressed = true;

        // set up the dragon textures
        float x = (this.player.getBody()).getPosition().x;
        float y = (this.player.getBody()).getPosition().y;
        Texture dragon = new Texture("Dragon Image.png");
        dragon1 = new ObjectSprites(dragon, 0, 0);
        dragon1.setBounds(x - 85 - 60, y - 50, 120, 120);
        dragon2 = new ObjectSprites(dragon, 0, 0);
        dragon2.setFlip(true, false);
        dragon2.setBounds(x + 85 - 60, y - 50, 120, 120);
    }

    /**
     * Set up the buttons for the menu. These buttons are: saveQuit access the save
     * and quit menu settings access the settings menu character access the main
     * menu toGame return to the main game save save the game quit quit the game
     */
    private void setButtons() {
        buttonAtlas = new TextureAtlas("Buttons/Buttons.pack");
        Skin skin = new Skin();
        skin.addRegions(buttonAtlas);

        // Set the button for returning to the game
        toGameStyle = new TextButtonStyle();
        toGameStyle.font = font;
        toGameStyle.up = skin.getDrawable("ReturntoGameButton");
        toGameStyle.down = skin.getDrawable("ReturntoGameButton");
        toGameStyle.checked = skin.getDrawable("ReturntoGameButton");
        toGame = new TextButton("", toGameStyle);
        toGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                returnToGame();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
            }
        });

        // Set the button for accessing the settings screen
        settingsStyle = new TextButtonStyle();
        settingsStyle.font = font;
        settingsStyle.up = skin.getDrawable("SettingsButton");
        settingsStyle.down = skin.getDrawable("SettingsButton");
        settingsStyle.checked = skin.getDrawable("SettingsButton");
        settings = new TextButton("", settingsStyle);
        settings.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                settingsMenu();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
            }
        });

        // Set the button for accessing the save and quit screen
        saveQuitStyle = new TextButtonStyle();
        saveQuitStyle.font = font;
        saveQuitStyle.up = skin.getDrawable("Save&Quit");
        saveQuitStyle.down = skin.getDrawable("Save&Quit");
        saveQuitStyle.checked = skin.getDrawable("Save&Quit");
        saveQuit = new TextButton("", saveQuitStyle);
        saveQuit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                saveMenu();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
            }
        });

        // Set the button for accessing the main screen
        characterStyle = new TextButtonStyle();
        characterStyle.font = font;
        characterStyle.up = skin.getDrawable("CharacterButton");
        characterStyle.down = skin.getDrawable("CharacterButton");
        characterStyle.checked = skin.getDrawable("CharacterButton");
        character = new TextButton("", characterStyle);
        character.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mainMenu();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
            }
        });

        // Set the button for saving the game
        saveButtonStyle = new TextButtonStyle();
        saveButtonStyle.font = font;
        saveButtonStyle.up = skin.getDrawable("SaveButton");
        saveButtonStyle.down = skin.getDrawable("SaveButton");
        saveButtonStyle.checked = skin.getDrawable("SaveButton");
        saveButton = new TextButton("", saveButtonStyle);
        saveButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                returnToGame();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
            }
        });

        // Set the button for quitting the game
        quitButtonStyle = new TextButtonStyle();
        quitButtonStyle.font = font;
        quitButtonStyle.up = skin.getDrawable("QuitGameButton");
        quitButtonStyle.down = skin.getDrawable("QuitGameButton");
        quitButtonStyle.checked = skin.getDrawable("QuitGameButton");
        quitButton = new TextButton("", quitButtonStyle);
        quitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
            }
        });
    }

    /**
     * Set up the main menu stage for the menu screen
     */
    private void mainMenu() {
        this.isSaveQuit = false;
        this.stage = new Stage(screen.getViewPort());
        Gdx.input.setInputProcessor(stage);

        // Get all of the labels for your table to render
        Label classLabel = new Label("Class:", new Label.LabelStyle(font, Color.RED));
        Label levelLabel = new Label("Level:", new Label.LabelStyle(font, Color.WHITE));
        Label expLabel = new Label("Current Experience:", new Label.LabelStyle(font, Color.WHITE));
        Label strLabel = new Label("Strength:", new Label.LabelStyle(font, Color.WHITE));
        Label intLabel = new Label("Intelligence:", new Label.LabelStyle(font, Color.WHITE));
        Label dexLabel = new Label("Dexterity:", new Label.LabelStyle(font, Color.WHITE));
        Label lckLabel = new Label("Luck:", new Label.LabelStyle(font, Color.WHITE));
        Label classVal = new Label(this.player.getCharClass(), new Label.LabelStyle(font, Color.RED));
        Label levelVal = new Label(String.valueOf(this.player.getLevel()), new Label.LabelStyle(font, Color.WHITE));
        Label dexVal = new Label(String.valueOf(this.player.getDexterity()), new Label.LabelStyle(font, Color.WHITE));
        Label expVal = new Label(String.valueOf(this.player.getExperience()), new Label.LabelStyle(font, Color.WHITE));
        Label strVal = new Label(String.valueOf(this.player.getStrength()), new Label.LabelStyle(font, Color.WHITE));
        Label intVal = new Label(String.valueOf(this.player.getIntelligence()),
                new Label.LabelStyle(font, Color.WHITE));
        Label lckVal = new Label(String.valueOf(this.player.getLuck()), new Label.LabelStyle(font, Color.WHITE));

        // Fill the table with values
        Table table = new Table();
        table.setFillParent(true);
        table.padTop(10);
        table.center().top();
        table.add(classLabel).left().prefWidth(200);
        table.add(classVal).right().prefWidth(50);
        table.row();
        table.add(levelLabel).left();
        table.add(levelVal).right();
        table.row();
        table.add(expLabel).left();
        table.add(expVal).right();
        table.row();
        table.add(strLabel).left();
        table.add(strVal).right();
        table.row();
        table.add(intLabel).left();
        table.add(intVal).right();
        table.row();
        table.add(dexLabel).left();
        table.add(dexVal).right();
        table.row();
        table.add(lckLabel).left();
        table.add(lckVal).right();
        table.row().height(100);
        stage.addActor(table);

        // Create the table for the buttons and fill it up
        Table table2 = new Table();
        table2.setFillParent(true);
        table2.bottom().padBottom(20);
        table2.add(settings).width(60).height(20).padRight(30);
        table2.add(saveQuit).width(60).height(20).padRight(30);
        table2.add(toGame).width(60).height(20);
        stage.addActor(table2);
    }

    private void settingsMenu() {
        this.isSaveQuit = false;
        this.stage = new Stage(screen.getViewPort());
        Gdx.input.setInputProcessor(stage);

        // Get all of the labels for your table to render
        Label classLabel = new Label("Class:", new Label.LabelStyle(font, Color.RED));
        Label levelLabel = new Label("Level:", new Label.LabelStyle(font, Color.WHITE));
        Label expLabel = new Label("Current Experience:", new Label.LabelStyle(font, Color.WHITE));
        Label strLabel = new Label("Strength:", new Label.LabelStyle(font, Color.WHITE));
        Label intLabel = new Label("Intelligence:", new Label.LabelStyle(font, Color.WHITE));
        Label dexLabel = new Label("Dexterity:", new Label.LabelStyle(font, Color.WHITE));
        Label lckLabel = new Label("Luck:", new Label.LabelStyle(font, Color.WHITE));
        Label classVal = new Label(this.player.getCharClass(), new Label.LabelStyle(font, Color.RED));
        Label levelVal = new Label(String.valueOf(this.player.getLevel()), new Label.LabelStyle(font, Color.WHITE));
        Label dexVal = new Label(String.valueOf(this.player.getDexterity()), new Label.LabelStyle(font, Color.WHITE));
        Label expVal = new Label(String.valueOf(this.player.getExperience()), new Label.LabelStyle(font, Color.WHITE));
        Label strVal = new Label(String.valueOf(this.player.getStrength()), new Label.LabelStyle(font, Color.WHITE));
        Label intVal = new Label(String.valueOf(this.player.getIntelligence()),
                new Label.LabelStyle(font, Color.WHITE));
        Label lckVal = new Label(String.valueOf(this.player.getLuck()), new Label.LabelStyle(font, Color.WHITE));

        // Fill the table with values
        Table table = new Table();
        table.setFillParent(true);
        table.center().top();
        table.padTop(10);
        table.add(classLabel).left().prefWidth(200);
        table.add(classVal).right().prefWidth(50);
        table.row();
        table.add(levelLabel).left();
        table.add(levelVal).right();
        table.row();
        table.add(expLabel).left();
        table.add(expVal).right();
        table.row();
        table.add(strLabel).left();
        table.add(strVal).right();
        table.row();
        table.add(intLabel).left();
        table.add(intVal).right();
        table.row();
        table.add(dexLabel).left();
        table.add(dexVal).right();
        table.row();
        table.add(lckLabel).left();
        table.add(lckVal).right();
        table.row().height(100);
        stage.addActor(table);

        // Create the table for the buttons and fill it up
        Table table2 = new Table();
        table2.setFillParent(true);
        table2.bottom().padBottom(20);
        table2.add(character).width(60).height(20).padRight(30);
        table2.add(saveQuit).width(60).height(20).padRight(30);
        table2.add(toGame).width(60).height(20);
        stage.addActor(table2);
    }

    /**
     * Create the menu for the save Menu. Includes a label, a save button, and a
     * quit button
     */
    private void saveMenu() {
        this.isSaveQuit = true;
        this.stage = new Stage(screen.getViewPort());
        Gdx.input.setInputProcessor(stage);

        Label saveQuestionLabel = new Label("PathToTheNecromancer", new Label.LabelStyle(font, Color.RED));

        // Fill the table with values
        Table table = new Table();
        table.setFillParent(true);
        table.top().padTop(10);
        table.add(saveQuestionLabel).padBottom(30);
        table.row().height(60);
        table.add(saveButton).center().width(60).height(20).padBottom(30);
        table.row().height(60);
        table.add(quitButton).center().width(60).height(20);
        stage.addActor(table);

        // Create the table for the buttons and fill it up
        Table table2 = new Table();
        table2.setFillParent(true);
        table2.bottom().padBottom(20);
        table2.add(settings).width(60).height(20).padRight(30);
        table2.add(character).width(60).height(20).padRight(30);
        table2.add(toGame).width(60).height(20);
        stage.addActor(table2);

    }

    /**
     * Returns the user to the PlayScreen and resets the input processor
     */
    private void returnToGame() {
        game.setScreen(screen);
        Gdx.input.setInputProcessor(screen.getInputProcessor());
    }

    /**
     * Unimplemented
     */
    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    /**
     * Unimplemented
     */
    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    /**
     * Unimplemented
     */
    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    /**
     * Render the Menu Screen
     */
    @Override
    public void render(float delta) {
        // determine whether to return to the game based off of the keys
        // the user presses
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            if (!stillPressed)
                returnToGame();
        } else
            stillPressed = false;

        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (isSaveQuit) {
            this.batch.begin();
            this.dragon1.draw(batch);
            this.dragon2.draw(batch);
            this.batch.end();
        }
        stage.draw();

    }

    /**
     * Resize the view port
     */
    @Override
    public void resize(int width, int height) {
        this.screen.getViewPort().update(width, height);
    }

    /**
     * Unimplemented
     */
    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    /**
     * Unimplemented
     */
    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

}
