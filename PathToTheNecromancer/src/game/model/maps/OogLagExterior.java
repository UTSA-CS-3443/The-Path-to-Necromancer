package game.model.maps;

import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;

/**
 * The second map of the game. The area outside of OogLag's tavern.
 * 
 * @author enigma-phi
 *
 */
public class OogLagExterior extends GameMaps {

    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map02-Oog-lag Tavern.tmx";

    /**
     * layer 3 = south transition
     */
    private static final int SOUTHTRANSITION = 3;
    /**
     * layer 4 = north transition
     */
    private static final int NORTHTRANSITION = 4;
    /**
     * layer 5 = tavern interior transition
     */
    private static final int TAVERNTRANSITION = 5;
    /**
     * layer 6 = map collision layer
     */
    private static final int[] COLLISIONLAYERS = { 6 };
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
    public OogLagExterior(MapManager manager) {
        super(MAPNAME);
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
        this.creator.createTransition(world, this, TAVERNTRANSITION, DOOR);

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
            break;
        // the transition to the south
        case SOUTH:
            this.manager.setMap(new IntroArea(this.manager), 130, 375);
            break;
        // the transition through the door
        case DOOR:
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
    public EnemySprites getEnemy() {
        return null;
    }

    /**
     * Create all of the sprites for the map.
     * 
     * @param world
     *            allows sprites with box2d bodies to be generated in the map
     */
    public void createSprites(World world) {
    };

}
