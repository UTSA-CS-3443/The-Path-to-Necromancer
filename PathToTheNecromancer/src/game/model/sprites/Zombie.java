package game.model.sprites;

import com.sun.prism.Texture;
/**
 * The class for zombies combat values
 * @author cux144
 *
 */
public class Zombie extends EnemySprites{
	/**
	 * sets up the zombie for a texture
	 */
	private Texture ZombieTexture;
	/**
	 * Give the zombie the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Zombie() {
		super.setBaseStats(8, 1, 1, 0, 140);
	}
}
