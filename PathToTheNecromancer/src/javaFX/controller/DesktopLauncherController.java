package javaFX.controller;
import java.io.IOException;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.model.PathToNecromancer;
import javaFX.model.Settings;
import javaFX.view.DesktopLauncher;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

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
	 * Background image for the game
	 */
	private Background bkImg;
	/**
	 * is pancakes background
	 */
	private Boolean isPancakes;
	/**
	 * new game
	 */
	@FXML
	private Button newGame = new Button();
	/**
	 * load game Button
	 */
	@FXML
	private Button loadGame = new Button();
	/**
	 * settings button
	 */
	@FXML
	private Button settingButton = new Button();
	/**
	 * quit Button
	 */
	@FXML
	private Button quit = new Button();
	/**
	 * starts the game
	 */
   @Override public void handle(ActionEvent e) {
	   		this.settings.setNewGame(true);
		   LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	        new LwjglApplication(new PathToNecromancer(settings, null), config);
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
			this.settingMenu.setBackground(bkImg);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		scene = new Scene(settingMenu,400,400);
		((LoadGameController)loader.getController()).setSettings(settings);
		((LoadGameController)loader.getController()).setBkImg(bkImg);
		((LoadGameController)loader.getController()).isItPancakes(isPancakes);
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
		try {
			this.settingMenu = loader.load();
			this.settingMenu.setBackground(bkImg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		SettingsController controller = loader.getController();
		controller.setSettings(settings);
		controller.setBkImg(bkImg);
		controller.settingMenuSetup();
		controller.isItPancakes(isPancakes);
		scene = new Scene(settingMenu,400,400);
		DesktopLauncher.theStage.setScene(scene);
		
	}
	@FXML
	public void initialize() {
	setup();
	}
	
	private void setup() {
		this.newGame.setStyle("-fx-base: #00FFFF;");
	    this.loadGame.setStyle("-fx-base: #00FFFF;");
	    this.settingButton.setStyle("-fx-base: #00FFFF;");
	    this.quit.setStyle("-fx-base: #00FFFF;");
	}
	
	
	public void isItPancakes(Boolean isPancakes) {
		if(isPancakes) {
		
		    this.newGame.setStyle("-fx-base: #D2691E;");
		    this.loadGame.setStyle("-fx-base: #D2691E;");
		    this.settingButton.setStyle("-fx-base: #D2691E;");
		    this.quit.setStyle("-fx-base: #D2691E;");
		}
		this.isPancakes = isPancakes;
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
	public Background getBkImg() {
		return bkImg;
	}
	public void setBkImg(Background bkImg) {
		this.bkImg = bkImg;
	}

	
	
	

}
