package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.controller.GameController;
import game.model.PathToNecromancer;
import game.model.sprites.EnemySprites;
import game.model.sprites.player.Player;


public class CombatScreenAnimations implements Screen
{
	    private PathToNecromancer game;
	    private SpriteBatch batch;
	    private BitmapFont font =  new BitmapFont();
	    
	    private Texture background = new Texture(Gdx.files.internal("assets/NewPaper.jpg"));
	    private Texture box = new Texture(Gdx.files.internal("assets/Box.jpg"));
	    private Texture black = new Texture(Gdx.files.internal("assets/BlackBack.jpg"));
	    private Texture enemy = new Texture(Gdx.files.internal("assets/Box.jpg"));
	    private Texture player;
	    private Texture frame = new Texture(Gdx.files.internal("Sprite Image.jpg"));
	   // Gdx.input.setInputProcessor(new combatController());
	    /*
	    private Texture black = new Texture(Gdx.files.internal("BlackBack.jpg"));
	    private Texture frame = new Texture(Gdx.files.internal("Frame.png"));
*/
	    // deals with the boxes(box1, box2,box3,box4)
	    private int X;
	    private int Y;
	    
	    //Once boxes are placed, bText = 1
	    private int bText;
	    private float Fade;
	    
	    private Player p;
	    private int PlayerHealth; //Used as a temp variable to determine if the player dies
	    
	    private EnemySprites e;
	    private int EnemyHealth; //Used as a temp variable to determine if the enemy dies
	    
	    private int time; //Used to draw animations forward
	    private int backTime; //Used to draw animations 
	    
	    private boolean miscBoolean;
	    
	    private int rest;
	    private int amIDone;
	    public CombatScreenAnimations(PathToNecromancer game, SpriteBatch batch, PlayScreen playScreen) 
	    {
	    	this.amIDone = 1;
	    	this.batch = batch;
	        this.font.setColor(Color.BLACK);
	    	this.X = 0;
	    	this.Y = 0;
	    	this.bText = 0;
	    	this.Fade = 0;
	    	this.p = playScreen.getPlayer();
	    	this.time = 0;
	    }
	    
	    /**
	     * Returns who get's to go first
	     */
	    public int getWhoGoFirst(Player p, EnemySprites e)
	    {
	    	if(e.getDexterity() > p.getDexterity())
	    		return 1; //If the player has higher dexterity, they get to go first
	    	else
	    		return 2; //If the enemy has higher dexterity, they get to go first
	    }
	    /**
	     * Essentially the combat will continually go into one of these actions which is passed via the controller option
	     * 1 = Attack sequence which will then jump to the enemy attack later
	     * 2 = Inventory - Temporary variable
	     * 3 = Interact - Will move to the "PickedInteract" which will begin a banter sequence
	     * 4 = Run - Will move to the "PickedRun" which will begin a run sequence
	     * @return - Return will return a positive number if combat is still going, a negative if it ends
	     * 			 Only beginAttack() or beginEnemyAttack will return negatives
	     */
	    public int StartBattleSequence(int Action, EnemySprites e, int time)
	    { 		
	    	this.e = e;
	    	this.time = time;
	    	
	    	if(Action == 1)  //Attack
	    	{
	    		return beginPlayerAttack(); 
	    	}
	    	
	    	if(Action == 2) //Inventory
	    	{
	    		return beginInventory();
	    	}
	    	
	    	if(Action == 3) //Interact
	    	{
	    		return beginInteract();
	    	}
	    	
	    	if(Action == 4) //Run
	    	{
	    		return beginRun();
	    	}
	    	
	    	if(Action == 5)
	    	{
	    		return beginEnemyAttack();
	    	}
	    	
	    	//Whatever animation I'm currently using is done
	    	//Some animations require a time of 200 so it will 
	    	if(time >= 100)
	    	{
	    		return 0;
	    	}

	    	return -1;
	    }
	    
	    
	    
	    
	    public int beginEnemyAttack()
	    {
	    	int amIdone = attackAnimation(1, 490, 330); 
	    	
	    	if(this.EnemyHealth - this.p.getIntelligence() <= 0 && amIdone == 0) //You have killed the enemy
	    	{
	    		wasAttackedAnimation(490,300,p.getTexture());
	    		return -1;
	    	}
	    	
	    	//Begin the "taken damage" animation for the enemy
	    	else if(this.EnemyHealth - this.p.getIntelligence() > 0  && amIdone == 0) //Just dealing damage to the enemy
	    	{
	    		wasAttackedAnimation(490, 330, p.getTexture());
	    		if(this.time == 100)
	    		{
	    			this.PlayerHealth-= this.e.getAttack();
	    		}
	    	}
	    	
	    	return 2;
	    }
	    
