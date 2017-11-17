package game.model;

import java.io.Serializable;

import javaFX.model.Settings;

public class Savestate implements Serializable {
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The player's x coordinate
	 */
	private float playerX;
	/**
	 * The player's Y coordinate
	 */
	private float playerY;
	/**
	 * The name of the current map
	 */
	private String map;
	/**
	 * The current game settings
	 */
	private Settings setting;
	/**
	 * Boolean for if the player is a warrior
	 */
	private boolean isWarrior;
	/**
	 * Boolean for if the player is a rogue
	 */
	private boolean isRogue;
	/**
	 * Boolean for if the player is a mage
	 */
	private boolean isMage;
	/**
	 *  Constructor
	 * @param playerX players x coordinate 
	 * @param playerY players y coordinate 
	 * @param map	the name of the current map
	 * @param setting the game settings
	 * @param isWarrior boolean for if the player is a warrior
	 * @param isRogue   boolean for if the player is a rogue
	 * @param isMage    boolean for if the player is a mage
	 */
	public Savestate(float playerX, float playerY, String map, Settings setting, boolean isWarrior, boolean isRogue,
			boolean isMage) {
		super();
		this.playerX = playerX;
		this.playerY = playerY;
		this.map = map;
		this.setting = setting;
		this.isWarrior = isWarrior;
		this.isRogue = isRogue;
		this.isMage = isMage;
	}
	/*
	 * Gets the player's X  coordinate 
	 *  @return the player's x value
	 */
	public int getplayerX() {
		return (int) playerX;
	}
	/**
	 * sets the players current x value
	 * @param playerX the value of the players x position
	 */
	public void setplayerX(float playerX) {
		this.playerX = playerX;
	}
	/*
	 * Gets the player's Y coordinate 
	 * @return the player's x value
	 */
	public int getplayerY() {
		return (int) playerY;
	}
	/**
	 * sets the players current y value
	 * @param playerY the value of the players x position
	 */
	public void setplayerY(float playerY) {
		this.playerY = playerY;
	}
	/**
	 * gets the current map name
	 * @return the current map name
	 */
	public String getMap() {
		return map;
	}
	/**
	 * sets the current map name
	 * @param map the current map name
	 */
	public void setMap(String map) {
		this.map = map;
	}
	/**
	 * gets the current game settings
	 * @return the current game settings
	 */
	public Settings getSetting() {
		return setting;
	}
	/**
	 * sets the current game settings
	 * @param setting the current setting for the game
	 */
	public void setSetting(Settings setting) {
		this.setting = setting;
	}
	/**
	 * if the player is a warrior
	 * @return true if player is a warrior false if not
	 */
	public boolean isWarrior() {
		return isWarrior;
	}
	/**
	 * sets the is warrior boolean
	 * @param isWarrior if the player is a warrior
	 */
	public void setWarrior(boolean isWarrior) {
		this.isWarrior = isWarrior;
	}
	/**
	 * if the player is a rogue
	 * @return true if player is a rogue false if not
	 */
	public boolean isRogue() {
		return isRogue;
	}
	/**
	 * sets the is rogue boolean
	 * @param isRogue if the player is a rogue
	 */
	public void setRogue(boolean isRogue) {
		this.isRogue = isRogue;
	}
	/**
	 * if the player is a mage
	 * @return true if player is a mage false if not
	 */
	public boolean isMage() {
		return isMage;
	}
	
	/**
	 * sets the isMage boolean
	 * @param isMage if the player is a mage
	 */
	public void setMage(boolean isMage) {
		this.isMage = isMage;
	}
	
}

