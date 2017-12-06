package game.model.maps;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.badlogic.gdx.physics.box2d.World;
import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;

/**
 * 
 * This is the class for the middle mountain map
 * @author ToTryHardRay
 *
 */
public class MiddleMountain extends GameMaps {
	
	/**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map06-MiddleMountain.tmx";

    /**
     * layer 4 = south transition
     */
    private static final int SOUTHTRANSITION = 4;
    /**
     * layer 2 = north transition
     */
    private static final int EASTTRANSITION = 2;
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
    public MiddleMountain(MapManager manager) {
        super(MAPNAME, false);
        this.manager = manager; // manager for loading maps
        this.creator = B2WorldCreator.getInstance(); // for creating the game's physics
        this.setMusic();
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
        this.creator.createTransition(world, this, EASTTRANSITION, EAST);
        this.creator.createTransition(world, this, SOUTHTRANSITION, SOUTH);
       

    }

    /**
     * Transition between areas
     * 
     * North: PlainsArea South: IntroArea Door: OogLagInterior
     * 
     * @param the
     *            type of transition for the map
     */
    @Override
    public void transitionAreas(short transitionType) {
        switch (transitionType) {
        // the transition to the north
        case EAST:
        	 this.manager.setMap(new MountainRight(this.manager), 30, 2275);
            break;
        // the transition to the south
        case SOUTH:
            this.manager.setMap(new MountainLeft(this.manager), 295, 1575);
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
    @Override
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
     * Create the sprites for the map
     * @param world is the world to place the sprites in
     */
	@Override
	public void createSprites(World world) {
		// TODO Auto-generated method stub
		
	}
	
    /**
     * Set the music for the map
     */
	@Override
	public void setMusic() {
		manager.getMusicManager().setMusic((Music) Gdx.audio.newMusic(Gdx.files.internal("Music/Left-side of Mountain Town.mp3")));
	}
}
