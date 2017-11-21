package game.model.maps;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

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
    @Override
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
    @Override
	public void createSprites(World world) {
    	// the height of the map since 0,0 in Tiled is in the top left
        // compared to the TiledMap's 0,0 in the bottom left
        int height = 2400;

        // file locations of different trees
       TextureAtlas atlas = new TextureAtlas("Game Tilesets/Trees/Trees.pack");
       Skin skin = new Skin();
       skin.addRegions(atlas);
       TextureRegion T2 = skin.getRegion("T2");
       TextureRegion T3 = skin.getRegion("T3");
       TextureRegion T7 = skin.getRegion("T7");
       TextureRegion T9 = skin.getRegion("T9");
       TextureRegion T10 = skin.getRegion("T10");
        
        // add all of the trees
       	this.addSprite(new ObjectSprites(T2, 236, height - 1490));
       
        this.addSprite(new ObjectSprites(T9, 622, height - 1907));
        this.addSprite(new ObjectSprites(T9, 683, height - 1687));
        this.addSprite(new ObjectSprites(T9, 174, height - 1851));
        this.addSprite(new ObjectSprites(T9, 361, height - 1643));
       
        this.addSprite(new ObjectSprites(T10, 572, height - 1354));
        this.addSprite(new ObjectSprites(T10, 0, height - 1475));
        this.addSprite(new ObjectSprites(T10, -9, height - 1707));
        this.addSprite(new ObjectSprites(T10, 675, height - 1479));
        this.addSprite(new ObjectSprites(T10, 416, height - 1903));

        this.addSprite(new ObjectSprites(T3, 428, height - 1453));
        this.addSprite(new ObjectSprites(T3, 596, height - 1122));
        this.addSprite(new ObjectSprites(T3, -32, height - 988));
        this.addSprite(new ObjectSprites(T3, 476, height - 864));
        this.addSprite(new ObjectSprites(T3, 640, height - 725));
        this.addSprite(new ObjectSprites(T3, 424, height - 2123));
        
        this.addSprite(new ObjectSprites(T7, 145, height - 1347));
        this.addSprite(new ObjectSprites(T7, 83, height - 1935));
        this.addSprite(new ObjectSprites(T7, 585, height - 2031));
        this.addSprite(new ObjectSprites(T7, 290, height - 2304));
    }
}
    
   


