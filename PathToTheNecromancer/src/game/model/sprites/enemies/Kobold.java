package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;

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
	public  Kobold(int x) {
		super.setBaseStats(1, 2, 3, 2, 4, x, 10);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
}
