	package javaFX.model;

import java.io.Serializable;

/**
 * Setting for path of the Necromancer
 * @author HangedDragon96
 *
 */
public class Settings implements Serializable{
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * New game boolean
	 */
	private boolean isNewGame;

	/**
	 * mute control
	 */
	private boolean mute;
	
	/**
	 * brightness
	 */
	private float brightness;
	/**
	 * in game music sound
	 */
	private int musicSound;


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
	 * @param mute If the game is mute
	 * @param brightness The brightness for the game
	 * @param musicSound The volume of the must
	 * @param difficulty The difficulty 
	 */
	public Settings( boolean mute,  float brightness, int musicSound, Difficulty difficulty) {
		
		this.mute = mute;
		this.brightness = brightness;
		this.musicSound = musicSound;
		this.difficulty = difficulty;
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
	 * gets brightness
	 * @return the brightness level for the game
	 */
	public float getBrightness() {
		return brightness;
	}
	/**
	 * sets brightness
	 * @param brightness the level of brightness
	 */
	public void setBrightness(float brightness) {
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
