package game.model;

import java.util.ArrayList;

/**
 * An implementation of a graph. Contains an ArrayList of GraphNode that
 * may connect to a later node. It is important to node, that the names of the 
 * nodes in the graph correspond to their index in the ArrayList of nodes.
 * 
 * @author enigma-phi
 *
 */
public class DialogueGraph {
	/**
	 * The array list of nodes
	 */
	public ArrayList<GraphNode> nodes;
	/**
	 * The current node. This is used for iterating through the graph
	 */
	public int currentNode;
	/**
	 * The previous node held
	 */
	public int previousNode;

	/**
	 * Constructor. Initialize values in the graph
	 */
	public DialogueGraph() {
		this.nodes = new ArrayList<GraphNode>();
		this.currentNode = -1;
		this.previousNode = -1;
	}

	/**
	 * Add a new node to the ArrayList of nodes
	 * 
	 * @param node
	 *            is the node to add
	 */
	public void addNode(GraphNode node) {
		if (!nodes.contains(node))
			nodes.add(node);
		if (currentNode == -1 && !nodes.isEmpty())
			this.currentNode = 0;
	}

	/**
	 * Add a new node to the ArrayList of nodes.
	 * 
	 * @param text
	 *            is the text the new node contains
	 */
	public void addNode(String text) {
		GraphNode node = new GraphNode(text, this.nodes.size());
		this.nodes.add(node);
		this.currentNode = 0;
	}

	/**
	 * Get the next node along the graph
	 * 
	 * @return the next node
	 */
	public GraphNode getNext() {
		this.previousNode = this.currentNode;
		this.currentNode = this.nodes.get(this.currentNode).getFirstEdge();
		return this.nodes.get(this.currentNode);
	}

	/**
	 * Get the previous node in the graph
	 * 
	 * @return the previous node
	 */
	public GraphNode getPrevious() {
		return this.nodes.get(this.previousNode);
	}

	/**
	 * Add an edge to the graph
	 * 
	 * @param a
	 *            the out-vertex
	 * @param b
	 *            the in-vertex
	 */
	public void addEdge(int a, int b) {
		this.nodes.get(a).addEdge(b);
	}

	/**
	 * Get the text at the current node held
	 * 
	 * @return the text
	 */
	public String getCurrentText() {
		return this.nodes.get(this.currentNode).getText();
	}

	/**
	 * Get the count of the number of edges adjacent to the current edge
	 * 
	 * @return the edge count
	 */
	public int getEdgeCount() {
		return this.nodes.get(this.currentNode).getEdgeCount();
	}

	/**
	 * Get all of the edges for the current node
	 * 
	 * @return an ArrayList of the edges
	 */
	public ArrayList<Integer> getEdges() {
		return this.nodes.get(this.currentNode).getEdges();
	}

	/**
	 * Get a specific node from the graph at a specific index.
	 * 
	 * @param index
	 *            is the index to get the node from
	 * @return the node at that index
	 */
	public GraphNode getNode(int index) {
		return this.nodes.get(index);
	}

	/**
	 * Get all of the nodes adjacent to the current node
	 * 
	 * @return an ArrayList of the nodes
	 */
	public ArrayList<GraphNode> getEdgeNodes() {
		if (this.getEdgeCount() == 0)
			return null;

		ArrayList<GraphNode> edgeNodes = new ArrayList<GraphNode>();
		for (int i : this.getEdges()) {
			edgeNodes.add(this.getNode(i));
		}
		return edgeNodes;
	}

	/**
	 * Set the current node
	 * 
	 * @param index
	 *            the index of the current node
	 */
	public void setCurrent(int index) {
		this.currentNode = index;
	}

	/**
	 * Reset the graph for iterating through
	 */
	public void reset() {
		this.currentNode = 0;
		this.previousNode = -1;
	}

	/**
	 * Get the current graph node
	 * 
	 * @return the current graph node
	 */
	public GraphNode getCurrent() {
		return this.nodes.get(this.currentNode);
	}
}
