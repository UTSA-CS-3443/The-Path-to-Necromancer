package game.controller;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import game.model.maps.GameMaps;
import game.view.PlayScreen;

/**
 * Manage the map the PlayScreen is currently rendering.
 * 
 * @author enigma-phi
 *
 */
public class MapManager {

    /**
     * The screen that the MapManager manages
     */
    private PlayScreen screen;
    /**
     * The type of map the manager currently has
     */
    private GameMaps gameMap;
    /**
     * Load the TiledMaps
     */
    private TmxMapLoader mapLoader;

    /**
     * Initialize the MapManager
     * 
     * @param screen
     *            is the screen for the MapManager to manage
     */
    public MapManager(PlayScreen screen) {
        this.screen = screen;
        this.mapLoader = new TmxMapLoader();
    }

    /**
     * Set the map currently displayed by the screen and set the position of the
     * player character.
     * 
     * @param gameMap
     *            is the map to change the screen to
     * @param x
     *            is the new x-position for the player's character
     * @param y
     *            is the new y-position for the player's character
     */
    public void setMap(GameMaps gameMap, int x, int y) {
        // Reset the world
        World world = new World(new Vector2(0, 0), true);
        world.setContactListener(new WorldContactListener());
        this.screen.setWorld(world);

        // Set and load the map.
        this.gameMap = gameMap;
        gameMap.loadMap(mapLoader);

        // Change the map renderer
        this.screen.setRenderer(new OrthogonalTiledMapRenderer(gameMap.getMap()));

        // generate the physics for the world
        gameMap.generateWorld(world);

        // set the player's position
        this.screen.getPlayer().defineBody(world, x, y);
    }

    /**
     * Throw away the garbage.
     */
    public void dispose() {
        this.gameMap.dispose();
    }

}
