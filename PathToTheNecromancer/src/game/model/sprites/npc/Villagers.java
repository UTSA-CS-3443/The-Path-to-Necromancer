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
 * This is the class for the animation of the villagers
 *
 */
public class Villagers extends CharacterSprites {
	private ColorAndGender color;
	private ColorAndGender gender;
	private Texture villagerTexture;
	private static final int VILLAGER_HEIGHT = 30;
	private static final int VILLAGER_WIDTH = 15;

	public Villagers(ColorAndGender color, ColorAndGender gender) {
		this.color = color;
		this.gender = gender;
		setTextureValues();
		// Set the size of the Villager
		setBounds(0, 0, VILLAGER_WIDTH, VILLAGER_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	@Override
	public void setTextureValues() {
		int rowHeight = 0;
		int width = 27;
		int height = 50;

		// Use the Villager animations until we have the player SpriteSheet
		villagerTexture = new Texture("CharacterSprites/Villagers.png");

		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();
		// switching based on color and gender
		switch (color) {
		default:
			break;
		case RED:
			if (gender == ColorAndGender.MALE)
				rowHeight = 0;
			else
				rowHeight = 50;
			break;

		case BLUE:
			if (gender == ColorAndGender.MALE)
				rowHeight = 100;
			else
				rowHeight = 150;
			break;

		case GREEN:
			if (gender == ColorAndGender.MALE)
				rowHeight = 200;
			else
				rowHeight = 250;
			break;

		case BROWN:
			if (gender == ColorAndGender.MALE)
				rowHeight = 300;
			else
				rowHeight = 350;
			break;

		case BLACK:
			if (gender == ColorAndGender.MALE)
				rowHeight = 400;
			else
				rowHeight = 450;
			break;

		case WHITE:
			if (gender == ColorAndGender.MALE)
				rowHeight = 500;
			else
				rowHeight = 550;
			break;
		}

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(villagerTexture, 0, rowHeight, width, height));
		
		// move down
		frames.add(new TextureRegion(villagerTexture, (0) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (3) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (4) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (6) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (4) * width, rowHeight, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up
		frames.add(new TextureRegion(villagerTexture,(7 + 0) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 3) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 4) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 6) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 4) * width, rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move left
		frames.add(new TextureRegion(villagerTexture,(0 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(3 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(2 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(1 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(6 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(5 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(4 + 14) * width, rowHeight, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move right
		frames.add(new TextureRegion(villagerTexture,(6 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(3 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(4 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(5 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(0 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(1 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(2 + 21) * width, rowHeight, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		super.setMoveDownLeft(super.getMoveDown());
		super.setMoveDownRight(super.getMoveDown());
		super.setMoveUpRight(super.getMoveUp());
		super.setMoveUpLeft(super.getMoveUp());
		super.setAnimationSpeed(3);
		super.setRunningSpeed(2);
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

		// coordinates of the Villager's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-VILLAGER_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(VILLAGER_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-VILLAGER_WIDTH / 2, -VILLAGER_HEIGHT / 2); // bottom
		// left
		vertice[3] = new Vector2(VILLAGER_WIDTH / 2, -VILLAGER_HEIGHT / 2); // bottom
		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);

	}
}
