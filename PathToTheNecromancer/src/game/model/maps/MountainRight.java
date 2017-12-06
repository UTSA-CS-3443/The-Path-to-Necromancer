package game.model.maps;


import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
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
 * Right side of the mountain map 
 * Connects with MountainLeft and LavaFields
 * @author ToTryHardRay
 *
 */
public class MountainRight extends GameMaps {
	
    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map07-RightMountain.tmx";

    /**
     * layer 2 = south transition
     */
    private static final int SOUTHTRANSITION = 2;
    /**
     * layer 4 = north transition
     */
    private static final int NORTHTRANSITION = 4;
    /**
     * layer 3 = map collision layer
     */
    private static final int[] COLLISIONLAYERS = { 3 };
    /**
     * Used to manage map transitions.
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
    public MountainRight(MapManager manager) {
        super(MAPNAME, false);
        this.manager = manager; // manager for loading maps
        this.creator = B2WorldCreator.getInstance(); // for creating the game's physics
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
     * Transition between areas
     * 
     * North: PlainsArea 
     * South: IntroArea 
     * Door: OogLagInterior
     * 
     * @param the
     *            type of transition for the map
     */
    @Override
    public void transitionAreas(short transitionType) {
        switch (transitionType) {
        // the transition to the north
        case NORTH:
        	 this.manager.setMap(new MiddleMountain(this.manager), 450, 85);
            break;
        // the transition to the south
        case SOUTH:
            this.manager.setMap(new LavaFields(this.manager), 440, 1160);
            break;
        // no transition
        default:
            break;
        }
    }

    /**
     * Return an enemy from the specific gameMap
     * 
     * @param level is the player's level for scaling
     * @return an EnemySprite
     */
    public EnemySprites getEnemy(int level) {
    	int enemyCount = 2;
    	Random rand = new Random();
    	int enemyNum = rand.nextInt(enemyCount);
    	switch(enemyNum){
    	case 0:
    		//return new troll(level);
    		break;
    	case 1:
    		//return new golem(level);
    		break;
    	default:
    		return null;
    	}
    	return null;
    }

    /**
     * Create all of the sprites for the map.
     * 
     * @param world
     *            allows sprites with box2d bodies to be generated in the map
     */
    public void createSprites(World world) {
    	// height of map
    	int height = 2400;

    	// create tree textures
    	TextureAtlas atlas = new TextureAtlas("Game Tilesets/Trees/Trees.pack");
        Skin skin = new Skin();
        skin.addRegions(atlas);
        TextureRegion T5 = skin.getRegion("T5");
    	
        // location to render tree sprites
    	this.addSprite(new ObjectSprites(T5, 38, height - 2130));
        
        this.addSprite(new ObjectSprites(T5, 142, height - 2125));
        
        this.addSprite(new ObjectSprites(T5, 238, height - 2145));
        
        this.addSprite(new ObjectSprites(T5, 334, height - 2201));
        this.addSprite(new ObjectSprites(T5, 430, height - 2217));
        this.addSprite(new ObjectSprites(T5, 38, height - 2229));
        this.addSprite(new ObjectSprites(T5, 134, height - 2225));
        this.addSprite(new ObjectSprites(T5, 230, height - 2245));
        this.addSprite(new ObjectSprites(T5, 330, height - 2301));
        this.addSprite(new ObjectSprites(T5, 454, height - 2313));
        this.addSprite(new ObjectSprites(T5, 54, height - 2329));
        this.addSprite(new ObjectSprites(T5, -26, height - 2409));
        this.addSprite(new ObjectSprites(T5, 150, height - 2329));
        this.addSprite(new ObjectSprites(T5, 250, height - 2353));
        this.addSprite(new ObjectSprites(T5, 382, height - 2405));
        this.addSprite(new ObjectSprites(T5, 866, height - 2149));
        this.addSprite(new ObjectSprites(T5, 966, height - 2121));
        this.addSprite(new ObjectSprites(T5, 1066, height - 2097));
        this.addSprite(new ObjectSprites(T5, 1078, height - 2209));
        this.addSprite(new ObjectSprites(T5, 978, height - 2229));
        this.addSprite(new ObjectSprites(T5, 878, height - 2253));
        this.addSprite(new ObjectSprites(T5, 778, height - 2261));
        this.addSprite(new ObjectSprites(T5, 770, height - 2361));
        this.addSprite(new ObjectSprites(T5, 874, height - 2357));
        this.addSprite(new ObjectSprites(T5, 970, height - 2333));
        this.addSprite(new ObjectSprites(T5, 1070, height - 2313));
        
        Villagers villager1 = new Villagers(ColorAndGender.BLUE,ColorAndGender.FEMALE);
        villager1.isVelocityLooping(false);
        villager1.addVelocity(new Vector2(0,-10), 1);
        villager1.defineBody(world, 279, 2269);
        this.addSprite(villager1);
        
        Villagers villager2 = new Villagers(ColorAndGender.RED,ColorAndGender.FEMALE);
        villager2.isVelocityLooping(false);
        villager2.addVelocity(new Vector2(0,-10), 1);
        villager2.defineBody(world, 483, 2308);
        this.addSprite(villager2);
        
        Villagers villager3 = new Villagers(ColorAndGender.BLUE,ColorAndGender.MALE);
        villager3.isVelocityLooping(true);
        villager3.addVelocity(new Vector2(0,-25), 9);
        villager3.addVelocity(new Vector2(0,25), 9);
        villager3.defineBody(world, 863, 2279);
        this.addSprite(villager3);
        
        Villagers villager4 = new Villagers(ColorAndGender.BROWN,ColorAndGender.FEMALE);
        villager4.isVelocityLooping(true);
        villager4.addVelocity(new Vector2(-20,0), 6);
        villager4.addVelocity(new Vector2(20,0), 6);
        villager4.defineBody(world, 810, 2046);
        this.addSprite(villager4);
        
        Villagers villager5 = new Villagers(ColorAndGender.GREEN,ColorAndGender.MALE);
        villager5.isVelocityLooping(false);
        villager5.addVelocity(new Vector2(0,-10), 1);
        villager5.defineBody(world, 638, 2030);
        this.addSprite(villager5);
        
        Villagers villager6 = new Villagers(ColorAndGender.BLACK,ColorAndGender.FEMALE);
        villager6.isVelocityLooping(false);
        villager6.addVelocity(new Vector2(0,-10), 1);
        villager6.defineBody(world, 353, 2057);
        this.addSprite(villager6);
        
        Villagers villager7 = new Villagers(ColorAndGender.WHITE,ColorAndGender.MALE);
        villager7.isVelocityLooping(true);
        villager7.addVelocity(new Vector2(0,-25), 4);
        villager7.addVelocity(new Vector2(25,0), 4);
        villager7.addVelocity(new Vector2(0,25), 4);
        villager7.addVelocity(new Vector2(-25,0), 4);
        villager7.defineBody(world, 179, 1958);
        this.addSprite(villager7);
    }
    /**
     * Set the music for the map
     */
	@Override
	public void setMusic() {
		manager.getMusicManager().setMusic((Music) Gdx.audio.newMusic(Gdx.files.internal("assets/Music/Right-Side of Mountain Town.mp3")));
	}
}
