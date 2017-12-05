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
 * This is the class for animating the knight
 * @author ToTryHardRay
 * @author enigma-phi
 *
 */
public class Knight extends CharacterSprites implements InteractionSprites{
	/**
	 * The Texture associated with the Knight
	 */
	private Texture knightTexture;
	/**
	 * The Knight's height
	 */
	private static final int KNIGHT_HEIGHT = 36;
	/**
	 * The knight's width 
	 */
	private static final int KNIGHT_WIDTH = 32;

	/**
	 * Constructor
	 * Initialize Texture values
	 */
	public Knight() {
		setTextureValues();
		// Set the size of the Knight
		setBounds(0, 0, KNIGHT_WIDTH, KNIGHT_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	/**
	 * Set the knight's texture values
	 */
	@Override
	public void setTextureValues() {
		int width = 132;
		int height = 151;
		int rowHeight = 150;

		// Use the Knight animations until we have the player SpriteSheet
		knightTexture = new Texture("CharacterSprites/Knight.png");

		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(knightTexture, 0, 0, width, height));
		
		// move down
		frames.add(new TextureRegion(knightTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture, (3) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture, (4) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture, (6) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture, (4) * width, 0 * rowHeight, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up
		frames.add(new TextureRegion(knightTexture,(0) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(3) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(4) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(6) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(4) * width, 1 * rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move left
		frames.add(new TextureRegion(knightTexture,(4) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(3) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(1) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(6) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 2 * rowHeight, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move right
		frames.add(new TextureRegion(knightTexture,(6) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(3) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(1) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(4) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 3 * rowHeight, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-right
		frames.add(new TextureRegion(knightTexture,(1) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(3) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(4) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(6) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 4 * rowHeight, width, height));
		super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-left
		frames.add(new TextureRegion(knightTexture,(1) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(3) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(4) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(6) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 5 * rowHeight, width, height));
		super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move up-left
		frames.add(new TextureRegion(knightTexture,(1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(3) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(4) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(6) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(4) * width, 6 * rowHeight, width, height));
		super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move up-right
		frames.add(new TextureRegion(knightTexture,(1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(3) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(4) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(6) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(knightTexture,(4) * width, 7 * rowHeight, width, height));
		super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		super.setAnimationSpeed(2);
		super.setRunningSpeed(1);

	}

	/**
	 * Define the knight's physics body
	 * @param world is the world to place the knight in
	 * @param x is the knight's x-coord
	 * @param y is the knight's y-coord
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

		// coordinates of the Knight's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-KNIGHT_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(KNIGHT_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-KNIGHT_WIDTH / 2, -KNIGHT_HEIGHT / 2); // bottom
		// left
		vertice[3] = new Vector2(KNIGHT_WIDTH / 2, -KNIGHT_HEIGHT / 2); // bottom
		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);

	}
	/**
	 * 
	 * Takes in a player and this function is the dialogue between
	 * the player and the knight outside of Necromancer lair
	 * @param player
	 * @return the dialogue graph
	 */
	public DialogueGraph getDialogue(Player player) {
		DialogueGraph graph = new DialogueGraph();
		
		graph.addNode("Knight: HALT"); // 0
		graph.addNode("P: Cool!"); // 1
		graph.addNode("Knight: YES IT IS VERY COOL, I HAVE SERVED HIM FOR MANY YEARS!"); // 2
		graph.addNode("P: So that means you really know the guy huh?"); // 3
		graph.addNode("Knight: YES WE ARE GOOD PALS"); // 4
		graph.addNode("P: Are you though?"); // 5
		graph.addNode("Knight: WHAT DO YOU MEAN?"); // 6
		graph.addNode("P: I mean looking at things right now, he seems to hold all the power and here you are in a dusty suit of armor just sitting around."); // 7
		graph.addNode("Knight: I AM HIS MOST POWERFUL SERVANT!"); // 8
		graph.addNode("P: Even more powerful than him?"); // 9
		graph.addNode("P: Then why serve him?"); // 10
		graph.addNode("Knight: YES, WAIT, WHY SHOULD I SERVE WHEN I AM THE GREATER BEING!"); // 11
		graph.addNode("P: Exactly you should open up your own castle and do your thing elsewhere, you know maybe you could use your immense power for good?"); // 12
		graph.addNode("Knight: WHY WOULD I DO THAT?"); // 13
		graph.addNode("P: Because deep down you still have a heart? Even if it is unbeating and cold and lifeless, it still has hopes for humans?"); // 14
		graph.addNode("P: Being a hero has no pay, but I hear mercenary work pays well."); // 15
		graph.addNode("Knight: PFFFFT, YEAH RIGHT DIE HERO"); // 16
		graph.addNode("Knight: OOOH MONEY YOU SAY? FINE THANKS FOR THE TALK HERO"); // 17
		graph.addNode("Knight: YOU KNOW NOT WHAT YOU SPEAK OF PREPARE FOR DEATH *trips and falls*"); // 18
		graph.addNode("P: Pffft no you aren’t."); // 19
		graph.addNode("Knight: WHAT? IF I AM NOT THEN WHO IS?"); // 20
		graph.addNode("P: The don?"); // 21
		graph.addNode("P: The hippie?"); // 22
		graph.addNode("Knight: THE DON IS WORTHLESS AND MEASLY HE LEADS A SMALL BANDIT GROUP"); // 23
		graph.addNode("Knight: THE HIPPIE IS WEAK AND ONLY WATCHES A VILLAGE WHILE I WATCH OVER THE ARMIES"); // 24
		graph.addNode("P: That bandit group now has benefits though."); // 25
		graph.addNode("P: The Don is weak I guess …"); // 26
		graph.addNode("Knight: OOO BENEFITS? EVEN I DON’T HAVE THOSE, I GUESS I REALLY AREN’T THE MOST TRUSTED, I FEEL BETRAYED, MAYBE I SHOULD JOIN YOU AND BE A HERO?"); // 27
		graph.addNode("P: What armies? I have been fighting only 1 enemy at a time. Poor coordination if you ask me."); // 28
		graph.addNode("Knight: OOF, YOU WOUND ME HERO, MAYBE I SHOULD WHIP THEM INTO SHAPE?"); // 29
		graph.addNode("P: Yeah you should do that now!"); // 30
		graph.addNode("Knight: GOOD IDEA, THANK YOU HERO"); // 31
		graph.addNode("Knight:I AM THE NECROMANCER’S MOST TRUSTED SERVANT!"); // 32

		
		graph.addEdge(0, 32);
		graph.addEdge(32,1);
		graph.addEdge(1,2);		
		graph.addEdge(2,3);
		graph.addEdge(3,4);
		graph.addEdge(4,5);
		graph.addEdge(5,6);
		graph.addEdge(6,7);
		graph.addEdge(7,8);
		graph.addEdge(8,9);
		graph.addEdge(9,11);
		graph.addEdge(11,12);
		graph.addEdge(12,13);
		graph.addEdge(13,14);
		graph.addEdge(14,16);
		graph.addEdge(13,15);
		graph.addEdge(15,17);
		graph.addEdge(8,10);
		graph.addEdge(10,18);
		graph.addEdge(32,19);
		graph.addEdge(19,20);
		graph.addEdge(20,21);
		graph.addEdge(21,23);
		// Dialogue 25 only occurs if the bandit union happened
		if(player.getStoryStats().getBanditEncounters() > 3)
			graph.addEdge(23,25);
		graph.addEdge(25,27);
		graph.addEdge(27,15);
		graph.addEdge(23,26);
		graph.addEdge(22,24);
		graph.addEdge(24,28);
		graph.addEdge(28,29);
		graph.addEdge(29,30);
		graph.addEdge(30,31);
		graph.addEdge(20,22);
		
		// Add actors
		DialogueActor actor = new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {	
				addVelocity(new Vector2(0, 20), 3);
				addVelocity(new Vector2(30, 0), 1);
				addVelocity(new Vector2(0, 25), 5);
			}
			
		};
		graph.getNode(16).addActor(actor);
		graph.getNode(17).addActor(actor);
		graph.getNode(18).addActor(actor);
		graph.getNode(26).addActor(actor);
		graph.getNode(27).addActor(actor);
		graph.getNode(31).addActor(actor);
		
		return graph;
	}

}
