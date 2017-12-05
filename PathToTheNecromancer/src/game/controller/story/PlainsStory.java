package game.controller.story;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.DialogueGraph;
import game.model.sprites.npc.Bandit;
import game.model.sprites.npc.Fighter;
import game.model.sprites.npc.Necromancer;
import game.model.sprites.player.Player;

/**
 * The story actor for the Plains Area
 * 
 * @author enigma-phi
 *
 */
public class PlainsStory implements Actor {

	/**
	 * The mapmanager to put sprites in
	 */
	private MapManager manager;
	/**
	 * The player to base actions off of
	 */
	private Player player;
	/**
	 * The world to put sprites in
	 */
	private World world;
	/**
	 * Different bandits for the unionization encounter
	 */
	private Bandit b1, b2, b3, b4, b5, b6;
	/**
	 * The necromancer for the unionization encounter
	 */
	private Necromancer nec;
	/**
	 * The fighter for the encounter with the fighter
	 */
	private Fighter fighter;

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
	public PlainsStory(MapManager manager, Player player, World world) {
		this.manager = manager;
		this.player = player;
		this.world = world;
		this.fighter = null;
	}

	/**
	 * Perform some specific actions
	 * 
	 * @param dt
	 *            is the change in time since the last render
	 */
	@Override
	public void act(float dt) {
		// set up fighter dialogue
		if (player.getStoryStats().getFighterEncounter() == 0 && this.fighter != null && player.getY() > 305) {
			player.addVelocity(new Vector2(0, 40), 3);
			DialogueGraph graph = this.fighter.getDialogue(player);
			manager.getMainScreen().startChat();
			manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
			player.getStoryStats().setFighterEncounter(1);
		}
		// Start dialogue for the first time
		if (player.getStoryStats().getBanditEncounters() == 0 && player.getY() > 1160) {
			DialogueGraph graph = b1.getDialogue(player);
			manager.getMainScreen().startChat();
			manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
			player.getStoryStats().setBanditEncounters(1);
		}
		// The player continues along the unionization encounter
		if (player.getStoryStats().getBanditEncounters() == 3 && player.getY() > 1835) {
			player.addVelocity(new Vector2(0, 20), 1);
			player.getStoryStats().setBanditEncounters(4);
			DialogueGraph graph = b6.getDialogue(player);
			manager.getMainScreen().startChat();
			manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
		}
		// Destroy the leader
		if (player.getStoryStats().getBanditEncounters() == 5) {
			manager.getSprites().remove(b6);
			world.destroyBody(b6.getBody());
			player.getStoryStats().setBanditEncounters(6);
		}
		// The Necromancer appears
		if (player.getStoryStats().getBanditEncounters() == 7) {
			nec = new Necromancer();
			nec.defineBody(world, 368, 1897);
			nec.banditDialogue(true);
			manager.addSprite(nec);
			DialogueGraph graph = nec.getDialogue(player);
			manager.getMainScreen().startChat();
			manager.setInteraction(new Interaction(graph, manager.getDialogueBox(), player));
			player.getStoryStats().setBanditEncounters(8);
		}
		// The Necromancer disappears
		if (player.getStoryStats().getBanditEncounters() == 9) {
			manager.getSprites().remove(nec);
			world.destroyBody(nec.getBody());
			player.getStoryStats().setBanditEncounters(10);
		}
	}

	/**
	 * Set up the actor initially
	 * 
	 * @param world
	 *            is the new world to put sprites in
	 * @param player
	 *            is the new player to set up sprites based off of
	 */
	@Override
	public void setUpActor(World world, Player player) {
		this.player = player;
		this.world = world;
		b1 = new Bandit();
		b2 = new Bandit();
		b3 = new Bandit();
		b4 = new Bandit();
		b5 = new Bandit();
		b6 = new Bandit();
		if (player.getStoryStats().getBanditEncounters() == 0)
			b1.defineBody(world, 368, 1245);
		else
			b1.defineBody(world, 473, 1275);
		if (player.getStoryStats().getFighterEncounter() == 0) {
			this.fighter = new Fighter();
			this.fighter.defineBody(world, 383, 463);
			manager.addSprite(fighter);
		}
		b2.defineBody(world, 307, 1882);
		b3.defineBody(world, 427, 1875);
		b4.defineBody(world, 429, 1910);
		b5.defineBody(world, 296, 1921);
		b6.defineBody(world, 382, 1945);
		manager.addSprite(b1);
		manager.addSprite(b2);
		manager.addSprite(b3);
		manager.addSprite(b4);
		manager.addSprite(b5);
		manager.addSprite(b6);
	}
}
