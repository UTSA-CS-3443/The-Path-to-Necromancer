package game.model.maps;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;
import game.model.sprites.ObjectSprites;
import game.model.sprites.npc.ColorAndGender;
import game.model.sprites.npc.Villagers;

/**
 * The area to the left of the mountain in the game. Connects with PlainsArea
 * and Mountain Right
 * 
 * @author enigma-phi
 * @author ToTryHardRay - added tree sprites
 *
 */
public class MountainLeft extends GameMaps {

	/**
	 * The name of the map file
	 */
	private static final String MAPNAME = "Maps/Map05-LeftSideOfTheMountain.tmx";
	/**
	 * layers 5 = collision layers
	 */
	private static final int[] COLLISIONLAYERS = { 5 };
	/**
	 * layer 7 = north transition
	 */
	private static final int NORTHTRANSITION = 7;
	/**
	 * layer 6 = north transition
	 */
	private static final int SOUTHTRANSITION = 6;
	/**
	 * Use to manage map transitions
	 */
	private MapManager manager;
	/**
	 * A world creator
	 */
	private B2WorldCreator creator;

	/**
	 * Constructor for the map
	 * 
	 * @param manager
	 *            for managing map transitions
	 */
	public MountainLeft(MapManager manager) {
		super(MAPNAME, false);
		this.manager = manager; // get the map manager to load the map
		this.creator = B2WorldCreator.getInstance(); // get the instance for the creator
	}

	/**
	 * Generate the world for the game's physics.
	 * 
	 * @param the
	 *            world to generate map's physics objects in
	 */
	@Override
	public void generateWorld(World world) {
		// create the static non-moving bodies
		this.creator.createWorld(world, this, COLLISIONLAYERS);
		// create the transition areas
		this.creator.createTransition(world, this, NORTHTRANSITION, NORTH);
		this.creator.createTransition(world, this, SOUTHTRANSITION, SOUTH);

	}

	/**
	 * Transition between the areas
	 */
	@Override
	public void transitionAreas(short transitionType) {
		switch (transitionType) {

		// the transition to the north
		case NORTH:
			this.manager.setMap(new MiddleMountain(this.manager), 60, 30);
			break;
		// the transition to the south
		case SOUTH:
			this.manager.setMap(new PlainsArea(this.manager), 400, 2250);
			break;
		}

	}

	/**
	 * Return an enemy from the specific gameMap
	 * 
     * @param level is the player's level for scaling
	 * @return an EnemySprite
	 */
	@Override
	public EnemySprites getEnemy(int level) {
		int enemyCount = 2;
		Random rand = new Random();
		int enemyNum = rand.nextInt(enemyCount);
		switch (enemyNum) {
		case 0:
			// return new troll(level);
			break;
		case 1:
			// return new golem(level);
			break;
		default:
			return null;
		}
		return null;
	}

