package game.model.maps;


import java.util.Random;

import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;

/**
 * Entrance to the Necromancer lair
 * Connects with NecromancerLair and LavaFields
 * @author ToTryHardRay
 *
 */
public class NecromancerEntry extends GameMaps {
	
    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map09-EntrytoNecromancer'sLair.tmx";

    /**
     * layer 2 = south transition
     */
    private static final int SOUTHTRANSITION = 2;
    /**
     * layer 1 = north transition
     */
    private static final int NORTHTRANSITION = 1;
  
    /**
     * layer 0 = map collision layer
     */
    private static final int[] COLLISIONLAYERS = { 0 };
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
    public NecromancerEntry(MapManager manager) {
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
        	  this.manager.setMap(new NecromancerLair(this.manager), 300, 30);
            break;
        // the transition to the south
        case SOUTH:
            this.manager.setMap(new LavaFields(this.manager), 1170, 200);
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
    	int enemyCount = 4;
    	Random rand = new Random();
    	int enemyNum = rand.nextInt(enemyCount);
    	switch(enemyNum){
    	case 0:
    		//return new skeleton(level);
    		break;
    	case 1:
    		//return new zombie(level);
    		break;
    	case 2:
    		//return new witch(level);
    		break;
    	case 3:
    		//return new ghost(level);
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
    /**
     * Set the music for the map
     */
	@Override
	public void setMusic() {
		// TODO Auto-generated method stub
		
	}
   

}
