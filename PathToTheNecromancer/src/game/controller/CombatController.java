package game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import game.view.CombatScreen;


public class CombatController {
	/**
	 * The stage that contains all of the buttons
	 */
	private Stage stage;
	/**
	 * The combat screen that it modifies
	 */
	private CombatScreen screen;
	/**
	 * Constructor for the controller
	 * @param screen is the screen that the controller modifies
	 */
	public CombatController(CombatScreen screen){
		this.screen = screen;
		// get the button texture
    	Texture texture = screen.getCombatButtonTexture();
    	TextureRegion image = new TextureRegion(texture);
    	TextureRegionDrawable draw = new TextureRegionDrawable(image);
    	
    	BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/MagicCardFont.fnt"), false);

    	// Get the buttons
    	TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(draw, draw, draw, font);
    	TextButton attackButton = new TextButton("Attack", style);		// battle = 1
    	TextButton interactButton = new TextButton("Interact", style);	// battle = 3
    	TextButton runButton = new TextButton("Run", style);			// battle = 4
    	TextButton magicButton = new TextButton("Magic", style);		// battle = 2
    	
    	// create the buttons
    	attackButton.addListener(new InputListener(){
    		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.setBattle(1);
                return true;
    		}
    	});
    	// create the buttons
    	magicButton.addListener(new InputListener(){
    		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                screen.setBattle(2);
                return true;
    		}
    	});
    	runButton.addListener(new InputListener(){
    		public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
    			screen.setBattle(3);
    			return true;
    		}
    	});
    	
    	
    	// add the buttons to the table to render
    	Table table = new Table();
    	table.setFillParent(true);
    	table.bottom();
    	table.add(attackButton);
    	table.add(magicButton);
    	table.row();
    	table.add();
    	table.add(runButton);
    	
    	Stage stage = new Stage(screen.getViewport());
    	Gdx.input.setInputProcessor(stage);		// lets us handle input from stage
    	stage.addActor(table);				// so now the stage has added this stuff
    	stage.addListener(new InputListener(){
    		public boolean keyDown (InputEvent event, int keyTyped) {
    			switch(keyTyped){
    			case Keys.NUM_1:
    				screen.setBattle(1);
    				break;
    			case Keys.NUM_2:
    				screen.setBattle(2);
    				break;
    			case Keys.NUM_3:
    				screen.setBattle(4);
    				break;
    			}
    			return true;
    		}
    	});
	}
	public void act(){
		this.stage.act();
		this.stage.draw();
	}
}
