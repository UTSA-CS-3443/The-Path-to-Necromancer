package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.controller.MapManager;
import game.controller.MusicManager;
import game.controller.ScreenManager;
import game.controller.GameController;
import game.controller.InitializeGame;
import game.model.Savestate;
import game.model.sprites.EnemySprites;
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
	 * The manager for the screen transitions
	 */
	private ScreenManager screenManager;
	/**
	 * Whether or not the character is in a chat
	 */
	private boolean inChat;
	/**
	 * The dialogue box that is rendered if the character is in a chat
	 */
	private DialogueBox dialogue;

	/**
	 * This constructor sets up the game and initializes game objects.
	 * 
	 * @param save
	 * @param game
	 */
	public PlayScreen(ScreenManager screenManager, Savestate save) {
		// Grab some parameters passed
		this.screenManager = screenManager;
		this.batch = screenManager.getBatch();

		// Set up the camera
		this.gameCam = new OrthographicCamera(300, 200); // set the camera size
		this.gamePort = new FitViewport(300, 200); // set the viewport size
		this.gameCam.position.set(gamePort.getWorldWidth() / 2, // camera position
				gamePort.getWorldHeight() / 2, 0);

		// Set up the controller and elements of the game
		this.mapManager = new MapManager(this); // initialize the mapManager
		this.b2dr = new Box2DDebugRenderer(); // set up for debugging
		new InitializeGame(this, save); // initialize the game for loading and new
		// game purposes

		this.gameController = new GameController(this); // controller for the game
		Gdx.input.setInputProcessor(gameController); // handle user input
	}

	/**
	 * Unimplemented
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	/**
	 * Determine if the character has moved and move the camera accordingly. Update
	 * the game world and character animations.
	 * 
	 * @param dt
	 *            is the change in time since the last render
	 */
	public void update(float dt) {

		// update position
		if (!inChat)
			this.gameController.handleInput();
		this.mapManager.updateSprites(dt);

		// handle physics
		this.world.step(1 / 60f, 6, 2);

		// camera view
		this.gameController.updateCamera(this.gameCam);
		this.renderer.setView(this.gameCam);
		this.mapManager.combat(dt);
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

		Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		this.renderer.render();
		this.renderer.getBatch().setColor((new Color(screenManager.getSettings().getBrightness(), screenManager.getSettings().getBrightness()
															,screenManager.getSettings().getBrightness(), 1f)));
		// render the physics
		//b2dr.render(world, gameCam.combined);

		// draw characters
		this.batch.setProjectionMatrix(this.gameCam.combined);
		this.batch.begin();
		for (GameSprites sprite : this.mapManager.getSprites()) {
		
			sprite.draw(batch);
			sprite.setColor((new Color(screenManager.getSettings().getBrightness(), screenManager.getSettings().getBrightness()
					,screenManager.getSettings().getBrightness(), 1f)));
		}

		this.batch.end();
	
		// render dialogue if the player is in chat
		if (inChat) {
			this.dialogue.render(delta);

		}
	}

	/**
	 * Change the size of the screen
	 */
	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
	}

	/**
	 * Unimplemented
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

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
	 * Set it so that the player starts to chat with somebody. If not initialized,
	 * initialize the DialogueBox and set the chat to render.
	 */
	public void startChat() {
		this.player.setVelocity(new Vector2(0, 0));
		this.inChat = true;
		if (this.dialogue == null)
			this.dialogue = new DialogueBox(this.screenManager);
		this.dialogue.setInput();
	}

	/**
	 * Set whether or not in-game dialogue is occurring
	 * 
	 * @param b
	 *            is the truth value of dialogueW
	 */
	public void setDialogue(boolean b) {
		this.inChat = b;
	}

	/**
	 * Get the box used for dialogue
	 * 
	 * @return the dialogue box
	 */
	public DialogueBox getDialogueBox() {
		return this.dialogue;
	}

	/**
	 * Return the Settings object
	 * 
	 * @return the settings object
	 */
	public Settings getSettings() {
		return this.screenManager.getSettings();
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
	 * Set the player character. If the player has already been set, we need to
	 * replace the player in other classes.
	 * 
	 * @param player
	 *            is the player character
	 */
	public void setPlayer(Player player) {
		float x = 100;
		float y = 100;
		if (this.player != null) {
			x = this.player.getX() + this.player.getWidth() / 2;
			y = this.player.getY() + this.player.getHeight() / 2;
			if (world != null)
				this.world.destroyBody(this.player.getBody());
			if (this.mapManager.getSprites().contains(this.player)) {
				this.mapManager.getSprites().remove(this.player);
				this.mapManager.getSprites().add(player);
			}
			this.gameController.setPlayer(player);
			this.mapManager.setPlayer(player);
		}

		this.player = player;
		if (this.world != null)
			this.player.defineBody(this.world, (int) x, (int) y);
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

	/**
	 * Get the viewport the game uses
	 * 
	 * @return the viewport
	 */
	public Viewport getViewPort() {
		return this.gamePort;
	}

	/**
	 * Start the combat sequence
	 */
	public void beginCombat() {
		this.screenManager.setCombat();
	}

	/**
	 * Get a random EnemySprite
	 * 
	 * @return the enemy sprite
	 */
	public EnemySprites getEnemy() {
		return this.mapManager.getEnemy();
	}

	/**
	 * Return the name of the current map
	 * 
	 * @return the map name
	 */
	public String getMapName() {
		return this.mapManager.getMapName();
	}

	/**
	 * Start the in-game menu
	 */
	public void setMenu() {
		this.screenManager.setMenu();
	}

	/**
	 * Set the dialogue box for the main game screen
	 * 
	 * @param box
	 *            is the dialogue box
	 */
	public void setDialogueBox(DialogueBox box) {
		this.dialogue = box;
		if (box == null)
			this.inChat = false;
	}

	/**
	 * Return the MusicManager for the game
	 * 
	 * @return the musicManager
	 */
	public MusicManager getMusicManager() {
		return this.screenManager.getMusicManager();
	}

	/**
	 * Begin combat with a fixed enemy
	 * 
	 * @param enemy
	 *            the enemy to begin combat with
	 */
	public void enemyCombat(EnemySprites enemy) {
		this.screenManager.setCombat(enemy);
	}

	/**
	 * End the game
	 */
	public void endGame() {
		this.screenManager.endGame();
	}
}
