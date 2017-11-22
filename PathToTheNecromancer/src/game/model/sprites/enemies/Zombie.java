package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;

/**
 * The class for zombies combat values
 * @author cux144
 *
 */
public class Zombie extends EnemySprites{
	/**
	 * sets up the zombie for a texture
	 */
	private Texture ZombieTexture;
	/**
	 * Give the zombie the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Zombie(int x) {
		super.setBaseStats(5, 1, 1, 0, 3, x, 10);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
}
