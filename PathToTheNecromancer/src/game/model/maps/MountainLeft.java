package game.model.maps;


import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;
import game.model.sprites.ObjectSprites;

/**
 * The area to the left of the mountain in the game.
 * Connects with PlainsArea and Mountain Right
 * @author enigma-phi
 * @author ToTryHardRay - added tree sprites
 *
 */
public class MountainLeft extends GameMaps {

    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map05-LeftSideOfTheMountain.tmx";
    /**
     * layers 5 = collision layers
     */
    private static final int[] COLLISIONLAYERS = { 5 };
    /**
     * layer 7 = north transition
     */
    private static final int NORTHTRANSITION = 7;
    /**
     * layer 6 = north transition
     */
    private static final int SOUTHTRANSITION = 6;
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
    public MountainLeft(MapManager manager) {
        super(MAPNAME, false);
        this.manager = manager; // get the map manager to load the map
        this.creator = B2WorldCreator.getInstance(); // get the instance for the creator
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
     * Transition between the areas
     */
    @Override
    public void transitionAreas(short transitionType) {
    	switch (transitionType) {
    	
    	// the transition to the north
        case NORTH:
        	 this.manager.setMap(new MiddleMountain(this.manager), 30, 2275);
             break;
        // the transition to the south
        case SOUTH:
            this.manager.setMap(new PlainsArea(this.manager), 400, 2250);
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
     * Create all of the GameSprites for the map (i.e. say trees)
     * 
     * @param world
     *            allows sprites with box2d bodies to be generated in the map
     */
    @Override
    public void createSprites(World world) {
        // the height of the map since 0,0 in Tiled is in the top left
        // compared to the TiledMap's 0,0 in the bottom left
        int height = 1600;

        // file locations of different trees
        TextureAtlas atlas = new TextureAtlas("Game Tilesets/Trees/Trees.pack");
        Skin skin = new Skin();
        skin.addRegions(atlas);
        TextureRegion T1 = skin.getRegion("T1");
        TextureRegion T2 = skin.getRegion("T2");
        TextureRegion T3 = skin.getRegion("T3");
        TextureRegion T4 = skin.getRegion("T4");
        TextureRegion H3 = new TextureRegion(new Texture("Game Tilesets/H3.png"));
        //Texture H3 = new Texture("Game Tilesets/H3");
        
        // add all of the trees
        this.addSprite(new ObjectSprites(T1, -12, height - 166));
        this.addSprite(new ObjectSprites(T1, 416, height - 166));
        this.addSprite(new ObjectSprites(T1, 682, height - 158));
        this.addSprite(new ObjectSprites(T1, 8, height - 477));
        this.addSprite(new ObjectSprites(T1, 232, height - 585));
        this.addSprite(new ObjectSprites(T1, 488, height - 465));
        this.addSprite(new ObjectSprites(T1, 680, height - 489));
        this.addSprite(new ObjectSprites(T1, -12, height - 826));
        this.addSprite(new ObjectSprites(T1, 408, height - 770));
        this.addSprite(new ObjectSprites(T1, 644, height - 826));
        
        
        this.addSprite(new ObjectSprites(T2, 625, height - 1204));

        this.addSprite(new ObjectSprites(T3, 788 - 128, height - 1081));
        this.addSprite(new ObjectSprites(T3, 112 - 128, height - 1337));
        this.addSprite(new ObjectSprites(T3, 151 - 127, height - 1592));
        
        this.addSprite(new ObjectSprites(T4, 98, height - 1211));
        this.addSprite(new ObjectSprites(T4, 661, height - 1523));
        
        this.addSprite(new ObjectSprites(H3, 224, height - 1440));
        
    }
}
