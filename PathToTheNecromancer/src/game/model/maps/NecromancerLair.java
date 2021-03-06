package game.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;
import game.model.sprites.npc.Guard;

/**
 * The Necromancer Lair
 * Connects with NecromancerEntry
 * @author ToTryHardRay
 *
 */
public class NecromancerLair extends GameMaps {
    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map10-Necromancer'sLair.tmx";

    /**
     * layer 5 = south transition
     */
    private static final int SOUTHTRANSITION = 5;
    /**
     * layer 7 = map collision layer
     */
    private static final int[] COLLISIONLAYERS = { 7 };
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
    public NecromancerLair(MapManager manager) {
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
       // this.creator.createTransition(world, this, NORTHTRANSITION, NORTH);
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
        // the transition to the south
        case SOUTH:
            this.manager.setMap(new NecromancerEntry(this.manager), 325, 575);
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
    public EnemySprites getEnemy( int level) {
        return null;
    }

    /**
     * Create all of the sprites for the map.
     * 
     * @param world
     *            allows sprites with box2d bodies to be generated in the map
     */
    public void createSprites(World world) {
    	Guard guard = new Guard();
    	guard.defineBody(world, 450, 111);
    	guard.isVelocityLooping(true);
    	guard.addVelocity(new Vector2(-31, 0), 10);
    	guard.addVelocity(new Vector2(31, 0), 10);
    	manager.addSprite(guard);
    }
    /**
     * Set the music for the map
     */
	@Override
	public void setMusic() {
		manager.getMusicManager().setMusic((Music) Gdx.audio.newMusic(Gdx.files.internal("Music/Necro-Castle.mp3")));
	}
   
}
