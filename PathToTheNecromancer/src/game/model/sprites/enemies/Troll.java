package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;

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
	public  Troll(int x) {
		super.setBaseStats(3, 0, 2, 0, 13, x, 15);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
}
