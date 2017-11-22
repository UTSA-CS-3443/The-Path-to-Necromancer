package game.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import game.model.Savestate;


public class SaveGame {

/**
 * Saves the current game state to a .txt file depending on what the user picks
 * @param save
 * @throws IOException
 */
static public void saveGame(Savestate save) throws IOException {
	FileOutputStream fos = null;
	File theDir = new File("Saves");
	if (!theDir.exists()) {    
	    try{
	        theDir.mkdirs();    
	    } 
	    catch(SecurityException se){
	        
	    }
	}
	try {
		fos = new FileOutputStream("Saves/save1.txt");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ObjectOutputStream oos = null;
	try {
		oos = new ObjectOutputStream(fos);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	oos.writeObject(save);
	oos.close();
	
	
	
	
}

	
}