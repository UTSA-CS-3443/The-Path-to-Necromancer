package game.model.sprites.player;

import game.model.sprites.CharacterSprites;

/**
 * The player class used throughout the game by the user.
 * 
 * @author enigma-phi
 *
 */
public abstract class Player extends CharacterSprites {
	/**
	 * The player's current level
	 */
	private int level;
	/**
	 * The player's current experience. After reaching a new level, the player's
	 * experience will be reset to zero.
	 */
	private int experience;

	/**
	 * The player's strength for physical attack and defense
	 */
	private int strength;

	/**
	 * The player's intelligence for magic attack and defense
	 */
	private int intelligence;

	/**
	 * The player's dexterity for intiative and reflex
	 */
	private int dexterity;

	/**
	 * The player's luck for critical hits
	 */
	private int luck;
	/**
	 * The player's max health
	 */
	private int maxHealth;
	/**
	 * The player's current health (for combat only)
	 */
	private int currentHealth;
	private int necEncounters;
	/**
	 * is the player a warrior
	 */
	private boolean isWarrior;
	/**
	 * is the player a rogue
	 */
	private boolean isRogue;
	/**
	 * is the player a mage
	 */
	private boolean isMage;

	/**
	 * Get the Character's Class
	 * 
	 * @return the class name
	 */
	public abstract String getCharClass();

	/**
	 * Get the player's current level
	 * 
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Set the player's current level
	 * 
	 * @param level
	 *            is the new level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Get the player's current experience
	 * 
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * Set the player's current experience
	 * 
	 * @param experience
	 *            is the new experience
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}

	/**
	 * Get the player's current strength
	 * 
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * Set the player's current strength
	 * 
	 * @param strength
	 *            is the new strength
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * Get the player's current intelligence
	 * 
	 * @return the intelligence
	 */
	public int getIntelligence() {
		return intelligence;
	}

	/**
	 * Set the player's current intelligence
	 * 
	 * @param intelligence
	 *            is the new intelligence
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	/**
	 * Set the player's current dexterity
	 * 
	 * @return the dexterity
	 */
	public int getDexterity() {
		return dexterity;
	}

	/**
	 * Set the player's current dexterity
	 * 
	 * @param dexterity
	 *            is the new dexterity
	 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	/**
	 * Get the player's current luck
	 * 
	 * @return the luck
	 */
	public int getLuck() {
		return luck;
	}

	/**
	 * Set the player's current luck
	 * 
	 * @param luck
	 *            is the new luck
	 */
	public void setLuck(int luck) {
		this.luck = luck;
	}

	/**
	 * Get the player's current velocity as the vector magnitude: square root(x^2 +
	 * y^2)
	 * 
	 * @return the player's velocity
	 */
	public double getVelocity() {
		return Math.sqrt(
				Math.pow(this.getBody().getLinearVelocity().x, 2) + Math.pow(this.getBody().getLinearVelocity().x, 2));
	}

	/**
	 * Return the player's max health
	 * 
	 * @return the max health
	 */
	public int getHealth() {
		return this.maxHealth;
	}

	/**
	 * Set the player's max health
	 * 
	 * @param the
	 *            player's new max health
	 */
	public void setHealth(int health) {
		this.maxHealth = health;
	}

	/**
	 * Get the player's current health
	 * 
	 * @return the player's current health
	 */
	public int getCurrentHealth() {
		return this.currentHealth;
	}

	/**
	 * Set the player's current health
	 * 
	 * @param health
	 *            to set it to
	 */
	public void setCurrentHealth(int health) {
		this.currentHealth = health;
	}

	public void setNecEncounters(int set) {
		this.necEncounters = set;
	}

	public int getNecEncounters() {
		return this.necEncounters;
	}

	public boolean isWarrior() {
		return isWarrior;
	}

	public void setWarrior(boolean isWarrior) {
		this.isWarrior = isWarrior;
	}

	public boolean isRogue() {
		return isRogue;
	}

	public void setRogue(boolean isRogue) {
		this.isRogue = isRogue;
	}

	public boolean isMage() {
		return isMage;
	}

	public void setMage(boolean isMage) {
		this.isMage = isMage;
	}
}