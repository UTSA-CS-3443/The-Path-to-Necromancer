package game.model.maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;

import game.model.sprites.CharacterSprites;
import game.model.sprites.EnemySprites;
import game.model.sprites.GameSprites;

/**
 * An abstract class establishing features present in all maps in the game.
 * Contains some general methods.
 * 
 * @author enigma-phi
 *
 */
public abstract class GameMaps {
    /**
     * The name of the map file
     */
    private String mapName;
    /**
     * The tiled map
     */
    private TiledMap map;
    /**
     * ArrayList of all the sprites in a map
     */
    private ArrayList<GameSprites> sprites;
    /**
     * Transition to the north
     */
    protected static final short NORTH = 1;
    /**
     * Transition to the south
     */
    protected static final short SOUTH = 2;
    /**
     * Transition to the east
     */
    protected static final short EAST = 4;
    /**
     * Transition to the west
     */
    protected static final short WEST = 8;
    /**
     * Transition through a door
     */
    protected static final short DOOR = 16;

    /**
     * Whether or not the specific GameMap has combat
     */
    private boolean mapHasCombat;
    /**
     * Random object for getting random int
     */
    private Random random;
    /**
     * Set the name of the map's path in the assets folder. Each map has to have
     * this.
     * 
     * @param mapName
     *            is the map's file path
     */
    public GameMaps(String mapName, boolean hasCombat) {
        super();
        this.mapName = mapName;
        this.mapHasCombat = hasCombat;
        this.sprites = new ArrayList<GameSprites>();
        this.random = new Random();
    }

    /**
     * Modify the world so that it contains all the objects on the map
     * 
     * @param world
     *            is the world to modify
     */
    public abstract void generateWorld(World world);

    /**
     * Get the TiledMap for any GameMap
     * 
     * @return the tiled map
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     * Set the TiledMap
     * 
     * @param map
     *            is the map to set to
     */
    public void setMap(TiledMap map) {
        this.map = map;
    }

    /**
     * Load a specific map.
     * 
     * @param mapLoader
     *            loads the maps
     */
    public void loadMap(TmxMapLoader mapLoader) {
        this.map = mapLoader.load(this.mapName);
    }

    /**
     * Transition between the areas
     * 
     * @param transitionType
     *            is the type of transition for determining where to transition to
     */
    public abstract void transitionAreas(short transitionType);

    /**
     * Throw away garbage.
     */
    public void dispose() {
        this.map.dispose();
    }

    /**
     * Add a sprite to the arraylist of sprites
     * 
     * @param sprite
     *            the sprite to add
     */
    public void addSprite(GameSprites sprite) {
        this.sprites.add(sprite);
    }

    /**
     * Return the arraylist of sprites
     * 
     * @return a sorted arraylist of sprites
     */
    public ArrayList<GameSprites> getSprites() {
        Collections.sort(this.sprites);
        return this.sprites;
    }

    /**
     * Create all of the sprites for the map
     * 
     * @param world
     *            allows sprites with box2d bodies to be generated in the map
     */
    public abstract void createSprites(World world);

    /**
     * Return an enemy from the map
     * 
     * @param the player's current level
     * @return the enemy
     */
    public abstract EnemySprites getEnemy(int level);

    /**
     * Returns whether the current map has combat
     * 
     * @return a boolean for combat
     */
    public boolean hasCombat() {
        if (mapHasCombat) {
            int x = random.nextInt(100);
            return x < 5 ? true : false;

        } else
            return false;
    }
    /**
     * Returns a String specifying the specific map
     * @return the map's name
     */
    public String getMapName() {
    	return this.mapName;
    }
    
    /**
     * Remove a specific character sprite
     * @param character
     */
    public void remove(CharacterSprites character) {
    	this.sprites.remove(character);
    }
    /**
     * Set the music for the current map
     */
    public abstract void setMusic();
}
