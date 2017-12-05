package game.model.sprites.npc;

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
 * The Necromancer is the main villain of the game.
 * 
 * @author enigma-phi
 *
 */
public class Necromancer extends CharacterSprites implements InteractionSprites {
	/**
	 * The pixel width of the Necromancer sprite
	 */
	private static final int NEC_WIDTH = 40;
	/**
	 * The pixel height of the Necromancer sprite
	 */
	private static final int NEC_HEIGHT = 50;
	/**
	 * The texture used to draw and render the Necromancer sprite
	 */
	private Texture necromancerTexture;
	/**
	 * Whether or not the necromancer is engaged in the bandit dialogue
	 */
	private boolean banditDialogue;
	/**
	 * The scaling factor used to draw the Necromancer.
	 */
	private static float SCALINGFACTOR = 0.65f;

	/**
	 * Create the Necromancer object.
	 */
	public Necromancer() {
		super();
		// Initialize the different texture regions associated with the Necromancer
		setTextureValues();
		// Set the size of the Necromancer
		setBounds(0, 0, NEC_WIDTH * SCALINGFACTOR, NEC_HEIGHT * SCALINGFACTOR);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	/**
	 * Set the different TextureRegions associated with the Necromancer for
	 * animation.
	 */
	@Override
	public void setTextureValues() {
		necromancerTexture = new Texture("CharacterSprites/Necromancer.png");
		int width = 50;
		int height = 51;

		// Set the default standing region of the Necromancer.
		super.setStandingRegion(new TextureRegion(necromancerTexture, 0, 0, width, height));
		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();

		// Set the animations for moving Down on the map
		frames.add(new TextureRegion(necromancerTexture, 0, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, 0, height, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Up on the map
		frames.add(new TextureRegion(necromancerTexture, width, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width, height, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Left on the map
		frames.add(new TextureRegion(necromancerTexture, width * 2, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 2, height, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Right on the map
		frames.add(new TextureRegion(necromancerTexture, width * 3, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 3, height, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Down-Left on the map
		frames.add(new TextureRegion(necromancerTexture, width * 4, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 4, height, width, height));
		super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Down-Right on the map
		frames.add(new TextureRegion(necromancerTexture, width * 5, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 5, height, width, height));
		super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Up-Right on the map
		frames.add(new TextureRegion(necromancerTexture, width * 6, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 6, height, width, height));
		super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Up-Left on the map
		frames.add(new TextureRegion(necromancerTexture, 0, height * 2, width, height));
		frames.add(new TextureRegion(necromancerTexture, width, height * 2, width, height));
		super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
	}

	/**
	 * Create the Necromancer's body for the box2d physics engine
	 * 
	 * @param world
	 *            is the world to put the character into
	 * @param x
	 *            is the x-coordinate to put the character on
	 * @param y
	 *            is the y-coordinate to put the character on
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

		// coordinates of the Necromancer's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-(NEC_WIDTH * SCALINGFACTOR) / 2 + 5, 0); // top left
		vertice[1] = new Vector2((NEC_WIDTH * SCALINGFACTOR) / 2 - 5, 0); // top right
		vertice[2] = new Vector2(-(NEC_WIDTH * SCALINGFACTOR) / 2 + 5, -(NEC_HEIGHT * SCALINGFACTOR) / 2); // bottom
																											// left
		vertice[3] = new Vector2((NEC_WIDTH * SCALINGFACTOR) / 2 - 5, -(NEC_HEIGHT * SCALINGFACTOR) / 2); // bottom
																											// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);
	}

	/**
	 * Whether or not the Necromancer is engaged in the bandit dialogue
	 * 
	 * @param b
	 */
	public void banditDialogue(boolean b) {
		this.banditDialogue = b;
	}

	/**
	 * Get the Necromancer's Dialogue Graph
	 * 
	 * @param the
	 *            player to base the graph off of
	 * @return the dialogue graph
	 */
	@Override
	public DialogueGraph getDialogue(Player player) {
		if (player.getStoryStats().getNecEncounters() == 1)
			return this.getFirstEncounter();
		else if (this.banditDialogue)
			return this.getUnionization();
		else if (player.getStoryStats().getFinaleEncounter() == 0)
			return this.getCastleEncounter();
		else if (player.getStoryStats().getFinaleEncounter() == 5)
			return this.preCombatDialogue();
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("The Necromancer you are calling is currently unavailable. Please try again later.");
		return graph;
	}

	/**
	 * Get a dialogue after the player is sent to the dungeon
	 * 
	 * @return the corresponding dialogue graph
	 */
	private DialogueGraph preCombatDialogue() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Necromancer: You have amused me greatly young padawan. I do not wish to cut your life short and will allow you to live... this time.");
		return graph;
	}

	/**
	 * Get the necromancer's dialogue for the player's first encounter
	 * 
	 * @return the dialogue box for the first encounter
	 */
	private DialogueGraph getFirstEncounter() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Necromancer: Hello there mortals. I am a mighty Necromancer. Here to see the new opponent. And I ..."); // 0
		graph.addNode("*You are unable to hear him as the thunder from outside is too loud*"); // 1
		graph.addNode("Necromancer: MUAHAHA and I *CRASH* will murder *BOOM* puppies, kittens, none shall escape *KERSHACK* and I will..."); // 2
		graph.addNode("*Thunder booms again*"); // 3
		graph.addNode("Necromancer: Farewell you fools! MUAHAHA"); // 4

		// add edges
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);

		graph.getNode(4).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				setVelocity(new Vector2(0, -15));
			}

		});

		return graph;
	}

	/**
	 * Get the dialogue for the unionization encounter with the Necromancer
	 * 
	 * @return the unionization dialogue
	 */
	private DialogueGraph getUnionization() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Necromancer: Whoa, whoa, whoa, what's going on here? You should be killing each other right now?!!"); // 0
		graph.addNode("P: We have moved past senselless killing ever since Bloody Foot willingly abdicated the throne."); // 1
		graph.addNode("P: We seizing the means of plundering."); // 2
		graph.addNode("P: *hide behind slightly larger bandit*"); // 3
		graph.addNode("Necromancer: You killed him?"); // 4
		graph.addNode("Necromancer: My goodness, you made the badits into communists?!"); // 5
		graph.addNode("Necromancer: I still see you. Get out here."); // 6
		graph.addNode("P: No, why would you think that?"); // 7
		graph.addNode("P: \'Kill\' is such a strong word. What about, \'Passed away forcibly?\'"); // 8
		graph.addNode("P: Yes"); // 9
		graph.addNode("Necromancer: He's absent and I saw a mysterious grave that wasn't here yesterday."); // 10
		graph.addNode("P: Really? How strange I must say!"); // 11
		graph.addNode("Necromancer: *sigh* I don't have time for this. Just to let you all know, I will not be paying you and will leave a negative review on Yelp."); // 12
		graph.addNode("P: You don't have the guts!"); // 13
		graph.addNode("P: I pushed these bandits towards the future of equal work, employment, and wages!"); // 14
		graph.addNode("P: I don't make them into Russians, sheesh, we all agreed to be Mexican from now on. We're done being Italian."); // 15
		graph.addNode("Necromancer: Why the heck Mexicans??"); // 16
		graph.addNode("P: They have better hats... and tacos."); // 17
		graph.addNode("P: Wouldn't you want to know?"); // 18
		graph.addNode("P: *whisper to bandit* Ha! Look at this greengo!"); // 19
		graph.addNode("P: Come get me if you dare!");
		graph.addNode("P: Sounds like someone is jealous."); // 21
		graph.addNode("Necromancer: So, communism?"); // 22
		graph.addNode("P: You do know that I'm able to obliterate all that stand in front of me right?"); // 23
		graph.addNode("Necromancer: Yes I would. I really want to know."); // 24
		graph.addNode("P: Well too bad!"); // 25
		graph.addNode("Necromancer: Esta deletreado \'gringo!\'"); // 26
		graph.addNode("P: Sorry, we don't speak that language here greengo."); // 27
		graph.addNode("P: *continue hiding behind bandit*"); // 28
		graph.addNode("Necromancer: I said get out here now!"); // 29
		graph.addNode("P: *continue hiding*"); // 30

		// add the edges
		graph.addEdge(0, 1);
		graph.addEdge(1, 4);
		graph.addEdge(0, 2);
		graph.addEdge(2, 5);
		graph.addEdge(0, 3);
		graph.addEdge(3, 6);
		graph.addEdge(4, 7);
		graph.addEdge(4, 8);
		graph.addEdge(4, 9);
		graph.addEdge(7, 10);
		graph.addEdge(10, 11);
		graph.addEdge(11, 12);
		graph.addEdge(8, 12);
		graph.addEdge(9, 12);
		graph.addEdge(5, 14);
		graph.addEdge(5, 15);
		graph.addEdge(14, 22);
		graph.addEdge(22, 21);
		graph.addEdge(21, 12);
		graph.addEdge(15, 16);
		graph.addEdge(16, 17);
		graph.addEdge(17, 20);
		graph.addEdge(16, 18);
		graph.addEdge(18, 24);
		graph.addEdge(24, 25);
		graph.addEdge(16, 19);
		graph.addEdge(61, 27);
		graph.addEdge(6, 28);
		graph.addEdge(28, 29);
		graph.addEdge(29, 30);
		graph.addEdge(6, 20);
		graph.addEdge(20, 23);
		graph.addEdge(23, 13);
		graph.addEdge(30, 12);
		graph.addEdge(13, 12);
		graph.addEdge(25, 12);

		// Add actors
		graph.getNode(12).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setBanditEncounters(9);
			}

		});

