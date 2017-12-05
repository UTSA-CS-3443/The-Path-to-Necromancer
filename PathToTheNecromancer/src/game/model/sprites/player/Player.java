package game.model.sprites.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

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
		return Math.sqrt(Math.pow(this.getBody().getLinearVelocity().x, 2) + Math.pow(this.getBody().getLinearVelocity().x, 2));
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

	/**
	 * Set the player up with Necromancer Textures
	 */
	public void setNecromancerTextures() {
		Texture necromancerTexture = new Texture("CharacterSprites/Necromancer.png");
		int width = 50;
		int height = 51;

		// Set the default standing region of the Necromancer.
		super.setStandingRegion(new TextureRegion(necromancerTexture, 0, 0, width, height));
		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();

		// Set the animations for moving Down on the map
		frames.add(new TextureRegion(necromancerTexture, 0, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, 0, height, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Up on the map
		frames.add(new TextureRegion(necromancerTexture, width, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width, height, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Left on the map
		frames.add(new TextureRegion(necromancerTexture, width * 2, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 2, height, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Right on the map
		frames.add(new TextureRegion(necromancerTexture, width * 3, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 3, height, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Down-Left on the map
		frames.add(new TextureRegion(necromancerTexture, width * 4, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 4, height, width, height));
		super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Down-Right on the map
		frames.add(new TextureRegion(necromancerTexture, width * 5, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 5, height, width, height));
		super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Up-Right on the map
		frames.add(new TextureRegion(necromancerTexture, width * 6, 0, width, height));
		frames.add(new TextureRegion(necromancerTexture, width * 6, height, width, height));
		super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// Set the animations for moving Up-Left on the map
		frames.add(new TextureRegion(necromancerTexture, 0, height * 2, width, height));
		frames.add(new TextureRegion(necromancerTexture, width, height * 2, width, height));
		super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		super.setAnimationSpeed(5);
		super.setRunningSpeed(4);
		setBounds(0, 0, 24, 30);
	}

	/**
	 * Get the player's story stats
	 * @return the story stats
	 */
	public StoryStats getStoryStats() {
		return this.storyStats;
	}

	/**
	 * Set the player's story stats
	 * @param stats is the player's new story stats
	 */
	public void setStoryStats(StoryStats stats) {
		this.storyStats = stats;
		if (stats.isHasNecroTextures())
			this.setNecromancerTextures();
	}
	
	/**
	 * Get whether or not the player is a Warrior
	 * @return whether or not a player is a Warrior
	 */
	public boolean isWarrior() {
		return isWarrior;
	}

	/**
	 * Set whether or not the player is a Warrior
	 * @param isWarrior whether or not the player is a Warrior
	 */
	public void setWarrior(boolean isWarrior) {
		this.isWarrior = isWarrior;
	}

	/**
	 * Get whether or not the player is a Rogue
	 * @return whether or not a player is a Rogue
	 */
	public boolean isRogue() {
		return isRogue;
	}
	/**
	 * Set whether or not the player is a Rogue
	 * @param isRogue whether or not the player is a Rogue
	 */
	public void setRogue(boolean isRogue) {
		this.isRogue = isRogue;
	}

	/**
	 * Get whether or not the player is a Mage
	 * @return whether or not a player is a Mage
	 */
	public boolean isMage() {
		return isMage;
	}

	/**
	 * Set whether or not the player is a Mage
	 * @param isMage whether or not the player is a Mage
	 */
	public void setMage(boolean isMage) {
		this.isMage = isMage;
	}

	/**
	 * Set the player's base stats
	 * @param level is the base level
	 * @param experience is the base experience
	 * @param strength is the base strength
	 * @param intelligence is the base intelligence
	 * @param dexterity is the base dexterity
	 * @param luck is the base luck
	 * @param maxHealth is the base maxHealth
	 */
	public void setBaseStats(int level, int experience, int strength, int intelligence, int dexterity, int luck, int maxHealth) {
		this.stats = new Stats(level, experience, strength, intelligence, dexterity, luck, maxHealth);

	}

	/**
	 * The stats for leveling up
	 * @param level is the increase in level
	 * @param experience is the increase in experience
	 * @param strength is the increase in strength
	 * @param intelligence is the increase in intelligence
	 * @param dexterity is the increase in dexterity
	 * @param luck is the increase in luck
	 * @param maxHealth is the increase in maxHeath
	 */
	public void levelUpStats(int level, int experience, int strength, int intelligence, int dexterity, int luck, int maxHealth) {
		this.stats.setLevel(level + this.stats.getLevel());
		this.stats.setExperience(0);
		this.stats.setStrength(strength + this.stats.getStrength());
		this.stats.setIntelligence(intelligence + this.stats.getIntelligence());
		this.stats.setDexterity(dexterity + this.stats.getDexterity());
		this.stats.setLuck(luck + this.stats.getLuck());
		this.stats.setMaxHealth(maxHealth + this.stats.getMaxHealth());
		this.stats.setLevelUp(false);

	}

	/**
	 * The method called when combat ends
	 * @param experience is the player's experience gain
	 */
	public void endCombat(int experience) {
		this.stats.setExperience(experience + this.stats.getExperience());
		if (this.stats.getExperience() >= 30) {
			this.stats.setLevelUp(true);
			this.stats.setExperience(this.stats.getExperience() - 30);
		}
	}

	/**
	 * Get whether or not the player levels up
	 * @return whether or not the player levels up
	 */
	public boolean getLevelUp() {
		return this.stats.isLevelUp();
	}

	/**
	 * Get the player's stats
	 * @return the player's stats
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * Set the player's stats
	 * @param stats is the player's stats
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}
}