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
	private Stats stats;
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
	 * The story stats for the character
	 */
	private StoryStats storyStats;

	/**
	 * Constructor for the player
	 */
	public Player() {
		super();
		this.storyStats = new StoryStats();
	}
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
		return this.stats.getLevel();
	}

	/**
	 * Set the player's current level
	 * 
	 * @param level
	 *            is the new level
	 */
	public void setLevel(int level) {
		this.stats.setLevel(level);
	}

	/**
	 * Get the player's current experience
	 * 
	 * @return the experience
	 */
	public int getExperience() {
		return this.stats.getExperience();
	}

	/**
	 * Set the player's current experience
	 * 
	 * @param experience
	 *            is the new experience
	 */
	public void setExperience(int experience) {
		this.stats.setExperience(experience);
	}

	/**
	 * Get the player's current strength
	 * 
	 * @return the strength
	 */
	public int getStrength() {
		return this.stats.getStrength();
	}

	/**
	 * Set the player's current strength
	 * 
	 * @param strength
	 *            is the new strength
	 */
	public void setStrength(int strength) {
		this.stats.setStrength(strength);
	}

	/**
	 * Get the player's current intelligence
	 * 
	 * @return the intelligence
	 */
	public int getIntelligence() {
		return this.stats.getIntelligence();
	}

	/**
	 * Set the player's current intelligence
	 * 
	 * @param intelligence
	 *            is the new intelligence
	 */
	public void setIntelligence(int intelligence) {
		this.stats.setIntelligence(intelligence);
	}

	/**
	 * Set the player's current dexterity
	 * 
	 * @return the dexterity
	 */
	public int getDexterity() {
		return this.stats.getDexterity();
	}

	/**
	 * Set the player's current dexterity
	 * 
	 * @param dexterity
	 *            is the new dexterity
	 */
	public void setDexterity(int dexterity) {
		this.stats.setDexterity(dexterity);
	}

	/**
	 * Get the player's current luck
	 * 
	 * @return the luck
	 */
	public int getLuck() {
		return this.stats.getLuck();
	}

	/**
	 * Set the player's current luck
	 * 
	 * @param luck
	 *            is the new luck
	 */
	public void setLuck(int luck) {
		this.stats.setLuck(luck);
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
		return this.stats.getMaxHealth();
	}

	/**
	 * Set the player's max health
	 * 
	 * @param the
	 *            player's new max health
	 */
	public void setHealth(int health) {
		this.stats.setMaxHealth(health);
	}

	public StoryStats getStoryStats() {
		return this.storyStats;
	}
	public void setStoryStats(StoryStats stats) {
		this.storyStats = stats;
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

	public void setBaseStats(int level, int experience, int strength, int intelligence, int dexterity, int luck, int maxHealth) {
		this.stats = new Stats(level,experience,strength,intelligence,dexterity,luck,maxHealth);
		
	}

	public void LevelUpStats(int level, int experience, int strength, int intelligence, int dexterity, int luck, int maxHealth) {
		this.stats.setLevel(level + this.stats.getLevel());
		this.stats.setExperience(experience + this.stats.getExperience());
		this.stats.setStrength(strength + this.stats.getStrength());
		this.stats.setIntelligence(intelligence + this.stats.getIntelligence());
		this.stats.setDexterity(dexterity + this.stats.getDexterity());
		this.stats.setLuck(luck + this.stats.getLuck());
		this.stats.setMaxHealth(maxHealth + this.stats.getMaxHealth());
		this.stats.setLevelUp(false);

	}
	
	public void isLevelUp(int experience ){
		this.stats.setExperience(experience + this.stats.getExperience());
		if(this.stats.getExperience() >= 30) {
			this.stats.setLevelUp(true);
			this.stats.setExperience(this.stats.getExperience() - 30);
		}
	}

	public boolean getLevelUp() {
		// TODO Auto-generated method stub
		return this.stats.isLevelUp();
	}
}