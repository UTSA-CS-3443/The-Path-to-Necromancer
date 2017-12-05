package game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.model.PathToNecromancer;
import game.model.Savestate;
import game.model.sprites.EnemySprites;
import game.model.sprites.player.Player;
import game.view.CombatScreen;
import game.view.DeathScreen;
import game.view.Menu;
import game.view.PlayScreen;
import javaFX.model.Settings;

/**
 * Manages the connections between the different screens in the game, with the
 * PlayScreen as the base screen.
 * 
 * @author enigma-phi
 *	@author HangedDragon96 added save
 */
public class ScreenManager {
	/**
	 * The main game the screens are running off of
	 */
	private PathToNecromancer game;
	/**
	 * The main PlayScreen for the entire game
	 */
	private PlayScreen mainScreen;
	/**
	 * The main InputProcessor for the entire game
	 */
	private InputProcessor mainInputProcessor;
	/**
	 * The CurrentScreen that the ScreenManager is rendering
	 */
	private Screen currentScreen;
	/**
	 * The MusicManager for managing the game's music
	 */
	private MusicManager musicManager;
	/**
	 * The current music playing
	 */
	private Music currentMusic;
	/**
	 * Whether or not the music needs to be returned to a previous state
	 */
	private boolean musicChanged;
	/**
	 * the current save for the game
	 */
	private Savestate save;
	/**
	 * Create and initialize important values in the ScreenManager
	 * 
	 * @param screen
	 *            is the main screen for the entire game
	 * @param game
	 *            is the main game that the screens are running off of
	 * @param save the current save
	 */
	public ScreenManager(PathToNecromancer game, Savestate save) {
		this.game = game;
		this.musicManager = new MusicManager();
		this.mainScreen = new PlayScreen(this, save);
		this.save = save;
		this.currentScreen = this.mainScreen;
		this.mainInputProcessor = mainScreen.getInputProcessor();
		this.toMainScreen();
	}

	/**
	 * Set the menu as the current screen
	 */
	public void setMenu() {
		this.currentScreen = new Menu(this,save);
		this.setScreen();
	}

	/**
	 * Set Combat as the current screen. Begin Combat.
	 */
	public void setCombat() {
		this.currentMusic = this.musicManager.getMusic();
		this.musicChanged = true;
		this.currentScreen = new CombatScreen(this.mainScreen, this.game);
		this.setScreen();
	}
	/**
	 * Set Combat as the current screen with a specific enemy. Begin Combat.
	 */
	public void setCombat(EnemySprites enemy) {
		this.currentMusic = this.musicManager.getMusic();
		this.musicChanged = true;
		this.currentScreen = new CombatScreen(this.mainScreen, this.game);//, enemy);
		this.setScreen();
	}

	/**
	 * Set the the screen to the current screen. Whichever screen you are working
	 * with now
	 */
	private void setScreen() {
		this.game.setScreen(this.currentScreen);
	}

	/**
	 * Return whichever screen you are on back to the main PlayScreen
	 */
	public void toMainScreen() {
		if(this.musicChanged) {
			this.musicChanged = false;
			this.musicManager.setMusic(this.currentMusic);
		}
		Screen tempScreen = this.currentScreen;
		this.currentScreen = this.mainScreen;
		Gdx.input.setInputProcessor(this.mainInputProcessor);
		this.setScreen();
		if (tempScreen != this.mainScreen && tempScreen != null)
			tempScreen.dispose();
	}
	
	/**
	 * End the game as the player has just died
	 */
	public void endGame() {
		this.currentScreen = new DeathScreen(this);
		this.setScreen();
	}
	/**
	 * Reset the input processor to the main processor
	 */
	public void resetInput() {
		Gdx.input.setInputProcessor(this.mainInputProcessor);
	}

	/**
	 * Set whether or not dialogue is occurring
	 * 
	 * @param b
	 *            is a boolean relating to dialogue
	 */
	public void setDialogue(boolean b) {
		this.mainScreen.setDialogue(b);
	}

	/**
	 * Get the player for the main game
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return this.mainScreen.getPlayer();
	}

	/**
	 * Get the viewport the game renders from
	 * 
	 * @return the viewport
	 */
	public Viewport getViewport() {
		return this.mainScreen.getViewPort();
	}

	/**
	 * Render the current screen
	 * 
	 * @param dt
	 *            is the amount of time since the last render
	 */
	public void render(float dt) {
		this.currentScreen.render(dt);
	}

	/**
	 * Get the SpriteBatch for the entire game
	 * 
	 * @return the SpriteBatch
	 */
	public SpriteBatch getBatch() {
		return this.game.getBatch();
	}

	/**
	 * Return the Settings for the main game
	 * 
	 * @return the game's settings
	 */
	public Settings getSettings() {
		return this.game.getSettings();
	}

	/**
	 * Get an EnemySprite from the main game screen
	 * 
	 * @return the enemy sprite
	 */
	public EnemySprites getEnemy() {
		return this.mainScreen.getEnemy();
	}

	/**
	 * Get the name of the current map
	 * 
	 * @return the map's name
	 */
	public String getMapName() {
		return this.mainScreen.getMapName();
	}

	/**
	 * Dispose of the screen manager
	 */
	public void dispose() {
		this.mainScreen.dispose();
	}

	/**
	 * Get the map manager for the classes
	 */
	public MapManager getMapManager() {
		return this.mainScreen.getMapManager();
	}
	/**
	 * Get the musicManager for managing the game's music
	 * @return the musicManager
	 */
	public MusicManager getMusicManager() {
		return this.musicManager;
	}
	/**
	 * sets the save object for saving the game
	 * @param save the current save file
	 */
	public void setSave(Savestate save) {
		// TODO Auto-generated method stub
		this.save = save;
		
	}
	/**
	 * gets the current save
	 * @return the current save state of the game
	 */
	public Savestate getSave() {
		// TODO Auto-generated method stub
		return this.save;
	}
	
}
