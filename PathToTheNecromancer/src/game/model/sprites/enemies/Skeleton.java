package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;

/**
 * The class for skeletons combat values
 * @author cux144
 *
 */
public class Skeleton extends EnemySprites{
	/**
	 * sets up the skeleton for a texture
	 */
	private Texture skeletonTexture;
	/**
	 * Give the skeleton the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Skeleton(int x) {
		super.setBaseStats(3, 5, 1, 1, 4, x,  15);
		this.skeletonTexture =  new Texture("EnemySprites/Skeleton.png");
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	public Texture getTexture() {
		return this.skeletonTexture;
	}
}
