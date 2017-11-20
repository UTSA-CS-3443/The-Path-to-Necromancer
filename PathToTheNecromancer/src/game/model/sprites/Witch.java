package game.model.sprites;

import com.sun.prism.Texture;
/**
 * The class for witches combat values
 * @author cux144
 *
 */
public class Witch extends EnemySprites{
	/**
	 * sets up the witch for a texture
	 */
	private Texture WitchTexture;
	/**
	 * Give the witch the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Witch() {
		super.setBaseStats(8, 15, 17, 19, 50);
	}
}
