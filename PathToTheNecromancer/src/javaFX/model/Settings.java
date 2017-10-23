package javaFX.model;

public class Settings {
	private boolean isNewGame;
	private int gameSound;
	private boolean mute;
	private boolean lineSpeed;
	private int brightness;
	private int musicSound;
	private boolean autoSkip;
	private boolean vSync;
	private Difficulty difficulty;
	public Settings() {
		
	}
	
	public Settings(int gameSound, boolean mute, boolean lineSpeed, int brightness, int musicSound, boolean autoSkip,
			boolean vSync, Difficulty difficulty) {
		super();
		this.gameSound = gameSound;
		this.mute = mute;
		this.lineSpeed = lineSpeed;
		this.brightness = brightness;
		this.musicSound = musicSound;
		this.autoSkip = autoSkip;
		this.vSync = vSync;
		this.difficulty = difficulty;
	}

	public int getGameSound() {
		return gameSound;
	}

	public void setGameSound(int gameSound) {
		this.gameSound = gameSound;
	}

	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public boolean getLineSpeed() {
		return lineSpeed;
	}

	public void setLineSpeed(boolean lineSpeed) {
		this.lineSpeed = lineSpeed;
	}

	public int getBrightness() {
		return brightness;
	}

	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}

	public int getMusicSound() {
		return musicSound;
	}

	public void setMusicSound(int musicSound) {
		this.musicSound = musicSound;
	}

	public boolean isAutoSkip() {
		return autoSkip;
	}

	public void setAutoSkip(boolean autoSkip) {
		this.autoSkip = autoSkip;
	}

	public boolean isvSync() {
		return vSync;
	}

	public void setvSync(boolean vSync) {
		this.vSync = vSync;
	}

	public boolean isNewGame() {
		// TODO Auto-generated method stub
		return this.isNewGame;
	}

	public Difficulty getDifficulty() {
		// TODO Auto-generated method stub
		return this.difficulty;
	}


	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void setNewGame(boolean isNewGame) {
		this.isNewGame = isNewGame;
	}


}
