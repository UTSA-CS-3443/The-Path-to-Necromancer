package game.model.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * The GameSprites class contains generic information for any sprite in the
 * game.
 * 
 * @author enigma-phi
 *
 */
public abstract class GameSprites extends Sprite implements Comparable<GameSprites> {

    /**
     * The compareTo method is used to sort the sprites by their y-coordinate. The
     * comparison returns the y-coordinate of the sprite passed in minus the
     * y-coordinate of the current sprite.
     * 
     * @param sprite
     *            is the sprite to compare to
     * @return the results of the comparison.
     */
    @Override
    public int compareTo(GameSprites sprite) {
        return (int) (sprite.getY() - this.getY());
    }

}
