package javaFX.controller;

import javaFX.model.Settings;
import javaFX.view.DesktopLauncher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	 * Pane for the main menu
	 */
	private AnchorPane mainMenu;
	/**
	 * the scene to return to the main menu
	 */
	private Scene scene;
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
	@Override
	public void handle(ActionEvent event) {
			Stage stage = (Stage) okButton.getScene().getWindow();
			stage.close();
		
	}
	public void isItPancakes(Boolean isPancakes) {
		if(isPancakes) {
		    this.okButton.setStyle("-fx-base: #D2691E;");
		}
		this.isPancakes = isPancakes;
}
	@FXML
	public void initialize() {
		this.okButton.setStyle("-fx-base: #00FFFF;");
		this.messageLabel.setTextFill(Color.WHITE);
	}
	public Settings getSettings() {
		return settings;
	}
	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	public Background getBkImg() {
		return bkImg;
	}
	public void setBkImg(Background bkImg) {
		this.bkImg = bkImg;
	}
	public Boolean getIsPancakes() {
		return isPancakes;
	}
	public void setIsPancakes(Boolean isPancakes) {
		this.isPancakes = isPancakes;
	}

}
