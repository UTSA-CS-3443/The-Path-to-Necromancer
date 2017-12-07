package game.model.sprites.npc;

import java.util.ArrayList;

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
 * The Fighter is the Necromancer's first general
 * 
 * @author ToTryHardRay
 * @author enigma-phi
 *
 */
public class Fighter extends CharacterSprites implements InteractionSprites {
	/**
	 * The Texture associated with the fighter
	 */
	private Texture fighterTexture;
	/**
	 * The TextureRegions for the crying animation
	 */
	private ArrayList<TextureRegion> crying;
	/**
	 * The TextureRegions for the first fighting animation
	 */
	private ArrayList<TextureRegion> fighting1;
	/**
	 * The TextureRegions for the second fighting animations
	 */
	private ArrayList<TextureRegion> fighting2;
	/**
	 * The TextureRegions for the third fighting animation
	 */
	private ArrayList<TextureRegion> fighting3;
	/**
	 * Whether or not the Fighter is currently crying
	 */
	private boolean isCrying;
	/**
	 * Whether or not the Fighter is currently engaged in the first fighting
	 * animation
	 */
	private boolean isFighting1;
	/**
	 * Whether or not the Fighter is currently engaged in the second fighting
	 * animation
	 */
	private boolean isFighting2;
	/**
	 * Whether or not the Fighter is currently engaged in the third fighting
	 * animation
	 */
	private boolean isFighting3;
	/**
	 * The time change since the last frame change
	 */
	private float timeChange;
	/**
	 * The index of the current frame for the Fighter animation
	 */
	private int currentFrame;
	/**
	 * The Fighter's width
	 */
	private static final int FIGHTER_HEIGHT = 30;
	/**
	 * The Fighter's height
	 */
	private static final int FIGHTER_WIDTH = 15;

	/**
	 * Constructor for fighter
	 */
	public Fighter() {
		setTextureValues();
		// Set the size of the Fighter
		setBounds(0, 0, FIGHTER_WIDTH, FIGHTER_HEIGHT);

		// Set the initial animation
		setRegion(super.getStandingRegion());
	}

	/**
	 * Set the TextureRegions associated with the Fighter
	 */
	@Override
	public void setTextureValues() {
		int width = 32;
		int height = 70;
		int rowHeight = 70;
		int specialWidth = 60;

		// Use the fighter animations until we have the player SpriteSheet
		fighterTexture = new Texture("CharacterSprites/Fighter.png");

		fighting1 = new ArrayList<TextureRegion>();
		fighting2 = new ArrayList<TextureRegion>();
		fighting3 = new ArrayList<TextureRegion>();

		fighting1.add(new TextureRegion(fighterTexture, 0 * specialWidth, 8 * rowHeight, specialWidth, height));
		fighting1.add(new TextureRegion(fighterTexture, 1 * specialWidth, 8 * rowHeight, specialWidth, height));
		fighting1.add(new TextureRegion(fighterTexture, 2 * specialWidth, 8 * rowHeight, specialWidth, height));

		fighting2.add(new TextureRegion(fighterTexture, 3 * specialWidth, 8 * rowHeight, specialWidth, height));
		fighting2.add(new TextureRegion(fighterTexture, 4 * specialWidth, 8 * rowHeight, specialWidth, height));
		fighting2.add(new TextureRegion(fighterTexture, 5 * specialWidth, 8 * rowHeight, specialWidth, height));

		fighting3.add(new TextureRegion(fighterTexture, 6 * specialWidth, 8 * rowHeight, specialWidth, height));
		fighting3.add(new TextureRegion(fighterTexture, 7 * specialWidth, 8 * rowHeight, specialWidth, height));
		fighting3.add(new TextureRegion(fighterTexture, 8 * specialWidth, 8 * rowHeight, specialWidth, height));

		crying = new ArrayList<TextureRegion>();
		crying.add(new TextureRegion(fighterTexture, 0, 9 * rowHeight, 32, 49));
		crying.add(new TextureRegion(fighterTexture, 32, 9 * rowHeight, 32, 49));

		// Array of frames used for the animations
		Array<TextureRegion> frames = new Array<TextureRegion>();

		// Set the default standing region of the character.
		super.setStandingRegion(new TextureRegion(fighterTexture, 0, 0, width, height));

		// move down
		frames.add(new TextureRegion(fighterTexture, (0) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (3) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (6) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 0 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (6) * width, 0 * rowHeight, width, height));
		super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up
		frames.add(new TextureRegion(fighterTexture, (0) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (3) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (6) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 1 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (6) * width, 1 * rowHeight, width, height));
		super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-left
		frames.add(new TextureRegion(fighterTexture, (0) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (3) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (6) * width, 2 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 2 * rowHeight, width, height));
		super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move down-right
		frames.add(new TextureRegion(fighterTexture, (6) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (3) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (0) * width, 3 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 3 * rowHeight, width, height));
		super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move right
		frames.add(new TextureRegion(fighterTexture, (0) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (3) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (0) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 4 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 4 * rowHeight, width, height));
		super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move Left
		frames.add(new TextureRegion(fighterTexture, (0) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (3) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (6) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 5 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 5 * rowHeight, width, height));
		super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up-left
		frames.add(new TextureRegion(fighterTexture, (0) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (3) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (2) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (6) * width, 6 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 6 * rowHeight, width, height));
		super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		// move up-right
		frames.add(new TextureRegion(fighterTexture, (6) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (3) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (4) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (5) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (0) * width, 7 * rowHeight, width, height));
		frames.add(new TextureRegion(fighterTexture, (1) * width, 7 * rowHeight, width, height));
		super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
		frames.clear();

		super.setAnimationSpeed(2);
		super.setRunningSpeed(1);

	}

