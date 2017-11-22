package game.model.sprites.objectSprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.controller.story.DialogueActor;
import game.model.DialogueGraph;
import game.model.sprites.GameSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Mage;
import game.model.sprites.player.Player;
import game.model.sprites.player.Rogue;
import game.model.sprites.player.Warrior;

/**
 * The book that gives the player the tutorial at the start of the game
 * 
 * @author enigma-phi
 *
 */
public class Book extends GameSprites implements InteractionSprites {
	/**
	 * The width of the book in the game
	 */
	private static final int BOOK_WIDTH = 9;
	/**
	 * The height of the book in the game
	 */
	private static final int BOOK_HEIGHT = 6;
	/**
	 * Constructor for the book
	 */
	public Book() {

	}

	/**
	 * Get the book's dialogue.
	 * @return the book's dialogue
	 */
	@Override
	public DialogueGraph getDialogue(Player player) {
		DialogueGraph graph = new DialogueGraph();
		if(player.getBookEncounters() > 0) {
			graph.addNode("It's a book. It contains strange magical symbols.");
			return graph;
		}
		graph.addNode("Note: Hit SPACEBAR to move text forward and skip text."); // 0
		graph.addNode("It is a book..."); // 1
		graph.addNode("Walk away."); // 2
		graph.addNode("Read book."); // 3
		graph.addNode("Hey man, where are you going? Don't you want to read me?"); // 4
		graph.addNode("Continue walking away from the now talking book."); // 5
		graph.addNode("Wait. Where are you going? You have to read me!"); // 6
		graph.addNode("Continue to walk away."); // 7
		graph.addNode("Go back and read weird book."); // 8
		graph.addNode("Reading is for the weak."); // 9
		graph.addNode("YOU THINK YOU ARE GOING TO ESCAPE FROM ME?! YOU WILL READ ME!"); // 10
		graph.addNode("Yeah man, just come read me. Pry me open."); // 11
		graph.addNode(
				"OH IT'S GOING TO BE LIKE THAT THEN! LET'S GO YOU LITTLE JERK! YOU'RE LUCKY I AM ONLY ABLE TO USE G-RATED LANGUAGE!"); // 12
		graph.addNode("Read talking book."); // 13
		graph.addNode("Phew, good thing I said something. It's dangerous out there."); // 14
		graph.addNode("What are you?"); // 15
		graph.addNode("I am a Warrior: my strength is my greatest asset."); // 16
		graph.addNode("I am a Rogue: speed guides my hand."); // 17
		graph.addNode("I am a Mage: outwitting my foes is what I do."); // 18
		graph.addNode("Begin tutorial."); // 19
		graph.addNode("I have already read this book before. I will move on."); // 20
		graph.addNode("To move: press the WASD or the arrow keys."); // 21
		graph.addNode("To run: left or right SHIFT keys."); // 22
		graph.addNode("To interact: ENTER key."); // 23
		graph.addNode("To open the menu and view stats: ESCAPE key."); // 24
		graph.addNode("When you enter combat, click the on-screen buttons."); // 25
		graph.addNode(
				"You level up by defeating enemies and gaining experience. When you level up, your stats increase."); // 26
		graph.addNode("Your stats affect how you do in combat. Health means how much damage you can take."); // 27
		graph.addNode("Strength affects your attack. Intelligence affects your magic attack."); // 28
		graph.addNode("Dexterity affects you initiative and dodge chance."); // 29
		graph.addNode("And luck affects your critical hit rate."); // 30
		graph.addNode("Warriors have a high strength. Mages have a high intelligence. Rogues have a high dexterity."); // 31
		graph.addNode("Multiple options will be surrounded by quotation marks. Click the dialogue option of your choice.");// 32
		graph.addNode("You approach the book and begin reading it."); // 33
		
		// Add the edges to the graph
		graph.addEdge(0, 32);
		graph.addEdge(32, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(6, 7);
		graph.addEdge(6, 8);
		graph.addEdge(6, 9);
		graph.addEdge(7, 10);
		graph.addEdge(8, 11);
		graph.addEdge(9, 12);
		graph.addEdge(4, 13);
		graph.addEdge(13, 14);
		graph.addEdge(33, 15);
		graph.addEdge(10, 33);
		graph.addEdge(11, 33);
		graph.addEdge(12, 33);
		graph.addEdge(14, 33);
		graph.addEdge(3, 33);
		graph.addEdge(15, 16);
		graph.addEdge(15, 17);
		graph.addEdge(15, 18);
		graph.addEdge(16, 19);
		graph.addEdge(16, 20);
		graph.addEdge(17, 19);
		graph.addEdge(17, 20);
		graph.addEdge(18, 19);
		graph.addEdge(18, 20);
		graph.addEdge(19, 21);
		graph.addEdge(21, 22);
		graph.addEdge(22, 23);
		graph.addEdge(23, 24);
		graph.addEdge(24, 25);
		graph.addEdge(25, 26);
		graph.addEdge(26, 27);
		graph.addEdge(27, 28);
		graph.addEdge(28, 29);
		graph.addEdge(29, 30);
		graph.addEdge(30, 31);

		// add the actors
		graph.getNode(0).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, 10), 1);
			}
			
		});
		graph.getNode(2).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, -5), 1);
			}
			
		});
		graph.getNode(5).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, -5), 1);
			}
			
		});
		graph.getNode(7).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, -5), 1);
			}
			
		});
		graph.getNode(33).addActor(new DialogueActor() {
			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, 5), 3);
			}

		});
		graph.getNode(16).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				Warrior warrior = new Warrior();
				warrior.setBookEncounters(1);
				manager.getMainScreen().setPlayer(warrior);
			}
			
		});
		graph.getNode(17).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				Rogue rogue = new Rogue();
				rogue.setBookEncounters(1);
				manager.getMainScreen().setPlayer(rogue);
			}
			
		});
		graph.getNode(18).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				Mage mage = new Mage();
				mage.setBookEncounters(1);
				manager.getMainScreen().setPlayer(mage);
			
			}
			
		});
	
		return graph;
	}

	/**
	 * Update the book's animation
	 */
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	/**
	 * Create the Book's body for the box2d physics engine
	 * 
	 * @param world
	 *            is the world to put the character into
	 * @param x
	 *            is the x-coordinate to put the character on
	 * @param y
	 *            is the y-coordinate to put the character on
	 */
	public void defineBody(World world, int x, int y) {

		// set the body of the character
		BodyDef bdef = new BodyDef();
		bdef.position.set(x, y); // position
		bdef.type = BodyDef.BodyType.DynamicBody; // body type

		// Create the body and add mass
		Body body = world.createBody(bdef);
		MassData mass = new MassData();
		mass.mass = 1000000f;
		body.setMassData(mass);

		// create the world fixture for collision
		FixtureDef fdef = new FixtureDef();
		PolygonShape rect = new PolygonShape();

		// coordinates of the Necromancer's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-(BOOK_HEIGHT) / 2 + 5, BOOK_HEIGHT / 2); // top left
		vertice[1] = new Vector2((BOOK_WIDTH) / 2 - 5, BOOK_HEIGHT / 2); // top right
		vertice[2] = new Vector2(-(BOOK_WIDTH) / 2 + 5, -(BOOK_WIDTH) / 2); // bottom
																											// left
		vertice[3] = new Vector2((BOOK_WIDTH) / 2 - 5, -(BOOK_HEIGHT) / 2); // bottom
																											// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		body.createFixture(fdef).setUserData(this);
	}

}
