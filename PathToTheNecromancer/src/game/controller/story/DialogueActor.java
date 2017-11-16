package game.controller.story;

import game.controller.MapManager;
import game.model.sprites.player.Player;

/**
 * A dialogue actor that performs certain actions based upon certain points in
 * the dialogue.
 * 
 * @author enigma-phi
 *
 */
public interface DialogueActor {
	/**
	 * Perform a specific action upon the player
	 * 
	 * @param player
	 *            is the player to act on
	 * 
	 * @param manager
	 *            is the manager to act upon
	 */
	public void act(Player player, MapManager manager);
}
