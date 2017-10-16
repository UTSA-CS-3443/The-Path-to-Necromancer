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

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Settings getSettings() {
        return settings;
    }

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
