package game.model.sprites;

import game.model.DialogueGraph;
import game.model.sprites.player.Player;

/**
 * An interface containing basic methods for interaction
 * 
 * @author enigma-phi
 *
 */
public interface InteractionSprites {
	/**
	 * Get the dialogue graph that for the interaction sprite
	 * @param player containing booleans for determining which dialogue to get
	 * @return the dialogue graph
	 */
	public DialogueGraph getDialogue(Player player);
}