	/**
	 * Define the physics body for the Fighter
	 * 
	 * @param world
	 *            is the world to place the fighter in
	 * @param x
	 *            is the fighter's x-coordinate
	 * @param y
	 *            is the fighter's y-coordinate
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

		// coordinates of the Fighter's collision box
		Vector2[] vertice = new Vector2[4];
		vertice[0] = new Vector2(-FIGHTER_WIDTH / 2, 0); // top left
		vertice[1] = new Vector2(FIGHTER_WIDTH / 2, 0); // top right
		vertice[2] = new Vector2(-FIGHTER_WIDTH / 2, -FIGHTER_HEIGHT / 2); // bottom
		// left
		vertice[3] = new Vector2(FIGHTER_WIDTH / 2, -FIGHTER_HEIGHT / 2); // bottom
		// right

		// set the world
		rect.set(vertice);
		fdef.shape = rect;
		this.getBody().createFixture(fdef).setUserData(this);

	}

	/**
	 * Update the fighter's animation
	 * 
	 * @param dt
	 *            is the change in time since the last render
	 * @return the textureRegion to render
	 */
	@Override
	public TextureRegion getFrame(float dt) {
		// get first fighting animation
		if (isFighting1) {
			this.timeChange += dt;
			if (this.timeChange > 0.5) {
				this.timeChange = 0;
				this.currentFrame++;
				if (this.currentFrame == 3) {
					this.isFighting1 = false;
					setBounds(0, 0, FIGHTER_WIDTH, FIGHTER_HEIGHT);
					return super.getFrame(dt);
				}
			}
			return this.fighting1.get(this.currentFrame);
			// get second fighting animation
		} else if (isFighting2) {
			this.timeChange += dt;
			if (this.timeChange > 0.5) {
				this.timeChange = 0;
				this.currentFrame++;
				if (this.currentFrame == 3) {
					this.isFighting2 = false;
					setBounds(0, 0, FIGHTER_WIDTH, FIGHTER_HEIGHT);
					return super.getFrame(dt);
				}
			}
			return this.fighting2.get(this.currentFrame);
			// get third fighting animation
		} else if (isFighting3) {
			this.timeChange += dt;
			if (this.timeChange > 0.5) {
				this.timeChange = 0;
				this.currentFrame++;
				if (this.currentFrame == 3) {
					this.isFighting3 = false;
					setBounds(0, 0, FIGHTER_WIDTH, FIGHTER_HEIGHT);
					return super.getFrame(dt);
				}
			}
			return this.fighting3.get(this.currentFrame);
			// get crying animation
		} else if (isCrying) {
			this.timeChange += dt;
			if (this.timeChange > 0.5) {
				this.timeChange = 0;
				this.currentFrame++;
			}
			return this.crying.get(this.currentFrame % 2);
		}
		// get default animation
		return super.getFrame(dt);
	}

