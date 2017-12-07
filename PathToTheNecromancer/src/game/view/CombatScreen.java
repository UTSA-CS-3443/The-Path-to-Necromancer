package game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.controller.CombatController;
import game.controller.ScreenManager;
import game.model.sprites.EnemySprites;
import game.model.sprites.player.Player;

//libgdx already has the for loop
//
/**
 * This class handles all combat inside the game, while calling on CombatScreenAnimations to animate
 * @author BenMW
 * 
 */
public class CombatScreen implements Screen
{
	
    private EnemySprites enemy;  //to tell it what to draw, you need to use setframe (for each enemy, you need to set every texture)
    private int movement;
    private FitViewport port;
    private CombatController input;
    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font =  new BitmapFont();//(Gdx.files.internal("type_writer.ttf"));
    
    
    
    //Batches used to draw the pictures required
    private Texture background = new Texture(Gdx.files.internal("assets/NewPaper.jpg"));
    private Texture box = new Texture(Gdx.files.internal("assets/Box.jpg"));
    private Texture black = new Texture(Gdx.files.internal("assets/BlackBack.jpg"));
    private Texture frame = new Texture(Gdx.files.internal("assets/Sprite Image.jpg"));
    
    //If crashing on textures, the path(or name) are incorrect
    private Texture enemyNPC; 
    private Texture player = new Texture(Gdx.files.internal("assets/EnemySprites/Witch.png"));
    
    private EnemySprites Enemy;
    //variables
    private boolean IntroScreen;          //Used to determine when to play the intro screen           | Intro Screen basics
    private int introScreenAnimation; //Used to determine for how long to play the intro screen       |
    
    private int battle; //Will always be true as long as your or the enemy's health have not reach 0
    
    private int ExitScreen;          //Used to determine when to play the exit screen             | Exit Screen basics
    private int ExitScreenAnimation; //Used to determine for how long to play the exit screen     |
    
    private CombatScreenAnimations Animate;
    
    //Music variables
    Music music = (Music) Gdx.audio.newMusic(Gdx.files.internal("assets/Music/Battle Music.mp3"));
    
    //Misc Variables
    private int time;
    private boolean Intro1End = false;
    private boolean musicRun = true;
   
    private Player p;
   // private CombatController controller;
    
    private int NextAction;
    private boolean resetTime;
    
    /**
     * The screenManager
     */
    private ScreenManager screenManager;
    /**
     * This sets up the combat screen to be used later
     * @param manager manages the screens
     */
    public CombatScreen(ScreenManager manager) 
    {
    	this.screenManager = manager;
        this.font.setColor(Color.BLACK);
    	this.IntroScreen = true;
    	this.Enemy = screenManager.getEnemy(); //gets a random enemy
    	this.enemyNPC = Enemy.getTexture();
    	this.time = 0;
    	this.NextAction = 1;
    	//this.battle = this.Animate.getWhoGoFirst(this.p, this.Enemy);  //Will tell us who will be going first
    	this.input = new CombatController(this);
    	this.Animate = new CombatScreenAnimations(this.batch, this.Enemy, this.p);
    	this.battle = 1;
    }
    /**
     * This sets up the combat screen to be used later
     * @param manager manages the screens
     * @param enemy is the enemy for the combat
     */
    public CombatScreen(ScreenManager manager, EnemySprites enemy) 
    {
    	this.screenManager = manager;
        this.font.setColor(Color.BLACK);
    	this.IntroScreen = true;
    	this.Enemy = enemy; 
    	this.enemyNPC = Enemy.getTexture();
    	this.time = 0;
    	this.NextAction = 1;
    	//this.battle = this.Animate.getWhoGoFirst(this.p, this.Enemy);  //Will tell us who will be going first
    	this.input = new CombatController(this);
    	this.Animate = new CombatScreenAnimations(this.batch, this.Enemy, this.p);
    	this.battle = 1;
    }
    //Calls the garbage collector |
    @Override
    public void dispose() 
    {
        // TODO Auto-generated method stub
        
    }
    //Hiding the screen | don't worry about it
    @Override
    public void hide() 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() 
    {
        // TODO Auto-generated method stub
        
    }

    /**
     * Update the combat 
     * @param dt is the used to update the character sprite (see CharacterSprites)
     */
    //Update let's you change position
    //dt - has to deal with motion
    //Model - holds the positions and everything
    //View - ASks model what to display
    //Controller - listens to view and changes accordingly
    public void update(float dt) 
    {
        //this is where you edit what the user processes
    }
    
