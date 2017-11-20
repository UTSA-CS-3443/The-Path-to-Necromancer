package game.model.sprites;

import com.sun.prism.Texture;
/**
 * The class for ghosts combat values
 * @author cux144
 *
 */
public class Ghost extends EnemySprites  {
	/**
	 * sets up the ghost for a texture
	 */
	private Texture GhostTexture;
	/**
	 * Give the ghost the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Ghost() {
		super.setBaseStats(0, 25, 5, 10, 40);
	}
}
