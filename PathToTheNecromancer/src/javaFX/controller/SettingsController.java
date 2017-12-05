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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
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
	 * slider for brightness
	 */
	@FXML
	private Slider brightnessSlider = new Slider();
	
	@FXML
	/**
	 * music slider Label
	 */
	private Label musicLabel = new Label();
	@FXML
	/**
	 * sound slider Label
	 */
	private Label soundLabel= new Label();
	/**
	 * brightness slider Label
	 */
	@FXML
	private Label brightnessLabel = new Label();
	
	/**
	 * difficultyLabel Label
	 */
	@FXML
	private Label difficultyLabel = new Label();
	/**
	 * ChoiceBox for difficulties
	 */
	@FXML
	private ChoiceBox<Difficulty> difficulty = new ChoiceBox<Difficulty>();
	
	/**
	 * check boxes for mute, auto forward
	 */	
	@FXML
	private CheckBox mute;
	/**
	 * apply button
	 */
	@FXML
	private Button applyButton = new Button();
	/**
	 * cancel Button
	 */
	@FXML
	private Button cancelButton = new Button();
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
	 * is pancakes background
	 */
	private Boolean isPancakes;
	/**
	 * sets the setting object then returns to the main menu
	 * @param e the action that triggered this event
	 */
	   @Override public void handle(ActionEvent e) {
	    	// storing everything in settings
	       settings.setDifficulty(difficulty.getValue());
	       settings.setBrightness((int) brightnessSlider.getValue());
	       settings.setMusicSound((int) musicSlider.getValue()) ;
	       settings.setMute(mute.isSelected());
	     
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
			musicSlider.setStyle("-fx-base: #00FFFF;");
			musicLabel.setStyle("-fx-base: #006400;");
			// setting up the in game sound slider
			
			soundLabel.setStyle("-fx-base: #006400;");
			// setting up brightness slider
			brightnessSlider.setValue(settings.getBrightness());
			brightnessSlider.setStyle("-fx-base: #00FFFF;");
			brightnessLabel.setStyle("-fx-base: #006400;");
			// check buttons
			mute.setSelected(settings.isMute());
			mute.setStyle("-fx-base: #00FFFF;");
			mute.setTextFill(Color.WHITE);
		
			// choice box
			difficulty.setItems(FXCollections.observableArrayList(
					  Difficulty.Easy, Difficulty.Medium,  Difficulty.Hard, Difficulty.Insane));
			difficulty.setValue(settings.getDifficulty());
			difficulty.setStyle("-fx-base: #00FFFF;");
			difficultyLabel.setStyle("-fx-base: #006400;");
			this.applyButton.setStyle("-fx-base: #00FFFF;");
			this.cancelButton.setStyle("-fx-base: #00FFFF;");
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
				((DesktopLauncherController)loader.getController()).isItPancakes(isPancakes);
				DesktopLauncher.theStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	   
	   /**
		 * gets current settings
		 * @return the current setting
		 */
		public Settings getSettings() {
			return settings;
		}
		/**
		 * sets the current settings
		 * @param settings the current settings
		 */
		public void setSettings(Settings settings) {
			this.settings = settings;
		}
		/**
		 * gets the current background image
		 * @return the current background
		 */
		public Background getBkImg() {
			return bkImg;
		}
		/**
		 * sets the current background
		 * @param bkImg
		 */
		public void setBkImg(Background bkImg) {
			this.bkImg = bkImg;
		}
		/**
		 * returns if its pancakes or not
		 * @return if the background is pancakes or not
		 */
		public Boolean getIsPancakes() {
			return isPancakes;
		}
	/**
	 * checks if the current background is pancakes the sets it
	 * @param isPancakes boolean if the background is pancakes or not
	 */
	public void isItPancakes(Boolean isPancakes) {
		if(isPancakes) {
			
			this.musicSlider.setStyle("-fx-base: #D2691E;");
						
			
						
			this.brightnessSlider.setStyle("-fx-base: #D2691E;");
						
			this.mute.setStyle("-fx-base: #D2691E;");
						
			
			this.difficulty.setStyle("-fx-base: #D2691E;");    
		    this.applyButton.setStyle("-fx-base: #D2691E;");
		    this.cancelButton.setStyle("-fx-base: #D2691E;");
		}
		this.isPancakes = isPancakes;
}
	
}
