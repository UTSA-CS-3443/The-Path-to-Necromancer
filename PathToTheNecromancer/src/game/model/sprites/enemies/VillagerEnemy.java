package game.model.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

import game.model.sprites.EnemySprites;
/**
 * setup for the Villagers combat values
 * @author cux144
 *
 */
public class VillagerEnemy extends EnemySprites{
	/**
	 * sets up the Villager for a texture
	 */
	private Texture VillageTexture;
	/**
	 * Give the Villager the stats potential for  future where we multiply the stats by the difficulty
	 */
	public  VillagerEnemy(int x) {
		super.setBaseStats(4, 5, 4, 0, 12, x, 60);
	}
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	public Texture getTexture() {
		return this.VillageTexture =  new Texture("EnemySprites/Villager.png");
	}
}
