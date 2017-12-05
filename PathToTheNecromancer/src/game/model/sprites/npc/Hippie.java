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

import game.model.DialogueGraph;
import game.model.sprites.CharacterSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Player;

/**
 * 
 * The Hippie is the Necromancer's second general
 * @author ToTryHardRay
 * @author enigma-phi
 *
 */
public class Hippie extends CharacterSprites implements InteractionSprites{
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
	private static final int HIPPIE_HEIGHT = 30;
	/**
	 * The Hippie's height
	 */
	private static final int HIPPIE_WIDTH = 15;

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
	 * Define the animations associated with the Hippie
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
		frames.add(new TextureRegion(hippieTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(3) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(6) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 1 * rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move left
		frames.add(new TextureRegion(hippieTexture,(2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(3) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(6) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 2 * rowHeight, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move right
		frames.add(new TextureRegion(hippieTexture,(2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(3) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(1) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(6) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 3 * rowHeight, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-left
		frames.add(new TextureRegion(hippieTexture,(1) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(3) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(1) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(6) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 4 * rowHeight, width, height));
		super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-right
		frames.add(new TextureRegion(hippieTexture,(1) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(3) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(1) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(6) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 5 * rowHeight, width, height));
		super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move up-right
		frames.add(new TextureRegion(hippieTexture,(1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(3) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(6) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 6 * rowHeight, width, height));
		super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move up-left
		frames.add(new TextureRegion(hippieTexture,(1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(3) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(6) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(hippieTexture,(4) * width, 7 * rowHeight, width, height));
		super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		super.setAnimationSpeed(2);
		super.setRunningSpeed(1);

	}
	/**
	 * Define the Hippie's physics body.
	 * @param world is the world to define the Hippie in
	 * @param x is the Hippie's x-coordinate
	 * @param y is the Hippie's y-coordinate
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
	 * Update the fighter's animation
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
	 * Takes in a player and this function is the dialogue between
	 * the player and the hippie outside of middle mountain
	 * @param player
	 * @return the dialogue graph
	 */
	public DialogueGraph getDialogue(Player player) {
		DialogueGraph graph = new DialogueGraph();
		
		graph.addNode("Hey man, what are you doing here? You're trespassing on the land where mother gaia sleeps."); // 0
		graph.addNode("How can you speak for the Earth God scoundrel only I can speak for it!"); // 1
		graph.addNode("*croud goes silent*"); // 2
		graph.addNode("What was that? Only I, Lord Nerco's second general can speak for Gaia, protector of the earth!"); // 3
		graph.addNode("I'm the real voice of Mother Gaia you fool, how dare you speak for her!"); // 4
		graph.addNode("*Crowd begins to boo* Angry Mod Occupant #1: She's the voice of mother Gaia you jerk!"); // 5
		graph.addNode("See even the croud agrees with me, you are a liar!"); // 6
		graph.addNode("You will all burn for your insolence, Mother Gaia won't allow you to silence me!"); // 7
		graph.addNode("*croud boo's louder* Angrier Mob Occupant #23: this man is a fool! tell him what's up about Motha Gaia"); // 8
		graph.addNode("Some just need to be reborn, and how does one be reborn in this world!"); // 9
		graph.addNode("Angry crowd: By Gorignak!"); // 10
		graph.addNode("*You are hit over the head with a rock*"); // 11
		graph.addNode("Yes, but how do we know that you aren't lying as well?"); // 12
		graph.addNode("Wait, what?"); // 13 go to logic route
		graph.addNode("*start taking off clothes*"); // 14
		graph.addNode("*crowd goes silent*"); // 15
		graph.addNode("What do you think you're doing?!"); // 16
		graph.addNode("I'm showing you the real environment, *begin taking off pants*"); // 17
		graph.addNode("Female Crowd Occupant #42: Oh wow!"); // 18
		graph.addNode("* a rock is thrown at you as you take off your undergarments*"); // 19
		graph.addNode(" Getting closer to the Earth, or are you telling me that you don't do this?"); // 20
		graph.addNode("Crowd whispers amongst themselves: if stripping naked is what one must really do..."); // 21
		graph.addNode("Be still followers of Gaia, this man is only acting like this because he has nothing else to lose."); // 22 connect to 37
		graph.addNode("*take off pants* I do these things because I'm not scared of being closer to Gaia"); // 23
		graph.addNode("Crowd: To be fair he is getting closer by removing his clothes..."); // 24
		graph.addNode("Do you take me for a fool, Gaia would never want us to strip to be closer to her!"); // 25
		graph.addNode("*take off undergarments* well, I disagree, watch and behold me!"); // 26
		graph.addNode("*the area goes silent*"); // 27
		graph.addNode("Begin making poses"); // 28
		graph.addNode("Stand heroically that you just stripped in front of multiple people"); // 29
		graph.addNode("As if, *eat dirt* I'm the one with mother Gaiai are you?!"); // 30
		graph.addNode("Crowd occupant #2: Did he just eat dirt?"); // 31
		graph.addNode("Why did you just eat dirt?"); // 32
		graph.addNode("You want some?"); // 33
		graph.addNode("NO! That's disgusting!"); // 34
		graph.addNode("My love for Gaia is absolute is yours?"); // 35
		graph.addNode("Of course it is, I am her representative!"); // 36
		graph.addNode("And how do we know you're not lying?"); // 37 connect to 13
		graph.addNode("Don't knock it till you tri it!"); // 38
		graph.addNode("Crowd: To be fair, we haven't eaten dirt yet, so we don't know how it tastes..."); // 39
		graph.addNode("Don't eat dirt! he's tryng to trick all of you!"); // 40
		graph.addNode("Trick you into loving the Earth more? I'm going to eat more because I love The Earth"); // 41 connect to 51
		graph.addNode("Don't listen to her, she wants to prevent you all from enjoying yourselves!"); // 42
		graph.addNode("Crowd: Enjoy ourselves by eating dirt?"); // 43
		graph.addNode("Do you see whay this man is a fool? He tried to mak us eat dirt! Tell me people are you fools?"); // 44
		graph.addNode("*crowd begins to boo*: Let's throw rocks at him!"); // 45
		graph.addNode("*A barrage of rocks hits you*"); // 46
		graph.addNode("We are what we eat, I am the Earth you see!"); // 47 connect to 52
		graph.addNode("*Crowd boos*: He's using science! Hang him!"); // 48
		graph.addNode("Be calm my children we don not hang the science-goers, we throw rocks at them!"); // 49 connect to 46
		graph.addNode("*continue shoveling dirt in your mouth* Because I love the Earth!"); // 50
		graph.addNode("Crowd: To be fair, if he does love the earth, he would eat it."); // 51
		graph.addNode("Eating dirt has nothing to do with being closer to Mother Gaia!"); // 52
		graph.addNode("*shovel more dirt into your mouth* Yesh ifh doths."); // 53
		graph.addNode("Crowd: he's still going, maybe we should be eating dirt..."); // 54
		graph.addNode("What are you doing my children of Gaia, don't eat dirt!"); // 55 maybe connect to 14
		graph.addNode("*cram more dirt in an already full mouth of dirt* WATGY MEHAS"); // 56
		graph.addNode("Crowd with dirt in their mouths: Oh my god, he's chocking, someone save him!"); // 57
		graph.addNode("*shove more dirt into your already exceedingly full mouth* ghgfdsfsd"); // 58
		graph.addNode("*corwd begins to chant Go, Go, Go!"); // 59 connect to 61
		graph.addNode("fall to the ground, chocking"); // 60
		graph.addNode("*You black out*"); // 61
		
		graph.addEdge(0,1);
		graph.addEdge(1,2);
		graph.addEdge(2,3);
		graph.addEdge(3,4);
		graph.addEdge(4,5);
		graph.addEdge(5,6);
		graph.addEdge(6,7);
		graph.addEdge(7,8);
		graph.addEdge(8,9);
		graph.addEdge(9,10);
		graph.addEdge(10,11);
		graph.addEdge(6,12);
		graph.addEdge(12,13);
		graph.addEdge(6,14);
		graph.addEdge(14,15);
		graph.addEdge(15,16);
		graph.addEdge(16,17);
		graph.addEdge(17,18);
		graph.addEdge(18,19);
		graph.addEdge(16,20);
		graph.addEdge(20,21);
		graph.addEdge(21,22);
		graph.addEdge(22,37);
		graph.addEdge(37,13);
		graph.addEdge(22,23);
		graph.addEdge(23,24);
		graph.addEdge(24,25);
		graph.addEdge(25,37);
		graph.addEdge(25,26);
		graph.addEdge(26,27);
		graph.addEdge(27,28);
		graph.addEdge(27,29);
		graph.addEdge(3,30);
		graph.addEdge(30,31);
		graph.addEdge(31,32);
		graph.addEdge(32,33);
		graph.addEdge(33,34);
		graph.addEdge(34,35);
		graph.addEdge(35,36);
		graph.addEdge(36,37);
		graph.addEdge(37,38);
		graph.addEdge(38,39);
		graph.addEdge(39,40);
		graph.addEdge(40,41);
		graph.addEdge(41,51);
		graph.addEdge(40,42);
		graph.addEdge(42,43);
		graph.addEdge(43,44);
		graph.addEdge(44,45);
		graph.addEdge(45,46);
		graph.addEdge(32,47);
		graph.addEdge(47,52);
		graph.addEdge(47,48);
		graph.addEdge(48,49);
		graph.addEdge(49,46);
		graph.addEdge(32,50);
		graph.addEdge(50,51);
		graph.addEdge(51,52);
		graph.addEdge(52,53);
		graph.addEdge(53,54);
		graph.addEdge(54,55);
		graph.addEdge(55,14);
		graph.addEdge(55,56);
		graph.addEdge(56,57);
		graph.addEdge(57,58);
		graph.addEdge(58,59);
		graph.addEdge(59,61);
		graph.addEdge(57,60);
		graph.addEdge(60,61);
		
		return graph;
	}
}
