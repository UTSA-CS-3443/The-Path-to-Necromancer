package game.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.maps.GameMaps;

/**
 * Create the objects for collision in the game.
 * 
 * @author enigma-phi
 *
 */
public class B2WorldCreator {

    /**
     * An instance of the object
     */
    private static B2WorldCreator creator = null;

    /**
     * A private constructor for creating a singleton.
     */
    private B2WorldCreator() {
    }

    /**
     * Instance method
     * 
     * @return the class instance
     */
    public static B2WorldCreator getInstance() {
        if (creator == null)
            creator = new B2WorldCreator();
        return creator;
    }

    /**
     * Add new collision objects to the world
     * 
     * @param world
     *            the world to which the collision objects are added
     * @param gameMap
     *            the gameMap the collision objects are located in
     * @param layers
     *            the layers of the map that will be used to create the collision
     *            objects
     */
    public void createWorld(World world, GameMaps gameMap, int[] layers) {
        TiledMap map = gameMap.getMap(); // the map for creating the objects
        BodyDef bdef = new BodyDef(); // body definition for the collision
        PolygonShape shape = new PolygonShape(); // shape of the collision object
        FixtureDef fdef = new FixtureDef(); // fixture definition for the body
        Body body;

        // Go through each layer
        for (int layer : layers) {
            // iterate through all of the objects in a layer
            for (MapObject object : map.getLayers().get(layer).getObjects().getByType(RectangleMapObject.class)) {
                // create a rectangle object in the world
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));

                // add it to the world
                body = world.createBody(bdef);

                // add the fixture definition to the world
                shape.setAsBox((rect.getWidth() / 2), (rect.getHeight() / 2));
                fdef.shape = shape;
                body.createFixture(fdef);
            }
        }

    }

    /**
     * Add new collision objects to the world
     * 
     * @param world
     *            the world to which the collision objects are added
     * @param gameMap
     *            the map the collision objects are located in
     * @param layer
     *            the layer of the map that will be used to create the collision
     *            objects. These collisions will result in transitions between areas
     * @param categoryBit
     *            the category of the transition for determining which type of collision occurs
     */
    public void createTransition(World world, GameMaps gameMap, int layer, short categoryBit) {

        BodyDef bdef = new BodyDef(); // body definition for the collision
        PolygonShape shape = new PolygonShape(); // shape of the collision object
        FixtureDef fdef = new FixtureDef(); // fixture definition for the body
        Body body;

        // iterate through all of the objects in the layer
        for (MapObject object : gameMap.getMap().getLayers().get(layer).getObjects()
                .getByType(RectangleMapObject.class)) {
            // create a rectangle object in the world
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2), (rect.getY() + rect.getHeight() / 2));
            
            // add it to the world
            body = world.createBody(bdef);

            // add the fixture definition to the world
            shape.setAsBox((rect.getWidth() / 2), (rect.getHeight() / 2));
            fdef.shape = shape;
            // set information for collision
            fdef.filter.categoryBits = categoryBit;
            fdef.isSensor = true;
            body.createFixture(fdef).setUserData(gameMap);
        }
    }
}
