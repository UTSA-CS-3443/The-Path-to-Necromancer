package game.model.maps;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import game.controller.MapManager;
import game.model.B2WorldCreator;
import game.model.sprites.EnemySprites;
import game.model.sprites.npc.ColorAndGender;
import game.model.sprites.npc.Merchant;
import game.model.sprites.npc.OogLag;
import game.model.sprites.npc.Villagers;

/**
 * The inside of oog-lag's tavern
 * Connects with OoglagExterior
 * @author ToTryHardRay
 *
 */
public class InsideTavern extends GameMaps{
	
    /**
     * The name of the map file
     */
    private static final String MAPNAME = "Maps/Map03-Inside Oog-Lag's Tavern.tmx";

    /**
     * layer 4 = door transition
     */
    private static final int DOORTRANSITION = 4;
    
    /**
     * layer 5 = tavern interior transition
     */
   
    private static final int[] COLLISIONLAYERS = { 5 };
    /**
     * Used to manage map transitions.
     */
    private MapManager manager;
    /**
     * A world creator
     */
    private B2WorldCreator creator;

    /**
     * Constructor for the map
     * 
     * @param manager
     *            for managing map transitions
     */
    public InsideTavern(MapManager manager) {
        super(MAPNAME, false);
        this.manager = manager; // manager for loading maps
        this.creator = B2WorldCreator.getInstance(); // for creating the game's physics
    }

    /**
     * Generate the world for the game's physics.
     * 
     * @param the
     *            world to generate map's physics objects in
     */
    @Override
    public void generateWorld(World world) {
        // create the static non-moving bodies
        this.creator.createWorld(world, this, COLLISIONLAYERS);
        // create the transition areas
        this.creator.createTransition(world, this, DOORTRANSITION, DOOR);

    }

    /**
     * Transition between areas
     * 
     * North: PlainsArea South: IntroArea Door: OogLagInterior
     * 
     * @param the
     *            type of transition for the map
     */
    @Override
    public void transitionAreas(short transitionType) {
        switch (transitionType) {
       
        // the transition through the door
        case DOOR:
        	this.manager.setMap(new OogLagExterior(this.manager), 410, 692);
            break;
        // no transition
        default:
            break;
        }
    }

    /**
     * Return an enemy from the specific gameMap
     * 
     * @param level is the player's level
     * @return an EnemySprite
     */
    public EnemySprites getEnemy(int level) {
        return null;
    }

    /**
     * Create all of the sprites for the map.
     * 
     * @param world
     *            allows sprites with box2d bodies to be generated in the map
     */
    public void createSprites(World world) {
    	OogLag ooglag = new OogLag();
    	ooglag.defineBody(world, 350, 150);
    	super.addSprite(ooglag);
    	
    	Villagers villager1 = new Villagers(ColorAndGender.BLACK,ColorAndGender.FEMALE);
        villager1.isVelocityLooping(false);
        villager1.addVelocity(new Vector2(0,10), 1);
        villager1.defineBody(world, 438, 160);
        this.addSprite(villager1);
        
        Villagers villager2 = new Villagers(ColorAndGender.WHITE,ColorAndGender.MALE);
        villager2.isVelocityLooping(false);
        villager2.addVelocity(new Vector2(0,-10), 1);
        villager2.defineBody(world, 243, 135);
        this.addSprite(villager2);
        
        Villagers villager3 = new Villagers(ColorAndGender.RED,ColorAndGender.MALE);
        villager3.isVelocityLooping(false);
        villager3.defineBody(world, 246, 72);
        villager3.addVelocity(new Vector2(0,10), 1);
        this.addSprite(villager3);
        
        Villagers villager4 = new Villagers(ColorAndGender.BROWN,ColorAndGender.FEMALE);
        villager4.isVelocityLooping(false);
        villager4.defineBody(world, 599, 70);
        villager4.addVelocity(new Vector2(0,-10), 1);
        this.addSprite(villager4);
        
        Villagers villager5 = new Villagers(ColorAndGender.BLUE,ColorAndGender.MALE);
        villager5.isVelocityLooping(false);
        villager5.addVelocity(new Vector2(-10,0), 1);
        villager5.defineBody(world, 775, 48);
        this.addSprite(villager5);
        
        Merchant merchant = new Merchant();
        merchant.isVelocityLooping(false);
        merchant.defineBody(world, 761, 210);
        merchant.addVelocity(new Vector2(0,-10), 1);
        this.addSprite(merchant);
    }
    
    /**
     * Set the music for the map
     */
	@Override
	public void setMusic() {
		// TODO Auto-generated method stub
		
	}
   
}
