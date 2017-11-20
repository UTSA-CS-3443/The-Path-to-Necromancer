package game.model.sprites;

import com.sun.prism.Texture;
/**
 * The class for kobolds combat values
 * @author cux144
 *
 */
public class Kobold extends EnemySprites{
	/**
	 * sets up the kobold for a texture
	 */
	private Texture KoboldTexture;
	/**
	 * Give the kobold the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Kobold() {
		super.setBaseStats(2, 4, 3, 2, 10);
	}
}
