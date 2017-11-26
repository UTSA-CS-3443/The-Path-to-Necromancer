package game.model.sprites.npc;

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
import game.model.sprites.player.Player;

/**
 * 
 * @author ToTryHardRay
 * This is the class for animating the knight
 *
 */
public class Knight extends CharacterSprites {
	private Texture KnightTexture;
	private static final int KNIGHT_HEIGHT = 36;
	private static final int KNIGHT_WIDTH = 32;

	public Knight() {
		setTextureValues();
		// Set the size of the Knight
		setBounds(0, 0, KNIGHT_WIDTH, KNIGHT_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	@Override
	public void setTextureValues() {
		int width = 132;
		int height = 151;
		int rowHeight = 150;

		// Use the Knight animations until we have the player SpriteSheet
		KnightTexture = new Texture("CharacterSprites/Knight.png");

		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(KnightTexture, 0, 0, width, height));
		
		// move down
		frames.add(new TextureRegion(KnightTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (3) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (4) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (6) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture, (4) * width, 0 * rowHeight, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up
		frames.add(new TextureRegion(KnightTexture,(0) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 1 * rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move left
		frames.add(new TextureRegion(KnightTexture,(4) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 2 * rowHeight, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move right
		frames.add(new TextureRegion(KnightTexture,(6) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 3 * rowHeight, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-right
		frames.add(new TextureRegion(KnightTexture,(1) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 4 * rowHeight, width, height));
		super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-left
		frames.add(new TextureRegion(KnightTexture,(1) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 5 * rowHeight, width, height));
		super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move up-left
		frames.add(new TextureRegion(KnightTexture,(1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 6 * rowHeight, width, height));
		super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		
		// move up-right
		frames.add(new TextureRegion(KnightTexture,(1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(3) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(2) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(6) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(KnightTexture,(4) * width, 7 * rowHeight, width, height));
		super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();
		super.setAnimationSpeed(2);
		super.setRunningSpeed(1);

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

		// coordinates of the Knight's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-KNIGHT_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(KNIGHT_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-KNIGHT_WIDTH / 2, -KNIGHT_HEIGHT / 2); // bottom
		// left
		vertice[3] = new Vector2(KNIGHT_WIDTH / 2, -KNIGHT_HEIGHT / 2); // bottom
		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);

	}
	/**
	 * 
	 * Takes in a player and this function is the dialogue between
	 * the player and the knight outside of necromancer lair
	 * @param player
	 * @return
	 */
	public DialogueGraph getDialogue(Player player) {
		DialogueGraph graph = new DialogueGraph();
		
		graph.addNode("I AM THE NECROMANCER’S MOST TRUSTED SERVANT!"); // 0
		graph.addNode("Cool!"); // 1
		graph.addNode("YES IT IS VERY COOL, I HAVE SERVED HIM FOR MANY YEARS!"); // 2
		graph.addNode("So that means you really know the guy huh?"); // 3
		graph.addNode("YES WE ARE GOOD PALS"); // 4
		graph.addNode("Are you though?"); // 5
		graph.addNode("WHAT DO YOU MEAN?"); // 6
		graph.addNode("I mean looking at things right now, he seems to hold all the power and here you are in a dusty suit of armor just sitting around."); // 7
		graph.addNode("I AM HIS MOST POWERFUL SERVANT!"); // 8
		graph.addNode("Even more powerful than him?"); // 9
		graph.addNode("Then why serve him?"); // 10
		graph.addNode("YES, WAIT, WHY SHOULD I SERVE WHEN I AM THE GREATER BEING!"); // 11
		graph.addNode("Exactly you should open up your own castle and do your thing elsewhere, you know maybe you could use your immense power for good?"); // 12
		graph.addNode("WHY WOULD I DO THAT?"); // 13
		graph.addNode("Because deep down you still have a heart? Even if it is unbeating and cold and lifeless, it still has hopes for humans?"); // 14
		graph.addNode("Being a hero has no pay, but I hear mercenary work pays well."); // 15
		graph.addNode("PFFFFT, YEAH RIGHT DIE HERO"); // 16
		graph.addNode("OOOH MONEY YOU SAY? FINE THANKS FOR THE TALK HERO"); // 17
		graph.addNode("YOU KNOW NOT WHAT YOU SPEAK OF PREPARE FOR DEATH (trips and falls)"); // 18
		graph.addNode("Pffft no you aren’t."); // 19
		graph.addNode("WHAT? IF I AM NOT THEN WHO IS?"); // 20
		graph.addNode("The don?"); // 21
		graph.addNode("The hippie?"); // 22
		graph.addNode("THE DON IS WORTHLESS AND MEASLY HE LEADS A SMALL BANDIT GROUP"); // 23
		graph.addNode("THE HIPPIE IS WEAK AND ONLY WATCHES A VILLAGE WHILE I WATCH OVER THE ARMIES"); // 24
		graph.addNode("That bandit group now has benefits though (only if bandit union)"); // 25
		graph.addNode("The Don is weak I guess …"); // 26
		graph.addNode("OOO BENEFITS? EVEN I DON’T HAVE THOSE, I GUESS I REALLY AREN’T THE MOST TRUSTED, I FEEL BETRAYED, MAYBE I SHOULD JOIN YOU AND BE A HERO?"); // 27
		graph.addNode("What armies? I have been fighting only 1 enemy at a time. Poor coordination if you ask me."); // 28
		graph.addNode("OOF, YOU WOUND ME HERO, MAYBE I SHOULD WHIP THEM INTO SHAPE?"); // 29
		graph.addNode("Yeah you should do that now!"); // 30
		graph.addNode("GOOD IDEA, THANK YOU HERO"); // 31
		
		graph.addEdge(0,1);
		graph.addEdge(1,2);		
		graph.addEdge(2,3);
		graph.addEdge(3,4);
		graph.addEdge(4,5);
		graph.addEdge(5,6);
		graph.addEdge(6,7);
		graph.addEdge(7,8);
		graph.addEdge(8,9);
		graph.addEdge(9,11);
		graph.addEdge(11,12);
		graph.addEdge(12,13);
		graph.addEdge(13,14);
		graph.addEdge(14,16);
		graph.addEdge(13,15);
		graph.addEdge(15,17);
		graph.addEdge(8,10);
		graph.addEdge(10,18);
		graph.addEdge(0,19);
		graph.addEdge(19,20);
		graph.addEdge(20,21);
		graph.addEdge(21,23);
		graph.addEdge(23,25);
		graph.addEdge(25,27);
		graph.addEdge(27,15);
		graph.addEdge(23,26);
		graph.addEdge(26,22);
		graph.addEdge(22,24);
		graph.addEdge(24,28);
		graph.addEdge(28,29);
		graph.addEdge(29,30);
		graph.addEdge(30,31);
		graph.addEdge(20,22);
		
		return graph;
	}

}
