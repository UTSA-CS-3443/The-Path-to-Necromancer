package javaFX.controller;

import javaFX.model.Settings;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * controller for SaveError.fxml
 * @author HangedDragon96
 *
 */
public class SaveErrorController implements EventHandler<ActionEvent> {
	
	@FXML
	/**
	 *  messageLabel
	 */
	private Label messageLabel = new Label();
	/**
	 * ok button
	 */
	@FXML
	private Button okButton = new Button();

	/**
	 * the settings object
	 */
	private Settings settings;
	/**
	 * Background image for the game
	 */
	private Background bkImg;
	/**
	 * is pancakes background
	 */
	private Boolean isPancakes;
	/**
	 * closes the save error window
	 */
	@Override
	public void handle(ActionEvent event) {
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
		
	}
	/**
	 *  checks if the current back ground is pancakes
	 */
	public void isItPancakes(Boolean isPancakes) {
		if(isPancakes) {
		    this.okButton.setStyle("-fx-base: #D2691E;");
		}
		this.isPancakes = isPancakes;
}
	/**
	 * sets up the save error styles
	 */
	@FXML
	public void initialize() {
		this.okButton.setStyle("-fx-base: #00FFFF;");
		this.messageLabel.setTextFill(Color.WHITE);
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
	 * sets the isPancakes boolean
	 * @param isPancakes
	 */
	public void setIsPancakes(Boolean isPancakes) {
		this.isPancakes = isPancakes;
	}

}
