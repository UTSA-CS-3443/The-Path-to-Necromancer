package game.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import game.model.Savestate;
import game.model.maps.GameMaps;
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
    
    private Map<String,String> maps;
   
    /**
     * Create the game
     * @param save 
     * 
     * @param the main game screen
     */
    public InitializeGame(PlayScreen screen, Savestate save) {
        this.settings = screen.getSettings();
        this.screen = screen;
        this.maps = new HashMap<String,String>();
        mapSetup();
        // Determine if the user wanted to start a new game or load an old
        // game
        if (save == null)
            this.newGame();
		else
			try {
				this.loadGame(save);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

   

	private void mapSetup() {
		this.maps.put("Maps/Map01-IntroArea.tmx", "game.model.maps.IntroArea");
		this.maps.put("Maps/Map02-Oog-lag Tavern.tmx", "game.model.maps.OogLagExterior");
		this.maps.put("Maps/Map03-Inside Oog-Lag's Tavern.tmx", "game.model.maps.InsideTavern");
		this.maps.put("Maps/Map04-Plains Area.tmx", "game.model.maps.PlainsArea");
		this.maps.put("Maps/Map06-MiddleMountain.tmx", "game.model.maps.MiddleMountain");
		this.maps.put("Maps/Map05-LeftSideOfTheMountain.tmx", "game.model.maps.MountainLeft");
		this.maps.put("Maps/Map07-RightMountain.tmx", "game.model.maps.MountainRight");
		this.maps.put("Maps/Map08-LavaFields.tmx", "game.model.maps.LavaFields");
		this.maps.put("Maps/Map09-EntrytoNecromancer'sLair.tmx", "game.model.maps.NecromancerEntry");
		this.maps.put("Maps/Map10-Necromancer'sLair.tmx", "game.model.maps.NecromancerLair");
		this.maps.put("Maps/Map11-Dungeon.tmx", "game.model.maps.Dungeon");	
	}



	/**
     * Create a new game
     */
    public void newGame() {
        // create a new player character
        screen.setPlayer(new DefaultCharacter());
        // Simply load up the first map.
        this.screen.getMapManager().setMap(new IntroArea(this.screen.getMapManager()), 135, 132);
    }

    /**
     * Load the game. Open the save game text file and read it using a Scanner.
     * Set values based off of what you read.
     * @param save 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws ClassNotFoundException 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadGame(Savestate save) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
    	 // create a new player character
        screen.setPlayer(new DefaultCharacter());
        Class c = Class.forName(this.maps.get(save.getMap()));
       
		Constructor ct = c.getConstructor(MapManager.class);
        // Simply load up the first map. 
		this.screen.getMapManager().setMap((GameMaps)(ct.newInstance(this.screen.getMapManager())), (save.getplayerX()), (save.getplayerY()));
		
    }
		
}
