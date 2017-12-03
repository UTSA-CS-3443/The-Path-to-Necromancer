package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;

/**
 * The class for ghosts combat values
 * @author cux144
 *
 */
public class Ghost extends EnemySprites  {
	/**
	 * sets up the ghost for a texture
	 */
	private Texture GhostTexture;
	/**
	 * Give the ghost the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  Ghost(int x) {
		super.setBaseStats(5, 4, 5, 00, 1,  x, 15);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	public Texture getTexture() {
		return this.GhostTexture =  new Texture("EnemySprites/Ghost.png");
	}
}
