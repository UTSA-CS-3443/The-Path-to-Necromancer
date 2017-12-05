package game.controller;

import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;

/**
 * Manage the game's music
 * @author enigma-phi
 * @author HangedDragon96 added the old volume from settings
 */
public class MusicManager {
	/**
	 * The music's volume
	 */
	private float volume;
	/**
	 * The current volume
	 */
	private Music music;
	/**
	 * the volume set before mute was hit 
	 */
	private int previousVolume;
	/**
	 * 
	 */
	/**
	 * Constructor
	 */
	public MusicManager() {
		this.volume = 1;
	}
	/**
	 * Set the volume level for the music. The volume should be
	 * between 0 and 100.
	 * @param volume is the new volume level
	 */
	public void setVolume(int volume) {
		this.volume = volume / (float) 100;
		if(this.volume > 1)
			this.volume = 1;
		else if(this.volume < 0)
			this.volume = 0;
		if(this.music != null) {
			this.music.setVolume(volume);
		}
	}
	/**
	 * Set the current music
	 * @param music is the new music
	 */
	public void setMusic(Music music) {
		Music oldMusic = this.music;
		this.music = music;
		if(oldMusic != null)
			oldMusic.dispose();
		if(this.music != null) {
			this.music.play();
			this.music.setLooping(true);
			this.music.setVolume(volume);
		}
	}
	/**
	 * Get the current music
	 * @return the music
	 */
	public Music getMusic() {
		return this.music;
	}
	public int getPreviousVolume() {
		return previousVolume;
	}
	public void setPreviousVolume(int previousVolume) {
		this.previousVolume = previousVolume;
	}
	/**
	 * gets the current volume
	 * @return the current volume
	 */
	public float getVolume() {
		return volume;
	}
	
}
