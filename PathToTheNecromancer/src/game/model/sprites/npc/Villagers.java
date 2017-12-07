
package game.model.sprites.npc;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import game.controller.MapManager;
import game.controller.story.DialogueActor;
import game.model.DialogueGraph;
import game.model.sprites.CharacterSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Player;

/**
 * 
 * This is the class for the animation of the villagers
 * Creates anonymous classes that implement DialogueActor
 * 
 * @author ToTryHardRay
 * @author enigma-phi
 *
 */
public class Villagers extends CharacterSprites implements InteractionSprites {
	/**
	 * The villager's color
	 */
	private ColorAndGender color;
	/**
	 * The villager's gender
	 */
	private ColorAndGender gender;
	/**
	 * The texture associated with the villager
	 */
	private Texture villagerTexture;
	/**
	 * Whether or not the villager has a specific special dialogue
	 */
	private boolean setSpecialDialogue;
	/**
	 * The villager's height
	 */
	private static final int VILLAGER_HEIGHT = 30;
	/**
	 * The villager's width
	 */
	private static final int VILLAGER_WIDTH = 15;
	/**
	 * Whether or not the character is a hidden villager
	 */
	private boolean hiddenVillager;

	/**
	 * Constructor for the villagers
	 * @param color is the villager's color
	 * @param gender is the villager's gender
	 */
	public Villagers(ColorAndGender color, ColorAndGender gender) {
		this.color = color;
		this.gender = gender;
		this.hiddenVillager = false;
		this.setSpecialDialogue = false;
		setTextureValues();
		// Set the size of the Villager
		setBounds(0, 0, VILLAGER_WIDTH, VILLAGER_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	/**
	 * Set the texture values for the villager
	 */
	@Override
	public void setTextureValues() {
		int rowHeight = 0;
		int width = 27;
		int height = 50;

		// Use the Villager animations until we have the player SpriteSheet
		villagerTexture = new Texture("CharacterSprites/Villagers.png");

		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();
		// switching based on color and gender
		switch (color) {
		default:
			break;
		case RED:
			if (gender == ColorAndGender.MALE)
				rowHeight = 0;
			else
				rowHeight = 50;
			break;

		case BLUE:
			if (gender == ColorAndGender.MALE)
				rowHeight = 100;
			else
				rowHeight = 150;
			break;

		case GREEN:
			if (gender == ColorAndGender.MALE)
				rowHeight = 200;
			else
				rowHeight = 250;
			break;

		case BROWN:
			if (gender == ColorAndGender.MALE)
				rowHeight = 300;
			else
				rowHeight = 350;
			break;

		case BLACK:
			if (gender == ColorAndGender.MALE)
				rowHeight = 400;
			else
				rowHeight = 450;
			break;

		case WHITE:
			if (gender == ColorAndGender.MALE)
				rowHeight = 500;
			else
				rowHeight = 550;
			break;
		}

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(villagerTexture, 0, rowHeight, width, height));

		// move down
		frames.add(new TextureRegion(villagerTexture, (0) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (3) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (4) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (6) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (4) * width, rowHeight, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up
		frames.add(new TextureRegion(villagerTexture, (7 + 0) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 3) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 4) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 6) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (7 + 4) * width, rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move left
		frames.add(new TextureRegion(villagerTexture, (0 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (3 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (2 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (1 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (6 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (5 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (4 + 14) * width, rowHeight, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move right
		frames.add(new TextureRegion(villagerTexture, (6 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (3 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (4 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (5 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (0 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (1 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture, (2 + 21) * width, rowHeight, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		super.setMoveDownLeft(super.getMoveDown());
		super.setMoveDownRight(super.getMoveDown());
		super.setMoveUpRight(super.getMoveUp());
		super.setMoveUpLeft(super.getMoveUp());
		super.setAnimationSpeed(3);
		super.setRunningSpeed(2);
	}

	/**
	 * Define the villager's physics body
	 * @param world is the world to place the body in
	 * @param x is the x-coordinate to place it in
	 * @param y is the y-coordinate to place it in
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

		// coordinates of the Villager's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-VILLAGER_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(VILLAGER_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-VILLAGER_WIDTH / 2, -VILLAGER_HEIGHT / 2); // bottom
		// left
		vertice[3] = new Vector2(VILLAGER_WIDTH / 2, -VILLAGER_HEIGHT / 2); // bottom
		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);

	}

	/**
	 * Get the Villager's Dialogue Sequence
	 * 
	 * @param player is the player to base the dialogue off of
	 * @return the dialogueGraph
	 */
	@Override
	public DialogueGraph getDialogue(Player player) {
		if(this.hiddenVillager)
			return this.getHiddenDialogue();
		else if(this.setSpecialDialogue)
			return getSetDialogue();
		return getRandomDialogue();
	}

	/**
	 * Get a random dialogue with a villager
	 * 
	 * @return the DialogueGraph
	 */
	private DialogueGraph getRandomDialogue() {
		DialogueGraph graph = new DialogueGraph();
		Random rand = new Random();
		int x = rand.nextInt(6);
		switch (x) {
		default:
			break;
		case 0:
			graph.addNode(
					"Villager: I do admit, my little boy rides a horse like he was born on one... crying and confused.");
			break;
		case 1:
			graph.addNode(
					"Villager: I must admit, after the necromancer's general came in, everyone has been free of sickness, free of pain!");
			break;
		case 2:
			graph.addNode(
					"Villager: Thought my kid was a picky eater until I saw him try only the most exotic of foods, like dirt, and some green stuff in the corner of my house.");
			break;
		case 3:
			graph.addNode(
					"Villager: While I do like the fact the Necromancer's general is coming in everyday to cure us, I hate the fact he(or she?) is so...weird...");
			break;
		case 4:
			graph.addNode("Villager: Communist jokes are never funny unless everyone gets them!");
			break;
		case 5:
			graph.addNode(
					"Villager: If there are infinite universes, what if we're in one where there only exists one universe?");
			break;
		}
		return graph;
	}

	/**
	 * Get a fixed dialoguegraph for a single villager
	 * 
	 * @return the dialogue graph
	 */
	private DialogueGraph getSetDialogue() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Villager: Welcome to our humble village traveler. *cough,cough*"); // 0
		graph.addNode("P: What happened here?"); // 1
		graph.addNode("Villager: Worry not. It is a mere common cold."); // 2
		graph.addNode(
				"Villager: Oh, it's a sad story, but we are cursed, and we are forever hungry."); // 3
		graph.addNode("P: That's terrible!"); // 4
		graph.addNode(
				"Villager: Indeed, but we have lived as such for years and shall continue to do so. Why are you here traveler?"); // 5
		graph.addNode("P: I have come here to help you!"); // 6
		graph.addNode("P: I have come to slay the Necromancer!"); // 7
		graph.addNode("P: I came for some of your famous mushroom soup"); // 8
		graph.addNode(
				"Villager: Ah, I am sorry traveler the life has been taken from our mushrooms as well and they are poisoned."); // 9
		graph.addNode("P: Nooooooo! well I came for..."); // 10
		graph.addNode("P: Wait how are you even alive, if the mushrooms are poisonous?"); // 11
		graph.addNode("Villager: You see... only one thing sates our eternal hunger... human flesh..."); // 12
		graph.addNode("Villager: Ah how wonderful, if you can slay the Necromancer we will finally be free!"); // 13
		graph.addNode("P: Free from what?"); // 14
		graph.addNode(
				"Villager: Alas traveler, not only has the Necromancer cursed us, but we are also trapped here."); 
		graph.addNode("P: I am not afraid of the necromancer. I will defeat him and rid you of his curse."); // 16
		graph.addNode("P: Don’t worry I will free you!"); // 17
		graph.addNode("Villager: How wonderful, thank you traveler!"); // 18
 		graph.addNode("Villager: Ah then you wouldn’t mind if we ask you for some food?"); // 19
		graph.addNode("P: Sure, why not?"); // 20
		graph.addNode("Villager: I am all out of food, but how about after I kill the Necromancer I treat you to a meal?"); // 21
		graph.addNode("Villager: *drooling* thank you ma boy. You see, only one food satisfies our hunger..."); // 22
		graph.addNode(
				"Villager: That would make the meal taste even better. Why not? We can wait a while longer. We have waited this long after all."); // 23
		graph.addNode("Villager: It is a more of a curse to which there is no cure."); // 24
		graph.addNode("Villager: No, son, me and this village have been sick a long time and we need help."); // 25
		graph.addNode("P: What do you mean sick?"); // 26
		graph.addNode("P: Is there a cure?"); // 27
		graph.addNode("P: That’s a shame."); // 28
		graph.addNode(
				"Villager: There is no cure for us son, but, we would be very grateful to you if you defeated the Necromancer."); // 29
		graph.addNode("P: What do you mean?"); // 30
		graph.addNode("Villager: Our lands, our minds, and our bodies are cursed."); // 31
		graph.addNode("P: Huh?"); // 32
		graph.addNode(
				"Villager: We are struck by an evil hunger. We have become evil creatures who crave human flesh."); // 33
		graph.addNode("P: That’s horrible!"); // 34
		graph.addNode("P: Are you gonna eat me?"); // 35
		graph.addNode("P: How can I save you?"); // 36
		graph.addNode("Villager: Kill the Necromancer and we will be eternally grateful"); // 37
		graph.addNode("Villager: Is that an invitation?"); // 38
		graph.addNode("P: Yes"); // 39
		graph.addNode("Villager: Get out of here before it is too late."); // 40
		graph.addNode("P: No"); // 41
		graph.addNode("Villager: Well then, get going before we can’t help ourselves."); // 42
		graph.addNode("Villager: Aye, just give the Necromancer a good punch for me."); // 43
		graph.addNode("P: I will."); // 44
		graph.addNode("P: Are you alright?"); // 45
		graph.addNode("Villager: Treacherous mountains lie to the west. Only a maze stands between us and the Necromancer's monsters to the South."); // 46
		graph.addNode("Villager: Human flesh... You should go."); // 47
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 45);
		graph.addEdge(1, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 8);
		graph.addEdge(5, 6);
		graph.addEdge(8, 9);
		graph.addEdge(5, 7);
		graph.addEdge(9, 11);
		graph.addEdge(11, 12);
		graph.addEdge(7, 13);
		graph.addEdge(13, 14);
		graph.addEdge(14, 15);
		graph.addEdge(16, 18);
		graph.addEdge(17, 18);
		graph.addEdge(15, 46);
		graph.addEdge(46, 16);
		graph.addEdge(46, 17);
		graph.addEdge(22, 47);
		graph.addEdge(6, 19);
		graph.addEdge(19, 20);
		graph.addEdge(20, 22);
		graph.addEdge(19, 21);
		graph.addEdge(21, 23);
		
		graph.addEdge(25, 26);
		graph.addEdge(26, 24);
		graph.addEdge(24, 30);
		graph.addEdge(25, 27);
		graph.addEdge(27, 29);
		graph.addEdge(29, 30);
		graph.addEdge(30, 31);
		graph.addEdge(31, 32);
		graph.addEdge(32, 33);
		graph.addEdge(33, 34);
		graph.addEdge(34, 43);
		graph.addEdge(43, 44);
		graph.addEdge(33, 35);
		graph.addEdge(35, 38);
		graph.addEdge(39, 40);
		graph.addEdge(38, 41);
		graph.addEdge(41, 42);
		graph.addEdge(33, 36);
		graph.addEdge(36, 37);
		graph.addEdge(2, 28);
		graph.addEdge(45, 25);

		graph.getNode(0).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				setSpecialDialogue = false;			
				player.getStoryStats().setVillagerConversations(1);
			}
			
		});
		return graph;
	}
	/**
	 * Special Hidden Dialogue
	 * @return the dialogue graph
	 */
	private DialogueGraph getHiddenDialogue() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Villager: You come up in here and kill things like it's all for the common good, but what right do you have to decide they should die?");
		return graph;
	}
	/**
	 * Whether or not the villager has it's special dialogue
	 * @param b sets the boolean
	 */
	public void setSpecialDialogue(boolean b) {
		this.setSpecialDialogue = b;
	}
	/**
	 * Whether or not the villager has the hidden dialogue
	 * @param b sets the boolean
	 */
	public void setHiddenVillager(boolean b) {
		this.hiddenVillager = b;
	}
}
