package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.controller.MenuController;
import game.controller.ScreenManager;
import game.model.sprites.player.Player;
import javaFX.model.Settings;

/**
 * The in-game menu screen. Contains stages for the character's stats, the
 * settings screen, the controls, and the save and quit menu.
 * 
 * @author enigma-phi
 *
 */
public class Menu implements Screen {
	/**
	 * The player for the game
	 */
	private Player player;
	/**
	 * The current stage for the menu
	 */
	private Stage stage;
	/**
	 * Controls the stage the menu is currently on.
	 */
	private MenuController controller;
	/**
	 * Manages the transition between different screens
	 */
	private ScreenManager screenManager;
	/**
	 * The viewport for the screen
	 */
	private Viewport viewport;

	/**
	 * Create the menu screen
	 * 
	 * @param screen
	 *            is the PlayScreen the user is on
	 * @param game
	 *            is the main game class
	 */
	public Menu(ScreenManager screenManager) {
		this.screenManager = screenManager;
		this.player = screenManager.getPlayer();
		this.viewport = screenManager.getViewport();
		this.controller = new MenuController(this);
	}

	/**
	 * Returns the user to the PlayScreen and resets the input processor
	 */
	public void returnToGame() {
		screenManager.toMainScreen();
	}

	/**
	 * Unimplemented
	 */
	@Override
	public void dispose() {
		this.controller.dispose();
	}

	/**
	 * Unimplemented
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/**
	 * Unimplemented
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/**
	 * Render the Menu Screen
	 */
	@Override
	public void render(float delta) {
		// clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

	}

	/**
	 * Resize the view port
	 */
	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height);
	}

	/**
	 * Unimplemented
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/**
	 * Unimplemented
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	/**
	 * Set the stage for the menu class
	 * 
	 * @param stage
	 *            is the stage to set it to
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Get the player character for the game
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Get the viewport for the game
	 * 
	 * @return the viewport
	 */
	public Viewport getViewPort() {
		return this.viewport;
	}

	/**
	 * Get the name of the current map
	 * 
	 * @return the name of the current map
	 */
	public String getMapName() {
		return this.screenManager.getMapName();
	}
	/**
	 * Get the settings for the game
	 * 
	 * @return the settings object for the game
	 */
	public Settings getSettings() {
		return this.screenManager.getSettings();
	}

}
