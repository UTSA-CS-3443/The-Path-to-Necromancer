package game.controller;

import game.model.maps.IntroArea;
import game.model.sprites.player.DefaultCharacter;
import game.view.PlayScreen;
import javaFX.model.Settings;

/**
 * Initialize the game based off of the settings.
 * 
 * @author enigma-phi
 *
 */
public class InitializeGame {
    /**
     * The settings for the game
     */
    private Settings settings;

    /**
     * The game screen
     */
    private PlayScreen screen;

    /**
     * Create the game
     * 
     * @param the main game screen
     */
    public InitializeGame(PlayScreen screen) {
        this.settings = screen.getSettings();
        this.screen = screen;
        // Determine if the user wanted to start a new game or load an old
        // game
        if (this.settings.isNewGame())
            this.newGame();
        else
            this.loadGame();
    }

    /**
     * Create a new game
     */
    public void newGame() {
        // create a new player character
        screen.setPlayer(new DefaultCharacter());
        // Simply load up the first map.
        this.screen.getMapManager().setMap(new IntroArea(this.screen.getMapManager()), 130, 170);
    }

    /**
     * Load the game. Open the save game text file and read it using a Scanner.
     * Set values based off of what you read.
     */
    public void loadGame() {
        // Set the player
    }
}
