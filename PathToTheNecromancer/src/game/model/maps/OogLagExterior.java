package game.model.maps;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;
import game.model.sprites.ObjectSprites;

/**
 * The second map of the game. The area outside of OogLag's tavern.
 * Connects with IntroArea, InsideTavern, and PlainsArea
 * @author enigma-phi
 * @author ToTryHardRay 
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
        	this.manager.setMap(new PlainsArea(this.manager), 525, 30);
            break;
        // the transition to the south
        case SOUTH:
            this.manager.setMap(new IntroArea(this.manager), 130, 375);
            break;
        // the transition through the door
        case DOOR:
        	this.manager.setMap(new InsideTavern(this.manager), 420, 30);
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
    	// height of the map
    	int height = 1200;
    	
    	// create the tree sprites
    	TextureAtlas atlas = new TextureAtlas("Game Tilesets/Trees/Trees.pack");
        Skin skin = new Skin();
        skin.addRegions(atlas);
        TextureRegion T8 = skin.getRegion("T8");
        
        // location to render the tree sprites
        this.addSprite(new ObjectSprites(T8, 438, height - 302));
        this.addSprite(new ObjectSprites(T8, 346, height - 342));
        this.addSprite(new ObjectSprites(T8, 227, height - 106));
        this.addSprite(new ObjectSprites(T8, 263, height - 126));
        this.addSprite(new ObjectSprites(T8, 238, height - 186));
        this.addSprite(new ObjectSprites(T8, 295, height - 182));
        this.addSprite(new ObjectSprites(T8, 239, height - 254));
        this.addSprite(new ObjectSprites(T8, 279, height - 258));
        this.addSprite(new ObjectSprites(T8, 335, height - 234));
        this.addSprite(new ObjectSprites(T8, 395, height - 234));
        this.addSprite(new ObjectSprites(T8, 439, height - 246));
        this.addSprite(new ObjectSprites(T8, 470, height - 242));
        this.addSprite(new ObjectSprites(T8, 523, height - 258));
        this.addSprite(new ObjectSprites(T8, 546, height - 302));
        this.addSprite(new ObjectSprites(T8, 518, height - 338));
        this.addSprite(new ObjectSprites(T8, 491, height - 290));
        this.addSprite(new ObjectSprites(T8, 478, height - 341));
        this.addSprite(new ObjectSprites(T8, 410, height - 342));
        this.addSprite(new ObjectSprites(T8, 387, height - 314));
        this.addSprite(new ObjectSprites(T8, 346, height - 286));
        this.addSprite(new ObjectSprites(T8, 311, height - 306));
        this.addSprite(new ObjectSprites(T8, 283, height - 334));
        this.addSprite(new ObjectSprites(T8, 251, height - 322));
        this.addSprite(new ObjectSprites(T8, 259, height - 402));
        this.addSprite(new ObjectSprites(T8, 223, height - 434));
     }
    
    

}
