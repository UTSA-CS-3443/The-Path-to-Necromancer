package game.model.sprites.npc;

import java.util.ArrayList;

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
 * The Hippie is the Necromancer's second general
 * Creates anonymous classes that implement DialogueActor
 * 
 * @author ToTryHardRay
 * @author enigma-phi
 * @author hidingrighthere
 *
 */
public class Hippie extends CharacterSprites implements InteractionSprites {
	/**
	 * The Hippie's Texture
	 */
	private Texture hippieTexture;
	/**
	 * The TextureRegions for the pointing animation
	 */
	private ArrayList<TextureRegion> pointing;
	/**
	 * The TextureRegions for the laughing animation
	 */
	private ArrayList<TextureRegion> laughing;
	/**
	 * Whether or not the Hippie is pointing
	 */
	private boolean isPointing;
	/**
	 * Whether or not the Hippie is laughing
	 */
	private boolean isLaughing;

	/**
	 * The time change since the last frame change
	 */
	private float timeChange;
	/**
	 * The index of the current frame for the Fighter animation
	 */
	private int currentFrame;
	/**
	 * The Hippie's width
	 */
	private static final int HIPPIE_HEIGHT = 35;
	/**
	 * The Hippie's height
	 */
	private static final int HIPPIE_WIDTH = 17;

