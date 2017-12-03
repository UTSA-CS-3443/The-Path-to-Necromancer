package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;

/**
 * The class for goblins combat values
 * @author cux144
 *
 */
public class Goblin extends EnemySprites{
	/**
	 * sets up the goblin for a texture
	 */
	private Texture GoblinTexture;
	/**
	 * Give the goblin the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Goblin(int x) {
		super.setBaseStats(1, 1, 5, 1, 2, x, 5);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	public Texture getTexture() {
		return this.GoblinTexture =  new Texture("EnemySprites/Goblin.png");
	}
}
