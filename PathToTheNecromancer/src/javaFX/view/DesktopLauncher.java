package javaFX.view;



import java.util.ArrayList;
import java.util.Random;

import javaFX.controller.DesktopLauncherController;
import javaFX.model.Difficulty;
import javaFX.model.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 * Contains the DesktopLauncher method of the game.
 * 
 * @author HangedDragon96
 *
 */
public class DesktopLauncher extends Application {
	/**
	 * The pane for the main menu
	 */
	private AnchorPane mainMenu;
	/**
	 *  the stage for the menus
	 */
	public static Stage theStage;
	/**
	 * The current scene
	 */
	private Scene scene;
	/**
	 *   settings
	 */
	private Settings settings;
	/**
	 * is pancakes background
	 */
	private Boolean isPancakes = false;
	/**
	 * Array list for pictures
	 */
	private ArrayList <String> urls;
	
	@Override
	public void start(Stage primaryStage) {
		urlSetup();	
		String background = urls.get((new Random()).nextInt(urls.size()));
		Image img = new Image(background);
		if(background.equals("Necro Pictures\\SabHunger.jpg")) {
			isPancakes = true;
		}
		BackgroundImage bgImg = new BackgroundImage(img, 
		    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
		    BackgroundPosition.DEFAULT, 
		    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
		Background bkImg = new Background(bgImg);
		theStage = primaryStage;
		settings = new Settings(100, false, false, 100, 100, false, false, Difficulty.EASY);
		primaryStage.setTitle("Path to the Necromancer");
		 try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(DesktopLauncher.class.getResource("/MainMenu.fxml"));
				this.mainMenu = loader.load();
				this.mainMenu.setBackground(bkImg);
				scene = new Scene(mainMenu,400,400);
				DesktopLauncherController controller = loader.getController();
				controller.setSettings(settings);
				controller.setBkImg(bkImg);
				controller.isItPancakes(isPancakes);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

	
	private void urlSetup() {
		urls = new ArrayList <String>();
		urls.add("Necro Pictures\\SabHunger.jpg");
		urls.add("Necro Pictures\\Necro1.jpg");
		urls.add("Necro Pictures\\necromancer_by_bogdan_mrk-d9biu3b.jpg");	
		urls.add("Necro Pictures\\6e4afebd45418a11ff00fd3ab3bbdaf0-d9gv7ld.jpg");
		urls.add("Necro Pictures\\Lord of the Dead II.jpg");
		urls.add("Necro Pictures\\Necromancy by Ramses Melendez.jpg");
		urls.add("Necro Pictures\\Necromancer_new_design.jpg");
		urls.add("Necro Pictures\\4c5f45ef5275bb06398762a600ce1fb0-d9k2yqb.jpg");
		urls.add("Necro Pictures\\9501b75731ff67b48196c2b555eb9047-d9lstx7.jpg");
		
	}


	/**
	 * main method starts the main Menu
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}



