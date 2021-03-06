package game.model.sprites.objectSprites;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import game.model.sprites.CharacterSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Mage;
import game.model.sprites.player.Player;
import game.model.sprites.player.Rogue;
import game.model.sprites.player.StoryStats;
import game.model.sprites.player.Warrior;

/**
 * The book that gives the player the tutorial at the start of the game
 * Creates anonymous classes that implement DialogueActor
 * 
 * @author enigma-phi
 *
 */
public class Book extends CharacterSprites implements InteractionSprites {
	/**
	 * The width of the book in the game
	 */
	private static final int BOOK_WIDTH = 30;
	/**
	 * The height of the book in the game
	 */
	private static final int BOOK_HEIGHT = 30;
	/**
	 * The book's animation
	 */
	private ArrayList<TextureRegion> frames;
	/**
	 * Whether or not to turn the book
	 */
	private boolean setOpen;
	/**
	 * Whether or not to turn the page
	 */
	private boolean setTurnPage;
	/**
	 * The current textureRegion for the animation
	 */
	private TextureRegion currentRegion;
	/**
	 * The timer for animations
	 */
	private float timer;

	/**
	 * Constructor for the book
	 */
	public Book() {
		setTextureValues();
		this.currentRegion = this.frames.get(0);
		setRegion(this.currentRegion);
		setBounds(0, 0, BOOK_WIDTH, BOOK_HEIGHT);
	}

	/**
	 * Set the book's texture regions
	 */
	@Override
	public void setTextureValues() {
		Texture bookTexture = new Texture("CharacterSprites/SassyBook.png");
		frames = new ArrayList<TextureRegion>();

		for (int i = 0; i < 5; i++) {
			frames.add(new TextureRegion(bookTexture, i * 720, 0, 720, 854));
		}
	}

	/**
	 * Get the book's current animation frame
	 * @param dt is the change in time since the last render
	 */
	@Override
	public TextureRegion getFrame(float dt) {
		if(this.setOpen || this.setTurnPage)
			timer += dt;
		if(this.setOpen) {
			if (timer < 0.25)
				this.currentRegion = (this.frames.get(1));
			else {
				this.currentRegion = this.frames.get(2);
				this.setOpen = false;
			}
		}
		if(this.setTurnPage) {
			if (timer < 0.25)
				this.currentRegion = this.frames.get(3);
			else {
				this.currentRegion = this.frames.get(4);
				this.setOpen = false;
			}
		}
		return this.currentRegion;
	}

	/**
	 * Whether or not to start the opening book animation
	 * 
	 * @param b
	 *            is the boolean to set to
	 */
	public void setOpen(boolean b) {
		this.setOpen = b;
		this.timer = 0;
	}

	/**
	 * Whether or not to start the turning page animation
	 * 
	 * @param b
	 *            is the boolean to set to
	 */
	public void setTurnPage(boolean b) {
		this.setTurnPage = b;
		this.timer = 0;
	}