		return graph;
	}

	/**
	 * Get the Necromancer's dialogue for when the player is in the Castle at the
	 * end of the game.
	 * 
	 * @return the dialogue graph
	 */
	private DialogueGraph getCastleEncounter() {
		DialogueGraph graph = new DialogueGraph();

		graph.addNode("Necromancer: Well well well. Look who decided to show up? I set this banquet out and you leave me waiting here for an eternity?"); // 0
		graph.addNode("P: An eternity?"); // 1
		graph.addNode("Necromancer: When you live forever, time has no meaning anymore."); // 2
		graph.addNode("P: You made all this for me?"); // 3
		graph.addNode("P: That must be lonely."); // 4
		graph.addNode("P: Where did you get this food from if you don\'t need to eat?"); // 5
		graph.addNode("Necromancer: Of course I made it for you. Who else would come out here? Door to door salesmen?"); // 6
		graph.addNode("P: Thank you, I think?"); // 7
		graph.addNode("P: Those poor salesmen!"); // 8
		graph.addNode("P: Did you try using some bleach?"); // 9
		graph.addNode("Necromancer: You are welcome, now sit down so you can have your last meal and I can kill you. It is a bit of tradition for me with heroes now."); // 10
		graph.addNode("P: But why?"); // 11
		graph.addNode("Necromancer: Look when I see you young heroes come in here I get hopeful that maybe you will be the one to end my eternity, but you always die."); // 12
		graph.addNode("P: I will defeat you!"); // 13
		graph.addNode("Necromancer: Many have tried and all have failed, no hero. "); // 14
		graph.addNode("Necromancer: While you\' here, be sure to sit down please and eat, don\\'t let your last moments be spent in hungering agony as well."); // 15
		graph.addNode("*Attempt to fight*"); // 16
		graph.addNode("Necromancer: Now get some good rest in my dungeon."); // 17 // teleport player to dungeon
		graph.addNode("Necromancer: YOU KNOW WHAT? FINE, YOU GET YOUR BATTLE ON AN EMPTY STOMACH, BUT FIRST … TO THE DUNGEON!"); // 18
		graph.addNode("Necromancer: They are salesmen, merchants no one will miss. Besides they are all cons anyway. Shame I never got my money back though."); // 19
		graph.addNode("P: I will avenge those salesmen!"); // 20
		graph.addNode("P: Yeah they are truly the worst of merchants."); // 21
		graph.addNode("Necromancer: Very true, now get a good meal and then a nap before I destroy you."); // 22 // player can leave and return later
		graph.addNode("Necromancer: Bleach never works, besides it leaves behind a smell that gives me a killer headache."); // 23
		graph.addNode("P: Wait. I thought you couldn't smell?"); // 24
		graph.addNode(
				"Necromancer: HOW WOULD YOU KNOW? Sorry, sorry. It has been a long day and I just wish to destroy you, I was gonna let you eat this fine feast but now … "); // 25
		graph.addNode("Necromancer: What would you know of lonely? I have lived for eons with only myself... can you really call that living?"); // 26
		graph.addNode("P: C\'mon you are more than just a Necromancer, you are an icon of fear! You inspire heroes to rise up! Don\'t be so down on yourself."); // 27
		graph.addNode("P: Motions? What motions?"); // 28
		graph.addNode("Necromancer: You know, flattery won\'t save you and neither will pity."); // 29
		graph.addNode("Necromancer: Oh, you know. Kill the sick and raise them as undead to watch over their towns and continue with their jobs."); // 30
		graph.addNode("P: WAIT WHAT?"); // 31
		graph.addNode("Necromancer: Hmmm?"); // 32
		graph.addNode("P: YOUR THE GOOD GUY?! YOU CANT BE! YOU'RE A NECROMANCER."); // 33
		graph.addNode("Necromancer: Why not? Because I am \'evil\'? Because my magic isn\'t holy? Well I never asked to have these gifts."); // 34
		graph.addNode("P: …"); // 35
		graph.addNode("Necromancer: He was a good man, but he asked me for too much, his greed took over him and I had to leave him."); // 36 "
		graph.addNode("P: But in doing so haven\'t you-"); // 37
		graph.addNode("Necromancer: Become the very tyrant I swore to stop? Very astute of you. Why do you think I have been waiting for a hero this whole time?");
		graph.addNode("P: …"); // 39
		graph.addNode("Necromancer: How about you? Will you be the one to end my suffering?"); // 40
		graph.addNode("P: Yes"); // 41
		graph.addNode("P: Yes, but I would like to save you, not end you."); // 42 // only possible if the player organized the bandit camp and took care of
																				// the villagers with the mushrooms
		graph.addNode("Necromancer: Thank you, now get good rest in the dungeon. I look forward to our fight."); // 43
		graph.addNode("Necromancer: What do you mean save me?"); // 44
		graph.addNode("P: You are a good man, I wish to join your cause, and save you from your loneliness."); // 45
		graph.addNode("Necromancer: You cannot, you are the hero! You must either end me or die by my hands, there is no saving!"); // 46
		graph.addNode("P: But what if I followed in your footsteps? I already organized your bandit camp and took care of the villagers with the mushrooms?"); // 47
		graph.addNode("Necromancer: I suppose so. Hmmm. Are you really willing to give up your mortality to save the world? To live forever is not an easy thing."); // 48
		graph.addNode("P: Yes"); // 49
		graph.addNode("P: No"); // 50
		graph.addNode("Necromancer: You know what? Don\'t question a free lunch."); // 51
		graph.addNode("P: My parents said there\'s no such thing as free lunch."); // 52
		graph.addNode("Necromancer: Well this one is."); // 53
		graph.addNode("P: But its dinner."); // 54
		graph.addNode("Necromancer: Fine, don\'t question a free dinner."); // 55
		graph.addNode("P: But I don\'t want bread, do you have gluten free?"); // 56
		graph.addNode("Necromancer: … Its bread."); // 57
		graph.addNode("P: Fine how about something other than root beer?"); // 58
		graph.addNode("Necromancer: I only have access to what I got from Oog-Lag."); // 59
		graph.addNode("P: Fine then I\'m not eating, let\'s fight!"); // 60
		graph.addNode("Necromancer: YOU KNOW WHAT? YOU JUST TICKED ME OFF. DUNGEON TIME."); // 61 // player teleported to dungeon
		graph.addNode(
				"Necromancer: It is just a chore. And when you don\'t need to eat and you prepare a feast for someone who doesn\'t show up … you get a little upset."); // 62
		graph.addNode("Necromancer: I mostly just go through the motions now."); // 63
		graph.addNode("Necromancer: Hire bandits to keep the normal humans safe from the horrifying mushrooms on the mountains."); // 64
		graph.addNode("Necromancer: Hire a poor knight who was looking for a master to serve and gave them a roof to sleep under and purpose."); // 65
		graph.addNode("Necromancer: I was once praised as the savior of this very Kingdom you know? This castle was the king\'s in fact."); // 66
		graph.addNode("Necromancer: He grew mad and so I became \'evil\' and stopped him."); // 67
		graph.addNode("Necromancer: He destroyed everything I had and so I had nothing else to do but to stay here making sure no tyrant grows again to immense power."); // 68
		graph.addNode("Necromancer: It was not to stop you but to find peace. You see I have been cursed to eternal life until I am slain by a true hero."); // 69
		graph.addNode("Necromancer: Very well then."); // 70
		graph.addNode("*You feel a strange sensation as your body changes*"); // 71
		graph.addNode("*You are now a Necromancer*"); // 72
		graph.addNode("Necromancer: Very well then. Enjoy a nice night in the Dungeon."); // 73
		graph.addNode("Necromancer: Now eat your food and go take a nap in my dungeon so I can be rid of you with a clear conscience."); // 74 // player teleported to
																																			// dungeon combat if player
																																			// returns
		graph.addNode("Necromancer: No I killed all of them ages ago, darn shamwow never absorbed all the blood."); // 75
		graph.addNode(
				"Necromancer: I might as well give you a good final meal. Forgive the lack of variety, the only thing that grows in this kingdom anymore is wheat."); // 76
		graph.addNode("Necromancer: Now I am just going to send you to the dungeon and fight you when I am calmer. Bye."); // 77 // player is teleported to the dungeon

		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 62);
		graph.addEdge(62, 3);
		graph.addEdge(62, 4);
		graph.addEdge(62, 5);
		graph.addEdge(3, 6);
		graph.addEdge(6, 75);
		graph.addEdge(75, 7);
		graph.addEdge(75, 8);
		graph.addEdge(75, 9);
		graph.addEdge(7, 10);
		graph.addEdge(10, 11);
		graph.addEdge(11, 12);
		graph.addEdge(12, 76);
		graph.addEdge(76, 13);
		graph.addEdge(13, 14);
		graph.addEdge(14, 15);
		graph.addEdge(15, 17);
		graph.addEdge(8, 19);
		graph.addEdge(19, 20);
		graph.addEdge(19, 21);
		graph.addEdge(20, 16);
		graph.addEdge(21, 22);
		graph.addEdge(16, 18);
		graph.addEdge(9, 23);
		graph.addEdge(23, 24);
		graph.addEdge(24, 25);
		graph.addEdge(25, 77);
		graph.addEdge(4, 26);
		graph.addEdge(26, 63);
		graph.addEdge(63, 27);
		graph.addEdge(63, 28);
		graph.addEdge(27, 29);
		graph.addEdge(29, 74);
		graph.addEdge(28, 30);
		graph.addEdge(30, 64);
		graph.addEdge(64, 65);
		graph.addEdge(65, 31);
		graph.addEdge(31, 32);
		graph.addEdge(32, 33);
		graph.addEdge(33, 34);

		graph.addEdge(34, 66);
		graph.addEdge(66, 35);
		graph.addEdge(35, 36);
		graph.addEdge(36, 67);
		graph.addEdge(67, 68);
		graph.addEdge(68, 37);
		graph.addEdge(37, 38);
		graph.addEdge(38, 69);
		graph.addEdge(69, 39);
		graph.addEdge(39, 40);
		graph.addEdge(40, 41);
		graph.addEdge(41, 43);
		graph.addEdge(40, 42);
		graph.addEdge(42, 44);
		graph.addEdge(44, 45);
		graph.addEdge(45, 46);
		graph.addEdge(46, 47);
		graph.addEdge(47, 48);
		graph.addEdge(48, 49);
		graph.addEdge(48, 50);
		graph.addEdge(50, 73);
		graph.addEdge(49, 70);
		graph.addEdge(70, 71);
		graph.addEdge(71, 72);

		graph.addEdge(5, 51);
		graph.addEdge(51, 52);
		graph.addEdge(52, 53);
		graph.addEdge(53, 54);
		graph.addEdge(54, 55);
		graph.addEdge(55, 56);
		graph.addEdge(56, 57);
		graph.addEdge(57, 58);
		graph.addEdge(58, 59);
		graph.addEdge(59, 60);
		graph.addEdge(60, 61);

		// Add actors
		DialogueActor transitionActor = new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setFinaleEncounter(2);
			}

		};
		graph.getNode(17).addActor(transitionActor);
		graph.getNode(18).addActor(transitionActor);
		graph.getNode(22).addActor(transitionActor);
		graph.getNode(43).addActor(transitionActor);
		graph.getNode(61).addActor(transitionActor);
		graph.getNode(73).addActor(transitionActor);
		graph.getNode(74).addActor(transitionActor);
		graph.getNode(72).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.setNecromancerTextures();
				player.getStoryStats().setFinaleEncounter(6);
			}

		});
		return graph;
	}
}
