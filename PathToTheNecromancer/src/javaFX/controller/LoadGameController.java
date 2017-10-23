package javaFX.controller;

import java.io.File;

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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
/**
 * Controller for the load class
 * @author HangedDragon96
 *
 */
public class LoadGameController implements EventHandler<ActionEvent>{

@FXML
/**
 * Save 1 toggle button
 */
private ToggleButton save1 = new ToggleButton();
@FXML
/**
 * Save 2 toggle button
 */
private ToggleButton save2 = new ToggleButton();
@FXML
/**
 * Save 3 toggle button
 */
private ToggleButton save3 = new ToggleButton();
@FXML
/**
 * Save 1 toggle button
 */
private Label save1Date = new Label();
@FXML
/**
 * Save 2 Date Labels
 */
private Label save2Date = new Label();
/**
 * Save 3 Date labels
 */
@FXML
private Label save3Date = new Label();
/**
 * the toggle group the the 3 save buttons
 */
private ToggleGroup saveGroup = new ToggleGroup();

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
 * Load the game from the state that is read
 */
@Override
public void handle(ActionEvent event) {
	/* just does what new game does now because I wanted to see what save 
	 * does before I coded this since I know I am reading a text file but
	 * I don't know what will be stored in the file until we code it
	 * other wise load is pretty much done
	 */
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    new LwjglApplication(new PathToNecromancer(settings), config);
    DesktopLauncher.theStage.close();
	
}
/**
 * goes make to the main menu if the cancel button is hit
 */
@FXML
public void cancel() {
	DesktopLauncherMenu();	
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
		scene = new Scene(mainMenu,400,400);
		DesktopLauncher.theStage.setScene(scene);
	
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
@FXML
	public void initialize() {
	loadSetup();
	}
/**
 * sets up the toggle buttons for the load page
 */
	private void loadSetup() {
		File theDir = new File("C:\\Users\\danie\\git\\The-Path-to-Necromancer\\PathToTheNecromancer\\saves");
		// if the directory does not exist, create it
		if (!theDir.exists()) {    
		    try{
		        theDir.mkdir();
		        save1.setToggleGroup(saveGroup);
		        save2.setToggleGroup(saveGroup);
		        save3.setToggleGroup(saveGroup);
		        save1.setText("No Saves Available");
		        save2.setText("No Saves Available");
		        save3.setText("No Saves Available");
		        
		    } 
		    catch(SecurityException se){
		        
		    }        
		    
		
		} else if(theDir.length() == 0) {
			this.save1.setText("No Saves Available");
	        this.save2.setText("No Saves Available");
	        this.save3.setText("No Saves Available");
		}
	}
}
