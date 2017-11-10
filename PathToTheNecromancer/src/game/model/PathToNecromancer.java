package game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.controller.ScreenManager;
import game.view.PlayScreen;
import javaFX.model.Settings;

/**
 * The initial game class.
 * 
 * @author enigma-phi
 *
 */
public class PathToNecromancer extends Game {
    /**
     * SpriteBatch for rendering sprites
     */
    private SpriteBatch batch;
    /**
     * The game's screen
     */
    private PlayScreen playscreen;
    /**
     * The game's settings
     */
    private Settings settings;
    /**
     * The screen manager for the game
     */
    private ScreenManager screenManager;
    /**
     * Constructor for the game
     * 
     * @param settings
     *            is the settings object for the game
     */
    public PathToNecromancer(Settings settings) {
        super();
        this.settings = settings;
    }

    /**
     * Initial game set up
     */
    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.screenManager = new ScreenManager(this);

    }

    /**
     * Get the SpriteBatch
     * 
     * @return the batch
     */
    public SpriteBatch getBatch() {
        return this.batch;
    }

    /*
     * Set the SpriteBatch
     * 
     * @param is the sprite batch to set to
     */
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * Get the Settings object for the game
     * 
     * @return the settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Set the Settings object
     * 
     * @param settings
     *            is the settings object to set to
     */
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    /**
     * Render to the screen
     */
    @Override
    public void render() {
    	float dt = Gdx.graphics.getDeltaTime();
        screenManager.render(dt);
    }

    /**
     * Dispose of garbage
     */
    @Override
    public void dispose() {
        batch.dispose();
        screenManager.dispose();
    }
}
