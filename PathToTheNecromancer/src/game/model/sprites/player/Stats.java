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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public boolean isLevelUp() {
		return isLevelUp;
	}

	public void setLevelUp(boolean isLevelUp) {
		this.isLevelUp = isLevelUp;
	}

}
