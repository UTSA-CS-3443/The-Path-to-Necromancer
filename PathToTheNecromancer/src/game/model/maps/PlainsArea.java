package game.model.maps;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;
import game.model.sprites.ObjectSprites;

/**
 * The Plains area map
 * Connects with OoglagExterior and MountainLeft
 * @author ToTryHardRay
 *
 */
public class PlainsArea extends GameMaps{
	
    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map04-Plains Area.tmx";

    /**
     * layer 15 = south transition
     */
    private static final int SOUTHTRANSITION = 15;
    /**
     * layer 14 = north transition
     */
    private static final int NORTHTRANSITION = 14;
    /**
     * layer 13 = map collision layer
     */
    private static final int[] COLLISIONLAYERS = { 13 };
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
    public PlainsArea(MapManager manager) {
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
        	this.manager.setMap(new MountainLeft(this.manager), 400,30);
            break;
        // the transition to the south
        case SOUTH:
            this.manager.setMap(new OogLagExterior(this.manager), 350, 1175);
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
    	int enemyCount = 3;
    	Random rand = new Random();
    	int enemyNum = rand.nextInt(enemyCount);
    	switch(enemyNum){
    	case 0:
    		//return new goblin(level);
    		break;
    	case 1:
    		//return new kobold(level);
    		break;
    	case 2:
    		//return new Bandit(level);
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
    	// the height of the map since 0,0 in Tiled is in the top left
        // compared to the TiledMap's 0,0 in the bottom left
        int height = 2400;

        // file locations of different trees
        Texture T1 = new Texture("Game Tilesets/T1.png");
        Texture T2 = new Texture("Game Tilesets/T2.png");
        Texture T3 = new Texture("Game Tilesets/T3.png");
        Texture T4 = new Texture("Game Tilesets/T4.png");
        
        // add all of the trees
        //this.addSprite(new ObjectSprites(T4, 629, height - 1855));
        //this.addSprite(new ObjectSprites(T4, 689, height - 1635));
        //this.addSprite(new ObjectSprites(T4, 181, height - 1799));
        //this.addSprite(new ObjectSprites(T4, 365, height - 1591));
        
        this.addSprite(new ObjectSprites(T3, 571, height - 1315));
        this.addSprite(new ObjectSprites(T3, -1, height - 1436));
        this.addSprite(new ObjectSprites(T3, -9, height - 1669));
        this.addSprite(new ObjectSprites(T3, 675, height - 1440));
        this.addSprite(new ObjectSprites(T3, 415, height - 1863));
        
        this.addSprite(new ObjectSprites(T3, 596, height - 999));
        this.addSprite(new ObjectSprites(T3, -28, height - 915));
        this.addSprite(new ObjectSprites(T3, 480, height - 795));
        this.addSprite(new ObjectSprites(T3, 644, height - 655));
        this.addSprite(new ObjectSprites(T3, 424, height - 2084));
        
        this.addSprite(new ObjectSprites(T4, 148, height - 1319));
        this.addSprite(new ObjectSprites(T4, 88, height - 1907));
        this.addSprite(new ObjectSprites(T4, 588, height - 2003));
        this.addSprite(new ObjectSprites(T4, 296, height - 2276));
    }
}
    
   


