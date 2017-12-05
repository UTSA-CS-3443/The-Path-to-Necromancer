package game.controller.story;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.DialogueGraph;
import game.model.maps.Dungeon;
import game.model.sprites.npc.ColorAndGender;
import game.model.sprites.npc.Knight;
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
	 * The player character
	 */
	private Player player;
	/**
	 * The world the player is in
	 */
	private World world;
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
	}

	/**
	 * Perform some action based off the current map
	 * 
	 * @param is
	 *            the change in time since the last render
	 */
	public void act(float dt) {
		if (actor != null)
			actor.act(dt);
	}

	/**
	 * Set the current map name and alter the actor based off of the map
	 * 
	 * @param mapName
	 *            is the name of the current map
	 */
	public void setMapName(String mapName) {
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
					if (player.getStoryStats().isBookEncounters()) {
						book = new Book();
						book.defineBody(manager.getWorld(), 140, 215);
						manager.addSprite(book);
						DialogueGraph graph = book.getDialogue(player);
						manager.getMainScreen().startChat();
						manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
						player.getStoryStats().setBookEncounters(false);
					}
				}

				/**
				 * Set up the initial book encounter
				 */
				@Override
				public void setUpActor(World world, Player player) {
				}

			}

			actor = new IntroArea();
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
					if (player.getStoryStats().getNecEncounters() == 1 && player.getY() < 80 && Math.abs(player.getX() - 400) < 50) {
						nec = new Necromancer();
						nec.defineBody(world, 415, 30);
						manager.addSprite(nec);
						DialogueGraph graph = nec.getDialogue(player);
						manager.getMainScreen().startChat();
						manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
						player.getStoryStats().setNecEncounters(2);
						nec.addVelocity(new Vector2(0, 15), 3);
					}
				}

				@Override
				public void setUpActor(World world, Player player) {
				}

			}

			actor = new Tavern();
			break;
		// The Plains Area
		case "Maps/Map04-Plains Area.tmx":
			actor = new PlainsStory(manager, player, world);
			break;
		case "Maps/Map07-RightMountain.tmx":
			class RightMount implements Actor {
				@Override
				public void act(float dt) {
				}

				/**
				 * Set up the special dialogue villager
				 * 
				 * @param world
				 *            is the world to put the villager
				 * @param player
				 *            is used to determine whether the villager has special dialoge
				 */
				@Override
				public void setUpActor(World world, Player player) {

					Villagers villager = new Villagers(ColorAndGender.BLACK, ColorAndGender.MALE);
					villager.defineBody(world, 93, 2268);
					manager.addSprite(villager);

					if (player.getStoryStats().getVillagerConversations() == 0) {
						player.getStoryStats().setVillagerConversations(1);
						villager.setSpecialDialogue(true);
					}
				}
			}

			actor = new RightMount();
			break;
		case "Maps/Map09-EntrytoNecromancer'sLair.tmx":
			class NecLair implements Actor {
				private Knight knight;

				@Override
				public void setUpActor(World world, Player player) {
					if (player.getStoryStats().isKnightEncounter()) {
						this.knight = new Knight();
						this.knight.defineBody(world, 298, 520);
						manager.addSprite(knight);
					}
				}

				@Override
				public void act(float dt) {
					if (player.getY() >= 420 && player.getStoryStats().isKnightEncounter() && this.knight != null) {
						DialogueGraph graph = this.knight.getDialogue(player);
						manager.getMainScreen().startChat();
						manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
						player.getStoryStats().setKnightEncounter(false);
						knight.addVelocity(new Vector2(0, -25), 2);
					}
				}

			}
			;
			actor = new NecLair();
			break;
		// The interior of the Necromancer's Lair
		case "Maps/Map10-Necromancer'sLair.tmx":
			class necInterior implements Actor {
				private Necromancer nec;

				@Override
				public void setUpActor(World world, Player player) {
					this.nec = new Necromancer();
					nec.defineBody(world, 238, 1062);
					manager.addSprite(nec);
					if (player.getStoryStats().getFinaleEncounter() == 3)
						player.getStoryStats().setFinaleEncounter(4);
				}

				@Override
				public void act(float dt) {
					if (player.getY() > 960 && player.getStoryStats().getFinaleEncounter() == 0) {
						player.addVelocity(new Vector2(0, 10), 4);
						DialogueGraph graph = this.nec.getDialogue(player);
						manager.getMainScreen().startChat();
						manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
						player.getStoryStats().setFinaleEncounter(1);
					}
					if (player.getStoryStats().getFinaleEncounter() == 2) {
						manager.setMap(new Dungeon(manager), 200, 200);
						player.getStoryStats().setFinaleEncounter(3);
					}
					if (player.getStoryStats().getFinaleEncounter() == 4 && player.getY() > 960) {
						player.getStoryStats().setFinaleEncounter(5);
						player.addVelocity(new Vector2(0, 10), 4);
						DialogueGraph graph = this.nec.getDialogue(player);
						manager.getMainScreen().startChat();
						manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
					}
				}

			}
			actor = new necInterior();
			break;
		default:
			actor = null;
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
