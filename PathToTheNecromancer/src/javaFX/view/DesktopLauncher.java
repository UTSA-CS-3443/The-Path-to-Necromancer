package javaFX.view;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Contains the DesktopLauncher method of the game.
 * 
 * @author enigma-phi
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
	 * starts the menus
	 */
	@Override
	public void start(Stage primaryStage) {
		
		theStage = primaryStage;
		 
		 primaryStage.setTitle("Path to the Necromancer");
		 try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(DesktopLauncher.class.getResource("/MainMenu.fxml"));
				this.mainMenu = loader.load();
				scene = new Scene(mainMenu,400,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

	
	/**
	 * main method starts the main Menu
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}



