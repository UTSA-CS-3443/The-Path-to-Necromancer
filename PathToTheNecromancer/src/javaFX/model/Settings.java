package javaFX.model;

import java.io.Serializable;

/**
 * Setting for path of the Necromancer
 * @author HangedDragon96
 *
 */
public class Settings implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * New game boolean
	 */
	private boolean isNewGame;
	/**
	 * game sound
	 */
	private int gameSound;
	/**
	 * mute control
	 */
	private boolean mute;
	/**
	 * line speed control
	 */
	private boolean lineSpeed;
	/**
	 * brightness
	 */
	private int brightness;
	/**
	 * in game music sound
	 */
	private int musicSound;
	/**
	 * auto skip control
	 */
	private boolean autoSkip;

	/** 
	 * the difficulty
	 */
	private Difficulty difficulty;
	/**
	 * constructor
	 */
	public Settings() {
		
	}
	/**
	 * constructor
	 * @param gameSound The volume of the game
	 * @param mute If the game is mute
	 * @param linespeed Is the line speed moving faster
	 * @param brightness The brightness for the game
	 * @param musicSound The volume of the must
	 * @param autoSkip Is auto skip turned on
	 * @param vSync Is vSync turned on
	 * @param difficulty The difficulty 
	 */
	public Settings(int gameSound, boolean mute, boolean lineSpeed, int brightness, int musicSound, boolean autoSkip, Difficulty difficulty) {
		this.gameSound = gameSound;
		this.mute = mute;
		this.lineSpeed = lineSpeed;
		this.brightness = brightness;
		this.musicSound = musicSound;
		this.autoSkip = autoSkip;
	
		this.difficulty = difficulty;
	}
	/**
	 * gets gameSound
	 * @return returns the sound level for the game
	 */
	public int getGameSound() {
		return gameSound;
	}
	/**
	 * sets the game sound
	 * @param gameSound
	 */
	public void setGameSound(int gameSound) {
		this.gameSound = gameSound;
	}
	/**
	 * gets mute
	 * @return true if mute is set false otherwise
	 */
	public boolean isMute() {
		return mute;
	}
	/**
	 * sets mute 
	 * @param mute a boolean if mute is set
	 */
	public void setMute(boolean mute) {
		this.mute = mute;
	}
	/**
	 * checks if line speed is checked
	 * @return true if line speed is checked false otherwise
	 */
	public boolean getLineSpeed() {
		return lineSpeed;
	}
	/**
	 * sets line speed 
	 * @param linespeed a boolean if line speed is set
	 */
	public void setLineSpeed(boolean lineSpeed) {
		this.lineSpeed = lineSpeed;
	}
	/**
	 * gets brightness
	 * @return the brightness level for the game
	 */
	public int getBrightness() {
		return brightness;
	}
	/**
	 * sets brightness
	 * @param brightness the level of brightness
	 */
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	/**
	 * gets the music sound
	 * @return  the music sound level
	 */
	public int getMusicSound() {
		return musicSound;
	}
	/**
	 * sets musicSound
	 * @param musicSound the level of music for the game
	 */
	public void setMusicSound(int musicSound) {
		this.musicSound = musicSound;
	}
	/**
	 * gets autoSkip
	 * @return true if autoSkip is set false otherwise
	 */
	public boolean isAutoSkip() {
		return autoSkip;
	}
	/**
	 * sets autoSkip
	 * @param autoSkip a boolean if autoSkip is set
	 */
	public void setAutoSkip(boolean autoSkip) {
		this.autoSkip = autoSkip;
	}
	

	/**
	 * gets difficulty
	 * @return The difficulty of the game
	 */
	public Difficulty getDifficulty() {		
		return this.difficulty;
	}

	/**
	 * sets the difficulty 
	 * @param difficulty The difficulty for the game
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	/**
	 * gets isNewGame
	 * @return true if isNewGame is set false otherwise
	 */
	public boolean isNewGame() {
		return this.isNewGame;
	}
	
	/**
	 * sets isNewGame 
	 * @param isNewGame if its a new game or not
	 */
	public void setNewGame(boolean isNewGame) {
		this.isNewGame = isNewGame;
	}


}
