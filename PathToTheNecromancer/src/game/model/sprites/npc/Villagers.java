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

import game.model.DialogueGraph;
import game.model.sprites.CharacterSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Player;

/**
 * 
 * @author ToTryHardRay
 * This is the class for the animation of the villagers
 *
 */
public class Villagers extends CharacterSprites implements InteractionSprites {
	private ColorAndGender color;
	private ColorAndGender gender;
	private Texture villagerTexture;
	private static final int VILLAGER_HEIGHT = 30;
	private static final int VILLAGER_WIDTH = 15;

	public Villagers(ColorAndGender color, ColorAndGender gender) {
		this.color = color;
		this.gender = gender;
		setTextureValues();
		// Set the size of the Villager
		setBounds(0, 0, VILLAGER_WIDTH, VILLAGER_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

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
		frames.add(new TextureRegion(villagerTexture,(7 + 0) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 3) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 2) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 1) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 4) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 6) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 5) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(7 + 4) * width, rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move left
		frames.add(new TextureRegion(villagerTexture,(0 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(3 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(2 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(1 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(6 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(5 + 14) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(4 + 14) * width, rowHeight, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move right
		frames.add(new TextureRegion(villagerTexture,(6 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(3 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(4 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(5 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(0 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(1 + 21) * width, rowHeight, width, height));
		frames.add(new TextureRegion(villagerTexture,(2 + 21) * width, rowHeight, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		super.setMoveDownLeft(super.getMoveDown());
		super.setMoveDownRight(super.getMoveDown());
		super.setMoveUpRight(super.getMoveUp());
		super.setMoveUpLeft(super.getMoveUp());
		super.setAnimationSpeed(3);
		super.setRunningSpeed(2);
	}

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
		public DialogueGraph getDialogue(Player player) {
			DialogueGraph graph = new DialogueGraph();
			Random rand = new Random();
			int x = rand.nextInt(7);
			switch(x){
			default:
				break;
			case 0:
				graph.addNode("I do admit, my little boy rides a horse like he was born on one, crying, naked, and confused");
				break;
			case 1:
				graph.addNode("I must admit, after the necromancer's general came in, everyone has been free of sickness, free of pain!");
				break;
			case 2:
				graph.addNode("Thought my kid was a picky eater until I saw him try only the most exotic of foods, like dirt, and some green stuff in the corner of my house");
				break;
			case 3:
				graph.addNode("While I do like the fact the necromancer's general is coming in everyday to cure us, I hate the fact he(or she?) is so...weird...");
				break;
			case 4:
				graph.addNode("Communist jokes are never funny unless everyone gets them!");
				break;
			case 5:
				graph.addNode("I have to admit, the sounds of the necromancer's skeleton army is slightly less offputting thanks to the xylophone sound the skeletons make as they walk");
				break;
			case 6:
				graph.addNode("If there are infinite universes, what if we're in one where there only exists one universe?");
				break;
			}
			return graph;
		}
		public DialogueGraph getSetDialogue(Player player) {
			DialogueGraph graph = new DialogueGraph();
			graph.addNode("Welcome to out humble village traveler. *cough,cough*");
			graph.addNode("what happened here?");
			graph.addNode("I mean a common cold.");
			graph.addNode("Oh, it's a sad story, the necromancer used the hippie to siphon the life from this land and our people to the other side of the mountain.");
			graph.addNode("That's terrible!");
			graph.addNode("Indeed, but we have lived here for years and shall continue to do so, why are you here traveler?");
			graph.addNode("I have come here to help you!");
			graph.addNode("I have come to slay the necromancer!");
			graph.addNode("I came for some of your famous mushroom soup");
			graph.addNode("Ah, I am sorry traveler the life has been taken from our mushrooms as well and now all that is left is poison inside of them.");
			graph.addNode("Nooooooo! well I came for...");
			graph.addNode("Wait how are you even alive, if the mushroom are poisonous?");
			graph.addNode("Well, you see... We survive on the flesh of mortals, GET HIM LADS!");
			graph.addNode("Ah how wonderful, if you can slay the necromancer we will finally be free!");
			graph.addNode("What do you mean, aren�t you free right now?");
			graph.addNode("Alas traveler, we built a maze to the south to prevent monsters from attacking when our food grew short, and the path west is too dangerous.");
			graph.addNode("Well I just freed the west side.");
			graph.addNode("Don�t worry I will free you!");
			graph.addNode("How wonderful, thank you traveler!");
			graph.addNode("Ah then you wouldn�t mind if we ask you for some food?");
			graph.addNode("Sure why not?");
			graph.addNode("I am all out of food, but how about after I kill the Necromancer I treat you to a meal?");
			graph.addNode("*drooling* thank you ma boy, GET HIM LADS");
			graph.addNode("That would make the meal taste even better, sure why not, we can wait a while longer. We have waited this long after all.");
			graph.addNode("Trust me you don't want to know.");
			graph.addNode("No, son me and this village have been sick a long time and we need help.");
			graph.addNode("What do you mean sick?");
			graph.addNode("Is there a cure?");
			graph.addNode("That�s a shame.");
			graph.addNode("There is no cure for us son, the only way to help us is to defeat the necromancer, only then may we be free.");
			graph.addNode("What do you mean?");
			graph.addNode("I mean our bodies and the land aren�t the only thing affected by the Necromancer my boy.");
			graph.addNode("Huh?");
			graph.addNode("Our minds have been taken over by the curse. It turned us into evil creatures and now we hunger for flesh.");
			graph.addNode("That�s horrible!");
			graph.addNode("Are you gonna eat me?");
			graph.addNode("How can I save you?");
			graph.addNode("Like I said kill the Necromancer and we will be eternally grateful");
			graph.addNode("Is that an invitation?");
			graph.addNode("Yes");
			graph.addNode("GET HIM LADS");
			graph.addNode("No");
			graph.addNode("Well then, get going before we can�t help ourselves.");
			graph.addNode("Ay, just give the necromancer a good punch for me and let us pass on.");
			graph.addNode("I will.");
			
			graph.addEdge(1,2);
			graph.addEdge(2,4);
			graph.addEdge(4,5);
			graph.addEdge(5,6);
			graph.addEdge(6,9);
			graph.addEdge(9,10);
			graph.addEdge(10,11);
			graph.addEdge(11,8);
			graph.addEdge(11,7);
			graph.addEdge(11,12);
			graph.addEdge(12,13);
			graph.addEdge(8,14);
			graph.addEdge(14,15);
			graph.addEdge(15,16);
			graph.addEdge(16,17);
			graph.addEdge(17,19);
			graph.addEdge(19,25);
			graph.addEdge(16,18);
			graph.addEdge(18,19);
			graph.addEdge(7,20);
			graph.addEdge(20,21);
			graph.addEdge(21,23);
			graph.addEdge(20,22);
			graph.addEdge(22,24);
			graph.addEdge(1,3);
			graph.addEdge(26,27);
			graph.addEdge(27,46);
			graph.addEdge(46,31);
			graph.addEdge(26,28);
			graph.addEdge(28,30);
			graph.addEdge(30,31);
			graph.addEdge(31,32);
			graph.addEdge(32,33);
			graph.addEdge(33,34);
			graph.addEdge(34,35);
			graph.addEdge(35,44);
			graph.addEdge(44,45);
			graph.addEdge(45,25);
			graph.addEdge(34,36);
			graph.addEdge(36,39);
			graph.addEdge(39,40);
			graph.addEdge(40,41);
			graph.addEdge(39,42);
			graph.addEdge(42,43);
			graph.addEdge(43,25);
			graph.addEdge(34,37);
			graph.addEdge(37,38);
			graph.addEdge(38,25);
			graph.addEdge(3,29);
			graph.addEdge(29,47);

			return graph;
		}
		
}
