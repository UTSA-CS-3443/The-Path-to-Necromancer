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
 * Lava Fields map
 * Connects with MountainRight and NecromancerEntry
 * @author ToTryHardRay
 *
 */
public class LavaFields extends GameMaps {
	
    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map08-LavaFields.tmx";

    /**
     * layer 3 = south transition
     */
    private static final int EASTTRANSITION = 3;
    /**
     * layer 2 = north transition
     */
    private static final int NORTHTRANSITION = 2;
    /**
     * layer 4 = map collision layer
     */
    private static final int[] COLLISIONLAYERS = { 4 };
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
    public LavaFields(MapManager manager) {
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
        this.creator.createTransition(world, this, NORTHTRANSITION, NORTH);
        this.creator.createTransition(world, this, EASTTRANSITION, EAST);

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
        	this.manager.setMap(new MountainRight(this.manager), 650, 30);
            break;
        // the transition to the south
        case EAST:
            this.manager.setMap(new NecromancerEntry(this.manager), 30, 70);
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
    @Override
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
    @Override
	public void createSprites(World world) {
    	// height of the map
    	int height = 1200;
    	
    	// create tree textures
    	TextureAtlas atlas = new TextureAtlas("Game Tilesets/Trees/Trees.pack");
        Skin skin = new Skin();
        skin.addRegions(atlas);
        TextureRegion T5 = skin.getRegion("T5");
    	
        // tree sprite locations for rendering
    	this.addSprite(new ObjectSprites(T5, -18, height - 342));
        this.addSprite(new ObjectSprites(T5, 98, height - 342));
        this.addSprite(new ObjectSprites(T5, 34, height - 230));
        this.addSprite(new ObjectSprites(T5, 62, height - 118));
        this.addSprite(new ObjectSprites(T5, 158, height - 122));
        this.addSprite(new ObjectSprites(T5, 154, height - 230));
        this.addSprite(new ObjectSprites(T5, 250, height - 58));
        this.addSprite(new ObjectSprites(T5, 254, height - 190));
        this.addSprite(new ObjectSprites(T5, 582, height - 170));
        this.addSprite(new ObjectSprites(T5, 586, height - 270));
        this.addSprite(new ObjectSprites(T5, 682, height - 242));
        this.addSprite(new ObjectSprites(T5, 678, height - 146));
        this.addSprite(new ObjectSprites(T5, 778, height - 90));
        this.addSprite(new ObjectSprites(T5, 850, height - 70));
        this.addSprite(new ObjectSprites(T5, 762, height - 190));
        this.addSprite(new ObjectSprites(T5, 862, height - 166));
        this.addSprite(new ObjectSprites(T5, 802, height - 282));
    }
    /**
     * Set the music for the map
     */
	@Override
	public void setMusic() {
		//manager.getMusicManager().setMusic((Music) Gdx.audio.newMusic(Gdx.files.internal("assets/song.mp3")));
	}  

}