	/**
	 * Constructor. Initialize Texture Values
	 */
	public Hippie() {
		setTextureValues();
		// Set the size of the Hippie
		setBounds(0, 0, HIPPIE_WIDTH, HIPPIE_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	/**
	 * Set the different TextureRegions associated with the Hippie for
	 * animation.
	 */
	@Override
	public void setTextureValues() {
		int width = 71;
		int height = 151;
		int rowHeight = 151;
		int specialWidth = 86;

		// Use the Hippie animations until we have the player SpriteSheet
		hippieTexture = new Texture("CharacterSprites/Hippie.png");

		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();
		laughing = new ArrayList<TextureRegion>();
		pointing = new ArrayList<TextureRegion>();

		laughing.add(new TextureRegion(hippieTexture, 2 * specialWidth, 8 * rowHeight, specialWidth, height));
		laughing.add(new TextureRegion(hippieTexture, 3 * specialWidth, 8 * rowHeight, specialWidth, height));

		pointing.add(new TextureRegion(hippieTexture, 0 * specialWidth, 8 * rowHeight, specialWidth, height));
		pointing.add(new TextureRegion(hippieTexture, 1 * specialWidth, 8 * rowHeight, specialWidth, height));

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(hippieTexture, 0, 0, width, height));

		// move down
		frames.add(new TextureRegion(hippieTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (3) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (6) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 0 * rowHeight, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up
		frames.add(new TextureRegion(hippieTexture, (1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (3) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (6) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 1 * rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move left
		frames.add(new TextureRegion(hippieTexture, (2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (3) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (6) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 2 * rowHeight, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move right
		frames.add(new TextureRegion(hippieTexture, (2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (3) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (1) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (6) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 3 * rowHeight, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-left
		frames.add(new TextureRegion(hippieTexture, (1) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (3) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (1) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (6) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 4 * rowHeight, width, height));
		super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-right
		frames.add(new TextureRegion(hippieTexture, (1) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (3) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (1) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (6) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 5 * rowHeight, width, height));
		super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up-right
		frames.add(new TextureRegion(hippieTexture, (1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (3) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (6) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 6 * rowHeight, width, height));
		super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up-left
		frames.add(new TextureRegion(hippieTexture, (1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (3) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (6) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture, (4) * width, 7 * rowHeight, width, height));
		super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		super.setAnimationSpeed(2);
		super.setRunningSpeed(1);

	}

	/**
	 * Define the Hippie's physics body.
	 * 
	 * @param world
	 *            is the world to define the Hippie in
	 * @param x
	 *            is the Hippie's x-coordinate
	 * @param y
	 *            is the Hippie's y-coordinate
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

		// coordinates of the Hippie's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-HIPPIE_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(HIPPIE_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-HIPPIE_WIDTH / 2, -HIPPIE_HEIGHT / 2); // bottom
		// left
		vertice[3] = new Vector2(HIPPIE_WIDTH / 2, -HIPPIE_HEIGHT / 2); // bottom
		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);

	}

	/**
	 * Update the hippie's animation
	 * 
	 * @param dt
	 *            is the change in time since the last render
	 * @return the textureRegion to render
	 */
	@Override
	public TextureRegion getFrame(float dt) {
		// get the laughing animation
		if (isLaughing) {
			this.timeChange += dt;
			if (this.timeChange > 0.5) {
				this.timeChange = 0;
				this.currentFrame++;
				if (this.currentFrame == 2) {
					this.isLaughing = false;
					return super.getFrame(dt);
				}
			}
			return this.laughing.get(this.currentFrame);
			// get the pointing animation
		} else if (isPointing) {
			this.timeChange += dt;
			if (this.timeChange > 0.5) {
				this.timeChange = 0;
				this.currentFrame++;
				if (this.currentFrame == 2) {
					this.isPointing = false;
					return super.getFrame(dt);
				}
			}
			return this.pointing.get(this.currentFrame);
		}
		// get default animation
		return super.getFrame(dt);
	}

	/**
	 * Set the hippie to be in the pointing animation
	 * 
	 * @param b
	 *            is the boolean value to set to
	 */
	public void setIsPointing(boolean b) {
		this.isPointing = b;
		this.timeChange = 0;
		this.currentFrame = 0;
	}

	/**
	 * Set the hippie to be in the laughing animation
	 * 
	 * @param b
	 *            is the boolean value to set to
	 */
	public void setIsLaughing(boolean b) {
		this.isLaughing = b;
		this.timeChange = 0;
		this.currentFrame = 0;
	}

	/**
	 * 
	 * Takes in a player and this function is the dialogue between the player and
	 * the hippie outside of middle mountain
	 * 
	 * @param player
	 * @return the dialogue graph
	 */
	@Override
	public DialogueGraph getDialogue(Player player) {
		if (player.getStoryStats().getHippieEncounter() == 0)
			return this.getFirstDialogue();
		if (player.getStoryStats().getHippieEncounter() == 2)
			return this.getSecondDialogue(player);
		if (player.getStoryStats().getHippieEncounter() == 4)
			return this.getLogicDialogue();
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Hippie: I am done teaching you. Now be gone.");
		return graph;
	}

	/**
	 * Get the Hippie's initial dialogue graph
	 * 
	 * 
	 * @return the dialogue graph
	 */
	private DialogueGraph getFirstDialogue() {
		DialogueGraph graph = new DialogueGraph();

		graph.addNode("Hippie: Hey man, what are you doing here? You're trespassing on the land where Mother Gaia sleeps."); // 0
		graph.addNode("P: How can you speak for the Earth God scoundrel? Only I can speak for her!"); // 1
		graph.addNode("*Crowd goes silent*"); // 2
		graph.addNode("Hippie: What was that? Only I, Lord Necro's second general can speak for Gaia, protector of the earth!"); // 3
		graph.addNode("P: I'm the real voice of Mother Gaia you fool, how dare you speak for her!"); // 4
		graph.addNode("*Crowd begins to boo* Angry Mob Occupant #1: \"She's the voice of mother Gaia you jerk!\""); // 5
		graph.addNode("Hippie: See even the crowd agrees with me, you are a liar!"); // 6
		graph.addNode("P: You will all burn for your insolence; Mother Gaia won't allow you to silence me!"); // 7
		graph.addNode("*Crowd boos louder* Angrier Mob Occupant #4: \"This man is a fool! tell him what's up about Mother Gaia\""); // 8
		graph.addNode("Hippie: Some just need to be reborn, and how does one be reborn in this world!"); // 9
		graph.addNode("Angry crowd: By Gorignak!"); // 10
		graph.addNode("*You are hit over the head with a rock*"); // 11 summon death screen
		graph.addNode("P: Yes, but how do we know that you aren't lying as well?"); // 12
		graph.addNode("Hippie: Wait, what?"); // 13 go to logic route
		graph.addNode(""); // 14
		graph.addNode(""); // 15
		graph.addNode(""); // 16
		graph.addNode(""); // 17
		graph.addNode(""); // 18
		graph.addNode(""); // 19 summon death screen
		graph.addNode("P: Getting closer to the Earth, or are you telling me that you don't do this?"); // 20
		graph.addNode("Crowd whispers amongst themselves: \"if stripping to undergarments is what one must really do...\""); // 21
		graph.addNode("Hippie: Be still followers of Gaia, this man is only acting like this because he has nothing else to lose."); // 22 connect to 37
		graph.addNode("P: *take off pants* I do these things because I'm not scared of being closer to Gaia."); // 23
		graph.addNode("Crowd: To be fair he is getting closer by removing his clothes..."); // 24
		graph.addNode("Hippie: Do you take me for a fool?! Gaia would never want us to strip to be closer to her!"); // 25
		graph.addNode("P: Well, I disagree, watch and behold me!"); // 26
		graph.addNode("*The area goes silent*"); // 27 teleport to next map
		graph.addNode("P: Begin making poses"); // 28 teleport to next map
		graph.addNode("P: Stand heroically that you just stripped in front of multiple people."); // 29
		graph.addNode("P: As if, *eat dirt* I'm the one with mother Gaia, are you?!"); // 30
		graph.addNode("Crowd occupant #2: Did he just eat dirt?"); // 31
		graph.addNode("Hippie: Why did you just eat dirt?"); // 32
		graph.addNode("P: You want some?"); // 33
		graph.addNode("Hippie: NO! That's disgusting!"); // 34
		graph.addNode("P: My love for Gaia is absolute is yours?"); // 35
		graph.addNode("Hippie: Of course it is, I am her representative!"); // 36
		graph.addNode("P: And how do we know you're not lying?"); // 37 connect to 13
		graph.addNode("P: Don't knock it till you try it!"); // 38
		graph.addNode("Crowd: To be fair, we haven't eaten dirt yet, so we don't know how it tastes..."); // 39
		graph.addNode("Hippie: Don't eat dirt! he's trying to trick all of you!"); // 40
		graph.addNode("P: Trick you into loving the Earth more? I'm going to eat more because I love The Earth"); // 41 connect to 51
		graph.addNode("P: Don't listen to her, she wants to prevent you all from enjoying yourselves!"); // 42
		graph.addNode("Crowd: Enjoy ourselves by eating dirt?"); // 43
		graph.addNode("Hippie: Do you see why this man is a fool? He tried to make us eat dirt! Tell me people are you fools?"); // 44
		graph.addNode("*Crowd begins to boo*: Let's throw rocks at him!"); // 45
		graph.addNode("*A barrage of rocks hits you*"); // 46 death screen
		graph.addNode("P: We are what we eat, I am the Earth you see!"); // 47 connect to 52
		graph.addNode("*Crowd boos*: He's using science! Hang him!"); // 48
		graph.addNode("Hippie: Be calm my children we do not hang the science-goers, we throw rocks at them!"); // 49 connect to 46
		graph.addNode("P: *continue shoveling dirt in your mouth* Because I love the Earth!"); // 50
		graph.addNode("Crowd: To be fair, if he does love the earth, he would eat it."); // 51
		graph.addNode("Hippie: Eating dirt has nothing to do with being closer to Mother Gaia!"); // 52
		graph.addNode("P: *Shovel more dirt into your mouth* Yesh ifh doths."); // 53
		graph.addNode("Crowd: he's still going, maybe we should be eating dirt..."); // 54
		graph.addNode("Hippie: What are you doing my children of Gaia, don't eat dirt!"); // 55 maybe connect to 14
		graph.addNode("P: *cram more dirt in an already full mouth of dirt* WATGY MEHAS"); // 56
		graph.addNode("Crowd with dirt in their mouths: Oh my god, he's chocking, someone save him!"); // 57
		graph.addNode("P: *shove more dirt into your already exceedingly full mouth* ghgfdsfsd"); // 58
		graph.addNode("Crowd begins to chant: Go, Go, Go!"); // 59 connect to 61
		graph.addNode("P: Fall to the ground, chocking"); // 60
		graph.addNode("*You black out*"); // 61 // teleport to the next map
		graph.addNode("AND STAY OUT YOU WEIRDO"); // 62
		graph.addNode("Note: \"We appreciate what you have done. We all shall eat dirt to get closer to Mother Gaia.\""); // 63
		graph.addNode("P: Who are you to speak for a god that stays silent?"); // 64
		graph.addNode("Hippie: I'm surprised you have not heard of me. I am the speaker of trees, speaker of the earth and healer of the sick."); // 65
		graph.addNode("Hippie: I am the Necromancer's general #2."); // 66
		graph.addNode("P: What the heck are you talking about?"); // 67 alternate path dialogue
		graph.addNode("Hippie: Never mind. Just go."); // 68
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		graph.addEdge(8, 9);
		graph.addEdge(9, 10);
		graph.addEdge(10, 11);
		graph.addEdge(6, 12);
		graph.addEdge(12, 13);
		graph.addEdge(14, 15);
		graph.addEdge(15, 16);
		graph.addEdge(16, 17);
		graph.addEdge(17, 18);
		graph.addEdge(18, 19);
		graph.addEdge(0, 64);
		graph.addEdge(65, 66);
		graph.addEdge(64, 65);
		graph.addEdge(0, 67);

		graph.addEdge(16, 20);
		graph.addEdge(20, 21);
		graph.addEdge(21, 22);
		graph.addEdge(22, 37);
		graph.addEdge(37, 13);
		graph.addEdge(22, 23);
		graph.addEdge(23, 24);
		graph.addEdge(24, 25);
		graph.addEdge(25, 37);
		graph.addEdge(25, 26);
		graph.addEdge(26, 27);
		graph.addEdge(27, 28);
		graph.addEdge(27, 29);

		graph.addEdge(3, 30);
		graph.addEdge(30, 31);
		graph.addEdge(31, 32);
		graph.addEdge(32, 33);
		graph.addEdge(33, 34);
		graph.addEdge(34, 35);
		graph.addEdge(34, 38);
		graph.addEdge(35, 36);
		graph.addEdge(36, 37);

		graph.addEdge(37, 13);
		graph.addEdge(38, 39);
		graph.addEdge(39, 40);
		graph.addEdge(40, 41);
		graph.addEdge(41, 51);
		graph.addEdge(40, 42);
		graph.addEdge(42, 43);
		graph.addEdge(43, 44);
		graph.addEdge(44, 45);
		graph.addEdge(45, 46);

		graph.addEdge(32, 47);
		graph.addEdge(47, 52);
		graph.addEdge(47, 48);
		graph.addEdge(48, 49);
		graph.addEdge(49, 46);
		graph.addEdge(32, 50);
		graph.addEdge(50, 51);
		graph.addEdge(51, 52);
		graph.addEdge(52, 53);
		graph.addEdge(53, 54);
		graph.addEdge(54, 55);
		graph.addEdge(55, 56);
		graph.addEdge(56, 57);
		graph.addEdge(57, 58);
		graph.addEdge(58, 59);
		graph.addEdge(59, 61);
		graph.addEdge(57, 60);
		graph.addEdge(60, 61);
		graph.addEdge(61, 63);
		graph.addEdge(28, 62);
		graph.addEdge(29, 62);
		graph.addEdge(13, 68);

		// add the actors
		DialogueActor deathActor = new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setHippieEncounter(10);
			}

		};
		DialogueActor transitionActor = new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setHippieEncounter(11);
			}

		};
		DialogueActor logicActor = new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setHippieEncounter(4);
			}
			
		};

		graph.getNode(0).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				setIsPointing(true);
			}
			
		});
		
		graph.getNode(11).addActor(deathActor);
		graph.getNode(19).addActor(deathActor);
		graph.getNode(46).addActor(deathActor);

		graph.getNode(29).addActor(transitionActor);
		graph.getNode(28).addActor(transitionActor);
		graph.getNode(61).addActor(transitionActor);

		graph.getNode(13).addActor(logicActor);
		graph.getNode(66).addActor(logicActor);

		graph.getNode(67).addActor(new DialogueActor() {
			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setHippieEncounter(2);
			}

		});
		return graph;
	}

	/**
	 * Get the Hippie's second branch of dialogue
	 * 
	 * @param player
	 *            is the player to base dialogue off of
	 * @return the dialogue graph
	 * 
	 */
	private DialogueGraph getSecondDialogue(Player player) {
		DialogueGraph graph = new DialogueGraph();

		// add nodes
		graph.addNode("Crowd: Tell him Gaia's representative!"); // Node# 0
		graph.addNode("Hippie: This is the location where mother Gaia was born! Where she first came into this world!"); // Node# 1
		graph.addNode("P: *kick her aside* YES! I KNOW THIS! WE MUST DEFEND MOTHER GAIA!"); // Node# 2
		graph.addNode("Crowd: shocked with a whisper: What did he say?"); // Node# 3
		graph.addNode("P: *knock out the general*"); // Node# 4
		graph.addNode("P: I said we must do everything in our power to defend mother Gaia."); // Node# 5
		graph.addNode("Crowd: Hey he just knocked out Gaia's representative out!"); // Node# 6
		graph.addNode("Crowd: but why'd you kick her aside?"); // Node# 7
		graph.addNode("P: Because I'm overcome with joy of defending mother Gaia!"); // Node# 8
		graph.addNode("P: I did not knock her out, I simple re-scheduled her bedtime!"); // Node# 9
		graph.addNode("P: Can you prove that I did?"); // Node# 10
		graph.addNode("Hippie: Yes, because I am the real representative of Gaia!"); // Node# 11
		graph.addNode("Crowd: We just saw you do it!"); // Node# 12
		graph.addNode("P: Nuh uh!"); // Node# 13
		graph.addNode("P: Now that I think of it, I may have done that, though it's a bit foggy."); // Node# 14
		graph.addNode("Crowd: WE JUST SAW YOU"); // Node# 15
		graph.addNode("P: Nope, you guys are just crazy!"); // Node# 16
		graph.addNode("*A rock flies at you knocking you unconscious*"); // Node# 17
		graph.addNode("Crowd: *is silent*"); // Node# 18
		graph.addNode("P: Sooo, I'm just going to leave, but I hope you can guys can figure out who knocked your leader out."); // Node# 19
		graph.addNode("*A rock flies at you knocking you unconscious*"); // Node# 20
		graph.addNode("Crowd: *boos are heard* you are lying! What can you do that she can't?"); // Node# 21
		graph.addNode("P: Watch me mortals as I commit feats of magic never seen before!"); // Node# 22
		graph.addNode("P: I can remain conscious after being hit once!"); // Node# 23
		graph.addNode("Crowd: Like?"); // Node# 24
		graph.addNode("P: For example, anyone have a deck of cards?"); // Node# 25
		graph.addNode("P: Well, I can cast a very nice sleep spell."); // Node# 26
		graph.addNode("*A rock flies at you knocking you unconscious*"); // Node# 27
		graph.addNode("P: *Do card trick* Pick a card any card!"); // Node# 28
		graph.addNode("Crowd: I do!"); // Node# 29
		graph.addNode("Crowd: He's not even doing anything, he's just yodeling!!!"); // Node# 30
		graph.addNode("P: Fantastic, now throw it up in the air!"); // Node# 31
		graph.addNode("*Cards go flying in the air*"); // Node# 32
		graph.addNode("P: *begin yodeling*"); // Node# 33
		graph.addNode("P: *Run away* "); // Node# 34
		graph.addNode("*A rock flies at you knocking you unconscious*"); // Node# 35
		graph.addNode("P: Fools, this is my anger spell, behold you are all filled with rage! My magic works!"); // Node# 36
		graph.addNode("P: *do card trick as the crowd watches you*"); // Node# 37
		graph.addNode("P: Now, is THIS your card?"); // Node# 38
		graph.addNode("Crowd: NO, IT'S NOT, YOU'RE A FRAUD!"); // Node# 39
		graph.addNode("P: Ahh yes, but did I say this is the card you chose?"); // Node# 40
		graph.addNode("Crowd: All in favor of killing him raise your hand?"); // Node# 41
		graph.addNode("*entire audience raises their hands*"); // Node# 42
		graph.addNode("P: Yes, yes I did"); // Node# 43
		graph.addNode("Crowd: And you kicked her from being so excited??"); // Node# 44
		graph.addNode("Hippie: *The general gets back up* WHAT THE HECK!"); // Node# 45
		graph.addNode("P: I'm so sorry, I'm such a spaz"); // Node# 46
		graph.addNode("P: *kick to the floor again* Dang not again"); // Node# 47
		graph.addNode("P: Is that so? Perhaps you would like to hear more of Mother Gaia?"); // Node# 48
		graph.addNode("Crowd: Come on man, control yourself!"); // Node# 49
		graph.addNode("Hippie: *brushes herself off* It's fine I guess, it's always okay to see a new fan"); // Node# 50
		graph.addNode("P: Sorry sorry, I'll try to calm down"); // Node# 51
		graph.addNode("P: *kick again* I just can't! Just *kick* too *kick* excited!"); // Node# 52
		graph.addNode("P: *deck her*"); // Node# 53
		graph.addNode("P: I really am your biggest fan, you wouldn't believe it"); // Node# 54
		graph.addNode("Crowd: *Begins crying* Please stop we beseech thee!"); // Node# 55
		graph.addNode("Crowd: Just go north of here, just leave please!"); // Node# 56
		graph.addNode("P: *stop kicking* I have got to get out of here, which way is out?"); // Node# 57
		graph.addNode("P: I just can't, TOO EXCITED FOR WORDS!"); // Node# 58
		graph.addNode("Crowd: screams and cries, the heavens weep as you continue kicking her"); // Node# 59
		graph.addNode("*A rock flies at you knocking you unconscious*"); // Node# 60
		graph.addNode("*Congratulation, you have joined the cult of Gaia, the next 40 years you spend spreading her great word, and hugging trees*"); // Node# 61
		graph.addNode("P: Please, tell me more"); // Node# 62
		graph.addNode("*You soon die after attempting to meld with a tree.*"); // Node# 63
		graph.addNode("Crowd: Turns around"); // Node# 64
		graph.addNode("P: *run away*"); // Node# 65
		graph.addNode("P: Well, if you're not going to sacrifice anyone, I'll be on my way"); // Node# 66
		graph.addNode("P: We all know we do weird things when we get excited am I right people?"); // Node# 67
		graph.addNode("Crowd: No, we won't, guys let's get him before he tries to love us!"); // Node# 68
		graph.addNode("Crowd: well yeah, but none of us hurt each other"); // Node# 69
		graph.addNode("P: Then you guys have never been truly excited!"); // Node# 70
		graph.addNode("P: You guy have never heard of tough love?"); // Node# 71
		graph.addNode("Crowd: Yeah, but I don't think that's tough love..."); // Node# 72
		graph.addNode("P: *Run at crowd with fists swinging: LET ME LOVE YOU!*"); // Node# 73
		graph.addNode("Crowd Please no"); // Node# 74
		graph.addNode("P: Scream: YOU WILL LOVE ME!"); // Node# 75
		graph.addNode("P: Here let me love you"); // Node# 76
		graph.addNode("Note: Take your love somewhere else!"); // Node# 77
		graph.addNode("*A rock flies at you knocking you unconscious*"); // Node# 78
		graph.addNode("Crowd: we were plenty excited when Gaia healed the sick"); // Node# 79
		graph.addNode("P: Healing the sick impresses you that much, watch this! *pull a coin out of your ear*"); // Node# 80
		graph.addNode("P: Yes, I am really the cash god, if you like me, much cash will come to you in the future."); // Node# 81
		graph.addNode("Crowd: Did you just spawn money from your ear?"); // Node# 82
		graph.addNode("P: Watch me do it again *take a coin out of your ear again*"); // Node# 83
		graph.addNode("P: Yes, don't tell anyone, but I'm the representative of the cash god, BiggusBuckes."); // Node# 84
		graph.addNode("Crowd: collective gasp, moving closer to you"); // Node# 85
		graph.addNode("Crowd: BiggusBuckes?"); // Node# 86
		graph.addNode("Crowd: So, can you summon gold?"); // Node# 87
		graph.addNode("Crowd: Gasps again, then make a run at you"); // Node# 88
		graph.addNode("P: Watch as I do it again!"); // Node# 89
		graph.addNode("P: Yes, let me pass,"); // Node# 90
		graph.addNode("P: Yes, BiggusBuckus is the god of wealth. fortune, and deer"); // Node# 91
		graph.addNode("Crowd: Alright sir, please bless us before we go"); // Node# 92
		graph.addNode("P: Blessings are for those that are free of sin"); // Node# 93
		graph.addNode("P: *throw coins at them* behold the blessing of the god of BiggusBuckus!"); // Node# 94
		graph.addNode("*The crowd tears you apart, desperately trying to steal the change inside of you*"); // Node# 95
		graph.addNode("P: Why yes I can! Just look behind you"); // Node# 96
		graph.addNode("P: I require a sacrifice to truly sacrifice for you!"); // Node# 97
		graph.addNode("*The crowd shifts uncomfortably*"); // Node# 98
		graph.addNode("*All fear the god BiggusBuckus*"); // Node# 99
		graph.addNode("*you spend the next few years representing this new bogus god, resulting in a new religion to take shape."); // Node# 100
		graph.addNode("The crowd boos you: Hey maybe he has gold inside of him!"); // Node# 101

		// add edges
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(4, 6);
		graph.addEdge(7, 8);
		graph.addEdge(6, 10);
		graph.addEdge(9, 9);
		graph.addEdge(10, 12);
		graph.addEdge(12, 13);
		graph.addEdge(13, 15);
		graph.addEdge(16, 17);
		graph.addEdge(14, 18);
		graph.addEdge(9, 20);
		graph.addEdge(11, 21);
		graph.addEdge(22, 24);
		graph.addEdge(24, 25);
		graph.addEdge(24, 26);
		graph.addEdge(23, 27);
		graph.addEdge(25, 29);
		graph.addEdge(34, 35);
		graph.addEdge(31, 32);
		graph.addEdge(32, 34);
		graph.addEdge(32, 33);
		graph.addEdge(33, 30);
		graph.addEdge(30, 35);
		graph.addEdge(28, 37);
		graph.addEdge(37, 38);
		graph.addEdge(38, 39);
		graph.addEdge(39, 40);
		graph.addEdge(39, 36);
		graph.addEdge(36, 41);
		graph.addEdge(41, 42);
		graph.addEdge(44, 43);
		graph.addEdge(43, 45);
		graph.addEdge(45, 47);
		graph.addEdge(45, 46);
		graph.addEdge(47, 49);
		graph.addEdge(49, 52);
		graph.addEdge(49, 51);
		graph.addEdge(46, 50);
		graph.addEdge(50, 54);
		graph.addEdge(53, 55);
		graph.addEdge(5, 7);
		
		graph.addEdge(58, 59);
		graph.addEdge(55, 58);
		graph.addEdge(55, 57);
		graph.addEdge(57, 56);
		graph.addEdge(59, 60);
		graph.addEdge(54, 48);
		graph.addEdge(48, 62);
		graph.addEdge(62, 61);
		graph.addEdge(61, 63);
		graph.addEdge(64, 65);
		graph.addEdge(69, 71);
		graph.addEdge(67, 69);
		graph.addEdge(71, 72);
		graph.addEdge(72, 76);
		graph.addEdge(73, 77);
		graph.addEdge(74, 75);
		graph.addEdge(74, 73);
		graph.addEdge(76, 74);
		graph.addEdge(75, 68);
		graph.addEdge(68, 78);
		graph.addEdge(79, 80);
		graph.addEdge(70, 79);
		graph.addEdge(80, 82);
		graph.addEdge(84, 86);
		graph.addEdge(83, 85);
		graph.addEdge(89, 88);
		graph.addEdge(91, 87);
		graph.addEdge(93, 101);
		graph.addEdge(88, 95);
		graph.addEdge(96, 64);
		graph.addEdge(90, 92);
		graph.addEdge(98, 66);
		graph.addEdge(97, 98);
		graph.addEdge(100, 99);
		graph.addEdge(94, 100);
		graph.addEdge(66, 101);
		graph.addEdge(51, 50);
		graph.addEdge(101, 95);
		graph.addEdge(48, 53);
		graph.addEdge(50, 53);
		graph.addEdge(52, 55);
		graph.addEdge(42, 35);
		graph.addEdge(40, 41);
		graph.addEdge(18, 19);
		graph.addEdge(81, 92);
		graph.addEdge(8, 44);
		graph.addEdge(8, 67);
		graph.addEdge(3, 5);
		graph.addEdge(3, 4);
		graph.addEdge(6, 11);
		graph.addEdge(6, 9);
		graph.addEdge(15, 16);
		graph.addEdge(15, 14);
		graph.addEdge(29, 28);
		graph.addEdge(29, 31);
		graph.addEdge(21, 22);
		graph.addEdge(21, 23);
		graph.addEdge(69, 70);
		graph.addEdge(82, 84);
		graph.addEdge(82, 83);
		graph.addEdge(85, 81);
		graph.addEdge(85, 89);
		graph.addEdge(92, 94);
		graph.addEdge(92, 93);
		graph.addEdge(86, 91);
		graph.addEdge(86, 90);
		graph.addEdge(87, 96);
		graph.addEdge(87, 97);

		// add the actors
		DialogueActor deathActor = new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setHippieEncounter(10);
			}

		};
		DialogueActor transitionActor = new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setHippieEncounter(11);
			}

		};
		graph.getNode(17).addActor(deathActor);
		graph.getNode(20).addActor(deathActor);
		graph.getNode(27).addActor(deathActor);
		graph.getNode(35).addActor(deathActor);
		graph.getNode(60).addActor(deathActor);
		graph.getNode(63).addActor(deathActor);
		graph.getNode(78).addActor(deathActor);
		graph.getNode(95).addActor(deathActor);
		graph.getNode(99).addActor(deathActor);

		graph.getNode(19).addActor(transitionActor);
		if (player.isRogue())
			graph.getNode(34).addActor(transitionActor);
		graph.getNode(56).addActor(transitionActor);
		graph.getNode(65).addActor(transitionActor);
		graph.getNode(77).addActor(transitionActor);

		return graph;
	}

