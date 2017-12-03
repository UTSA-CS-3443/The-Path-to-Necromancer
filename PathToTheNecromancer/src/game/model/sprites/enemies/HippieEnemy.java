package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;
/**
 * The setup for the Hippie combat values
 * @author cux144
 *
 */
public class HippieEnemy extends EnemySprites{
	/**
	 * sets up the Hippie for a texture
	 */
	private Texture HippieTexture;
	/**
	 * Give the bandit the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  HippieEnemy(int x) {
		super.setBaseStats(7, 3, 1, 0, 15, x, 40);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	public Texture getTexture() {
		return this.HippieTexture =  new Texture("EnemySprites/Hippie.png");
	}
}
