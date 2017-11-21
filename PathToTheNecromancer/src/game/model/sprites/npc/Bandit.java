package game.model.sprites.npc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.model.DialogueGraph;
import game.model.sprites.CharacterSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Player;
/**
 * 
 * @author ToTryHardRay
 * The class for the bandit sprite with the dialogue
 *
 */
public class Bandit extends CharacterSprites implements InteractionSprites {
	private static final int BANDIT_HEIGHT = 30;
	private static final int BANDIT_WIDTH = 15;
	private Texture banditTexture;

	public Bandit() {
		setTextureValues();
		// Set the size of the Bandit
		setBounds(0, 0, BANDIT_WIDTH, BANDIT_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}
	@Override
	public DialogueGraph getDialogue(Player player) {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Be still traveler you are about to walk upon the land of bloody foot himself, and his mother was foot herself!");
		graph.addNode("No need to worry fellow citizen bandit, I'm here as a representative for the badit/Don union");
		graph.addNode("How dare you speak to me lowly bandit! I am the strong and powerful chosen one!");
		graph.addNode("No need to worry, just passing through");
		graph.addNode("The bandit/Don union?");
		graph.addNode("Why yes! We strive for equal shares in all plundering for bandits everywhere! Have you been feeling disgruntled at all recently?");
		graph.addNode("Indeed, we have noticed that your camp hasn't joined, so we came to check up on you");
		graph.addNode("Yes, would you like to join?");
		graph.addNode("Well, kinda, we don't even get dental...");
		graph.addNode("Oh, well, we never received any message from you.");
		graph.addNode("Never I stay with my Don, I'll never betray him!");
		graph.addNode("You don't even get dental?! How horrible, we have to come in to check this out!");
		graph.addNode("None, well we did send you multiple messages... maybe someone has been tossing them out?");
		graph.addNode("Guess it must've been lost in the mail!");
		graph.addNode("Please go ahead.");
		graph.addNode("Well, now that I think of it, the Don has been throwing out a lot of paper recently...");
		graph.addNode("Yeah i gue.... wait a minute, we don't have mail out here! You're lying moron!");
		graph.addNode("I see, I see... perhaps he's trying to prevent you from unionizing?");
		graph.addNode("I guess so... tell me, what does one do in a union?");
		graph.addNode("Well, first, have you been feeling disgruntled at all?");
		graph.addNode("Well, they protest my good sir. Teach the Don what's up. You need to fight!");
		graph.addNode("Good idea, let's do this! I'm tired of pretending to be Italian!");
		graph.addNode("The chosen one?");
		graph.addNode("haha, you think we allow sissies to pass through our camp?! ie weak one!");
		graph.addNode("Yes I am the chosen bandit, the one to lead all bandits to banditdom!");
		graph.addNode("COOL! Have any powers?");
		graph.addNode("Yes, watch me remove my thumb from my hand!");
		graph.addNode("I can't your eyes would melt from your eye sockets");
		graph.addNode("The Don can do that too! Except he just chops them off of those who snitch on him!");
		graph.addNode("You lie, show me now");
		graph.addNode("Yes, but can he put them back on?! *performs finger trick*");
		graph.addNode("My power derives from the sun! Stare at it, and you shall feel your eyes burning with my power!");
		graph.addNode("Alright, I lied, but may I pass through your camp?");
		graph.addNode("*silence*");
		graph.addNode("Fine! *bandit begins staring at sun*");
		graph.addNode("We kill liars here fool, die!");
		graph.addNode("*do it again*");
		graph.addNode("Do you believe me now?!");
		graph.addNode("Attempt to sneak by (if rogue allow to pass)");
		graph.addNode("Yes, yes are you feeling my power?!");
		graph.addNode("*even more silence*");
		graph.addNode("Please pass through my almighty wizard.");
		graph.addNode("Wait, where do you think you're going?");
		graph.addNode("*tears rolling down eyes* I feel it! MAKE IT STOP!!!!");
		graph.addNode("Just look away weakling.");
		graph.addNode("NEVER! BURN WEAKLING BURN!");
		graph.addNode("*do it again*");
		graph.addNode("I have not done it once, but twice! Do you believe me now?!");
		graph.addNode("*crying* I'm sorry for doubting you sun lord, please pass");
		graph.addNode("*screams with tears rolling down face*");
		graph.addNode("PLEASE STOP DISMEMBERING YOURSELF, IT'S UNNATURAL!");
		graph.addNode("NEVER! *do it again*");
		graph.addNode("*begins crying* Why do you keep hurting yourself, just go through the camp.");
		
		graph.addEdge(0,1);
		graph.addEdge(0,2);
		graph.addEdge(0,3);
		graph.addEdge(3,23);
		graph.addEdge(1,4);
		graph.addEdge(4,5);
		graph.addEdge(4,6);
		graph.addEdge(4,7);
		graph.addEdge(7,10);
		graph.addEdge(5,8);
		graph.addEdge(6,9);
		graph.addEdge(8,11);
		graph.addEdge(19,8);
		graph.addEdge(11,14);
		graph.addEdge(9,12);
		graph.addEdge(9,13);
		graph.addEdge(12,15);
		graph.addEdge(15,17);
		graph.addEdge(13,16);
		graph.addEdge(17,18);
		graph.addEdge(18,19);
		graph.addEdge(18,20);
		graph.addEdge(20,21);
		graph.addEdge(2,22);
		graph.addEdge(22,24);
		graph.addEdge(24,25);
		graph.addEdge(25,26);
		graph.addEdge(25,27);
		graph.addEdge(26,28);
		graph.addEdge(28,30);
		graph.addEdge(30,33);
		graph.addEdge(27,29);
		graph.addEdge(29,31);
		graph.addEdge(29,32);
		graph.addEdge(31,34);
		graph.addEdge(32,35);
		graph.addEdge(33,36);
		graph.addEdge(33,37);
		graph.addEdge(34,38);
		graph.addEdge(34,39);
		graph.addEdge(36,40);
		graph.addEdge(40,46);
		graph.addEdge(40,47);
		graph.addEdge(46,50);
		graph.addEdge(50,51);
		graph.addEdge(51,52);
		graph.addEdge(47,41);
		graph.addEdge(37,41);
		graph.addEdge(38,42);
		graph.addEdge(39,43);
		graph.addEdge(43,44);
		graph.addEdge(43,45);
		graph.addEdge(44,48);
		graph.addEdge(45,49);
		
		return null;
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

		// coordinates of the Bandit's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-BANDIT_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(BANDIT_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-BANDIT_WIDTH / 2, -BANDIT_HEIGHT / 2); // bottom
		// left
		vertice[3] = new Vector2(BANDIT_WIDTH / 2, -BANDIT_HEIGHT / 2); // bottom
		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);
	}

	@Override
	public void setTextureValues() {
		
		// Use the Guard animations until we have the player SpriteSheet
		 banditTexture = new Texture("EnemySprites/Bandit.png");
		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(banditTexture, 0, 0, banditTexture.getWidth(), banditTexture.getHeight()));
	}
	

}
