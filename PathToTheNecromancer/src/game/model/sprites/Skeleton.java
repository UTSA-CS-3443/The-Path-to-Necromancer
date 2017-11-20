package game.model.sprites;

import com.sun.prism.Texture;
/**
 * The class for skeletons combat values
 * @author cux144
 *
 */
public class Skeleton extends EnemySprites{
	/**
	 * sets up the skeleton for a texture
	 */
	private Texture SkeletonTexture;
	/**
	 * Give the skeleton the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Skeleton() {
		super.setBaseStats(12, 4, 8, 11, 50);
	}
}