	/**
	 * Get the book's dialogue.
	 * 
	 * @param player is the player to base the dialogue off of
	 * @return the book's dialogue
	 */
	@Override
	public DialogueGraph getDialogue(Player player) {
		DialogueGraph graph = new DialogueGraph();
		if (!player.getStoryStats().isBookEncounters()) {
			graph.addNode("It's a book. It contains strange magical symbols.");
			return graph;
		}
		graph.addNode("Note: Hit SPACEBAR to move text forward and skip text."); // 0
		graph.addNode("It is a book..."); // 1
		graph.addNode("P: Walk away."); // 2
		graph.addNode("P: Read book."); // 3
		graph.addNode("Book: Hey man, where are you going? Don't you want to read me?"); // 4
		graph.addNode("P: Continue walking away from the now talking book."); // 5
		graph.addNode("Book: Wait. Where are you going? You have to read me!"); // 6
		graph.addNode("P: Continue to walk away."); // 7
		graph.addNode("P: Go back and read weird book."); // 8
		graph.addNode("P: Reading is for the weak."); // 9
		graph.addNode("Book: YOU THINK YOU ARE GOING TO ESCAPE FROM ME?! YOU WILL READ ME!"); // 10
		graph.addNode("Book: Yeah man, just come read me. Pry me open."); // 11
		graph.addNode(
				"Book: OH, IT'S GOING TO BE LIKE THAT THEN! LET'S GO YOU LITTLE JERK! YOU'RE LUCKY I AM ONLY ABLE TO USE G-RATED LANGUAGE!"); // 12
		graph.addNode("P: Read talking book."); // 13
		graph.addNode("Book: Phew, good thing I said something. It's dangerous out there."); // 14
		graph.addNode("Book: What are you?"); // 15
		graph.addNode("P: I am a Warrior: my strength is my greatest asset."); // 16
		graph.addNode("P: I am a Rogue: speed guides my hand."); // 17
		graph.addNode("P: I am a Mage: outwitting my foes is what I do."); // 18
		graph.addNode("P: Begin tutorial."); // 19
		graph.addNode("P: I have already read this book before. I will move on."); // 20
		graph.addNode("To move: press the WASD or the arrow keys."); // 21
		graph.addNode("To run: left or right SHIFT keys."); // 22
		graph.addNode("To interact: ENTER key."); // 23
		graph.addNode("To open the menu and view stats: ESCAPE key."); // 24
		graph.addNode("When you enter combat, click the on-screen buttons."); // 25
		graph.addNode(
				"You level up by defeating enemies and gaining experience. When you level up, your stats increase."); // 26
		graph.addNode("Your stats affect how you do in combat. Health means how much damage you can take."); // 27
		graph.addNode("Strength affects your attack. Intelligence affects your magic attack."); // 28
		graph.addNode("Dexterity affects your initiative and dodge chance."); // 29
		graph.addNode("And luck affects your critical hit rate."); // 30
		graph.addNode("Warriors have high strength. Mages have high intelligence. Rogues have high dexterity."); // 31
		graph.addNode("You approach the book and begin reading it."); // 32
		graph.addNode("When dialogue occurs, it will render on screen."); // 33
		graph.addNode("If the player is speaking, the dialogue will be prefaced by a 'P:' to indicate player."); // 34
		graph.addNode("If multiple options occur (multiple instances of 'P:') click the option of your choice."); // 35
		
		// Add the edges to the graph
		graph.addEdge(0, 33);
		graph.addEdge(33, 34);
		graph.addEdge(34, 35);
		graph.addEdge(35 , 1);
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
		graph.addEdge(32, 15);
		graph.addEdge(10, 32);
		graph.addEdge(11, 32);
		graph.addEdge(12, 32);
		graph.addEdge(14, 32);
		graph.addEdge(3, 32);
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
		//graph.addEdge(24, 25);              trigger combat tutorial
		graph.addEdge(25, 26);
		graph.addEdge(26, 27);
		graph.addEdge(27, 28);
		graph.addEdge(28, 29);
		graph.addEdge(29, 30);
		graph.addEdge(30, 31);

		// add the actors
		// Make the player move towards the book
		graph.getNode(0).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, 10), 1);
			}

		});
		
		// The player walks away from the book
		graph.getNode(2).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, -5), 1);
			}

		});
		
		// The player walks away from the book
		graph.getNode(5).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, -5), 1);
			}

		});
		
		// The player walks away from the book
		graph.getNode(7).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, -5), 1);
			}

		});
		
		// The player walks towards the book
		graph.getNode(32).addActor(new DialogueActor() {
			@Override
			public void act(Player player, MapManager manager) {
				player.addVelocity(new Vector2(0, 5), 3);
			}

		});
		
		// The player decides to become a Warrior
		graph.getNode(16).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				StoryStats stats = player.getStoryStats();
				Warrior warrior = new Warrior();
				warrior.setStoryStats(stats);
				manager.getMainScreen().setPlayer(warrior);
				manager.getPlayer().setWarrior(true);
			}

		});
		
		// The player decides to become a Rogue
		graph.getNode(17).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				StoryStats stats = player.getStoryStats();
				Rogue rogue = new Rogue();
				rogue.setStoryStats(stats);
				manager.getMainScreen().setPlayer(rogue);
				manager.getPlayer().setRogue(true);
			}

		});
		
		// The player decides to become a Mage
		graph.getNode(18).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				StoryStats stats = player.getStoryStats();
				Mage mage = new Mage();
				mage.setStoryStats(stats);
				manager.getMainScreen().setPlayer(mage);
				manager.getPlayer().setMage(true);

			}
		});
		// The player opens the book
		graph.getNode(15).addActor(new DialogueActor() {
			@Override
			public void act(Player player, MapManager manager) {
				setOpen(true);
			}
		});
		
		// The player turns a page in the book
		graph.getNode(19).addActor(new DialogueActor() {
			@Override
			public void act(Player player, MapManager manager) {
				setTurnPage(true);
			}
		});

		return graph;
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
	@Override
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
		this.setBody(body);
		
		// create the world fixture for collision
		FixtureDef fdef = new FixtureDef();
		PolygonShape rect = new PolygonShape();

		// coordinates of the Necromancer's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-(BOOK_HEIGHT) / 2, 0 + 5); // top left
		vertice[1] = new Vector2((BOOK_WIDTH) / 2, 0 + 5); // top right
		vertice[2] = new Vector2(-(BOOK_WIDTH) / 2, -(BOOK_WIDTH) / 2); // bottom
																			// left
		vertice[3] = new Vector2((BOOK_WIDTH) / 2, -(BOOK_HEIGHT) / 2); // bottom
																			// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		body.createFixture(fdef).setUserData(this);
	}
}
