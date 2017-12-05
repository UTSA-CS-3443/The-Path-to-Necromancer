package javaFX.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.io.ObjectInputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.model.PathToNecromancer;

import game.model.Savestate;
import javaFX.model.Settings;
import javaFX.view.DesktopLauncher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

/**
 * Controller for the LoadGame.fxml
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
 * Save 4 toggle button
 */
private ToggleButton save4 = new ToggleButton();
@FXML
/**
 * Save 5 toggle button
 */
private ToggleButton save5 = new ToggleButton();

/**
 * the toggle group the the 3 save buttons
 */
private ToggleGroup saveGroup = new ToggleGroup();
/**
 * load button
 */
@FXML
private Button loadGame = new Button();
/**
 * delete Button
 */
@FXML
private Button deleteButton = new Button();
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
 * Background image for the game
 */
private Savestate save;
/**
 * is pancakes background
 */
private Boolean isPancakes;
/**
 * Load the game from the state that is read
 */
@Override
public void handle(ActionEvent event) {
	// checking which save is called
	if(this.saveGroup.getSelectedToggle().equals(save1)) {
		save = load("save1.txt");
		 LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	     new LwjglApplication(new PathToNecromancer(save.getSetting(), save), config);
	    DesktopLauncher.theStage.close();
	} else if(this.saveGroup.getSelectedToggle().equals(save2)) {
		save = load("save2.txt");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    new LwjglApplication(new PathToNecromancer(save.getSetting(), save), config);
	    DesktopLauncher.theStage.close();
	} else if (this.saveGroup.getSelectedToggle().equals(save3)){
		save = load("save3.txt");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    new LwjglApplication(new PathToNecromancer(save.getSetting(), save), config);
	    DesktopLauncher.theStage.close();
	}else if (this.saveGroup.getSelectedToggle().equals(save4)){
		save = load("save4.txt");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    new LwjglApplication(new PathToNecromancer(save.getSetting(), save), config);
	    DesktopLauncher.theStage.close();
	}else if (this.saveGroup.getSelectedToggle().equals(save5)){
		save = load("save5.txt");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    new LwjglApplication(new PathToNecromancer(save.getSetting(), save), config);
	    DesktopLauncher.theStage.close();
	}

	
	
}
/**
 * loads the game from a file
 * @param string
 */
private Savestate load(String saveName) {
	   InputStream fileIs = null;
       ObjectInputStream objIs = null;
       Savestate save = null;
       try {
           fileIs = new FileInputStream("Saves/"+ saveName);
           objIs = new ObjectInputStream(fileIs);
           save = (Savestate) objIs.readObject();
          
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } finally {
           try {
               if(objIs != null) objIs.close();
           } catch (Exception ex){
                
           }
       }
	return save;
}
/**
 * goes make to the main menu if the cancel button is hit
 */
@FXML
public void cancel() {
	DesktopLauncherMenu();	
}
/**
 * deletes a save file based on a toggle button
 */
	@FXML
	public void delete() {
		File theDir = null;
		if (this.saveGroup.getSelectedToggle().equals(save1)) {
			theDir = new File("Saves/save1.txt");
			if (theDir.delete()) {
				this.save1.setText("Save not Available");

			}

		} else if (this.saveGroup.getSelectedToggle().equals(save2)) {
			theDir = new File("Saves/save2.txt");
			if (theDir.delete()) {
				this.save2.setText("Save not Available");
			}
			

		} else if (this.saveGroup.getSelectedToggle().equals(save3)) {
			theDir = new File("Saves/save3.txt");
			if (theDir.delete()) {
				this.save3.setText("Save not Available");
			}
			

		} else if (this.saveGroup.getSelectedToggle().equals(save4)) {
			theDir = new File("Saves/save4.txt");
			if (theDir.delete()) {
				this.save4.setText("Save not Available");
			}
			

		} else if (this.saveGroup.getSelectedToggle().equals(save5)) {
			theDir = new File("Saves/save5.txt");
			if (theDir.delete()) {
				this.save5.setText("Save not Available");
			}
			

		}

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
/**
 * sets up the load menu before its displayed
 */
@FXML
	public void initialize() {
	loadSetup();
	}
/**
 * sets up the toggle buttons for the load page
 */
	private void loadSetup() {
		File theDir = new File("Saves");
		this.save1.setToggleGroup(saveGroup);
	    this.save2.setToggleGroup(saveGroup);
	    this.save3.setToggleGroup(saveGroup);
	    this.save4.setToggleGroup(saveGroup);
	    this.save5.setToggleGroup(saveGroup);
	   
	    this.save1.setStyle("-fx-base: #00FFFF;");
	    this.save2.setStyle("-fx-base: #00FFFF;");
	    this.save3.setStyle("-fx-base: #00FFFF;");
	    this.save4.setStyle("-fx-base: #00FFFF;");
	    this.save5.setStyle("-fx-base: #00FFFF;");
	   
	    this.loadGame.setStyle("-fx-base: #00FFFF;");
	    this.cancelButton.setStyle("-fx-base: #00FFFF;");
	    this.deleteButton.setStyle("-fx-base: #00FFFF;");
		// if the directory does not exist, create it
		if (!theDir.exists()) {    
		    try{
		       theDir.mkdirs();	        
		    } 
		    catch(SecurityException se){
		        System.out.println("Error I am not allowed to touch this");
		    }        
		    
		
		} else {		
			File[] listOfFiles = theDir.listFiles();
			BasicFileAttributes attr;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// sets the buttons to display the saves in the order they were made
		
			for(int i = 0; i < listOfFiles.length; i++) {
				Path file = Paths.get(listOfFiles[i].getPath());
				if(i == 0) {
				try {
					attr = Files.readAttributes(file, BasicFileAttributes.class);
					this.save1.setText("Save 1 : " + format.format(new Date(attr.creationTime().toMillis())));
					
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
				else if(i == 1) {	
					try {
						attr = Files.readAttributes(file, BasicFileAttributes.class);
						this.save2.setText("Save 2 : " + format.format(new Date(attr.creationTime().toMillis())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				}
				else if(i == 2) {
					try {
						attr = Files.readAttributes(file, BasicFileAttributes.class);
						this.save3.setText("Save 3 : " + format.format(new Date(attr.creationTime().toMillis())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(i == 3) {
					try {
						attr = Files.readAttributes(file, BasicFileAttributes.class);
						this.save4.setText("Save 4 : " + format.format(new Date(attr.creationTime().toMillis())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(i == 4) {
					try {
						attr = Files.readAttributes(file, BasicFileAttributes.class);
						this.save5.setText("Save 5 : " + format.format(new Date(attr.creationTime().toMillis())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		
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
 * sets the styles if the background is pancakes
 * @param isPancakes boolean to check if background is pancakes
 */
public void isItPancakes(Boolean isPancakes) {
			if(isPancakes) {
				this.save1.setStyle("-fx-base: #D2691E;");
			    this.save2.setStyle("-fx-base: #D2691E;");
			    this.save3.setStyle("-fx-base: #D2691E;");
			    this.save4.setStyle("-fx-base: #D2691E;");
			    this.save5.setStyle("-fx-base: #D2691E;");
			   
			    this.cancelButton.setStyle("-fx-base: #D2691E;");
			    this.loadGame.setStyle("-fx-base: #D2691E;");
			    this.deleteButton.setStyle("-fx-base: #D2691E;");
			}
			this.isPancakes = isPancakes;
}


}
