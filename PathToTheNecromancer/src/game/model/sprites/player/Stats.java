package game.model.sprites.player;

import java.io.Serializable;

/**
 * Store different stats
 * 
 * @author HangedDragon96
 * @author enigma-phi
 */
public class Stats implements Serializable  {
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 1L;
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
	 * The player's truth value for if they will level up
	 */
	private boolean isLevelUp;
	/**
	 * Constructor for the stats object. Set different statistics for the character.
	 * 
	 * @param level
	 * @param experience
	 * @param strength
	 * @param intelligence
	 * @param dexterity
	 * @param luck
	 * @param maxHealth
	 * @param currentHealth
	 */
	public Stats(int level, int experience, int strength, int intelligence, int dexterity, int luck, int maxHealth) {
		super();
		this.level = level;
		this.experience = experience;
		this.strength = strength;
		this.intelligence = intelligence;
		this.dexterity = dexterity;
		this.luck = luck;
		this.maxHealth = maxHealth;
	}
/**
 * gets the current player level
 * @return the current level
 */
	public int getLevel() {
		return level;
	}
/**
 * sets the current player level
 * @param level the new level
 */
	public void setLevel(int level) {
		this.level = level;
	}
/**
 * gets the players current experience amount
 * @return current experience
 */
	public int getExperience() {
		return experience;
	}
/**
 * sets the current player experience
 * @param experience the new experience
 */
	public void setExperience(int experience) {
		this.experience = experience;
	}
/**
 * gets the current player strength
 * @return the current strength for player
 */
	public int getStrength() {
		return strength;
	}
	/**
	 * sets the players current strength
	 * @param strength the strength value
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}
	/**
	 * gets the current player intelligence
	 * @return current intelligence
	 */
	public int getIntelligence() {
		return intelligence;
	}
	/**
	 * sets the players current intelligence
	 * @param intelligence the new intelligence value
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	/**
	 * gets the players current dexterity amount
	 * @return the current dexterity
	 */
	public int getDexterity() {
		return dexterity;
	}
	/**
	 * sets the current player dexterity
	 * @param dexterity the new dexterity value
	 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	/**
	 * gets the player's current luck
	 * @return the current luck value
	 */
	public int getLuck() {
		return luck;
	}
	/**
	 * sets the player's current luck
	 * @param luck the new luck value
	 */
	public void setLuck(int luck) {
		this.luck = luck;
	}
	/**
	 * gets the current max health value for player
	 * @return the current max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * sets the players current max health
	 * @param maxHealth the new max health value
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	/**
	 * if the player has leveled up
	 * @return true if he has leveled up false otherwise
	 */
	public boolean isLevelUp() {
		return isLevelUp;
	}
	/**
	 * sets if the player has leveled up or not
	 * @param isLevelUp has the player leveled or not
	 */
	public void setLevelUp(boolean isLevelUp) {
		this.isLevelUp = isLevelUp;
	}

}