	/**
	 * Get the logic dialogue sequence for the Hippie
	 * 
	 * @return the dialogue graph
	 */
	private DialogueGraph getLogicDialogue() {
		DialogueGraph graph = new DialogueGraph();
		
		// Add nodes
		graph.addNode("Hippie: I'm surprised you have not heard of me, I am the speaker for mother Gaia"); // Node# 0
		graph.addNode("P: Interesting, so you're telling me you can speak for Gaia herself?"); // Node# 1
		graph.addNode("P: Well, if this Gaia exists, and we are her children, how do you explain the cruelty that's in this world?"); // Node# 2
		graph.addNode("Crowd: *murmurs* is he an idiot or something?"); // Node# 3
		graph.addNode("Hippie: Why yes I do, what question would you like answered?"); // Node# 4
		graph.addNode("P: What is at the end of my life?"); // Node# 5
		graph.addNode("P: To live in pain and suffering."); // Node# 6
		graph.addNode("*The crowd laughs*"); // Node# 7
		graph.addNode("Hippie: *chuckling* You would be surprised how often that question is thrown at me."); // Node# 8
		graph.addNode("Hippie: What's the meaning of life to you if I may ask?"); // Node# 9
		graph.addNode("P: To enjoy life to its fullest."); // Node# 10
		graph.addNode("P: To reproduce of course."); // Node# 11
		graph.addNode("Hippie: We as a people are not born into this world filled with hope, we're born with pain."); // Node# 12
		graph.addNode("Hippie: Well, I disagree, I believe we seek hope more than anything else."); // Node# 13
		graph.addNode("P: Why love?"); // Node# 14
		graph.addNode("Hippie: Ha-ha, well that's close, we seek love above all else."); // Node# 15
		graph.addNode("P: Pfft, love is a useless emotion."); // Node# 16
		graph.addNode("Crowd: Boos"); // Node# 17
		graph.addNode("*A rock is thrown at you*"); // Node# 18
		graph.addNode("Hippie: Well, I doubt that my words will reach you, but hopefully mother Gaia will."); // Node# 19
		graph.addNode("P: Then why seek hope if we're always in pain? That's insanity."); // Node# 20
		graph.addNode("P: You're an utter moron!"); // Node# 21
		graph.addNode("Crowd: Boos"); // Node# 22
		graph.addNode("*A rock is thrown at you*"); // Node# 23
		graph.addNode("Hippie: Well, I doubt that my words will reach you, but hopefully Mother Gaia will."); // Node# 24
		graph.addNode("Hippie: Love is what we all seek is it not? The feeling of being wanted, wanting to finally not be alone?"); // Node# 25
		graph.addNode("Hippie: We are in a constant fight with ourselves if you can believe it..."); // Node# 26
		graph.addNode("P: Where are you going with this?"); // Node# 27
		graph.addNode("P: I believe it"); // Node# 28
		graph.addNode("P: Wait what?"); // Node# 29
		graph.addNode("Hippie: That depends, do you fear death?"); // Node# 30
		graph.addNode("Hippie: They're not your biggest discouragements, you are."); // Node# 31
		graph.addNode("Hippie: Do you though? I want you to think of three discouragements in your life."); // Node# 32
		graph.addNode("Hippie: I want you to think of your three discourages in your life."); // Node# 33
		graph.addNode("Hippie: You may pass through, but remember my words."); // Node# 34
		graph.addNode("Hippie: In the end, we are only holding ourselves back."); // Node# 35
		graph.addNode("P: No, I haven't."); // Node# 36
		graph.addNode("Hippie: Know when this was, when you were born."); // Node# 37
		graph.addNode("Hippie: Why?"); // Node# 38
		graph.addNode("P: Pfft, death is beneath me."); // Node# 39
		graph.addNode("P: Yes, I do."); // Node# 40
		graph.addNode("Hippie: Here I'll tell you, imagine waking from a sleep you never slept to."); // Node# 41
		graph.addNode("I don't know what'll happen."); // Node# 42
		graph.addNode("P: Because the void awaits me."); // Node# 43
		graph.addNode("Hippie: Why be afraid, you've experienced the void before."); // Node# 44
		graph.addNode("P: This better not be some sort of ooh I have depression joke."); // Node# 45
		graph.addNode("Crowd: *laughter is heard*"); // Node# 46
		graph.addNode("Hippie: I assure you I'm not."); // Node# 47
		graph.addNode("Hippie: Do you remember what happened before you were born?"); // Node# 48
		graph.addNode("Crowd: boos are heard."); // Node# 49
		graph.addNode("Hippie: The idea that we experience life after death is foolish, after-death is like before life."); // Node# 50
		graph.addNode("Hippie: There isn't wandering in the darkness, no hell, no heaven, just, nothing."); // Node# 51
		graph.addNode("Hippie: Then how can you tell me that you're scared of death?"); // Node# 52
		graph.addNode("P: No, not really?"); // Node# 53
		graph.addNode("P: Can I leave? This is getting me bummed out."); // Node# 54
		graph.addNode("P: Please tell me more."); // Node# 55
		graph.addNode("P: That's, weird can I leave now?"); // Node# 56
		graph.addNode("Hippie: In the end, just enjoy life, as while this is all we have, it's more than others get."); // Node# 57
		graph.addNode("*A rock is thrown at you*"); // Node# 58
		graph.addNode("Hippie: Well, I doubt that my words will reach you, but hopefully Mother Gaia will."); // Node# 59
		graph.addNode("P: Your lies are so transparent."); // Node# 60
		graph.addNode("Crowd: *Boos are heard*"); // Node# 61
		graph.addNode("Hippie: Why do you allow the past to influence you to such a degree?"); // Node# 62
		graph.addNode("P: It's not my fault, I was born this way."); // Node# 63
		graph.addNode("Hippie: You have a lot of anger inside of you, don't you?"); // Node# 64
		graph.addNode("Hippie: Well, you can always blame your parents"); // Node# 65
		graph.addNode("Hippie: and by this nature, has it benefited you?"); // Node# 66
		graph.addNode("P: It doesn't, it's just simply my nature.");   //Node# 67
		graph.addNode("P: I didn't ask to be brought here.");   //Node# 68
		graph.addNode("Hippie: We as humans are always subject to change, and for some reason we hate it.");   //Node# 69
		graph.addNode("P: I don't get where you're going with this..");   //Node# 70
		graph.addNode("P: What's your point.");   //Node# 71
		graph.addNode("P: What's the alternative, they are the ones that raised me.");   //Node# 72
		graph.addNode("P: Yes, I can.");   //Node# 73
		graph.addNode("Hippie: When all the people you can blame are gone, who is left but yourself.");   //Node# 74
		graph.addNode("Hippie: Think, right after your parents, who's next? Your friends? Family? The Governmemt");   //Node# 75
		graph.addNode("Hippie: Yet at one point, your parents aren't going to be there.");   //Node# 76
		graph.addNode("P: Why don't you just screw off with this deep stuff, I know myself.");   //Node# 77
		graph.addNode("P: So, you're telling me that I should love change?");   //Node# 78
		graph.addNode("Hippie: You may pass, you have learned a lesson from Mother Gaia, I hope you take it to heart.");   //Node# 79
		graph.addNode("Hippie: My point is that the only one that keeping you from being the best person you can be is yourself, no one else.");   //Node# 80
		graph.addNode("Hippie: We are never perfect, and it would be foolish to assume one of us are.");   //Node# 81
		graph.addNode("Hippie: Not love, but appreciate it, for the biggest changes in our lives tend to be both the happiest and saddest moments of our lives.");   //Node# 82
		graph.addNode("P: We hate it as some of us are done changing for others, we're perfect the way we are.");   //Node# 83
		graph.addNode("P: good point.");   //Node# 84
		graph.addNode("P: Ridiculous, how can something be sad, but happy?");   //Node# 85
		graph.addNode("Hippie: Perhaps seeing your child off as they go to college.");   //Node# 86
		graph.addNode("Hippie: You may pass, you have learned a lesson from Mother Gaia, I hope you take it to heart.");   //Node# 87

		// add edges
		graph.addEdge(0, 60);
		graph.addEdge(1, 3);
		graph.addEdge(0, 1);
		graph.addEdge(2, 7);
		graph.addEdge(3, 4);
		graph.addEdge(4, 2);
		graph.addEdge(4, 5);
		graph.addEdge(5, 30);
		graph.addEdge(6, 13);
		graph.addEdge(7, 8);
		graph.addEdge(8, 9);
		graph.addEdge(9, 11);
		graph.addEdge(9, 6);
		graph.addEdge(9, 10);
		graph.addEdge(10, 13);
		graph.addEdge(11, 15);
		graph.addEdge(12, 21);
		graph.addEdge(12, 20);
		graph.addEdge(13, 12);
		graph.addEdge(14, 25);
		graph.addEdge(15, 16);
		graph.addEdge(15, 14);
		graph.addEdge(16, 17);
		graph.addEdge(17, 19);
		graph.addEdge(19, 18);
		graph.addEdge(20, 26);
		graph.addEdge(21, 22);
		graph.addEdge(22, 24);
		graph.addEdge(24, 23);
		graph.addEdge(25, 26);
		graph.addEdge(26, 29);
		graph.addEdge(26, 28);
		graph.addEdge(27, 48);
		graph.addEdge(28, 32);
		graph.addEdge(29, 33);
		graph.addEdge(30, 40);
		graph.addEdge(30, 39);
		graph.addEdge(31, 35);
		graph.addEdge(32, 31);
		graph.addEdge(33, 31);
		graph.addEdge(35, 34);
		graph.addEdge(36, 48);
		graph.addEdge(37, 27);
		graph.addEdge(38, 43);
		graph.addEdge(38, 42);
		graph.addEdge(39, 49);
		graph.addEdge(40, 38);
		graph.addEdge(41, 37);
		graph.addEdge(42, 41);
		graph.addEdge(43, 44);
		graph.addEdge(44, 45);
		graph.addEdge(44, 36);
		graph.addEdge(45, 46);
		graph.addEdge(46, 47);
		graph.addEdge(47, 48);
		graph.addEdge(48, 53);
		graph.addEdge(49, 59);
		graph.addEdge(50, 57);
		graph.addEdge(51, 54);
		graph.addEdge(51, 55);
		graph.addEdge(52, 51);
		graph.addEdge(53, 52);
		graph.addEdge(54, 35);
		graph.addEdge(55, 50);
		graph.addEdge(56, 34);
		graph.addEdge(57, 56);
		graph.addEdge(59, 58);
		graph.addEdge(60, 61);
		graph.addEdge(61, 64);
		graph.addEdge(62, 68);
		graph.addEdge(62, 67);
		graph.addEdge(63, 62);
		graph.addEdge(64, 63);
		graph.addEdge(64, 2);
		graph.addEdge(65, 73);
		graph.addEdge(65, 72);
		graph.addEdge(66, 70);
		graph.addEdge(67, 66);
		graph.addEdge(68, 65);
		graph.addEdge(69, 78);
		graph.addEdge(69, 83);
		graph.addEdge(70, 69);
		graph.addEdge(71, 80);
		graph.addEdge(72, 76);
		graph.addEdge(73, 59);
		graph.addEdge(74, 77);
		graph.addEdge(74, 71);
		graph.addEdge(75, 74);
		graph.addEdge(76, 75);
		graph.addEdge(77, 59);
		graph.addEdge(78, 82);
		graph.addEdge(80, 79);
		graph.addEdge(81, 84);
		graph.addEdge(82, 84);
		graph.addEdge(82, 85);
		graph.addEdge(83, 81);
		graph.addEdge(84, 87);
		graph.addEdge(85, 86);
		graph.addEdge(86, 87);

		// add the actors
		DialogueActor deathActor = new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setHippieEncounter(10);
			}

		};

		graph.getNode(18).addActor(deathActor);
		graph.getNode(23).addActor(deathActor);
		graph.getNode(58).addActor(deathActor);
		
		return graph;
	}
}
