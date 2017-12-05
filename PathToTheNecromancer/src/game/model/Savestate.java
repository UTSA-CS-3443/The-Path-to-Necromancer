package game.model;

import java.io.File;
import java.io.Serializable;

import game.model.sprites.player.Stats;
import game.model.sprites.player.StoryStats;
import javaFX.model.Settings;
/**
 * save state for the game
 * @author HangedDragon96
 *
 */
public class Savestate implements Serializable {
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The id of the save file
	 */
	private String saveLabel;
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
	 * players current stats
	 */
	private Stats stat;
	/**
	 * the players current story stage
	 */
	private StoryStats story;
	/**
	 * Constructor
	 * @param playerX players x coordinate 
	 * @param playerY players y coordinate 
	 * @param map	the name of the current map
	 * @param setting the game settings
	 * @param isWarrior boolean for if the player is a warrior
	 * @param isRogue   boolean for if the player is a rogue
	 * @param isMage    boolean for if the player is a mage
	 */
	public Savestate(float playerX, float playerY, String map, Settings setting, boolean isWarrior, boolean isRogue,
			boolean isMage, Stats stat, StoryStats story) {
		super();
		this.setting = setting;
		generateSaveId(setting);
		this.playerX = playerX;
		this.playerY = playerY;
		this.map = map;
		this.isWarrior = isWarrior;
		this.isRogue = isRogue;
		this.isMage = isMage;
		this.stat = stat;
		this.story = story;
	}
	/**
	 * generates a save label
	 * @param setting the current settings to set new game to false
	 */
	private void generateSaveId(Settings setting) {
		File theDir = new File("Saves");
		if(setting == null) {
			setting = new Settings(false, 100, 100,  Difficulty.Easy);
		}
		this.setting.setNewGame(false);
		
		if(!theDir.exists()) {   
			   this.saveLabel = "save1.txt";
			   return;		
		}
			File[] listOfFiles = theDir.listFiles();
			if(listOfFiles.length == 0) {
				this.saveLabel = "save1.txt";
				return;
			} else if (listOfFiles.length == 5) {
				this.saveLabel = "save5.txt";
			}
			for(int i = 0; i < listOfFiles.length; i++) {
				if(("save"+ (i + 1) +".txt").equals(listOfFiles[i].getName())) {
					continue;
				}
				this.saveLabel = "save"+ (i + 1) +".txt";
				break;
			}
			if(this.saveLabel == null){
			   this.saveLabel = "save"+ (listOfFiles.length+ 1) +".txt";
			}
			
		 
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
		if(playerY < 50)
			return (int) Math.ceil(playerY);
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
	/**
	 * gets the id of the save file
	 * @return the id of the save file
	 */
	public String getSaveLabel() {
		return saveLabel;
	}
	/**
	 * gets the current story
	 * @return the current story
	 */
	public StoryStats getStory() {
		return story;
	}
	/**
	 * sets the current story
	 * @param story the current story
	 */
	public void setStory(StoryStats story) {
		this.story = story;
	}
	/**
	 * gets the player's current stats
	 * @return current player stats
	 */
	public Stats getStat() {
		return stat;
	}
	/**
	 * sets the player's current stat
	 * @param stat the player's current stat
	 */
	public void setStat(Stats stat) {
		this.stat = stat;
	}
	
}

