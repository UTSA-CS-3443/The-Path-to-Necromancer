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
		else if(player.getNecEncounters() == 5)
			return this.getUnionization();
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("The Necromancer you are calling is currently unavailable. Please try again later.");
		return graph;
	}

	/**
	 * Get the necromancer's dialogue for the player's first encounter
	 * @return the dialogue box for the first encounter
	 */
	private DialogueGraph getFirstEncounter() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("N: Hello there mortals. I am a mighty Necromancer. Here to see the new opponent. And I ..."); // 0
		graph.addNode("\"You are unable to hear him as the thunder from outside is too loud\""); // 1
		graph.addNode("N: MUAHAHA and I \"CRASH\" will murder \"BOOM\" puppies, kittens, none shall escape \"KERSHACK\" and I will...\""); // 2
		graph.addNode("\"Thunder booms again\""); // 3
		graph.addNode("N: Farewell you fools! MUAHAHA"); // 4
		
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
	/**
	 * Get the dialogue for the unionization encounter with the necromancer
	 * @return the unionization dialogue
	 */
	private DialogueGraph getUnionization() {
		DialogueGraph graph = new DialogueGraph();
		graph.addNode("N: Whoa, whoa, whoa, what's going on here? You should be killing each other right now?!!"); // 0
		graph.addNode("P: We have moved past senselless killing ever since Bloody Foot willingly abdicated the throne."); // 1
		graph.addNode("P: We seizing the means of plundering."); // 2
		graph.addNode("P: *hide behind slightly larger bandit*"); // 3
		graph.addNode("N: You killed him?"); // 4
		graph.addNode("N: My goodness, you made the badits into communists?!"); // 5
		graph.addNode("N: I still see you. Get out here."); // 6
		graph.addNode("P: No, why would you think that?"); // 7
		graph.addNode("P: \'Kill\' is such a strong word. What about, \'Passed away forcibly?\'"); // 8
		graph.addNode("P: Yes"); // 9
		graph.addNode("N: He's absent and I saw a mysterious grave that wasn't here yesterday."); // 10
		graph.addNode("P: Really? How strange I must say!"); // 11
		graph.addNode("N: *sigh* I don't have time for this. Just to let you all know, I will not be paying you and will leave a negative review on Yelp."); // 12
		graph.addNode("P: You don't have the guts!"); // 13
		graph.addNode("P: I pushed these bandits towards the future of equal work, employment, and wages!"); // 14
		graph.addNode("P: I don't make them into Russians, sheesh, we all agreed to be Mexican from now on. We're done being Italian."); // 15
		graph.addNode("N: Why the heck Mexicans??"); // 16
		graph.addNode("P: They have better hats... and tacos."); // 17
		graph.addNode("P: Wouldn't you want to know?"); // 18
		graph.addNode("P: *whisper to bandit* Ha! Look at this greengo!"); // 19
		graph.addNode("P: Come get me if you dare!");
		graph.addNode("P: Sounds like someone is jealous."); // 21
		graph.addNode("N: So, communism?"); // 22
		graph.addNode("P: You do know that I'm able to obliterate all that stand in front of me right?"); // 23
		graph.addNode("N: Yes I would. I really want to know."); // 24
		graph.addNode("P: Well too bad!"); // 25
		graph.addNode("N: Esta deletreado \'gringo!\'"); // 26
		graph.addNode("P: Sorry, we don't speak that language here greengo."); // 27
		graph.addNode("P: *continue hiding behind bandit*"); // 28
		graph.addNode("N: I said get out here now!"); // 29
		graph.addNode("P: *continue hiding*"); // 30
		
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
		
		// Add actors
		graph.getNode(12).addActor(new DialogueActor() {

			@Override
			public void act(Player player, MapManager manager) {
				player.setBanditEncounters(6);				
			}
		
		});
		
		return graph;
	}
	/**
	 * Get the Necromancer's dialogue for when the player is in the Castle at the end of the game.
	 * @return the dialogue graph
	 */
	private DialogueGraph getCastleEncounter() {
		DialogueGraph graph = new DialogueGraph();
		
		graph.addNode("Well well well. Look who decided to show up? I set this banquet out and you leave me waiting here for an eternity?"); // 0
		graph.addNode("It hasn’t been an eternity?"); // 1
		graph.addNode("When you live forever, time has no meaning anymore than just being a chore, and when you don’t need to eat and prepare a feast for someone who doesn’t show up … you get a little upset."); // 2
		graph.addNode("You made all this for me?"); // 3
		graph.addNode("That must be lonely?"); // 4
		graph.addNode("Where did you get this food from if you don’t need to eat?"); // 5
		graph.addNode("Of course I made it for you. Who else would come out here? Door to door salesmen? No I killed all of them ages ago, damn shamwow never absorbed all the blood."); // 6
		graph.addNode("Thank you, I think?"); // 7
		graph.addNode("Those poor salesmen!"); // 8
		graph.addNode("Did you try using some bleach?"); // 9
		graph.addNode("You are welcome, now sit down so you can have your last meal and I can kill you. It is a bit of tradition for me with heroes now."); // 10
		graph.addNode("But why?"); // 11
		graph.addNode("Look when I see you young heroes come in here honestly I get hopeful that maybe you will be the one to end my eternity, but you always die. I might as well give you a good final meal. Forgive the lack of variety, the only thing that grows in this kingdom anymore is wheat."); // 12
		graph.addNode("I will defeat you!"); // 13
		graph.addNode("Many have tried and all have failed, no hero. Sit down please and eat, don’t let your last moments be spent in hungering agony as well."); // 14
		graph.addNode("*Eat*"); // 14
		graph.addNode("*Attempt to fight*"); // 15
		graph.addNode("Thank you, now get some good rest in my dungeon, The General shall escort you for a good nap."); // 16
		graph.addNode("YOU KNOW WHAT? FINE, YOU GET YOUR BATTLE ON AN EMPTY STOMACH, BUT FIRST … GENERAL ESCORT THE HERO TO THE DUNGEON!"); // 17
		graph.addNode("They are salesmen, merchants no one will miss. Besides they are all cons anyway. Shame I never got my money back though."); // 18
		graph.addNode("I will avenge those salesmen!"); // 19
		graph.addNode("Yeah they are truly the worst of merchants."); // 20
		graph.addNode("Very true, now get a good meal and then a nap before I destroy you."); // 21
		graph.addNode("Bleach never works, besides it leaves behind a smell that gives me a killer headache."); // 22
		graph.addNode("But you can’t smell?"); // 23
		graph.addNode("HOW WOULD YOU KNOW? Sorry, sorry. It has been a long day and I just wish to destroy you, I was gonna let you eat this fine feast but now … now I am just gonna send you to the dungeon and fight you when I am calmer. Bye!"); // 24
		graph.addNode("What would you know of lonely? I have lived for eons with only myself, and who can call what I do living? I just go through the motions now."); // 25
		graph.addNode("C’mon you are more than just a Necromancer, you are an icon of fear! You inspire heroes to rise up! Don’t be so down on yourself."); // 26
		graph.addNode("Motions? What motions?"); // 27
		graph.addNode("You know, flattery won’t save you and neither will pity. Now eat your food and go take a nap in my dungeon so I can be rid of you with a clear conscience."); // 28
		graph.addNode("Oh, you know. Kill the sick and raise them as undead to watch over their towns and continue with their jobs. Hire bandits to keep the normal humans safe from the horrifying mushrooms on the mountains. Hire a poor knight who was looking for a master to serve and gave them a roof to sleep under and purpose."); // 29
		graph.addNode("WAIT WHAT?"); // 30
		graph.addNode("Hmmm?"); // 31
		graph.addNode("NO YOU CAN’T BE A GOOD GUY!"); // 32
		graph.addNode("Why not? Because I am ‘evil’? Because my magic isn’t holy? Well I never asked to have these gifts. I was once praised as the savior of this very Kingdom you know? This castle was the king’s in fact."); // 33
		graph.addNode("…"); // 34
		graph.addNode("He was a good man, but he asked me for too much, his greed took over him and I had to leave him. He grew mad and so I became ‘evil’ and stopped him. In the process all of my history was destroyed and so I stay here making sure no tyrant grows again to immense power."); // 35
		graph.addNode("But in doing so haven’t you-"); // 36
		graph.addNode("Become the very tyrant I swore to stop? Very astute of you. Why do you think I have been waiting for a hero this whole time? It was not to stop you but to find peace. You see I have been cursed to eternal life until I am slain by a true hero."); // 37
		graph.addNode("…"); // 38
		graph.addNode("How about you? Will you be the one to end my suffering?"); // 38
		graph.addNode("Yes"); // 39
		graph.addNode("Yes, but I would like to save you, not end you. (good ending ask Jamie to explain)"); // 40
		graph.addNode("Thank you, now get some good food and a good rest. I look forward to our fight."); // 41
		graph.addNode("What do you mean save me?"); // 42
		graph.addNode("You are a good man, I wish to join your cause, and save you from your loneliness."); // 43
		graph.addNode("You cannot, you are the hero! You must either end me or die by my hands, there is no saving!"); // 44
		graph.addNode("But what if I followed in your footsteps? I already organized your bandit camp, and took care of the villagers with the mushrooms?"); // 45
		graph.addNode("I suppose so, and you led my general towards becoming a good person again. Hmmm. Are you really willing to give up your mortality to save the world? To live forever is not an easy thing."); // 46
		graph.addNode("Yes (good ending finale)"); // 47
		graph.addNode("NO (true evil ending finale)"); // 48
		graph.addNode("You know what? Don’t question a free lunch."); // 49
		graph.addNode("My parents said there’s no such thing as free lunch?"); // 50
		graph.addNode("Well this one is."); // 51
		graph.addNode("But its dinner?"); // 52
		graph.addNode("Fine, don’t question a free dinner."); // 53
		graph.addNode("But I don’t want bread, do you have gluten free?"); // 54
		graph.addNode("… Its bread."); // 55
		graph.addNode("Fine how about something other than root beer?"); // 56
		graph.addNode("I only have access to what I got from Oog-Lag."); // 57
		graph.addNode("Fine then I’m not eating, let’s fight!"); // 58
		graph.addNode("YOU KNOW WHAT? DUNGEON TIME. YOU JUST TICKED ME OFF."); // 59
				
		graph.addEdge(0,1);
		graph.addEdge(1,2);
		graph.addEdge(2,3);
		graph.addEdge(3,6);
		graph.addEdge(6,7);
		graph.addEdge(7,10);
		graph.addEdge(10,11);//
		graph.addEdge(11,12);
		graph.addEdge(12,13);
		graph.addEdge(13,14);
		graph.addEdge(14,15);
		graph.addEdge(15,17);
		graph.addEdge(14,16);
		graph.addEdge(16,18);//
		graph.addEdge(6,8);
		graph.addEdge(8,19);
		graph.addEdge(19,20);
		graph.addEdge(20,16);
		graph.addEdge(19,21);
		graph.addEdge(21,22);
		graph.addEdge(22,15);
		graph.addEdge(22,16);
		graph.addEdge(6,9);
		graph.addEdge(9,23);
		graph.addEdge(23,24);
		graph.addEdge(24,25);
		graph.addEdge(2,4);
		graph.addEdge(4,26);
		graph.addEdge(26,27);
		graph.addEdge(27,29);
		graph.addEdge(29,15);
		graph.addEdge(26,28);
		graph.addEdge(28,30);
		graph.addEdge(30,31);
		graph.addEdge(31,32);
		graph.addEdge(32,33);
		graph.addEdge(33,34);
		graph.addEdge(34,35);
		graph.addEdge(35,36);		
		graph.addEdge(36,37);
		graph.addEdge(37,38);
		graph.addEdge(38,39);
		graph.addEdge(39,40);
		graph.addEdge(40,41);
		graph.addEdge(41,43);
		graph.addEdge(43,15);
		graph.addEdge(40,42);
		graph.addEdge(42,44);
		graph.addEdge(44,45);
		graph.addEdge(45,46);
		graph.addEdge(46,47);
		graph.addEdge(47,48);
		graph.addEdge(48,49);
		graph.addEdge(48,50);
		graph.addEdge(2,5);
		graph.addEdge(5,51);
		graph.addEdge(51,52);
		graph.addEdge(52,53);
		graph.addEdge(53,54);
		graph.addEdge(54,55);
		graph.addEdge(55,56);
		graph.addEdge(56,57);
		graph.addEdge(57,58);
		graph.addEdge(58,59);
		graph.addEdge(59,60);
		graph.addEdge(60,61);

		return graph;
	}
}
