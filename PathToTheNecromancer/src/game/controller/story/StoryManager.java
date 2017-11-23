package game.controller.story;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.DialogueGraph;
import game.model.sprites.npc.ColorAndGender;
import game.model.sprites.npc.Necromancer;
import game.model.sprites.npc.Villagers;
import game.model.sprites.objectSprites.Book;
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
		// The introduction area
		case "Maps/Map01-IntroArea.tmx":
			class IntroArea implements Actor {
				/**
				 * The book for the interactions
				 */
				private Book book;

				/**
				 * Perform different actions based off of the player's interactions
				 */
				@Override
				public void act(float dt) {
					// set up the initial encounter
					if (player.getBookEncounters() == 0) {
						book = new Book();
						book.defineBody(manager.getWorld(), 140, 215);
						manager.addSprite(book);
						DialogueGraph graph = book.getDialogue(player);
						manager.getMainScreen().startChat();
						manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
						player.setBookEncounters(1);
					}
					// begin book special animation 1
					if (player.getBookEncounters() == 2 && book != null) {
						book.setOpen(true);
						player.setBookEncounters(3);
					}
					// begin book special animation 2
					if (player.getBookEncounters() == 4 && book != null) {
						book.setTurnPage(true);
						player.setBookEncounters(5);
					}
				}

				@Override
				public void setUpActor(World world, Player player) {
					// TODO Auto-generated method stub
					
				}


			}

			actor = new IntroArea();
			storyActor.actor(actor);
			break;
		// Inside of Oog-Lag's Tavern
		case "Maps/Map03-Inside Oog-Lag's Tavern.tmx":
			class Tavern implements Actor {
				/**
				 * The necromancer for the interactions
				 */
				private Necromancer nec;

				/**
				 * Perform specific actions based off of the player's interactions
				 */
				@Override
				public void act(float dt) {
					if (player.getNecEncounters() == 1 && player.getY() < 80 && Math.abs(player.getX() - 400) < 50) {
						nec = new Necromancer();
						nec.defineBody(world, 415, 30);
						manager.addSprite(nec);
						DialogueGraph graph = nec.getDialogue(player);
						manager.getMainScreen().startChat();
						manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
						player.setNecEncounters(2);
						nec.addVelocity(new Vector2(0, 15), 3);
					}
					if (player.getNecEncounters() == 3 && nec != null) {
						nec.setVelocity(new Vector2(0, -15));
						player.setNecEncounters(4);
					}
				}

				@Override
				public void setUpActor(World world, Player player) {
					// TODO Auto-generated method stub
					
				}

			}

			actor = new Tavern();
			storyActor.actor(actor);
			break;
		// The Plains Area
		case "Maps/Map04-Plains Area.tmx":
			actor = new PlainsStory(manager, player, world);
			storyActor.actor(actor);
			break;
		case "Maps/Map07-RightMountain.tmx":
			class RightMount implements Actor {
				@Override
				public void act(float dt) {
				}

				@Override
				public void setUpActor(World world, Player player) {
					if(player.getVillagerConversations() == 0) {
						player.setVillagerConversations(1);
						Villagers villager = new Villagers(ColorAndGender.BLACK, ColorAndGender.MALE);
						villager.defineBody(world, 93, 2268);
						villager.setSpecialDialogue(true);
						manager.addSprite(villager);
					}
				}

			}

			actor = new RightMount();
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
		Actor oldActor = actor;
		this.setMapName(manager.getMapName());
		this.world = manager.getWorld();
		this.player = manager.getPlayer();
		if (this.actor != null && this.actor != oldActor)
			this.actor.setUpActor(world, player);
	}

	/*
	 * Set the current player
	 * 
	 * @param player is the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

}
