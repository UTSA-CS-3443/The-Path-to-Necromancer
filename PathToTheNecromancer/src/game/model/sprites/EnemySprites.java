package game.model.sprites;
/**
 * The class for Enemies and used for getting  the  enemies values for combat
 * @author cux144
 *
 */
public abstract class EnemySprites extends GameSprites {
	
	/**
	 * The enemy's strength for physical damage
	 */
	private int strength;
	/**
	 * The enemy's intelligence for magic attack and defense
	 */
	private int intelligence;
	/**
	 * The enemy's dexterity for intiative and reflex
	 */
	private int dexterity;
	/**
	 * The enemy's luck for critical hits
	 */
	private int luck;
	/**
	 * The enemy's max health
	 */
	private int maxHealth;
	/**
	 * The enemy's current health
	 */
	private int currentHealth;
	/**
	 * Sets up the stats for the enemy's
	 * @param str
	 * @param intel
	 * @param dex
	 * @param lck
	 * @param health
	 */
	public void setBaseStats(int str, int intel, int dex, int lck, int health){
	   	this.setIntelligence(intel);
	   	this.setDexterity(dex);
	   	this.setStrength(str);
	   	this.setLuck(lck);
	   	this.setCurrentHealth(health);
	   	this.setMaxHealth(health);
	}
	/**
	 * gets the intelligence of the enemy
	 * @return
	 */
	public int getIntelligence() {
		return intelligence;
	}
	/**
	 * sets the intelligence of the enemy
	 * @param intelligence
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	/**
	 * gets the strength of the enemy
	 * @return
	 */
	public int getStrength() {
		return strength;
	}
	/**
	 * sets the strength of the enemy
	 * @param strength
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}
	/**
	 * gets the dexterity of the enemy
	 * @return
	 */
	public int getDexterity() {
		return dexterity;
	}
	/**
	 * sets the dexterity of the enemy
	 * @param dexterity
	 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	/**
	 * gets the luck of the enemy
	 * @return
	 */
	public int getLuck() {
		return luck;
	}
	/**
	 * sets the luck of the enemy
	 * @param luck
	 */
	public void setLuck(int luck) {
		this.luck = luck;
	}
	/**
	 * gets the current health of the enemy
	 * @return
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}
	/**
	 * sets the current health of the enemy
	 * @param currentHealth
	 */
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	/**
	 * gets the max health of the enemy
	 * @return
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * sets the max health of the enemy
	 * @param maxHealth
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

}
