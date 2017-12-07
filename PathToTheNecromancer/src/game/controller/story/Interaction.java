package game.controller.story;

import java.util.ArrayList;

import game.model.GraphNode;
import game.controller.MapManager;
import game.model.DialogueGraph;
import game.model.sprites.player.Player;
import game.view.DialogueBox;

/**
 * Contains an interaction graph for dialogue. Goes down the graph based off of
 * the user's choices.
 * You must call getFirstInteraction() the to get the first bit of dialogue and 
 * getNextInteraction for each subsequent portion of the interaction.
 * 
 * @author enigma-phi.
 *
 */
public class Interaction {
	/**
	 * The dialogue graph to iterate through
	 */
	private DialogueGraph dialogueGraph;
	/**
	 * The dialogue box to go through
	 */
	private DialogueBox dialogueBox;
	/**
	 * The options that the user has to chose from
	 */
	private ArrayList<GraphNode> options;

	/**
	 * Constructor
	 * 
	 * @param graph
	 *            the dialogue graph to iterate through
	 * @param box
	 *            is the dialogue box to set text to
	 * @param player
	 *            is the player to modify
	 */
	public Interaction(DialogueGraph graph, DialogueBox box, Player player) {
		this.dialogueGraph = graph;
		this.dialogueBox = box;
		this.options = null;
	}

	/**
	 * Get the player's first interaction with somebody. This must always be called
	 * before getNextInteraction()
	 */
	public void getFirstInteraction() {
		if (this.dialogueGraph == null)
			return;
		this.dialogueBox.setText(this.dialogueGraph.getCurrentText());
	}

	/**
	 * Get the next interaction along the graph
	 */
	public void getNextInteraction() {
		// if the dialogue is over, end the conversation
		if (this.dialogueGraph.getEdgeCount() == 0) {
			this.dialogueBox.endDialogue();
		}
		// if there is only one piece of following text, go there
		else if (this.dialogueGraph.getEdgeCount() == 1) {
			this.dialogueBox.setText(this.dialogueGraph.getNext().getText());
		}
		// if there is more than one text option, render them as buttons
		else {
			this.options = this.dialogueGraph.getEdgeNodes();
			dialogueBox.buttonsStart();
			for (GraphNode node : options) {
				this.dialogueBox.addButton(node.getText());
			}
			dialogueBox.buttonsEnd();
		}
	}

	/**
	 * Set the dialogue input for the game
	 * 
	 * @param input
	 *            is the input for the game
	 */
	public void setDialogueInput(int input, MapManager manager) {
		GraphNode node = this.options.get(input);
		this.dialogueGraph.setCurrent(node.getName());
		node.act(manager.getPlayer(), manager);
		this.getNextInteraction();
	}

	/**
	 * Reset the graph for the next dialogue sequence
	 */
	public void resetDialogue() {
		this.dialogueGraph.reset();
	}

	/**
	 * Have the interactions perform there given actions.
	 * 
	 * @param player
	 *            the player to act upon
	 * @param manager
	 *            allows actions on the maps
	 */
	public void act(Player player, MapManager manager) {
		this.dialogueGraph.getCurrent().act(player, manager);
	}
}
