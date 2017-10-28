package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.controller.MapManager;
import game.controller.GameController;
import game.controller.InitializeGame;
import game.model.PathToNecromancer;
import game.model.sprites.GameSprites;
import game.model.sprites.player.Player;
import javaFX.model.Settings;

/**
 * This class handles the main game. It initializes the game objects and handles
 * rendering of the main game.
 * 
 * @author enigma-phi
 *
 */
public class PlayScreen implements Screen {
    /**
     * The game class
     */
    private PathToNecromancer game;
    /**
     * World holding all collision objects
     */
    private World world;
    /**
     * Used for debugging collision and box2d objects
     */
    private Box2DDebugRenderer b2dr;
    /**
     * Camera following the player so that the player is always in the center of the
     * screen
     */
    private OrthographicCamera gameCam;
    /**
     * Viewport for the game screen
     */
    private Viewport gamePort;
    /**
     * Renders tiled maps
     */
    private OrthogonalTiledMapRenderer renderer;
    /**
     * Manage the current map the user is on for the game's tiled map renderer
     */
    private MapManager mapManager;
    /**
     * The user's character
     */
    private Player player;
    /**
     * Update the game based on the user's input
     */
    private GameController gameController;
    /**
     * The sprite batch used throughout the game to draw the characters
     */
    private SpriteBatch batch;
    /**
     * The settings for the game
     */
    private Settings settings;

    /**
     * This constructor sets up the game and initializes game objects.
     * 
     * @param game
     */
    public PlayScreen(PathToNecromancer game) {
        // Grab some parameters passed
        this.batch = game.getBatch();
        this.settings = game.getSettings();
        this.game = game;

        // Set up the camera
        gameCam = new OrthographicCamera(300, 200); // set the camera size
        gamePort = new FitViewport(300, 200); // set the viewport size
        gameCam.position.set(gamePort.getWorldWidth() / 2, // camera position
                gamePort.getWorldHeight() / 2, 0);

        // Set up the controller and elements of the game
        mapManager = new MapManager(this); // initialize the mapManager
        b2dr = new Box2DDebugRenderer(); // set up for debugging
        new InitializeGame(this); // initialize the game for loading and new
                                  // game purposes

        gameController = new GameController(this.player); // controller for the game
        Gdx.input.setInputProcessor(gameController); // handle user input
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    /**
     * Determine if the character has moved and move the camera accordingly. Update
     * the game world and character animations.
     * 
     * @param dt
     */
    public void update(float dt) {
        // update position
        this.gameController.handleInput();
        this.player.update(dt);

        // handle physics
        this.world.step(1 / 60f, 6, 2);

        // camera view
        this.gameController.updateCamera(this.gameCam);
        this.renderer.setView(this.gameCam);
    }

    /**
     * Render the game world
     */
    @Override
    public void render(float delta) {

        // update world
        this.update(delta);

        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render the map
        this.renderer.render();

        // render the physics
        // b2dr.render(world, gameCam.combined);

        // draw characters
        this.batch.setProjectionMatrix(this.gameCam.combined);
        this.batch.begin();

        for (GameSprites sprite : this.mapManager.getSprites()) {
            sprite.draw(batch);
        }

        this.batch.end();

    }

    /**
     * Changing the size of the screen
     */
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    /**
     * Dispose of garbage
     */
    @Override
    public void dispose() {
        mapManager.dispose();
        world.dispose();
        renderer.dispose();
        b2dr.dispose();
    }

    /**
     * Return the Settings object
     * 
     * @return the settings object
     */
    public Settings getSettings() {
        return this.settings;
    }

    /**
     * Get the renderer for the maps
     * 
     * @return the OrthogonalTiledMapRenderer renderer
     */
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    /**
     * Set the renderer for the maps
     * 
     * @param renderer
     *            is the new renderer to set the OrthogonalTiledMapRenderer to
     */
    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Set the player character
     * 
     * @param player
     *            is the player character
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the player character
     * 
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Get the world for the physics engine
     * 
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Set the world for the physics engine
     * 
     * @param world
     *            is the new world to set the game world to
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Get the map manager for the game
     * 
     * @return the mapManager
     */
    public MapManager getMapManager() {
        return this.mapManager;
    }

    /**
     * Get the game controller for the whole game
     * 
     * @return the game controller
     */
    public GameController getInputProcessor() {
        return this.gameController;
    }
}
