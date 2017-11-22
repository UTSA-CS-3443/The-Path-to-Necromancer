package game.model.sprites.objectSprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.DialogueGraph;
import game.model.sprites.GameSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Player;

/**
 * The book that gives the player the tutorial at the start of the game
 * 
 * @author enigma-phi
 *
 */
public class BillBoard extends GameSprites implements InteractionSprites {
	/**
	 * The width of the book in the game
	 */
	private static final int BILLBOARD_WIDTH = 30;
	/**
	 * The height of the book in the game
	 */
	private static final int BILLBOARD_HEIGHT = 30;
	/**
	 * Constructor for the book
	 */
	public BillBoard() {

	}

	/**
	 * Get the book's dialogue.
	 * @return the book's dialogue
	 */
	@Override
	public DialogueGraph getDialogue(Player player) {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Welcoome to Oog-Lag's Tavern! Finest establishment in all of Niarzul."); 
		return graph;
	}

	/**
	 * Update the book's animation
	 */
	@Override
	public void update(float dt) {
	}

	/**
	 * Create the Book's body for the box2d physics engine
	 * 
	 * @param world
	 *            is the world to put the character into
	 * @param x
	 *            is the x-coordinate to put the character on
	 * @param y
	 *            is the y-coordinate to put the character on
	 */
	public void defineBody(World world, int x, int y) {

		// set the body of the character
		BodyDef bdef = new BodyDef();
		bdef.position.set(x, y); // position
		bdef.type = BodyDef.BodyType.DynamicBody; // body type

		// Create the body and add mass
		Body body = world.createBody(bdef);

		// create the world fixture for collision
		FixtureDef fdef = new FixtureDef();
		PolygonShape rect = new PolygonShape();

		// coordinates of the Necromancer's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-(BILLBOARD_HEIGHT) / 2, BILLBOARD_HEIGHT / 2); // top left
		vertice[1] = new Vector2((BILLBOARD_WIDTH) / 2, BILLBOARD_HEIGHT / 2); // top right
		vertice[2] = new Vector2(-(BILLBOARD_WIDTH) / 2, -(BILLBOARD_WIDTH) / 2); // bottom
																											// left
		vertice[3] = new Vector2((BILLBOARD_WIDTH) / 2, -(BILLBOARD_HEIGHT) / 2); // bottom
																											// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData(this);
	}

}