    /**
     * Render the game's combat
     */
    @Override
    //Just going to call render
    //Width is 640 and height is 480
    public void render(float delta) 
    {
    
    	System.out.println("YOUR BATTLE IS " +this.battle);
    	music.setVolume(0);
    	//System.out.println("YOUR DELTA IS: " + delta);

    	batch.begin();

    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        /**
         * BEGIN INTRODUCTION SEQUENCE
         * Will continue to run until the introscreen has reached a certain iTime, 
         * then it will set the IntroScreen to FALSE to continue on to battle
         */
    	if(this.IntroScreen) 
    	{
    		
    		if(this.time < 400 && this.Intro1End == false) //IntroScreen1
    		{
    			this.Animate.getIntroAnimation(this.time);	
    			this.time+=4;
    		}
    		else if(this.time < 800)  //IntroScreen2
    		{
    			if(this.Intro1End == false)
    			{
    				this.Intro1End = true; //prevent render from re-entering the IntroScreen1 
        			this.time = 0;
    			}
    			batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    			this.Animate.drawObjectSprites(640, 330, 490, 330, time, this.enemyNPC, 120, 120); //Draws the player
    			this.Animate.drawObjectSprites(0, 180, 60, 180, time, this.player, 120 ,120); //Draws the enemy
    			this.Animate.drawDefaultCombatBackground();
    			this.time+=5;
    		}
    		else
    		{
    			this.IntroScreen = false;
    			this.resetTime = true;
    		}
    		
    	}
    	
    	/**
    	 * BEGIN BATTLE SEQUENCE
    	 * After IntroScreen has run, it will then move to the battle sequence, allowing you to fight
    	 * The battle sequence will then jump to endCombat later
    	 */
    	else if(!this.IntroScreen)
    	{	
    		System.out.println("Your time is " +this.time);
    		if((this.battle > 0 && resetTime) || this.time >= 100)
    		{
    			System.out.println("TIME IS RESET");
    			this.time = 0;
    			this.resetTime = false;
    		}
    		//Awaiting user input, so just draw the background with the player and enemy
    		if (this.battle == 0)
    		{
    			this.time = 0;
    			this.Animate.drawDefaultCombatBackground();
    			// In order to render the buttons
    			//this.controller.act(this);
    		}
    		
    		//Will run into StartBattleSequence until the animation is done, then will reset based on the action taken via the controller
    		if(this.battle > 0) 
    		{
    			
    			System.out.println("ENTERING BATTLE and your time is " +this.time);
    			//Resets time to always, and I mean always, run with 100s for every animation
    			if(this.time == 100)
    			{
    				this.time = 0;
    			}
    			//Attack = 1, Inventory = 2, Interact = 3, Run = 4
    			this.battle = this.Animate.StartBattleSequence(this.battle, this.Enemy, this.time);
    			this.time++;
    			
    			//Be done with the sequence, reset time, then re-enter the waiting period for user controls
    			if(this.battle == -5)
    			{
    				this.time = 0;
    				this.battle = 0;
    			}
    			//Player has died
    			else if(this.battle == -1 || this.battle == -2) 
    			{
    				endCombat();
    			}
    			
    		}
    	}
		batch.end();
		/*
        // clear the screen
    	else {
    		
    		//batch.begin();
    		//Draw the background and basic layout
    		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    		batch.draw(black, 0, 0, 802, 165);
    		//Draw every box
    		
    		batch.draw(box, 440, 0, 200, 80); //
    		batch.draw(box, 235, 85, 200, 80);
    		batch.draw(box, 440, 85, 200, 80);
    		batch.draw(box, 235, 0, 200, 80);
    		
    		batch.draw(this.enemyNPC, 490, 330,120,120);
    		batch.draw(this.player, 60, 180,100,100);
    		
    		batch.draw(frame, 0,0, 110, 165);
        
        
    		//Draw the following commands
    		font.getData().setScale(100);
    		font.draw(batch, "Run", 525,50);
    		font.draw(batch, "Inventory", 505,130);
    		font.draw(batch, "Attack", 315,130);
    		font.draw(batch, "Interact", 310,50);
    		//stage.act();
        	//stage.draw();
    		
    	}*/
    	this.update(delta);
    	if(this.time == 200)
    		music.dispose();
    	//batch.end();
    }
    
    /**
     * Perform operations that end the combat and return to the PlayScreen
     */
    public void endCombat() 
    {
        // This is where to perform some end combat operation
        // change the screen and the input processor
        this.IntroScreen = true;
        this.Intro1End = false;
        this.screenManager.toMainScreen();
    }
    

    public Texture getCombatButtonTexture() {
    	return this.box;
    }
    @Override
    public void resize(int width, int height) 
    {
        this.screenManager.getViewport().update(width, height);
    }

    @Override
    public void resume() 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void show() 
    {
        // TODO Auto-generated method stub
        
    }

	public int getMovement() {
		return movement;
	}
	public void setBattle(int battle) {
		this.battle = battle;
	}

	public void setMovement(int movement) {
		this.movement += movement;
	}
	public Viewport getViewport() 
	{
		// TODO Auto-generated method stub
		return this.screenManager.getViewport();
	}
}