	    /** ALL THE FOLLOWING WILL RETURN THE FOLLOWING:
	     *	1 if battle is not finished
	     * -1 if to end battle because of player death
	     * -2 if to end battle because of enemy death
	     */
	    public int beginPlayerAttack()
	    {
	    	System.out.println("HELLO player attack animation " + this.amIDone + " and your time is " + this.time);
	    	
	    	//Customize time to fit the following method (this works with the speed of player attack
	    	this.time = this.time * 4;  //Higher the x in this.time * x, the faster the animation
	    	
	    	
	    	//Will draw the attack 
	    	this.amIDone = attackAnimation(this.amIDone, 60, 180);
	    	if(this.amIDone == 0 && this.time != 101)
	    	{
	    			wasAttackedAnimation(490, 330, e.getTexture());
	    	}
	    	
	    		
	    	/*
	    	if(this.EnemyHealth - this.p.getAttack() <= 0 && amIdone == 0) //You have killed the enemy
	    	{
	    		wasAttackedAnimation(490,300,p.getTexture());
	    		return -1;
	    	}
	    	
	    	//Begin the "taken damage" animation for the enemy
	    	else if(this.EnemyHealth - this.p.getAttack() > 0  && amIdone == 0) //Just dealing damage to the enemy
	    	{
	    		wasAttackedAnimation(490, 330, p.getTexture());
	    		if(this.time == 100)
	    		{
	    		this.EnemyHealth-= this.p.getAttack();
	    		}
	    	}*/
	    	if(this.amIDone == 0)
	    		return 1;
	    	
	    	return 1;
	    }
	    
	    
	    
	    
	    /**
	     * 1 - Player attack animation
	     * 2 - Enemy  attack animation
	     * (int "who's animation", "original x", "original y")
	     * Will return 1 if the attack animation is still going
	     * Will return 0 if the attack animation is finished
	     * iTime will have to be 200 so it will continue
	     * distance will determine 
	     */
	    public int attackAnimation(int whoAmI, int originalX, int originalY)
	    {
	    	drawDefaultCombatBackground();
	    	//Player animation
	    	if(whoAmI == 1)
	    	{
	    		batch.draw(this.e.getTexture(), 490, 330,  120, 120); //Draw enemy NPC
	    		//Go forward animation
	    		if(this.time <= 50)
	    		{
	    			DrawObjectSprites(originalX, originalY, 90, 180, this.time, this.p.getTexture(), 120, 120);
	    			this.time+=4;	    			
	    			return 1;	
	    		}
	    		//Go backward animation
	    		else if(this.time > 50)
	    		{
	    			//iTime - 50 allows it to still decrement by 1 rather than 50
	    			DrawObjectSpritesBackwards(90, 180, originalX, originalY, this.time-50, this.p.getTexture(), 120, 120);
	    			
	    			if(this.time >= 100)
	    			{
	    				System.out.println("--------------RESET TIME----------");
	    				this.time = 0;	
	    				return 0;
	    			}
	    			else
	    			{
		    			this.time+=4;
	    				return 1;
	    			}
	    		}
	    	}
	    	/**
	    	 * This begins the enemy's animation after being attacked
	    	 */
	    	//Enemy animation
	    	else if(whoAmI == 2)
	    	{
	    		//Go forward animation
	    		if(this.time <= 150)
	    		{
	    			DrawObjectSpritesBackwards(originalX, originalY, 460, 330, this.time, e.getTexture(), 120, 120);
	    			this.time+=2;
	    			return 2;
	    		}
	    		
	    		//Go backward animation
	    		else if(this.time <= 200)
	    		{
	    			DrawObjectSprites(460, 330, originalX, originalY, this.time, e.getTexture(), 120, 120);	
	    			
	    			if(this.time == 200)
	    				return 0;
	    			
	    			this.time+=2;
	    			return 2; //Animation is done
	    			
	    		}
	    	}

	    		return 0;
	    }
	    
	    /**
	     * Takes in an x, y, and texture, then run a "if attacked" thingy on it 
	     * @param x
	     * @param y
	     * @param t
	     */
	    public void wasAttackedAnimation(int x, int y, Texture t)
	    {
	    	System.out.println("You are in attacked Animation with a time of " + this.time);
	    	//every 20th frame, don't draw for 5 frames
	    	if(this.time%25 == 0  && this.rest != 5)
	    	{
		    	batch.draw(t, x, y, 120, 120);  
		    	this.rest++;
	    	}
	    	else if(this.rest == 5)
	    	{
	    		batch.draw(t, x, y, 120, 120);  
	    		rest = 0;
	    	}
	    }
	    
	    /**
	     * 
	     */
	    public int beginInventory()
	    {
	    	return 1;
	    }
	    public int beginInteract()
	    {
	    	return 1;
	    }
	    public int beginRun()
	    {
	    	return 1;
	    }
	    
	    public int beginEnemyAnimation()
	    {
	    	return 1;
	    }
	    
