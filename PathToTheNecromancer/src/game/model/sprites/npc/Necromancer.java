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

import game.controller.MapManager;
import game.controller.story.DialogueActor;
import game.model.DialogueGraph;
import game.model.sprites.CharacterSprites;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Player;

/**
 * 
 * The Necromancer is the main villain of the game.
 * 
 * @author enigma-phi
 *
 */
public class Necromancer extends CharacterSprites implements InteractionSprites{
    /**
     * The pixel width of the Necromancer sprite
     */
    private static final int NEC_WIDTH = 40;
    /**
     * The pixel height of the Necromancer sprite
     */
    private static final int NEC_HEIGHT = 50;
    /**
     * The texture used to draw and render the Necromancer sprite
     */
    private Texture necromancerTexture;
    /**
     * The scaling factor used to draw the Necromancer.
     */
    private static float SCALINGFACTOR = 0.65f;

    /**
     * Create the Necromancer object.
     */
    public Necromancer() {
        super();
        // Initialize the different texture regions associated with the Necromancer
        setTextureValues();
        // Set the size of the Necromancer
        setBounds(0, 0, NEC_WIDTH * SCALINGFACTOR, NEC_HEIGHT * SCALINGFACTOR);

        // Set the initial animation
        setRegion(super.getStandingRegion());
    }

    /**
     * Set the different TextureRegions associated with the Necromancer for
     * animation.
     */

