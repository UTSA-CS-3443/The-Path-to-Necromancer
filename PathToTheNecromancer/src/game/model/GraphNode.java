package game.model;

import java.util.ArrayList;

import game.controller.MapManager;
import game.controller.story.DialogueActor;
import game.model.sprites.player.Player;

/**
 * Implement a simple adjacency list representation of a graph. Each node holds
 * some text, the count of the number edges, and an ArrayList of numbers
 * corresponding to the next edges in a directed graph. The numbers correspond
 * to the name of the next node.
 * 
 * @author enigma-phi
 *
 */
public class GraphNode {
	/**
	 * The number of edges leading away from a specific node
	 */
	private ArrayList<Integer> adjNodes;
	/**
	 * Count of the number of adjacent nodes leading away from the node
	 */
	private int edgeCount;
	/**
	 * The text in the current node
	 */
	private String text;
	/**
	 * The "name" of the current node. Should be it's index in the graph
	 */
	private int name;
	/**
	 * The actor for dialogue
	 */
	private DialogueActor actor;

	/**
	 * Constructor. Create a graph node without any text or the name.
	 */
	public GraphNode() {
		this.adjNodes = new ArrayList<Integer>();
		this.edgeCount = 0;
		this.text = "";
		this.name = -1;
		this.actor = null;
	}

	/**
	 * Constructor. Create a graph node without any text;
	 */
	public GraphNode(int name) {
		this.adjNodes = new ArrayList<Integer>();
		this.edgeCount = 0;
		this.text = "";
		this.name = name;
		this.actor = null;
	}

	/**
	 * Constructor. Create a graph node set with a given text
	 * 
	 * @param text
	 *            is the text held by the graph node
	 * @param name is the integer name of the node
	 */
	public GraphNode(String text, int name) {
		this.adjNodes = new ArrayList<Integer>();
		this.edgeCount = 0;
		this.text = text;
		this.name = name;
		this.actor = null;
	}

	/**
	 * Set the text in the graph node
	 * 
	 * @param is
	 *            the text to set it to
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Add an edge to the list of edge nodes
	 * 
	 * @param edge
	 *            is the edge node to add
	 */
	public void addEdge(int edge) {
		if (!this.adjNodes.contains(edge))
			this.adjNodes.add(edge);
		this.edgeCount++;
	}

	/**
	 * Add multiple edges to the graph node
	 * 
	 * @param edges
	 *            is the edges to add
	 */
	public void addEdges(int[] edges) {
		for (int edge : edges)
			this.adjNodes.add(edge);
		this.edgeCount += edges.length;
	}

	/**
	 * Set the list of edgeNodes
	 * 
	 * @param edges
	 *            is the list of adjacent nodes to set it to
	 */
	public void setEdges(ArrayList<Integer> edges) {
		this.adjNodes = edges;
		this.edgeCount = edges.size();
	}

	/**
	 * Set the list of edgeNodes
	 * 
	 * @param edges
	 *            is the list of adjacent nodes to set it to
	 */
	public void setEdges(int[] edges) {
		this.adjNodes.clear();
		for (int edge : edges)
			this.adjNodes.add(edge);
		this.edgeCount = edges.length;
	}

	/**
	 * Get the number of adjacent edges
	 * 
	 * @return the edge count
	 */
	public int getEdgeCount() {
		return this.edgeCount;
	}

	/**
	 * Get all of the nodes adjacent to the graph node
	 * 
	 * @return the adjacent edges
	 */
	public ArrayList<Integer> getEdges() {
		return this.adjNodes;
	}

	/**
	 * Get the value of the next adjacent node
	 * 
	 * @return the node's index
	 */
	public int getFirstEdge() {
		if (this.adjNodes.size() == 0)
			return -1;
		else
			return this.adjNodes.get(0);
	}

	/**
	 * Get the text associated with a certain edge node
	 * 
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Get the next Node based off of a specific position in the ArrayList of Nodes
	 * 
	 * @param next
	 *            is the position in the ArrayList of nodes to get from
	 * @return the next edge's index
	 */
	public int getNode(int next) {
		return this.adjNodes.get(next);
	}

	/**
	 * Get the name of the graph node
	 * 
	 * @return the name
	 */
	public int getName() {
		return this.name;
	}

	/**
	 * Set the current dialogue actor
	 * 
	 * @param actor
	 *            is the dialogue actor to set to
	 */
	public void addActor(DialogueActor actor) {
		this.actor = actor;
	}

	/**
	 * Have the dialogue actor perform its specific actions
	 * 
	 * @param player
	 *            is the player to perform actions upon
	 * @param manager
	 *            manages the map actions
	 */
	public void act(Player player, MapManager manager) {
		if (this.actor == null)
			return;
		this.actor.act(player, manager);
	}

}
