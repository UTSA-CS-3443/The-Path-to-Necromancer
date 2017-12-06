package game.controller.story;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.DialogueGraph;
import game.model.maps.MountainRight;
import game.model.sprites.npc.ColorAndGender;
import game.model.sprites.npc.Hippie;
import game.model.sprites.npc.Villagers;
import game.model.sprites.player.Player;

/**
 * The Hippie's Story at the middle mountain
 * @author enigma-phi
 *
 */
public class HippieStory implements Actor{

	/**
	 * The Hippie character
	 */
	private Hippie hippie;
	/**
	 * The different villagers
	 */
	private Villagers v1, v2, v3, v4, v5, v6;
	/**
	 * The mapmanager to put sprites in
	 */
	private MapManager manager;
	/**
	 * The player to base actions off of
	 */
	private Player player;
	
	/**
	 * Constructor. Set up variables
	 * 
	 * @param manager
	 *            is the manager for putting sprites in
	 * @param player
	 *            is the player to base story off of
	 * @param world
	 *            is the world to put sprite's bodies in
	 */
	public HippieStory(MapManager manager, Player player, World world) {
		this.manager = manager;
		this.player = player;
	}
	
	/**
	 * Set up the actor 
	 * @param world is the world to place the actor in
	 * @param player is the player to base the story off of
	 */
	@Override
	public void setUpActor(World world, Player player) {
		this.player = player;
		if(player.getStoryStats().getHippieEncounter() == 0) {
			this.hippie = new Hippie();
			this.hippie.defineBody(world, 291, 80);
			this.manager.addSprite(this.hippie);
			
			this.v1 = new Villagers(ColorAndGender.RED, ColorAndGender.FEMALE);
			this.v1.defineBody(world, 254, 33);
			this.v1.addVelocity(new Vector2(0, 5), 1);
			this.v1.addVelocity(new Vector2(5, 0), 1);
			this.manager.addSprite(this.v1);
			
			this.v2 = new Villagers(ColorAndGender.BLUE, ColorAndGender.MALE);
			this.v2.defineBody(world, 309, 22);
			this.v2.addVelocity(new Vector2(0, 5), 1);
			this.manager.addSprite(this.v2);
			
			this.v3 = new Villagers(ColorAndGender.BROWN, ColorAndGender.FEMALE);
			this.v3.defineBody(world, 355, 72);
			this.v3.addVelocity(new Vector2(0, 5), 1);
			this.v3.addVelocity(new Vector2(-10, 0), 1);
			this.manager.addSprite(this.v3);
			
			this.v4 = new Villagers(ColorAndGender.GREEN, ColorAndGender.MALE);
			this.v4.defineBody(world, 389, 42);
			this.v4.addVelocity(new Vector2(-10, 0), 4);
			this.manager.addSprite(this.v4);
			
			this.v5 = new Villagers(ColorAndGender.BLACK, ColorAndGender.FEMALE);
			this.v5.defineBody(world, 221, 89);
			this.v5.addVelocity(new Vector2(5, 0), 2);
			this.manager.addSprite(this.v5);
			
			this.v6 = new Villagers(ColorAndGender.WHITE, ColorAndGender.MALE);
			this.v6.defineBody(world, 200, 40);
			this.v6.addVelocity(new Vector2(10, 0), 3);
			this.manager.addSprite(this.v6);
		}
	}

	/**
	 * Perform different actions
	 * @param dt is the change in time since the last render
	 */
	@Override
	public void act(float dt) {
		if(player.getStoryStats().getHippieEncounter() == 0 && player.getX() > 213) {
			DialogueGraph graph = hippie.getDialogue(player);
			manager.getMainScreen().startChat();
			manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
			player.addVelocity(new Vector2(10, 0), 3);
			player.getStoryStats().setHippieEncounter(1);
		} else if(player.getStoryStats().getHippieEncounter() == 2) {
			DialogueGraph graph = hippie.getDialogue(player);
			manager.getMainScreen().startChat();
			manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
			player.getStoryStats().setHippieEncounter(3);
		} else if(player.getStoryStats().getHippieEncounter() == 4) {
			DialogueGraph graph = hippie.getDialogue(player);
			manager.getMainScreen().startChat();
			manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
			player.getStoryStats().setHippieEncounter(5);
		} else if(player.getStoryStats().getHippieEncounter() == 10) {
			manager.endGame();
		} else if(player.getStoryStats().getHippieEncounter() == 11) {
			manager.setMap(new MountainRight(this.manager), 30, 2275);
		}
	}

}
