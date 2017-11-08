package game.model.maps;


import java.util.Random;

import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;

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
     * North: PlainsArea South: IntroArea Door: OogLagInterior
     * 
     * @param the
     *            type of transition for the map
     */
    @Override
    public void transitionAreas(short transitionType) {
        switch (transitionType) {
        // the transition to the north
        case NORTH:
        	 this.manager.setMap(new MountainLeft(this.manager), 295, 1575);
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
    public void createSprites(World world) {}
   

}
