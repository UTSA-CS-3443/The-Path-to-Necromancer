package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;
/**
 * Sets up the Necromancer's combat values
 * @author cux144
 *
 */
public class Necromancer extends EnemySprites{
	/**
	 * sets up the Necromancer for a texture
	 */
	private Texture NecromancerTexture;
	/**
	 * Give the bandit the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Necromancer(int x) {
		super.setBaseStats(5, 5, 1, 0, 20, x, 100);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	
	public Texture getTexture() {
		return this.NecromancerTexture =  new Texture("EnemySprites/Necromancer2.png");
	}
}
