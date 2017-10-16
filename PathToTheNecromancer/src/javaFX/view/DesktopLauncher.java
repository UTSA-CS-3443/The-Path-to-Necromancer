package javaFX.view;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.model.PathToNecromancer;
import javaFX.model.Settings;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Contains the main method of the game.
 * 
 * @author enigma-phi
 *
 */
public class DesktopLauncher extends Application {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new PathToNecromancer(new Settings()), config);

    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub

    }
}
