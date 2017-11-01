package game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
        batch = new SpriteBatch();
        this.playscreen = new PlayScreen(this);

        setScreen(this.playscreen);
    }

    /**
     * Get the SpriteBatch
     * 
     * @return the batch
     */
    public SpriteBatch getBatch() {
        return batch;
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
        super.render();
    }

    /**
     * Dispose of garbage
     */
    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
