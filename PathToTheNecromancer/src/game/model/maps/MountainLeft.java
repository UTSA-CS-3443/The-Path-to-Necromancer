package game.model.maps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;
import game.model.sprites.ObjectSprites;

/**
 * The area to the left of the mountain in the game.
 * @author enigma-phi
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
     * layer 6 = north transition
     */
    private static final int NORTHTRANSITION = 6;
    /**
     * layer 7 = north transition
     */
    private static final int SOUTHTRANSITION = 7;
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
        super(MAPNAME, true);
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

    }

    /**
     * Return an enemy from the specific gameMap
     * 
     * @return an EnemySprite
     */
    @Override
    public EnemySprites getEnemy() {
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
        Texture T1 = new Texture("Game Tilesets/T1.png");
        Texture T2 = new Texture("Game Tilesets/T2.png");
        Texture T3 = new Texture("Game Tilesets/T3.png");
        Texture T4 = new Texture("Game Tilesets/T4.png");
        
        // add all of the trees
        this.addSprite(new ObjectSprites(T1, -12, height - 119));
        this.addSprite(new ObjectSprites(T1, 416, height - 119));
        this.addSprite(new ObjectSprites(T1, 682, height - 111));
        this.addSprite(new ObjectSprites(T1, 8, height - 430));
        this.addSprite(new ObjectSprites(T1, 232, height - 538));
        this.addSprite(new ObjectSprites(T1, 488, height - 418));
        this.addSprite(new ObjectSprites(T1, 680, height - 442));
        this.addSprite(new ObjectSprites(T1, -12, height - 779));
        this.addSprite(new ObjectSprites(T1, 408, height - 723));
        this.addSprite(new ObjectSprites(T1, 644, height - 779));

        this.addSprite(new ObjectSprites(T3, 788 - 128, height - 1041));
        this.addSprite(new ObjectSprites(T3, 112 - 128, height - 1297));
        this.addSprite(new ObjectSprites(T3, 151 - 127, height - 1552));
    }
}
