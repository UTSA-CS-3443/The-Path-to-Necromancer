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
 * @author ToTryHardRay
 * This is the class for animating the knight
 *
 */
public class Knight extends CharacterSprites {
	private Texture KnightTexture;
	private static final int KNIGHT_HEIGHT = 36;
	private static final int KNIGHT_WIDTH = 32;

	public Knight() {
		setTextureValues();
		// Set the size of the Knight
		setBounds(0, 0, KNIGHT_WIDTH, KNIGHT_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	@Override
	public void setTextureValues() {
		int width = 132;
		int height = 151;
		int rowHeight = 150;

		// Use the Knight animations until we have the player SpriteSheet
		KnightTexture = new Texture("CharacterSprites/Knight Sprite Sheet-Adjusted.png");

		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(KnightTexture, 0, 0, width, height));
		
		// move down
		frames.add(new TextureRegion(KnightTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (3) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (4) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (6) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (4) * width, 0 * rowHeight, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up
		frames.add(new TextureRegion(KnightTexture,(0) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 1 * rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move left
		frames.add(new TextureRegion(KnightTexture,(4) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 2 * rowHeight, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move right
		frames.add(new TextureRegion(KnightTexture,(6) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 3 * rowHeight, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-right
		frames.add(new TextureRegion(KnightTexture,(1) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 4 * rowHeight, width, height));
		super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-left
		frames.add(new TextureRegion(KnightTexture,(1) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 5 * rowHeight, width, height));
		super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move up-left
		frames.add(new TextureRegion(KnightTexture,(1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 6 * rowHeight, width, height));
		super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move up-right
		frames.add(new TextureRegion(KnightTexture,(1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 7 * rowHeight, width, height));
		super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		super.setAnimationSpeed(2);
		super.setRunningSpeed(1);

	}

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

}
