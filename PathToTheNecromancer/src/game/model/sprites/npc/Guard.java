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
 * This is the class for the animation of the guard
 *
 */
public class Guard extends CharacterSprites {
	private Texture GuardTexture;
	private static final int GUARD_HEIGHT = 36;
	private static final int GUARD_WIDTH = 12;

	public Guard() {
		setTextureValues();
		// Set the size of the Guard
		setBounds(0, 0, GUARD_WIDTH, GUARD_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	@Override
	public void setTextureValues() {
		int width = 46;
		int height = 133;
		int rowHeight = 133;

		// Use the Guard animations until we have the player SpriteSheet
		GuardTexture = new Texture("CharacterSprites/Guard.png");

		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(GuardTexture, 0, 0, width, height));
		
		// move down
		frames.add(new TextureRegion(GuardTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture, (3) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture, (4) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture, (6) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture, (4) * width, 0 * rowHeight, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up
		frames.add(new TextureRegion(GuardTexture,(0) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(3) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(6) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(4) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(GuardTexture,(6) * width, 1 * rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move left
		frames.add(new TextureRegion(GuardTexture,(1) * width, 2 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(2) * width, 2 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(3) * width, 2 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(2) * width, 2 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(5) * width, 2 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(6) * width, 2 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(5) * width, 2 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(4) * width, 2 * rowHeight + 1, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move right
		frames.add(new TextureRegion(GuardTexture,(1) * width, 3 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(2) * width, 3 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(3) * width, 3 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(2) * width, 3 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(1) * width, 3 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(5) * width, 3 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(6) * width, 3 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(5) * width, 3 * rowHeight + 1, width, height));
		frames.add(new TextureRegion(GuardTexture,(4) * width, 3 * rowHeight + 1, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		super.setMoveDownLeft(super.getMoveDown());
		super.setMoveDownRight(super.getMoveDown());
		super.setMoveUpRight(super.getMoveUp());
		super.setMoveUpLeft(super.getMoveUp());
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

		// coordinates of the Guard's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-GUARD_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(GUARD_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-GUARD_WIDTH / 2, -GUARD_HEIGHT / 2); // bottom
		// left
		vertice[3] = new Vector2(GUARD_WIDTH / 2, -GUARD_HEIGHT / 2); // bottom
		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);

	}

}
