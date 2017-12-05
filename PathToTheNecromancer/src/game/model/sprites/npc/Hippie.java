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
 * This is the class for animating the Hippie 
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

	@Override
	public DialogueGraph getDialogue(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
}