	/**
	 * Create the Fighter's dialogue graph
	 * 
	 * @param player
	 *            is the player to base the dialogue graph off of
	 * @return is the dialogue graph
	 */
	@Override
	public DialogueGraph getDialogue(Player player) {
		DialogueGraph graph = new DialogueGraph();
		if (player.getStoryStats().getFighterEncounter() == 2) {
			graph.addNode("*You see the Fighter bawling his eyes out.");
			return graph;
		} else if (player.getStoryStats().getFighterEncounter() == 1) {
			graph.addNode("Fighter: Get away from me coward.");
			graph.getNode(0).addActor(new DialogueActor() {

				@Override
				public void act(Player player, MapManager manager) {
					setIsFighting3(true);
				}

			});
			return graph;
		}
		graph.addNode("Fighter: Hold there young one."); // 0
		graph.addNode("Fighter: I am the Fighter. Great general of the Necromancer and esteemed 11th degree black-belt in any martial art you can think of."); // 1
		graph.addNode("Fighter: AND I CHALLENGE YOU TO A DUEL!"); // 2
		graph.addNode("P: I refuse to fight you. I am a pacifist."); // 3
		graph.addNode("P: YOU\'RE ON!!!"); // 4
		graph.addNode("Fighter: Weakling!"); // 5
		graph.addNode("P: Am I weak? Or are you the weak one for seeking satisfaction in a match of physical combat?"); // 6
		graph.addNode("P: I am leaving."); // 7
		graph.addNode("Fighter: Have a nice life! The bandits will get ya!"); // 8
		graph.addNode("Fighter: ... I am so confused... did you just insult me? I don't know, just go."); // 9
		graph.addNode("Fighter: HIYA!"); // 10
		graph.addNode("P: *jump spinning side kick*"); // 11
		graph.addNode("P: *gentle smack*"); // 12
		graph.addNode("Fighter: OW OW OW. That hurt!"); // 13
		graph.addNode("Fighter: *unphased from strong kick*  Mwahaha! You are no match for my physical prowess!"); // 14
		graph.addNode("P: I barely touched you..."); // 15
		graph.addNode("Fighter: Get away from me, you big meanie."); // 16

		// add edges
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		graph.addEdge(5, 6);
		graph.addEdge(5, 7);
		graph.addEdge(6, 9);
		graph.addEdge(7, 8);
		graph.addEdge(4, 10);
		graph.addEdge(10, 11);
		graph.addEdge(10, 12);
		graph.addEdge(11, 14);
		graph.addEdge(14, 12);
		graph.addEdge(12, 13);
		graph.addEdge(13, 15);
		graph.addEdge(15, 16);

		// add some actors
		graph.getNode(0).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				setIsFighting1(true);
			}

		});
		graph.getNode(3).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setFighterEncounter(1);
			}

		});
		graph.getNode(4).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.getStoryStats().setFighterEncounter(2);
			}

		});
		graph.getNode(5).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				setIsFighting2(true);
			}

		});
		graph.getNode(10).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				setIsFighting2(true);
			}

		});
		DialogueActor actor = new DialogueActor() {
			@Override
			public void act(Player player, MapManager manager) {
				setIsFighting3(true);
			}
		};
		graph.getNode(8).addActor(actor);
		graph.getNode(9).addActor(actor);
		graph.getNode(13).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				setIsCrying(true);
			}

		});

		return graph;
	}

	/**
	 * Set the fighter to be crying
	 * 
	 * @param b
	 *            is the boolean value to set to
	 */
	public void setIsCrying(boolean b) {
		this.isCrying = b;
		this.timeChange = 0;
		this.currentFrame = 0;
	}

	/**
	 * Set the fighter to be in the first fighting animation
	 * 
	 * @param b
	 *            is the boolean value to set to
	 */
	public void setIsFighting1(boolean b) {
		this.isFighting1 = b;
		this.timeChange = 0;
		this.currentFrame = 0;
		setBounds(0, 0, FIGHTER_WIDTH * 2, FIGHTER_HEIGHT);
	}

	/**
	 * Set the fighter to be in the second fighting animation
	 * 
	 * @param b
	 *            is the boolean value to set to
	 */
	public void setIsFighting2(boolean b) {
		this.isFighting2 = b;
		this.timeChange = 0;
		this.currentFrame = 0;
		setBounds(0, 0, FIGHTER_WIDTH * 2, FIGHTER_HEIGHT);
	}

	/**
	 * Set the fighter to be in the third fighting animation
	 * 
	 * @param b
	 *            is the boolean value to set to
	 */
	public void setIsFighting3(boolean b) {
		this.isFighting3 = b;
		this.timeChange = 0;
		this.currentFrame = 0;
		setBounds(0, 0, FIGHTER_WIDTH * 2, FIGHTER_HEIGHT);
	}
}
