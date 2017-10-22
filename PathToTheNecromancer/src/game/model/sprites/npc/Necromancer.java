package game.model.sprites.npc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import game.model.sprites.CharacterSprites;

/**
 * 
 * The Necromancer is the main villain of the game.
 * 
 * @author enigma-phi
 *
 */
public class Necromancer extends CharacterSprites {
    /**
     * The pixel width of the Necromancer sprite
     */
    private static final int NEC_WIDTH = 40;
    /**
     * The pixel height of the Necromancer sprite
     */
    private static final int NEC_HEIGHT = 50;

    /**
     * The texture used to draw and render the Necromancer sprite
     */
    private Texture necromancerTexture;
    /**
     * The scaling factor used to draw the Necromancer.
     */
    private static float SCALINGFACTOR = 0.65f;

    /**
     * Create the Necromancer object.
     */
    public Necromancer() {
        super();
        // Initialize the different texture regions associated with the Necromancer
        setTextureValues();
        // Set the size of the Necromancer
        setBounds(0, 0, NEC_WIDTH * SCALINGFACTOR, NEC_HEIGHT * SCALINGFACTOR);

        // Set the initial animation
        setRegion(super.getStandingRegion());
    }

    /**
     * Set the different TextureRegions associated with the Necromancer for
     * animation.
     */

    @Override
    public void setTextureValues() {
        necromancerTexture = new Texture("Necromancer2.png");

        // Set the default standing region of the Necromancer.
        super.setStandingRegion(new TextureRegion(necromancerTexture, 20, 140, 40, 50));
        // Array of frames used for the animations
        Array<TextureRegion> frames = new Array<TextureRegion>();

        // Set the animations for moving Down on the map
        frames.add(new TextureRegion(necromancerTexture, 20, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 20, 201, 40, 50));
        super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Up on the map
        frames.add(new TextureRegion(necromancerTexture, 60, 140, 50, 50));
        frames.add(new TextureRegion(necromancerTexture, 60, 201, 50, 50));
        super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Left on the map
        frames.add(new TextureRegion(necromancerTexture, 120, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 120, 201, 40, 50));
        super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Right on the map
        frames.add(new TextureRegion(necromancerTexture, 160, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 160, 201, 40, 50));
        super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Down-Left on the map
        frames.add(new TextureRegion(necromancerTexture, 200, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 200, 201, 40, 50));
        super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Down-Right on the map
        frames.add(new TextureRegion(necromancerTexture, 260, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 260, 201, 40, 50));
        super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Up-Right on the map
        frames.add(new TextureRegion(necromancerTexture, 310, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 310, 201, 40, 50));
        super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Up-Left on the map
        frames.add(new TextureRegion(necromancerTexture, 350, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 350, 201, 40, 50));
        super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();
    }

    /**
     * Create the Necromancer's body for the box2d physics engine
     * 
     * @param world
     *            is the world to put the character into
     * @param x
     *            is the x-coordinate to put the character on
     * @param y
     *            is the y-coordinate to put the character on
     */
    @Override
    public void defineBody(World world, int x, int y) {

        // set the body of the character
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y); // position
        bdef.type = BodyDef.BodyType.DynamicBody; // body type
        this.setBody(world.createBody(bdef)); // place into the world

        // create the world fixture for collision
        FixtureDef fdef = new FixtureDef();
        PolygonShape rect = new PolygonShape();

        // coordinates of the Necromancer's collision box
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-(NEC_WIDTH * SCALINGFACTOR) / 2 + 5, 0); // top left
        vertice[1] = new Vector2((NEC_WIDTH * SCALINGFACTOR) / 2 - 5, 0); // top right
        vertice[2] = new Vector2(-(NEC_WIDTH * SCALINGFACTOR) / 2 + 5, -(NEC_HEIGHT * SCALINGFACTOR) / 2); // bottom
                                                                                                           // left
        vertice[3] = new Vector2((NEC_WIDTH * SCALINGFACTOR) / 2 - 5, -(NEC_HEIGHT * SCALINGFACTOR) / 2); // bottom
                                                                                                          // right

        // set the world
        rect.set(vertice);
        fdef.shape = rect;
        this.getBody().createFixture(fdef).setUserData("Necromancer");
    }

}