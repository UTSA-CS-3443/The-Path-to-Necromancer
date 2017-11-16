
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

import game.model.sprites.CharacterSprites;

/**
 * 
 * The Merchant is a dwarf. Despite his name, he does not actually sell the player
 * anything
 * 
 * @author enigma-phi
 *
 */
public class Merchant extends CharacterSprites {
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
}
