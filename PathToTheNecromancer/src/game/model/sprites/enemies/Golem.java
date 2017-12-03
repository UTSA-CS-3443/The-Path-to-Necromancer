package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;

/**
 * The class for golems combat values
 * @author cux144
 *
 */
public class Golem extends EnemySprites  {
	/**
	 * sets up the golem for a texture
	 */
	private Texture GolemTexture;
	/**
	 * Give the golem the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Golem(int x) {
		super.setBaseStats(1, 4, 1, 0, 10, x, 10);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	public Texture getTexture() {
		return this.GolemTexture =  new Texture("EnemySprites/Golem.png");
	}
}
