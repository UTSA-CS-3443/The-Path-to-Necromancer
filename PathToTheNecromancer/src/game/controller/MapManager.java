package game.controller;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.story.Interaction;
import game.controller.story.StoryManager;
import game.model.maps.GameMaps;
import game.model.sprites.EnemySprites;
import game.model.sprites.GameSprites;
import game.model.sprites.player.Player;
import game.view.DialogueBox;
import game.view.PlayScreen;

/**
 * Manage the map the PlayScreen is currently rendering.
 * 
 * @author enigma-phi
 *
 */
public class MapManager {
	/**
	 * The screen that the MapManager manages
	 */
	private PlayScreen screen;
	/**
	 * The type of map the manager currently has
	 */
	private GameMaps gameMap;
	/**
	 * Load the TiledMaps
	 */
	private TmxMapLoader mapLoader;
	/**
	 * Time count used to ensure that combat does not happen too often
	 */
	private float time;
	/**
	 * The current story manager for the game
	 */
	private StoryManager storyManager;

	/**
	 * Initialize the MapManager
	 * 
	 * @param screen
	 *            is the screen for the MapManager to manage
	 */
	public MapManager(PlayScreen screen) {
		this.screen = screen;
		this.mapLoader = new TmxMapLoader();
		this.storyManager = new StoryManager(this);
	}

	/**
	 * Set the map currently displayed by the screen and set the position of the
	 * player character.
	 * 
	 * @param gameMap
	 *            is the map to change the screen to
	 * @param x
	 *            is the new x-position for the player's character
	 * @param y
	 *            is the new y-position for the player's character
	 */
	public void setMap(GameMaps gameMap, int x, int y) {
		// Reset the world
		World world = new World(new Vector2(0, 0), true);
		world.setContactListener(new WorldContactListener());
		this.screen.setWorld(world);

		// Set and load the map.
		this.gameMap = gameMap;
		this.gameMap.loadMap(mapLoader);

		// Change the map renderer
		this.screen.setRenderer(new OrthogonalTiledMapRenderer(gameMap.getMap()));

		// generate the physics for the world
		this.gameMap.generateWorld(world);

		// set the player's position
		this.screen.getPlayer().defineBody(world, x, y);

		// add the player to the arraylist of sprites
		this.gameMap.addSprite(this.screen.getPlayer());

		// set up the sprites for the map
		this.gameMap.createSprites(world);

		// update the story manager
		this.storyManager.updateWorld();
	}

	/**
	 * Get an enemy from a specific map Note: returns null if the map does not have
	 * an enemy
	 * 
	 * @return an enemy
	 */
	public EnemySprites getEnemy() {
		int difficulty = 0;
		switch(this.screen.getSettings().getDifficulty()) {
		case Easy:
			difficulty = 0;
			break;
		case Medium:
			difficulty = 3;
			break;
		case Hard:
			difficulty = 6;
			break;
		case Insane:
			difficulty = 9;
			break;
		default:
			difficulty = 0;
			break;
		}
		return this.gameMap.getEnemy(this.screen.getPlayer().getLevel()+difficulty);
	}

	/**
	 * Get all of the sprites from a specific map sorted by height. Greater
	 * y-coordinate sprites are rendered first.
	 * 
	 * @return all of the sprites with the sprites at the top first
	 */
	public ArrayList<GameSprites> getSprites() {
		return this.gameMap.getSprites();
	}

	/**
	 * Add a sprite to the arraylist of sprites
	 * 
	 * @param sprite
	 *            the sprite to add
	 */
	public void addSprite(GameSprites sprite) {
		this.gameMap.addSprite(sprite);
	}

	/**
	 * Throw away the garbage.
	 */
	public void dispose() {
		this.gameMap.dispose();
	}

	/**
	 * Determine if the player has entered combat. If so, start a new combat scene.
	 * 
	 * @param the
	 *            change in time since the last render
	 */
	public void combat(float dt) {
		Player player = this.screen.getPlayer();
		this.time -= dt;
		if (this.time <= 0 && player.getVelocity() != 0 && this.gameMap.hasCombat()) {
			this.time = 5;
			screen.beginCombat();
		}
	}

	/**
	 * Begin Combat with a specific enemy
	 * @param enemy is the enemy to begin combat with
	 */
	public void enemyCombat(EnemySprites enemy) {
		this.screen.enemyCombat(enemy);
	}
	/**
	 * Update each sprite animation
	 * 
	 * @param the
	 *            change in time since the last render
	 */
	public void updateSprites(float dt) {
		for (GameSprites sprite : this.getSprites()) {
			sprite.update(dt);
		}
		try {
			this.storyManager.act(dt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Get the name of the current map
	 * 
	 * @return the map's name
	 */
	public String getMapName() {
		return this.gameMap.getMapName();
	}

	/**
	 * Get the player's character
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return this.screen.getPlayer();
	}

	/**
	 * Get the current World for the game
	 * 
	 * @return the world
	 */
	public World getWorld() {
		return this.screen.getWorld();
	}

	/**
	 * Start a chat sequence
	 */
	public void startChat() {
		this.screen.startChat();
	}

	/**
	 * Set the interaction for the dialogue
	 * 
	 * @param interact
	 *            the interaction to set to
	 */
	public void setInteraction(Interaction interact) {
		this.screen.getDialogueBox().setInteraction(interact);
	}

	/**
	 * Get the dialogue box for the screen
	 * 
	 * @return the dialogue box
	 */
	public DialogueBox getDialogueBox() {
		return this.screen.getDialogueBox();
	}
	/**
	 * Set the player for everything
	 * @param player is the player to set to
	 */
	public void setPlayer(Player player) {
		if(this.storyManager != null)
			this.storyManager.setPlayer(player);
	}
	/**
	 * Get the main screen
	 * @return the main play screen
	 */
	public PlayScreen getMainScreen() {
		return this.screen;
	}
	/**
	 * Get the music manager for the game
	 * @return the music manager
	 */
	public MusicManager getMusicManager() {
		return this.screen.getMusicManager();
	}
}
