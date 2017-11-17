package javaFX.controller;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
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
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
/**
 * Controller for the load class
 * @author HangedDragon96
 *
 */
public class LoadGameController implements Externalizable, EventHandler<ActionEvent>{

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
 * load button
 */
@FXML
private Button loadGame = new Button();
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
	   
	    this.save1.setStyle("-fx-base: #00FFFF;");
	    this.save2.setStyle("-fx-base: #00FFFF;");
	    this.save3.setStyle("-fx-base: #00FFFF;");
	    this.loadGame.setStyle("-fx-base: #00FFFF;");
	    this.cancelButton.setStyle("-fx-base: #00FFFF;");
		// if the directory does not exist, create it
		if (!theDir.exists()) {    
		    try{
		        System.out.println(theDir.mkdirs());
		        this.save1.setText("No Saves Available");
		        this.save2.setText("No Saves Available");
		        this.save3.setText("No Saves Available");
		        
		    } 
		    catch(SecurityException se){
		        
		    }        
		    
		
		} else if(theDir.list().length == 0) {
			this.save1.setText("No Saves Available");
	        this.save2.setText("No Saves Available");
	        this.save3.setText("No Saves Available");
		} else {
			
			File[] listOfFiles = theDir.listFiles();
			BasicFileAttributes attr;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			for(int i = 0; i < listOfFiles.length; i++) {
				if(i == 0) {
				Path file = Paths.get("Saves/save1.txt");
				try {
					attr = Files.readAttributes(file, BasicFileAttributes.class);
					this.save1.setText("Save 1 : " + format.format(new Date(attr.creationTime().toMillis())));
					
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
				else if(i == 1) {
					Path file = Paths.get("Saves/save2.txt");
					try {
						attr = Files.readAttributes(file, BasicFileAttributes.class);
						this.save2.setText("Save 2 : " + format.format(new Date(attr.creationTime().toMillis())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				}
				else if(i == 2) {
					Path file = Paths.get("Saves/save3.txt");
					try {
						attr = Files.readAttributes(file, BasicFileAttributes.class);
						this.save3.setText("Save 3 : " + format.format(new Date(attr.creationTime().toMillis())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		
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
public void isItPancakes(Boolean isPancakes) {
			if(isPancakes) {
				this.save1.setStyle("-fx-base: #D2691E;");
			    this.save2.setStyle("-fx-base: #D2691E;");
			    this.save3.setStyle("-fx-base: #D2691E;");
			    this.cancelButton.setStyle("-fx-base: #D2691E;");
			    this.loadGame.setStyle("-fx-base: #D2691E;");
			}
			this.isPancakes = isPancakes;
}
@Override
public void writeExternal(ObjectOutput out) throws IOException {
	// TODO Auto-generated method stub
	
}
@Override
public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	// TODO Auto-generated method stub
	
}

}
