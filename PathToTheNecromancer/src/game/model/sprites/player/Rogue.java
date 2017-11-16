package game.model.sprites.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * The rogue character class
 * 
 * @author enigma-phi
 *
 */
public class Rogue extends Player{
    /**
     * The pixel width of the character sprite
     */
    private static final int PLAYER_WIDTH = 15;
    /**
     * The pixel height of the character sprite
     */
    private static final int PLAYER_HEIGHT = 30;

    /**
     * The texture used to draw and render the character sprite
     */
    private Texture rogueTexture;

    /**
     * Create the DefaultPlayer object.
     */
    public Rogue() {
        super();
        // Initialize the different texture regions associated with the character
        setTextureValues();
        // Set the size of the character
        setBounds(0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);

        // Set the initial animation
        setRegion(super.getStandingRegion());
    }

    /**
     * Set the different TextureRegions associated with the Default Character for
     * animation.
     */

    @Override
    public void setTextureValues() {
    	int rowHeight = 210;
    	int width = 31;
    	int height = 70;
    	
        // Use the Rogue animations until we have the player SpriteSheet
        rogueTexture = new Texture("CharacterSprites/MainCharacter-Adjusted.png");

        // Set the default standing region of the character.
        super.setStandingRegion(new TextureRegion(rogueTexture, 0, 210, width, height));
        // Array of frames used for the animations
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 0; i <= 3; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 2; i >= 1; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 6; i >= 4; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 5; i <= 6; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Up on the map
        for(int i = 0+7; i <= 3+7; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 2+7; i >= 1+7; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 6+7; i >= 4+7; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 5+7; i <= 6+7; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Down-Left on the map
        for(int i = 0+14; i < 4+14; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 2+14; i > 0+14; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 5+14; i < 6+14; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 6+14; i > 3+14; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Down-Right on the map
        for(int i = 28-1; i >= 28-4; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 28-3; i <= 28-2; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 28-5; i >= 28-7; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 28-6; i <= 28-5; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Right on the map
        for(int i = 0+28; i <= 3+28; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 2+28; i >= 1+28; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 4+28; i <= 6+28; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 5+28; i >= 4+28; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Left on the map
        for(int i = 42-1; i >= 42-4; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 42-3; i <= 42-2; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 42-5; i >= 42-7; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 42-6; i <= 42-5; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Up-Left on the map
        for(int i = 0+42; i <= 3+42; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 2+42; i >= 1+42; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 4+42; i <= 6+42; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 5+42; i >= 4+42; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Up-Right on the map
        for(int i = 56-1; i >= 56-4; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 56-3; i <= 56-2; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        for(int i = 56-5; i >= 56-7; i--) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        
        for(int i = 56-6; i <= 56-5; i++) {
        	frames.add(new TextureRegion(rogueTexture, i*width, rowHeight, width, height));
        }
        super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();
        super.setAnimationSpeed(2);
    }

    /**
     * Create the DefaultCharacter's body for the box2d physics engine
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
        
        // Create the body and add mass
        Body body = world.createBody(bdef);
        MassData mass = new MassData();
        mass.mass = 0;
        body.setMassData(mass);
        this.setBody(body); // place into the world

        // create the world fixture for collision
        FixtureDef fdef = new FixtureDef();
        PolygonShape rect = new PolygonShape();

        // coordinates of the character's collision box
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-(PLAYER_WIDTH) / 2, 0); // top left
        vertice[1] = new Vector2((PLAYER_WIDTH) / 2, 0); // top right
        vertice[2] = new Vector2(-(PLAYER_WIDTH) / 2, -(PLAYER_HEIGHT) / 2); // bottom
                         
        // left
        vertice[3] = new Vector2((PLAYER_WIDTH) / 2, -(PLAYER_HEIGHT) / 2); // bottom
        // right

        // put the character's collision box in the world
        rect.set(vertice);
        fdef.shape = rect;
        this.getBody().createFixture(fdef).setUserData(this);
        
        // create the world fixture for collision
        fdef = new FixtureDef();
        rect = new PolygonShape();

        // coordinates of the character's collision box
        vertice = new Vector2[4];
        vertice[0] = new Vector2(-(PLAYER_WIDTH) / 2 + 5 - 20, (PLAYER_HEIGHT) - 10); // top left
        vertice[1] = new Vector2((PLAYER_WIDTH) / 2 - 5 + 20, (PLAYER_HEIGHT) - 10); // top right
        vertice[2] = new Vector2(-(PLAYER_WIDTH) / 2 + 5 - 20, -(PLAYER_HEIGHT) / 2 - 10); // bottom
                                                                                                                 // left
        vertice[3] = new Vector2((PLAYER_WIDTH) / 2 - 5 + 20, -(PLAYER_HEIGHT) / 2 - 10); // bottom
        // right

        // put the character's collision box in the world
        rect.set(vertice);
        fdef.isSensor = true;
        fdef.shape = rect;
        this.getBody().createFixture(fdef).setUserData("Interaction");
    }
    /**
     * Get the Character's Class
     * 
     * @return the class name
     */
    public String getCharClass() {
        return "Rogue";
    }

	
}
