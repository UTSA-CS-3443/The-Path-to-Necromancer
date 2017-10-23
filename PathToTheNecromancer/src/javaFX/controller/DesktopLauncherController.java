package javaFX.controller;
import java.io.IOException;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.model.PathToNecromancer;
import javaFX.model.Difficulty;
import javaFX.model.Settings;
import javaFX.view.DesktopLauncher;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;

/**
 * Controller for the MainMenu
 * @author HangedDragon
 *
 */
public class DesktopLauncherController implements EventHandler<ActionEvent> {
	/**
	 * The scene for the next page
	 */
	private Scene scene;
	/**
	 * settings
	 */
	private Settings settings;
	/** 
	 * Pane for the settings menu
	 */
	private AnchorPane settingMenu;
	/**
	 * starts the game
	 */
   @Override public void handle(ActionEvent e) {
		   LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	        new LwjglApplication(new PathToNecromancer(settings), config);
	        DesktopLauncher.theStage.close();
	    }
	 /**
	  * Load the game by going through a directory of txt files
	  */
	@FXML
	private void loadGame() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DesktopLauncher.class.getResource("/LoadGame.fxml"));
		try {
			this.settingMenu = loader.load();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		scene = new Scene(settingMenu,400,400);
		((LoadGameController)loader.getController()).setSettings(settings);
		DesktopLauncher.theStage.setScene(scene);
	}
	/**
	 * closes the game
	 */
	@FXML
	private void closeGame() {
		DesktopLauncher.theStage.close();
	}

	/**
	 * loads the settings menu.
	 */
	@FXML
	private void settingSetup() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DesktopLauncher.class.getResource("/Settings.fxml"));
		if(this.settings == null) {
			this.settings = new Settings(100, false, false, 100, 100, false, false, Difficulty.EASY);
		}
		
		try {
			this.settingMenu = loader.load();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		SettingsController controller = loader.getController();
		controller.setSettings(settings);
		controller.settingMenuSetup();
		scene = new Scene(settingMenu,400,400);
		DesktopLauncher.theStage.setScene(scene);
		
	}
	/**
	 * gets the settings object
	 * @return the settings
	 */
	public Settings getSettings() {
		return settings;
	}
	/**
	 * sets the settings
	 * @param settings The settings object
	 */
	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	

}
