package game.model.sprites;

import com.sun.prism.Texture;
/**
 * The class for golems combat values
 * @author cux144
 *
 */
public class Golem extends EnemySprites  {
	/**
	 * sets up the golem for a texture
	 */
	private Texture GolemTexture;
	/**
	 * Give the golem the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Golem() {
		super.setBaseStats(8, 1, 1, 5, 70);
	}
}