    @Override
    public void setTextureValues() {
        necromancerTexture = new Texture("Necromancer2.png");

        // Set the default standing region of the Necromancer.
        super.setStandingRegion(new TextureRegion(necromancerTexture, 20, 140, 40, 50));
        // Array of frames used for the animations
        Array<TextureRegion> frames = new Array<TextureRegion>();

        // Set the animations for moving Down on the map
        frames.add(new TextureRegion(necromancerTexture, 20, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 20, 201, 40, 50));
        super.setMoveDown(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Up on the map
        frames.add(new TextureRegion(necromancerTexture, 60, 140, 50, 50));
        frames.add(new TextureRegion(necromancerTexture, 60, 201, 50, 50));
        super.setMoveUp(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Left on the map
        frames.add(new TextureRegion(necromancerTexture, 120, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 120, 201, 40, 50));
        super.setMoveLeft(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Right on the map
        frames.add(new TextureRegion(necromancerTexture, 160, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 160, 201, 40, 50));
        super.setMoveRight(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Down-Left on the map
        frames.add(new TextureRegion(necromancerTexture, 200, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 200, 201, 40, 50));
        super.setMoveDownLeft(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Down-Right on the map
        frames.add(new TextureRegion(necromancerTexture, 260, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 260, 201, 40, 50));
        super.setMoveDownRight(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Up-Right on the map
        frames.add(new TextureRegion(necromancerTexture, 310, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 310, 201, 40, 50));
        super.setMoveUpRight(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();

        // Set the animations for moving Up-Left on the map
        frames.add(new TextureRegion(necromancerTexture, 350, 140, 40, 50));
        frames.add(new TextureRegion(necromancerTexture, 350, 201, 40, 50));
        super.setMoveUpLeft(new Animation<TextureRegion>(0.1f, frames));
        frames.clear();
    }

    /**
     * Create the Necromancer's body for the box2d physics engine
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
        vertice[0] = new Vector2(-(NEC_WIDTH * SCALINGFACTOR) / 2 + 5, 0); // top left
        vertice[1] = new Vector2((NEC_WIDTH * SCALINGFACTOR) / 2 - 5, 0); // top right
        vertice[2] = new Vector2(-(NEC_WIDTH * SCALINGFACTOR) / 2 + 5, -(NEC_HEIGHT * SCALINGFACTOR) / 2); // bottom
                                                                                                           // left
        vertice[3] = new Vector2((NEC_WIDTH * SCALINGFACTOR) / 2 - 5, -(NEC_HEIGHT * SCALINGFACTOR) / 2); // bottom
                                                                                                          // right

        // set the world
        rect.set(vertice);
        fdef.shape = rect;
        this.getBody().createFixture(fdef).setUserData(this);
    }

    /**
     * Get the Necromancer's Dialogue Graph
     * @param the player to base the graph off of
     * @return the dialogue graph 
     */
	@Override
	public DialogueGraph getDialogue(Player player) {
		if(player.getNecEncounters() == 1) 
			return this.getFirstEncounter();
		return null;
	}

	/**
	 * Get the necromancer's dialogue for the player's first encounter
	 * @return the dialogue box for the first encounter
	 */
	private DialogueGraph getFirstEncounter() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Hello there mortals. I am a mighty Necromancer. Here to see the new opponent. And I ..."); // 0
		graph.addNode("\"You are unable to hear him as the thunder from outside is too loud\""); // 1
		graph.addNode("MUAHAHA and I \"CRASH\" will murder \"BOOM\" puppies, kittens, none shall escape \"KERSHACK\" and I will...\""); // 2
		graph.addNode("\"Thunder booms again\""); // 3
		graph.addNode("Farewell you fools! MUAHAHA"); // 4
		
		// add edges 
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		
		graph.getNode(4).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.setNecEncounters(3);
			}
			
		});
		
		return graph;
	}
	
	private DialogueGraph getUnionization() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("Whoa, whoa, whoa, what's going on here? You should be killing each other right now?!!"); // 0
		graph.addNode("We have moved past senselless killing ever since Bloody Foot willingly abdicated the throne."); // 1
		graph.addNode("What we have done and still are doing is seizing the means of plundering."); // 2
		graph.addNode("*hide behind slightly larger bandit*"); // 3
		graph.addNode("You killed him?"); // 4
		graph.addNode("My goodness, you made the badits into communists?!"); // 5
		graph.addNode("I still see you. Get out here."); // 6
		graph.addNode("No, why would you think that?"); // 7
		graph.addNode("\'Kill\' is such a strong word. What about, \'Passed away forcibly?\'"); // 8
		graph.addNode("Yes"); // 9
		graph.addNode("He's absent and I saw a mysterious grave that wasn't here yesterday."); // 10
		graph.addNode("Really? How strange I must say!"); // 11
		graph.addNode("*sigh* I don't have time for this. Just to let you all know, I will not be paying you and will leave a negative review on Yelp."); // 12
		graph.addNode("You don't have the guts!"); // 13
		graph.addNode("I pushed these bandits towards the future of equal work, employment, and wages!"); // 14
		graph.addNode("I don't make them into Russians, sheesh, we all agreed to be Mexican from now on. We're done being Italian."); // 15
		graph.addNode("Why the heck Mexicans??"); // 16
		graph.addNode("They have better hats... and tacos."); // 17
		graph.addNode("Wouldn't you want to know?"); // 18
		graph.addNode("*whisper to bandit* Ha! Look at this greengo!"); // 19
		graph.addNode("Come get me if you dare!");
		graph.addNode("Sounds like someone is jealous."); // 21
		graph.addNode("So, communism?"); // 22
		graph.addNode("You do know that I'm able to obliterate all that stand in front of me right?"); // 23
		graph.addNode("Yes I would. I really want to know."); // 24
		graph.addNode("Well too bad!"); // 25
		graph.addNode("Esta deletreado \'gringo!\'"); // 26
		graph.addNode("Sorry, we don't speak that language here greengo."); // 27
		graph.addNode("*continue hiding behind bandit*"); // 28
		graph.addNode("I said get out here now!"); // 29
		graph.addNode("*continue hiding*"); // 30
		
		// add the edges
		graph.addEdge(0, 1);
		graph.addEdge(1, 4);
		graph.addEdge(0, 2);
		graph.addEdge(2, 5);
		graph.addEdge(0, 3);
		graph.addEdge(3, 6);
		graph.addEdge(4, 7);
		graph.addEdge(4, 8);
		graph.addEdge(4, 9);
		graph.addEdge(7, 10);
		graph.addEdge(10, 11);
		graph.addEdge(11, 12);
		graph.addEdge(8, 12);
		graph.addEdge(9, 12);
		graph.addEdge(5, 14);
		graph.addEdge(5, 15);
		graph.addEdge(14, 22);
		graph.addEdge(22, 21);
		graph.addEdge(21, 12);
		graph.addEdge(15, 16);
		graph.addEdge(16, 17);
		graph.addEdge(17, 20);
		graph.addEdge(16, 18);
		graph.addEdge(18, 24);
		graph.addEdge(24, 25);
		graph.addEdge(16, 19);
		graph.addEdge(19, 26);
		graph.addEdge(26, 27);
		graph.addEdge(6, 28);
		graph.addEdge(28, 29);
		graph.addEdge(29, 30);
		graph.addEdge(6, 20);
		graph.addEdge(20, 23);
		graph.addEdge(23, 13);
		graph.addEdge(30, 12);
		graph.addEdge(13, 12);
		
		
		return graph;
	}
}
