package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;
/**
 * sets up the combat values for the Fighter boss
 * @author cux144
 *
 */
public class FighterEnemy extends EnemySprites{
	/**
	 * sets up the Fighter for a texture
	 */
	private Texture FighterTexture;
	/**
	 * Give the bandit the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  FighterEnemy(int x) {
		super.setBaseStats(6, 0, 2, 0, 10, x, 25);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	
	public Texture getTexture() {
		return this.FighterTexture =  new Texture("EnemySprites/Fighter.png");
	}
}