	/**
	 * Create all of the GameSprites for the map (i.e. say trees)
	 * 
	 * @param world
	 *            allows sprites with box2d bodies to be generated in the map
	 */
	@Override
	public void createSprites(World world) {
		// the height of the map since 0,0 in Tiled is in the top left
		// compared to the TiledMap's 0,0 in the bottom left
		int height = 1600;

		// file locations of different trees
		TextureAtlas atlas = new TextureAtlas("Game Tilesets/Trees/Trees.pack");
		Skin skin = new Skin();
		skin.addRegions(atlas);
		TextureRegion T1 = skin.getRegion("T1");
		TextureRegion T2 = skin.getRegion("T2");
		TextureRegion T3 = skin.getRegion("T3");
		TextureRegion T4 = skin.getRegion("T4");
		TextureRegion H3 = new TextureRegion(new Texture("Game Tilesets/H3.png"));
		// Texture H3 = new Texture("Game Tilesets/H3");

		// add all of the trees
		this.addSprite(new ObjectSprites(T1, -12, height - 166));
		this.addSprite(new ObjectSprites(T1, 416, height - 166));
		this.addSprite(new ObjectSprites(T1, 682, height - 158));
		this.addSprite(new ObjectSprites(T1, 8, height - 477));
		this.addSprite(new ObjectSprites(T1, 232, height - 585));
		this.addSprite(new ObjectSprites(T1, 488, height - 465));
		this.addSprite(new ObjectSprites(T1, 680, height - 489));
		this.addSprite(new ObjectSprites(T1, -12, height - 826));
		this.addSprite(new ObjectSprites(T1, 408, height - 770));
		this.addSprite(new ObjectSprites(T1, 644, height - 826));

		this.addSprite(new ObjectSprites(T2, 624, height - 1204));

		this.addSprite(new ObjectSprites(T3, 788 - 128, height - 1078));
		this.addSprite(new ObjectSprites(T3, 112 - 128, height - 1335));
		this.addSprite(new ObjectSprites(T3, 151 - 127, height - 1591));

		this.addSprite(new ObjectSprites(T4, 100, height - 1211));
		this.addSprite(new ObjectSprites(T4, 661, height - 1522));

		this.addSprite(new ObjectSprites(H3, 224, height - 1440));

		Villagers villager1 = new Villagers(ColorAndGender.BLUE, ColorAndGender.MALE);
		villager1.isVelocityLooping(true);
		villager1.addVelocity(new Vector2(10, 0), 4);
		villager1.addVelocity(new Vector2(-10, 0), 4);
		villager1.defineBody(world, 347, 202);
		this.addSprite(villager1);

		Villagers villager2 = new Villagers(ColorAndGender.RED, ColorAndGender.FEMALE);
		villager2.isVelocityLooping(false);
		villager2.addVelocity(new Vector2(0, -10), 1);
		villager2.defineBody(world, 779, 533);
		this.addSprite(villager2);

		Villagers villager3 = new Villagers(ColorAndGender.GREEN, ColorAndGender.FEMALE);
		villager3.isVelocityLooping(true);
		villager3.addVelocity(new Vector2(0, -30), 6);
		villager3.addVelocity(new Vector2(-30, 0), 8);
		villager3.addVelocity(new Vector2(0, 30), 6);
		villager3.addVelocity(new Vector2(30, 0), 8);
		villager3.defineBody(world, 726, 465);
		this.addSprite(villager3);

		Villagers villager4 = new Villagers(ColorAndGender.WHITE, ColorAndGender.MALE);
		villager4.isVelocityLooping(false);
		villager4.addVelocity(new Vector2(0, 10), 1);
		villager4.defineBody(world, 381, 489);
		this.addSprite(villager4);

		Villagers villager5 = new Villagers(ColorAndGender.BROWN, ColorAndGender.MALE);
		villager5.isVelocityLooping(false);
		villager5.addVelocity(new Vector2(0, -10), 1);
		villager5.defineBody(world, 782, 1441);
		villager5.setHiddenVillager(true);
		this.addSprite(villager5);

		Villagers villager6 = new Villagers(ColorAndGender.BLUE, ColorAndGender.FEMALE);
		villager6.isVelocityLooping(false);
		villager6.addVelocity(new Vector2(0, -10), 1);
		villager6.defineBody(world, 552, 478);
		this.addSprite(villager6);

		Villagers villager7 = new Villagers(ColorAndGender.BLACK, ColorAndGender.FEMALE);
		villager7.isVelocityLooping(false);
		villager7.addVelocity(new Vector2(0, -10), 1);
		villager7.defineBody(world, 265, 485);
		this.addSprite(villager7);

		Villagers villager8 = new Villagers(ColorAndGender.WHITE, ColorAndGender.FEMALE);
		villager8.isVelocityLooping(false);
		villager8.addVelocity(new Vector2(0, -10), 1);
		villager8.defineBody(world, 471, 75);
		this.addSprite(villager8);

		Villagers villager9 = new Villagers(ColorAndGender.RED, ColorAndGender.MALE);
		villager9.isVelocityLooping(false);
		villager9.addVelocity(new Vector2(0, -10), 1);
		villager9.defineBody(world, 267, 106);
		this.addSprite(villager9);

		Villagers villager10 = new Villagers(ColorAndGender.GREEN, ColorAndGender.MALE);
		villager10.isVelocityLooping(false);
		villager10.addVelocity(new Vector2(-10, 0), 1);
		villager10.defineBody(world, 168, 99);
		this.addSprite(villager10);

		Villagers villager11 = new Villagers(ColorAndGender.BROWN, ColorAndGender.FEMALE);
		villager11.isVelocityLooping(false);
		villager11.addVelocity(new Vector2(0, -10), 1);
		villager11.defineBody(world, 119, 280);
		this.addSprite(villager11);

		Villagers villager12 = new Villagers(ColorAndGender.BLACK, ColorAndGender.MALE);
		villager12.isVelocityLooping(true);
		villager12.addVelocity(new Vector2(0, -25), 6);
		villager12.addVelocity(new Vector2(0, 25), 6);
		villager12.defineBody(world, 492, 1071);
		villager12.setAnimationSpeed(2.5f);
		this.addSprite(villager12);

		Villagers villager13 = new Villagers(ColorAndGender.RED, ColorAndGender.MALE);
		villager13.isVelocityLooping(true);
		villager13.addVelocity(new Vector2(20, 0), 8);
		villager13.addVelocity(new Vector2(-20, 0), 8);
		villager13.defineBody(world, 138, 951);
		this.addSprite(villager13);

		Villagers villager14 = new Villagers(ColorAndGender.WHITE, ColorAndGender.FEMALE);
		villager14.isVelocityLooping(true);
		villager14.addVelocity(new Vector2(0, -35), 4);
		villager14.addVelocity(new Vector2(0, 35), 4);
		villager14.setAnimationSpeed(2);
		villager14.defineBody(world, 211, 1198);
		this.addSprite(villager14);

	}

	/**
	 * Set the music for the map
	 */
	@Override
	public void setMusic() {
		manager.getMusicManager().setMusic((Music) Gdx.audio.newMusic(Gdx.files.internal("assets/Music/Left-side of Mountain Town.mp3")));
	}

}
