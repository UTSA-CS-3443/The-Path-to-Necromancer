package game.model.sprites;

import com.sun.prism.Texture;
/**
 * The class for goblins combat values
 * @author cux144
 *
 */
public class Goblin extends EnemySprites{
	/**
	 * sets up the goblin for a texture
	 */
	private Texture BanditTexture;
	/**
	 * Give the goblin the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Goblin() {
		super.setBaseStats(3, 1, 7, 5, 10);
	}
}
