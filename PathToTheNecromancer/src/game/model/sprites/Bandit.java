package game.model.sprites;

import com.sun.prism.Texture;
/**
 * The class for bandits combat values
 * @author cux144
 *
 */
public class Bandit extends EnemySprites {
	/**
	 * sets up the bandit for a texture
	 */
	private Texture BanditTexture;
	/**
	 * Give the bandit the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Bandit() {
		super.setBaseStats(4, 1, 4, 1, 15);
	}
}
