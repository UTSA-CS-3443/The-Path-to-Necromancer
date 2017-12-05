package javaFX.controller;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.model.PathToNecromancer;
import javaFX.model.Settings;
import javaFX.model.SplashPage;
import javaFX.view.DesktopLauncher;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

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
	 *  the url for the current background
	 */
	private static String background;
	/**
	 * the splash screen
	 */
	public static SplashPage page;
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
   @Override public void handle(ActionEvent event) {
	   		this.settings.setNewGame(true);
	   		File theDir = new File("Saves");
	   		 if(theDir.exists() && theDir.listFiles().length == 5) {
	   			FXMLLoader loader = new FXMLLoader();
	   			Stage test = new Stage();
	   			loader.setLocation(DesktopLauncher.class.getResource("/SaveError.fxml"));
	   			try {	
	   				this.settingMenu = loader.load();
	   				this.settingMenu.setBackground(bkImg);
	   				
	   			} catch (IOException e) {
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			}	
	   			scene = new Scene(settingMenu,400,400);
	   			((SaveErrorController)loader.getController()).setBkImg(bkImg);
	   			((SaveErrorController)loader.getController()).isItPancakes(isPancakes);
	   			test.setScene(scene);
	   			test.show();
	   			
	   		} else {
	        DesktopLauncher.theStage.close();
	   		showSplashScreen();
		    launchGame();
		    EventQueue.invokeLater(new SplashScreenCloser());
	   		}
	   		
	    }
   /**
    * launches the game
    */
	private void launchGame() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	        new LwjglApplication(new PathToNecromancer(settings, null), config);

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
	/**
	 * initial setup for mainmenu.fxml
	 */
	@FXML
	public void initialize() {
	setup();
	}
	/**
	 * sets up the desktop launchers
	 */
	private void setup() {
		this.newGame.setStyle("-fx-base: #00FFFF;");
	    this.loadGame.setStyle("-fx-base: #00FFFF;");
	    this.settingButton.setStyle("-fx-base: #00FFFF;");
	    this.quit.setStyle("-fx-base: #00FFFF;");
	}
	
	/**
	 * checks if the current background is pancakes
	 * @param isPancakes is the background is pancakes
	 * 
	 */
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
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		DesktopLauncherController.background = background;
	}
	
	 /** 
	  * Removes the splash screen. 
	  *
	  * Invoke this Runnable using 
	  * EventQueue.invokeLater, in order to remove the splash screen
	  * in a thread-safe manner.
	  */
	  private static final class SplashScreenCloser implements Runnable {
	    @Override public void run(){
	      page.dispose();
	    }
	  }
	  /**
	   * Show a simple graphical splash screen, as a quick preliminary to the main screen.
	   */
	   private static void showSplashScreen(){    
	     page = new SplashPage(background);
	   	 page.splash();;
	   }
	   
	
	

}
