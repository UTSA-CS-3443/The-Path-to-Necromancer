package game.model.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Create an object sprite for animation. The object will consist of only a
 * Texture region to render at a specific position x and y.
 * 
 * @author enigma-phi
 *
 */
public class ObjectSprites extends GameSprites {

    /**
     * The texture to render.
     */
    private TextureRegion objectTexture;

    /**
     * Create the object sprite. Use the filename passed in to determine the image
     * and the position of the sprite.
     * 
     * @param texture
     *            is the sprite's texture to render.
     * @param x
     *            is the x-coordinate of the sprite
     * @param y
     *            is the y-coordinate of the sprite
     */
    public ObjectSprites(TextureRegion texture, float x, float y) {
        super();
        this.objectTexture = texture;
        super.setRegion(objectTexture);
        super.setPosition(x, y);
        super.setBounds(x, y, objectTexture.getRegionWidth(), objectTexture.getRegionHeight());
    }

    /**
     * Get the y-coordinate of the sprite. Because the sprite png's used do not
     * consist of the entire image, set the y-coordinate to be less than the actual
     * y-coordinate.
     */
    @Override
    public float getY() {
        return super.getY();
    }
    
    /**
     * Update the sprite. Since it is a static object, do nothing
     * @param dt is the change in time since the last render
     */
    public void update(float dt) {};
}
