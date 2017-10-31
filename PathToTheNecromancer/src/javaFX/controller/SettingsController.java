package javaFX.controller;

import javaFX.model.Difficulty;
import javaFX.model.Settings;
import javaFX.view.DesktopLauncher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
/**
 * Controller for the settings menu
 * @author HangedDragon
 *
 */
public class SettingsController implements EventHandler<ActionEvent> {
	/**
	 * slider for inGame music
	 */
	@FXML
	private Slider musicSlider = new Slider();
	/**
	 * slider for inGame Effect sounds
	 */
	@FXML
	private Slider inGameSlider = new Slider();
	/**
	 * slider for brightness
	 */
	@FXML
	private Slider brightnessSlider = new Slider();
	/**
	 * ChoiceBox for difficulties
	 */
	@FXML
	private ChoiceBox<Difficulty> difficulty = new ChoiceBox<Difficulty>();
	
	/**
	 * check boxes for mute, auto forward, vsync
	 */	
	@FXML
	private CheckBox mute, autoForward, vsync;
	/**
	 * Pane for the main menu
	 */
	private AnchorPane mainMenu;
	/**
	 * settings
	 */
	private Settings settings;
	/**
	 * the scene to return to the main menu
	 */
	private Scene scene;
	/**
	 * Background image for the game
	 */
	private Background bkImg;
	/**
	 * sets the setting object then returns to the main menu
	 * @param e the action that triggered this event
	 */
	   @Override public void handle(ActionEvent e) {
	    	// storing everything in settings
	       settings.setDifficulty(difficulty.getValue());
	       settings.setBrightness((int) brightnessSlider.getValue());
	       settings.setGameSound((int) inGameSlider.getValue());
	       settings.setMusicSound((int) musicSlider.getValue()) ;
	       settings.setMute(mute.isSelected());
	       settings.setAutoSkip(autoForward.isSelected());
	       settings.setvSync(vsync.isSelected());
	       DesktopLauncherMenu();
	       
	    }
	   /**
	    * goes back to main menu with changing the settings
	    */
	   @FXML
		private void cancel() {
			       DesktopLauncherMenu();	
		}
		/**
		 * sets up the setting menu with information for settings
		 */
		public void settingMenuSetup() {
			// setting up the music slider
			musicSlider.setValue(settings.getMusicSound());
			// setting up the in game sound slider
			inGameSlider.setValue(settings.getGameSound());
			// setting up brightness slider
			brightnessSlider.setValue(settings.getBrightness());
			// check buttons
			mute.setSelected(settings.isMute());
			autoForward.setSelected(settings.isAutoSkip());
			vsync.setSelected(settings.isvSync());
			// choice box
			difficulty.setItems(FXCollections.observableArrayList(
					  Difficulty.EASY, Difficulty.MEDIUM,  Difficulty.HARD, Difficulty.INSANE));
			difficulty.setValue(settings.getDifficulty());
		}
		/** 
		 * goes back to main menu
		 */
	   @FXML
		public void DesktopLauncherMenu() {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(DesktopLauncher.class.getResource("/MainMenu.fxml"));
				this.mainMenu = loader.load();
				this.mainMenu.setBackground(bkImg);
				scene = new Scene(mainMenu,400,400);
				DesktopLauncher.theStage.setScene(scene);
				((DesktopLauncherController)loader.getController()).setSettings(settings);
				((DesktopLauncherController)loader.getController()).setBkImg(bkImg);
				DesktopLauncher.theStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
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
