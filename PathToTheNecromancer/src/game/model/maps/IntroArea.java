package game.model.maps;

import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;

/**
 * The first area of the game. Where the user starts on a new game.
 * Connects to Oog-LagExterior
 * @author enigma-phi
 * @author ToTryHardRay - modified getEnemy()
 *
 */
public class IntroArea extends GameMaps {

    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map01-IntroArea.tmx";
    /**
     * layers 6,7 = collision layers
     */
    private static final int[] COLLISIONLAYERS = { 6, 7 };
    /**
     * layer 8 = north transition
     */
    private static final int NORTHTRANSITION = 8;
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

    public IntroArea(MapManager manager) {
        super(MAPNAME, false);
        this.manager = manager; // get the map manager to load the map
        this.creator = B2WorldCreator.getInstance();

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
    }

    /**
     * Transition between areas
     * 
     * North: OogLagExterior
     * 
     * @param the
     *            type of transition for the map
     */
    @Override
    public void transitionAreas(short transitionType) {
        this.manager.setMap(new OogLagExterior(this.manager), 390, 50);
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
    public void createSprites(World world) {};
}
