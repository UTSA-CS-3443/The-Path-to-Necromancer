package game.model.sprites;

import com.badlogic.gdx.graphics.Texture;

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
    private Texture objectTexture;

    /**
     * Create the object sprite. Use the filename passed in to determine the image
     * and the position of the sprite.
     * 
     * @param fileName
     *            is the name of the file the sprite is in
     * @param x
     *            is the x-coordinate of the sprite
     * @param y
     *            is the y-coordinate of the sprite
     */
    public ObjectSprites(String fileName, float x, float y) {
        super();
        this.objectTexture = new Texture(fileName);
        super.setRegion(objectTexture);
        super.setPosition(x, y);
        super.setBounds(x, y, objectTexture.getWidth(), objectTexture.getHeight());
    }

    /**
     * Get the y-coordinate of the sprite. Because the sprite png's used do not
     * consist of the entire image, set the y-coordinate to be less than the actual
     * y-coordinate.
     */
    @Override
    public float getY() {
        return super.getY() - objectTexture.getHeight() / 3;
    }
}
