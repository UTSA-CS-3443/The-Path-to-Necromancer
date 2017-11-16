package game.controller.story;

import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.sprites.player.Player;

/**
 * Manages the characters and the actions in the story based off of the given
 * map and the player's character
 * 
 * @author enigma-phi
 *
 */
public class StoryManager {
	/*
	 * The manager of the maps
	 */
	private MapManager manager;
	/**
	 * The name of the current map
	 */
	private String mapName;
	/**
	 * The player character
	 */
	private Player player;
	/**
	 * The world the player is in
	 */
	private World world;
	/**
	 * The story actor whose actions vary based off of the given map
	 */
	private StoryActor storyActor;
	/**
	 * The actor for the given class
	 */
	private Actor actor;
	/**
	 * Constructor for the story manager
	 * 
	 * @param manager
	 *            is the MapManager that the story is based off of
	 */
	public StoryManager(MapManager manager) {
		this.manager = manager;
		this.player = manager.getPlayer();
		this.storyActor = new StoryActor();
	}

	/**
	 * Perform some action based off the current map
	 * 
	 * @param is
	 *            the change in time since the last render
	 */
	public void act(float dt) {
		if (storyActor != null)
			storyActor.act(dt);
	}

	/**
	 * Set the current map name and alter the actor based off of the map
	 * 
	 * @param mapName
	 *            is the name of the current map
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
		switch (mapName) {
		case "Maps/Map01-IntroArea.tmx":
			class IntroArea implements Actor {
				@Override
				public void act(float dt) {
				}
			}
			actor = new IntroArea();
			storyActor.actor(actor);
			break;
		default:
			storyActor = new StoryActor();
			break;

		}
	}

	/**
	 * Update the game mode for the story manager
	 */
	public void updateWorld() {
		this.setMapName(manager.getMapName());
		this.world = manager.getWorld();
		this.player = manager.getPlayer();
	}

	/**
	 * Update story variables for the story manager
	 */
	public void updateStory() {
		if (mapName == null)
			return;
		if (mapName.equals("Maps/Map01-IntroArea.tmx"));
		// anything conditional to a specific area gets messed with
	}

}
