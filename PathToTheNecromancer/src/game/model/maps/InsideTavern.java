package game.model.maps;

import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;
import game.model.sprites.npc.OogLag;

/**
 * The inside of oog-lag's tavern
 * Connects with OoglagExterior
 * @author ToTryHardRay
 *
 */
public class InsideTavern extends GameMaps{
	
    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map03-Inside Oog-Lag's Tavern.tmx";

    /**
     * layer 4 = door transition
     */
    private static final int DOORTRANSITION = 4;
    
    /**
     * layer 5 = tavern interior transition
     */
   
    private static final int[] COLLISIONLAYERS = { 5 };
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
    public InsideTavern(MapManager manager) {
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
        this.creator.createTransition(world, this, DOORTRANSITION, DOOR);

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
       
        // the transition through the door
        case DOOR:
        	this.manager.setMap(new OogLagExterior(this.manager), 410, 692);
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
        return null;
    }

    /**
     * Create all of the sprites for the map.
     * 
     * @param world
     *            allows sprites with box2d bodies to be generated in the map
     */
    public void createSprites(World world) {
    	OogLag ooglag = new OogLag();
    	ooglag.defineBody(world, 350, 150);
    	super.addSprite(ooglag);
    }
}
