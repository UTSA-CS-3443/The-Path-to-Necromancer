package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;

/**
 * The class for witches combat values
 * @author cux144
 *
 */
public class Witch extends EnemySprites{
	/**
	 * sets up the witch for a texture
	 */
	private Texture WitchTexture;
	/**
	 * Give the witch the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Witch(int x) {
		super.setBaseStats(6, 1, 3, 11, 5, x, 15);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
}