	    public void drawDefaultCombatBackground() //Draws everything, including the sprites
	    {
	    	batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    		batch.draw(black, 0, 0, 802, 165);
    		//Draw every box
    		
    		batch.draw(box, 440, 0, 200, 80); //
    		batch.draw(box, 235, 85, 200, 80);
    		batch.draw(box, 440, 85, 200, 80);
    		batch.draw(box, 235, 0, 200, 80);
    		
    		//batch.draw(e.getTexture(), 490, 330,120,120);
    		batch.draw(this.p.getTexture(), 60, 180,100,100);
    		
    		batch.draw(frame, 0,0, 110, 165);
        
        
    		//Draw the following commands
    		font.getData().setScale(100);
    		font.draw(batch, "Run", 525,50);
    		font.draw(batch, "Inventory", 505,130);
    		font.draw(batch, "Attack", 315,130);
    		font.draw(batch, "Interact", 310,50);
    		
	    }
	    
	    /**
	     *  Used in junction with StartBattleSequence to grab the necessary values for the player and enemy
	     */
	    public void grabStats()
	    {
	    	
	    }
	    public void drawBox(int iTime, int MaxX, int MaxY)
	    {	

	    	if(iTime < MaxX)
	    	{
	    		this.X = iTime;
	    	}
	    	else if(iTime > MaxX)
	    	{
	    		this.X = MaxX; 
	    	}
	    	if(iTime < MaxY)
	    	{
	    		this.Y = iTime;
	    	}
	    	else if(iTime > MaxY)
	    	{
	    		this.Y = MaxY;
	    	}
	    	
	    	if(iTime > MaxY && iTime > MaxX)   //POSSIBLY REMOVABLE
	    	{
	    		this.bText = 1;
	    	}

	    	batch.draw(box, this.X, this.Y, 200, 80);        
	    	
	    	this.X = 0;
	    	this.Y = 0;

	    }
	    
	    public void drawBackground()
	    {
	    	batch.draw(this.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());    
	    }
	    
	    public void getIntroAnimation(int iTime)
	    {	    	
	    	batch.setColor(1.0f, 1.0f, 1.0f, this.Fade);		
	    	drawBackground();
	    	batch.draw(black, 0, 0, 802, 165);
	    	drawBox(iTime, 440, 0);
	    	drawBox(iTime, 440, 85);
	    	drawBox(iTime, 235, 85);
	    	drawBox(iTime, 235, 0); 	

    		batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	    	this.Fade += 0.01; // 1(Maximum)/400(max frames) * 10(each 1 frame is 10 frames)
	    	
	    }
	    
	    
	    public void drawFadeInText(String s, int x, int y, int time)
	    {
	    	font.setColor(1.0f, 1.0f, 1.0f, this.Fade);
	    	font.draw(batch, s, x,y);
	    	font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
	    	this.Fade +=0.025;
	    }
	    
	    public void DrawObjectSprites(int xFrom, int yFrom, int xTo, int yTo, int iTime, Texture t, int Width, int Height)
	    {
	    	if(iTime+xFrom < xTo)
	    	{
	    		this.X = iTime+xFrom;
	    	}
	    	
	    	else if(iTime + xFrom > xTo)
	    	{
	    		this.X = xTo; 
	    	}
	    	
	    	
	    	if(iTime + yFrom < yTo)
	    	{
	    		this.Y = iTime + yFrom;
	    	}
	    	
	    	else if(iTime + yFrom > yTo)
	    	{
	    		this.Y = yTo;
	    	}

	    	batch.draw(t, this.X, this.Y,  Width, Height);        
	    	//resets the sprites
	    	/*
	    	this.X = 0;
	    	this.Y = 0;
	    	*/
	    }
	    public void DrawObjectSpritesBackwards(int xFrom, int yFrom, int xTo, int yTo, int iTime, Texture t, int Width, int Height)
	    {
			System.out.println("YOU ARE INSIDE THE OTHER LOOP");
	    	if(xFrom - iTime > xTo)
	    	{
	    		this.X = xFrom - iTime;
	    	}
	    	
	    	else if(xFrom - iTime <= xTo)
	    	{
	    		this.X = xTo; 
	    	}
	    	
	    	
	    	if(yFrom - iTime > yTo)
	    	{
	    		this.Y = yFrom - iTime;
	    	}
	    	
	    	else if(yFrom - iTime <= yTo)
	    	{
	    		this.Y = yTo;
	    	}

	    	batch.draw(t, this.X, this.Y,  Width, Height);        
	    	//resets the sprites
	    	/*
	    	this.X = 0;
	    	this.Y = 0;
	    	*/
	    }
	    //Draws frames, everything, except for anything that doesn't change
	    public void drawBack()
	    {

    		batch.draw(black, 0, 0, 802, 165);
    		
    		//Draw every box
    		batch.draw(box, 440, 0, 200, 80); 
    		batch.draw(box, 235, 85, 200, 80);
    		batch.draw(box, 440, 85, 200, 80);
    		batch.draw(box, 235, 0, 200, 80);
        
        
    		batch.draw(this.frame, 0,0, 110, 165);
        
        
    		//Draw the following commands  		
    		font.draw(batch, "Run", 525,50);
    		font.draw(batch, "Inventory", 505,130);
    		font.draw(batch, "Attack", 315,130);
    		font.draw(batch, "Interact", 310,50);
	    }
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
