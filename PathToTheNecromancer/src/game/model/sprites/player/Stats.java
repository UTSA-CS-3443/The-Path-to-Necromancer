package game.model.sprites.player;

public class Stats {
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
	public Stats(int level, int experience, int strength, int intelligence, int dexterity, int luck, int maxHealth,
			int currentHealth, int necEncounters) {
		super();
		this.level = level;
		this.experience = experience;
		this.strength = strength;
		this.intelligence = intelligence;
		this.dexterity = dexterity;
		this.luck = luck;
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		this.necEncounters = necEncounters;
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
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	public int getNecEncounters() {
		return necEncounters;
	}
	public void setNecEncounters(int necEncounters) {
		this.necEncounters = necEncounters;
	}
	
	
}
