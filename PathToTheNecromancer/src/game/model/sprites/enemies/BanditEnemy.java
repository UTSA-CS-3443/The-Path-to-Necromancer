package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;

/**
 * The class for bandits combat values
 * @author cux144
 *
 */
public class BanditEnemy extends EnemySprites {
	/**
	 * sets up the bandit for a texture
	 */
	private Texture BanditTexture;
	/**
	 * Give the bandit the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  BanditEnemy(int x) {
		super.setBaseStats(4, 1, 4, 1, 5, x, 5);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	
	public Texture getTexture() {
		return this.BanditTexture =  new Texture("EnemySprites/Bandit.png");
	}
}
