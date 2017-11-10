package game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.model.PathToNecromancer;
import game.model.sprites.EnemySprites;
import game.model.sprites.player.Player;
import game.view.CombatScreen;
import game.view.Menu;
import game.view.PlayScreen;
import javaFX.model.Settings;

/**
 * Manages the connections between the different screens in the game, with
 * the PlayScreen as the base screen.
 * 
 * @author enigma-phi
 *
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
	 * Create and initialize important values in the ScreenManager
	 * @param screen is the main screen for the entire game
	 * @param game is the main game that the screens are running off of
	 */
	public ScreenManager(PathToNecromancer game) {
		this.game = game;
		this.mainScreen = new PlayScreen(this);
		this.currentScreen = this.mainScreen;
		this.mainInputProcessor = mainScreen.getInputProcessor();
		this.toMainScreen();
	}
	
	/**
	 * Set the menu as the current screen
	 */
	public void setMenu() {
		this.currentScreen = new Menu(this);
		this.setScreen();
	}
	public void setCombat() {
		this.currentScreen = new CombatScreen(this.mainScreen, this.game);
		this.setScreen();
	}
	
	/**
	 * Set the the screen to the current screen. Whichever screen
	 * you are working with now
	 */
	private void setScreen() {
		this.game.setScreen(this.currentScreen);
	}
	/**
	 * Return whichever screen you are on back to the main PlayScreen
	 */
	public void toMainScreen() {
		Screen tempScreen = this.currentScreen;
		this.currentScreen = this.mainScreen;
		Gdx.input.setInputProcessor(this.mainInputProcessor);
		this.setScreen();
		if(tempScreen != this.mainScreen && tempScreen != null) 
			tempScreen.dispose();
	}
	/**
	 * Get the player for the main game
	 * @return the player
	 */
	public Player getPlayer() {
		return this.mainScreen.getPlayer();
	}
	/**
	 * Get the viewport the game renders from
	 * @return the viewport
	 */
	public Viewport getViewport() {
		return this.mainScreen.getViewPort();
	}
	/**
	 * Render the current screen
	 * @param dt is the amount of time since the last render
	 */
	public void render(float dt) {
		this.currentScreen.render(dt);
	}

	/**
	 * Get the SpriteBatch for the entire game
	 * @return the SpriteBatch
	 */
	public SpriteBatch getBatch() {
		return this.game.getBatch();
	}
	/**
	 * Return the Settings for the main game
	 * @return the game's settings
	 */
	public Settings getSettings() {
		return this.game.getSettings();
	}
	/**
	 * Get an EnemySprite from the main game screen
	 * @return the enemy sprite
	 */
	public EnemySprites getEnemy() {
		return this.mainScreen.getEnemy();
	}
	
	/**
	 * Get the name of the current map 
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
}
