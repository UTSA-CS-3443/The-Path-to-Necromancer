
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
 * The Merchant is a dwarf. Despite his name, he does not actually sell the player
 * anything
 * 
 * @author enigma-phi
 * @author ToTryHardRay
 *
 */
public class Merchant extends CharacterSprites implements InteractionSprites{
	/**
	 * The pixel width of the Merchant sprite
	 */
	private static final int MERCH_WIDTH = 16;
	/**
	 * The pixel height of the Merchant sprite
	 */
	private static final int MERCH_HEIGHT = 24;
	/**
	 * The texture used to draw and render the Merchant sprite
	 */
	private Texture merchantTexture;

	/**
	 * Create the Merchant object.
	 */
	public Merchant() {
		super();
		// Initialize the different texture regions associated with the Merchant
		setTextureValues();
		// Set the size of the Merchant
		setBounds(0, 0, MERCH_WIDTH, MERCH_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	/**
	 * Set the different TextureRegions associated with the Merchant for animation.
	 */
	@Override
	public void setTextureValues() {
		int rowHeight = 74;
		int width = 40;
		int height = 74;

		// Use the Merchant animations until we have the player SpriteSheet
		merchantTexture = new Texture("CharacterSprites/Merchant.png");

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(merchantTexture, 0, 0, width, height));
		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();

		// for moving down
		for (int i = 1; i <= 3; i++) {
			frames.add(new TextureRegion(merchantTexture, i * width, 0 * rowHeight, width, height));
		}
		for (int i = 2; i >= 1; i--) {
			frames.add(new TextureRegion(merchantTexture, i * width, 0 * rowHeight, width, height));
		}
		for (int i = 6; i >= 4; i--) {
			frames.add(new TextureRegion(merchantTexture, i * width, 0 * rowHeight, width, height));
		}
		for (int i = 5; i <= 6; i++) {
			frames.add(new TextureRegion(merchantTexture, i * width, 0 * rowHeight, width, height));
		}
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Up on the map
		for (int i = 0; i <= 3; i++) {
			frames.add(new TextureRegion(merchantTexture, i * width, 1 * rowHeight, width, height));
		}
		for (int i = 2; i >= 1; i--) {
			frames.add(new TextureRegion(merchantTexture, i * width, 1 * rowHeight, width, height));
		}
		for (int i = 6; i >= 4; i--) {
			frames.add(new TextureRegion(merchantTexture, i * width, 1 * rowHeight, width, height));
		}
		for (int i = 5; i <= 6; i++) {
			frames.add(new TextureRegion(merchantTexture, i * width, 1 * rowHeight, width, height));
		}
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Left on the map
		for (int i = 0; i <= 3; i++) {
			frames.add(new TextureRegion(merchantTexture, i * width, 2 * rowHeight, width, height));
		}
		for (int i = 2; i >= 1; i--) {
			frames.add(new TextureRegion(merchantTexture, i * width, 2 * rowHeight, width, height));
		}
		for (int i = 4; i <= 6; i++) {
			frames.add(new TextureRegion(merchantTexture, i * width, 2 * rowHeight, width, height));
		}
		for (int i = 5; i >= 4; i--) {
			frames.add(new TextureRegion(merchantTexture, i * width, 2 * rowHeight, width, height));
		}
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Right on the map
		for (int i = 6; i >= 3; i--) {
			frames.add(new TextureRegion(merchantTexture, i * width, 3 * rowHeight, width, height));
		}
		for (int i = 4; i <= 5; i++) {
			frames.add(new TextureRegion(merchantTexture, i * width, 3 * rowHeight, width, height));
		}
		for (int i = 2; i >= 0; i--) {
			frames.add(new TextureRegion(merchantTexture, i * width, 3 * rowHeight, width, height));
		}
		for (int i = 1; i <= 2; i++) {
			frames.add(new TextureRegion(merchantTexture, i * width, 3 * rowHeight, width, height));
		}
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		super.setMoveDownLeft(super.getMoveDown());
		super.setMoveDownRight(super.getMoveDown());
		super.setMoveUpRight(super.getMoveUp());
		super.setMoveUpLeft(super.getMoveUp());

		super.setAnimationSpeed(2);
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
		vertice[0] = new Vector2(-MERCH_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(MERCH_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-MERCH_WIDTH / 2, -MERCH_HEIGHT / 2); // bottom
																		// left
		vertice[3] = new Vector2(MERCH_WIDTH / 2, -MERCH_HEIGHT / 2); // bottom
																		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);
	}
	/**
	 * Determine the DialogueGraph based off of whether the player has talked to
	 * the Merchant before
	 */
	public DialogueGraph getDialogue(Player player) {
		if(player.getStoryStats().isFirstMerchantChat()) {
			return this.getSetDialogue();
		}
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("The merchantglowers and refuses to speak.");
		return graph;
	}
	/**
	 * Get the Merchant's fixed dialoguegraph for the first time the player meets 
	 * the Merchant
	 * @return the DialogueGraph
	 */
	private DialogueGraph getSetDialogue() {
		DialogueGraph graph = new DialogueGraph();
	
		graph.addNode("Merchant: What do you want weak-looking fool?"); // 0 
		graph.addNode("P: Are you always this rude to people you just meet?"); // 1
		graph.addNode("Merchant: Of course not, I'm only rude to puny, annoying, and stupid looking people like yourself!"); // 2
		graph.addNode("P: Think I can ask you a few questions?"); // 3
		graph.addNode("Merchant: I don't have time to answer ye stupid questions, go ask Oog-lag."); // 4
		graph.addNode("P: *Look around* Did somebody say something?"); // 5
		graph.addNode("Merchant: I'm down here you little smart-aleck!"); // 6
		graph.addNode("P: Oh shoot, I didn't see you there..."); // 7
		graph.addNode("P: *Continue looking around* It's almost as if I hear a small creature attempting to talk to me..."); // 8
		graph.addNode("Merchant: You're asking for it now sonny, I'll clobber you into another dimension"); // 9
		graph.addNode("P: Oh I'm so scared, what are you going to do? Jump up and kick me in the knee?" ); // 10
		graph.addNode("Merchnat: No, I'll kick ye in your jewlery case so hard that the children of your children will be feeling you mistake!"); // 11
		graph.addNode("P: Nevermind, I'll leave you alone."); // 12
		graph.addNode("Merchant: You better be, now leave me alone!"); // 13
		
		graph.addEdge(0,1);
		graph.addEdge(1,2);
		graph.addEdge(0,3);
		graph.addEdge(3,4);
		graph.addEdge(0,5);
		graph.addEdge(5,6);
		graph.addEdge(6,7);
		graph.addEdge(6,8);
		graph.addEdge(7,9);
		graph.addEdge(8,9);
		graph.addEdge(9,10);
		graph.addEdge(10,11);
		graph.addEdge(9,12);
		graph.addEdge(12,13);
		
		graph.getNode(0).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setFirstMerchantChat(false);
			}
			
		});
		return graph;
	}
}
