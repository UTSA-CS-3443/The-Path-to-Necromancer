package game.model.maps;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;

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
     * Set the name of the map's path in the assets folder. Each map has to have
     * this.
     * 
     * @param mapName is the map's file path
     */
    public GameMaps(String mapName) {
        super();
        this.mapName = mapName;
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

}
