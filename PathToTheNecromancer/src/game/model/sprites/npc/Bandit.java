package game.model.sprites.npc;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import game.controller.MapManager;
import game.controller.story.DialogueActor;
import game.model.DialogueGraph;
import game.model.sprites.CharacterSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Player;

/**
 * 
 * The class for the bandit sprite with the dialogue
 * 
 * @author ToTryHardRay
 * @author enigma-phi
 *
 */
public class Bandit extends CharacterSprites implements InteractionSprites {
	/**
	 * The bandit's height
	 */
	private static final int BANDIT_HEIGHT = 30;
	/**
	 * The bandit's width
	 */
	private static final int BANDIT_WIDTH = 20;
	/**
	 * Get the texturea associated with the bandit
	 */
	private Texture banditTexture;

	/**
	 * Constructor
	 */
	public Bandit() {
		setTextureValues();
		// Set the size of the Bandit
		setBounds(0, 0, BANDIT_WIDTH, BANDIT_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	/**
	 * The Bandit's dialogue graph
	 */
	@Override
	public DialogueGraph getDialogue(Player player) {
		if (player.getStoryStats().getBanditEncounters() == 0)
			return getInitialBanditDialogue();
		else if (player.getStoryStats().getBanditEncounters() == 4)
			return getLeaderSnippet();
		else
			return getDefaultDialogue();

	}

	/**
	 * Get some default dialogue for the bandit
	 * 
	 * @param the
	 *            dialogueGraph
	 */
	private DialogueGraph getDefaultDialogue() {
		DialogueGraph graph = new DialogueGraph();
		Random rand = new Random();
		switch (rand.nextInt(2)) {
		case 0:
			graph.addNode("Bandit: You had better watch your back. It's dangerous out there.");
			break;
		case 1:
			graph.addNode("Bandit: Jokes about Communism have no Class. But jokes about java have some. I'm still usure about the communist java programmer.");
			break;
		}

		return graph;
	}

	/**
	 * Get the leader's dialogue snippet for the unionization encounter
	 * 
	 * @return the dialogueGraph
	 */
	private DialogueGraph getLeaderSnippet() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("..."); // 0
		graph.addNode("P: As you walk into camp, you see the bandit leader disappear."); // 1
		graph.addNode("Bandit: So, after we draft the Bill of Fights, we will then move onto providing equal plundering and make a cultural change ..."); // 2
		graph.addNode("Bandit: from Italian bandits to Mexican bandits as they have better hats..."); // 3
		graph.addNode("*Thunder*"); // 4

		// add the edges
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);

		graph.getNode(2).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setBanditEncounters(5);
			}

		});
		// Add actor
		graph.getNode(4).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setBanditEncounters(7);
			}

		});

		return graph;
	}

	/**
	 * Get the initial bandit dialogue
	 * 
	 * @return the dialogueGraph
	 */
	private DialogueGraph getInitialBanditDialogue() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Bandit: Be still traveler you are about to walk upon the land of Bloody Foot himself, and his mother was Foot herself!"); // 0
		graph.addNode("P: I'm here as a representative for the badit/Don union"); // 1
		graph.addNode("P: How dare you speak to me bandit! I am the chosen one!"); // 2
		graph.addNode("P: No need to worry, just passing through"); // 3
		graph.addNode("Bandit: The bandit/Don union?"); // 4
		graph.addNode("P: We strive for equal shares in plunder for bandits everywhere!"); // 5
		graph.addNode("P: Indeed, why hasn't your camp joined?"); // 6
		graph.addNode("P: Yes, would you like to join?"); // 7
		graph.addNode("Bandit: Well, kinda, we don't even get dental..."); // 8
		graph.addNode("Bandit: Oh, well, we never received any message from you."); // 9
		graph.addNode("Bandit: Never I stay with my Don, I'll never betray him! Get out of here before I have your head!"); // 10
		graph.addNode("P: You don't even get dental?! How horrible, we have to come in to check this out!"); // 11
		graph.addNode("P: None? well we did send you multiple messages... maybe someone has been tossing them out?"); // 12
		graph.addNode("P: Guess it must've been lost in the mail!"); // 13
		graph.addNode("Bandit: Please go ahead."); // 14
		graph.addNode("Bandit: Well, now that I think of it, the Don has been throwing out a lot of paper recently..."); // 15
		graph.addNode("Bandit: Yeah i gue.... wait a minute, we don't have mail out here! You're lying moron!"); // 16
		graph.addNode("P: I see, I see... perhaps he's trying to prevent you from unionizing?"); // 17
		graph.addNode("Bandit: I guess so... tell me, what does one do in a union?"); // 18
		graph.addNode("P: Well, first, have you been feeling disgruntled at all?"); // 19
		graph.addNode("P: Well, they protest my good sir. Teach the Don what's up. You need to fight!"); // 20
		graph.addNode("Bandit: Good idea, let's do this! I'm tired of pretending to be Italian!"); // 21
		graph.addNode("Bandit: The chosen one?"); // 22
		graph.addNode("Bandit: haha, sissy. You had better watch your back in camp!"); // 23
		graph.addNode("P: Yes I am the chosen bandit, the one to lead all bandits to banditdom!"); // 24
		graph.addNode("Bandit: COOL! Do you have any powers?"); // 25
		graph.addNode("P: Yes, watch me remove my thumb from my hand!"); // 26
		graph.addNode("P: I can't your eyes would melt from your eye sockets"); // 27
		graph.addNode("Bandit: The Don can do that too! Except he just chops them off of those who snitch on him!"); // 28
		graph.addNode("Bandit: You lie, show me now"); // 29
		graph.addNode("P: Yes, but can he put them back on?! *performs finger trick*"); // 30
		graph.addNode("P: My power derives from the sun! Stare at it, and you shall feel your eyes burning with my power!"); // 31
		graph.addNode("P: Alright, I lied, but may I pass through your camp?"); // 32
		graph.addNode("Bandit: *silence*"); // 33
		graph.addNode("Bandit: Fine! *bandit begins staring at sun*"); // 34
		graph.addNode("Bandit: We kill liars here fool, you had better watch your tongue passing through here!"); // 35
		graph.addNode("P: *do it again*"); // 36
		graph.addNode("P: Do you believe me now?!"); // 37
		graph.addNode("P: *Attempt to sneak by*"); // 38
		graph.addNode("P: Yes, yes are you feeling my power?!"); // 39
		graph.addNode("Bandit: *even more silence*"); // 40
		graph.addNode("Bandit: Please pass through my almighty wizard."); // 41
		graph.addNode("Bandit: Wait, where did you go?"); // 42
		graph.addNode("Bandit: *tears rolling down eyes* I feel it! MAKE IT STOP!!!!"); // 43
		graph.addNode("P: Just look away weakling."); // 44
		graph.addNode("P: NEVER! BURN WEAKLING BURN!"); // 45
		graph.addNode("P: *do it again*"); // 46
		graph.addNode("P: I have not done it once, but twice! Do you believe me now?!"); // 47
		graph.addNode("Bandit: *crying* I'm sorry for doubting you sun lord, please pass"); // 48
		graph.addNode("Bandit: *screams with tears rolling down face*"); // 49
		graph.addNode("Bandit:PLEASE STOP DISMEMBERING YOURSELF, IT'S UNNATURAL!"); // 50
		graph.addNode("P: NEVER! *do it again*"); // 51
		graph.addNode("Bandit: *begins crying* Why do you keep hurting yourself, just go through the camp."); // 52
		graph.addNode("P: Have you been feeling disgruntled at all recently?"); // 53

		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(3, 23);
		graph.addEdge(1, 4);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);
		graph.addEdge(4, 7);
		graph.addEdge(7, 10);
		graph.addEdge(5, 53);
		graph.addEdge(53, 8);
		graph.addEdge(6, 9);
		graph.addEdge(8, 11);
		graph.addEdge(19, 8);
		graph.addEdge(11, 14);
		graph.addEdge(9, 12);
		graph.addEdge(9, 13);
		graph.addEdge(12, 15);
		graph.addEdge(15, 17);
		graph.addEdge(13, 16);
		graph.addEdge(17, 18);
		graph.addEdge(18, 19);
		graph.addEdge(18, 20);
		graph.addEdge(20, 21);
		graph.addEdge(2, 22);
		graph.addEdge(22, 24);
		graph.addEdge(24, 25);
		graph.addEdge(25, 26);
		graph.addEdge(25, 27);
		graph.addEdge(26, 28);
		graph.addEdge(28, 30);
		graph.addEdge(30, 33);
		graph.addEdge(27, 29);
		graph.addEdge(29, 31);
		graph.addEdge(29, 32);
		graph.addEdge(31, 34);
		graph.addEdge(32, 35);
		graph.addEdge(33, 36);
		graph.addEdge(33, 37);
		graph.addEdge(34, 38);
		graph.addEdge(34, 39);
		graph.addEdge(36, 40);
		graph.addEdge(40, 46);
		graph.addEdge(40, 47);
		graph.addEdge(46, 50);
		graph.addEdge(50, 51);
		graph.addEdge(51, 52);
		graph.addEdge(47, 41);
		graph.addEdge(37, 41);
		graph.addEdge(38, 42);
		graph.addEdge(39, 43);
		graph.addEdge(43, 44);
		graph.addEdge(43, 45);
		graph.addEdge(44, 48);
		graph.addEdge(45, 49);

		// Add actors
		DialogueActor actor = new DialogueActor() {
			@Override
			public void act(Player player, MapManager manager) {
				addVelocity(new Vector2(0, 15), 2);
				addVelocity(new Vector2(20, 0), 5);
				player.getStoryStats().setBanditEncounters(2);
			}

		};
		graph.getNode(14).addActor(actor);
		graph.getNode(16).addActor(actor);
		graph.getNode(23).addActor(actor);
		graph.getNode(35).addActor(actor);
		graph.getNode(41).addActor(actor);
		graph.getNode(42).addActor(actor);
		graph.getNode(47).addActor(actor);
		graph.getNode(48).addActor(actor);
		graph.getNode(52).addActor(actor);
		graph.getNode(21).addActor(new DialogueActor() {
			@Override
			public void act(Player player, MapManager manager) {
				addVelocity(new Vector2(0, 15), 2);
				addVelocity(new Vector2(20, 0), 5);
				player.getStoryStats().setBanditEncounters(3);
			}

		});
		return graph;
	}

	/**
	 * Define the bandit's physics body
	 * 
	 * @param world
	 *            is the world to place the bandit in
	 * @param x
	 *            is the bandit's x-coordinate
	 * @param y
	 *            is the bandit's y-coordinate
	 */
	@Override
	public void defineBody(World world, int x, int y) {
		// set the body of the character
		BodyDef bdef = new BodyDef();
		bdef.position.set(x, y); // position
		bdef.type = BodyDef.BodyType.DynamicBody; // body type

		// Create the body and add mass
		Body body = world.createBody(bdef);
		MassData mass = new MassData();
		mass.mass = 1000000f;
		body.setMassData(mass);
		this.setBody(body);

		// create the world fixture for collision
		FixtureDef fdef = new FixtureDef();
		PolygonShape rect = new PolygonShape();

		// coordinates of the Bandit's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-BANDIT_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(BANDIT_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-BANDIT_WIDTH / 2, -BANDIT_HEIGHT / 2); // bottom
		// left
		vertice[3] = new Vector2(BANDIT_WIDTH / 2, -BANDIT_HEIGHT / 2); // bottom
		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);
	}

	/**
	 * Set the Texture values for the Bandit's animation.
	 */
	@Override
	public void setTextureValues() {

		banditTexture = new Texture("EnemySprites/Bandit.png");
		TextureRegion banditTextureRegion = new TextureRegion(banditTexture, 0, 0, banditTexture.getWidth(), banditTexture.getHeight());
		Array<TextureRegion> banditRegions = new Array<TextureRegion>();
		banditRegions.add(banditTextureRegion);
		Animation<TextureRegion> banditAnimation = new Animation<TextureRegion>(0.1f, banditRegions);

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(banditTexture, 0, 0, banditTexture.getWidth(), banditTexture.getHeight()));
		super.setMoveUp(banditAnimation);
		super.setMoveDown(banditAnimation);
		super.setMoveLeft(banditAnimation);
		super.setMoveRight(banditAnimation);
		super.setMoveUpLeft(banditAnimation);
		super.setMoveUpRight(banditAnimation);
		super.setMoveDownLeft(banditAnimation);
		super.setMoveDownRight(banditAnimation);
	}

}
