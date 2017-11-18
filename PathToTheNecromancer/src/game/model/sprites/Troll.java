package game.model.sprites;

import com.sun.prism.Texture;
/**
 * The class for trolls combat values
 * @author cux144
 *
 */
public class Troll extends  EnemySprites{
	/**
	 * sets up the troll for a texture
	 */
	private Texture TrollTexture;
	/**
	 * Give the troll the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Troll() {
		super.setBaseStats(11, 5, 10, 0, 110);
	}
}
