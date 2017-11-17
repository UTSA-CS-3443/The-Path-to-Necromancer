package game.model.sprites.npc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.controller.story.DialogueActor;
import game.model.DialogueGraph;
import game.model.GraphNode;
import game.model.sprites.CharacterSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Player;

public class OogLag extends CharacterSprites implements InteractionSprites {
	/**
	 * The pixel width of the Merchant sprite
	 */
	private static final int OOGLAG_WIDTH = 20;
	/**
	 * The pixel height of the Merchant sprite
	 */
	private static final int OOGLAG_HEIGHT = 40;
	/**
	 * The texture used to draw and render the Merchant sprite
	 */
	private Texture ooglagTexture;
	/**
	 * A state integer used to determine Oog-Lag's current animation state
	 */
	private double currentState;
	/**
	 * The first cup washing animation region
	 */
	private TextureRegion animation1;
	/**
	 * The second cup washing animation region
	 */
	private TextureRegion animation2;
	/**
	 * The current cup washing animation region
	 */
	private TextureRegion currentAnimation;
	/**
	 * Adjacency list graph of all of OogLag's dialogue options
	 */
	private DialogueGraph dialogueGraph;

	/**
	 * Create the Merchant object.
	 */
	public OogLag() {
		super();
		// initialize the current state for animation
		currentState = 0;
		// Initialize the different texture regions associated with the Merchant
		setTextureValues();
		// Set the size of the Merchant
		setBounds(0, 0, OOGLAG_WIDTH, OOGLAG_HEIGHT);
		this.dialogueGraph = new DialogueGraph();
		setUpDialogue();
	}

	/**
	 * Set the different TextureRegions associated with the Merchant for animation.
	 */
	@Override
	public void setTextureValues() {
		// Use the Merchant animations until we have the player SpriteSheet
		ooglagTexture = new Texture("CharacterSprites/OogLag.png");

		animation1 = new TextureRegion(ooglagTexture, 0, 0, 41, 91);
		animation2 = new TextureRegion(ooglagTexture, 41, 0, 41, 91);
		currentAnimation = animation1;
		setRegion(currentAnimation);
	}

	/**
	 * Get the current Animation frame the character is on for rendering
	 * 
	 * @param dt
	 *            the time interval for determining which frame the character
	 *            animation is on
	 * @return the texture region to render
	 */
	@Override
	public TextureRegion getFrame(float dt) {
		// get the character's current state of motion
		currentState += dt;

		// move the animation forward
		if (currentState >= 0.5) {
			if (currentAnimation == animation1)
				currentAnimation = animation2;
			else
				currentAnimation = animation1;
			currentState = 0;
		}
		// move the animation forward
		return currentAnimation;
	}

	/**
	 * Create the Merchant's body for the box2d physics engine
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

		// coordinates of the Merchant's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-OOGLAG_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(OOGLAG_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-OOGLAG_WIDTH / 2, -OOGLAG_HEIGHT / 2); // bottom
																			// left
		vertice[3] = new Vector2(OOGLAG_WIDTH / 2, -OOGLAG_HEIGHT / 2); // bottom
																		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);
	}

	/**
	 * Get the current dialogue graph for OogLag
	 * 
	 * @return OogLag's Dialogue graph
	 */
	@Override
	public DialogueGraph getDialogue(Player player) {
		return this.dialogueGraph;
	}

	/**
	 * Set up OogLag's dialogue graph.
	 */
	public void setUpDialogue() {
		// create the nodes in the graph
		this.dialogueGraph.addNode("Why hello there sir! How can I help you today?"); // 0
		this.dialogueGraph.addNode("Where am I?"); // 1
		this.dialogueGraph.addNode("Who are you?"); // 2
		this.dialogueGraph.addNode("Give me your finest beer!"); // 3
		this.dialogueGraph.addNode(
				"Well, you, my fine sir, are in the land Niarzul, land of adventure, gold, and very convenient plot devices."); // 4
		this.dialogueGraph.addNode("What is there to do around here?"); // 5
		this.dialogueGraph.addNode("Know where I can get some weapons?"); // 6
		this.dialogueGraph
				.addNode("Well mostly drink, talk, and give out the occasional quest!... Well what do we have here? "); // 7
		this.dialogueGraph.addNode(
				"Well normally you could talk to the nearest merchant. Thanks to the monsters, however, merchants don't go through here anymore."); // 8
		this.dialogueGraph.addNode("Coming right up! That'll be 20 gold and I am going to have to see your license."); // 9
		this.dialogueGraph.addNode("You know what? Nevermind."); // 10
		this.dialogueGraph.addNode("*Give license*"); // 11
		this.dialogueGraph.addNode("*Laughs* Alrighty then, have some milk. ON THE HOUSE!"); // 12
		this.dialogueGraph.addNode("This license is obviously fake. You crudely drew in a signature with a crayon."); // 13
		this.dialogueGraph.addNode("Hey you can't blame a man for trying!"); // 14
		this.dialogueGraph.addNode("*Chuckles* guess not"); // 15
		this.dialogueGraph.addNode("Well I'm Oog-Lag, the keeper of Oog-Lag tavern."); // 16
		
		// add the edges to the graph
		this.dialogueGraph.addEdge(0, 1);
		this.dialogueGraph.addEdge(0, 2);
		this.dialogueGraph.addEdge(0, 3);
		this.dialogueGraph.addEdge(1, 4);
		this.dialogueGraph.addEdge(4, 5);
		this.dialogueGraph.addEdge(4, 6);
		this.dialogueGraph.addEdge(5, 7);
		this.dialogueGraph.addEdge(6, 8);
		this.dialogueGraph.addEdge(3, 9);
		this.dialogueGraph.addEdge(9, 10);
		this.dialogueGraph.addEdge(9, 11);
		this.dialogueGraph.addEdge(10, 12);
		this.dialogueGraph.addEdge(11, 13);
		this.dialogueGraph.addEdge(13, 14);
		this.dialogueGraph.addEdge(14, 15);
		this.dialogueGraph.addEdge(2, 16);

		// add an actor to a node that gets acted upon when the user hits that node
		GraphNode node = this.dialogueGraph.getNode(7);
		node.addActor(new DialogueActor() {
			@Override
			public void act(Player player, MapManager manager) {
			}
		});
	}
}
