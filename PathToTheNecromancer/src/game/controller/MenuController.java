package game.controller;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.model.Savestate;
import game.model.sprites.player.Player;
import game.view.Menu;
import javaFX.model.Difficulty;

/**
 * The controller for the menu. It changes the current stage and sets up the
 * buttons for each stage.
 * 
 * @author enigma-phi
 * @author HangedDragon96  added settings to the menu controller
 *
 */
public class MenuController {
	/**
	 * The player for the game
	 */
	private Player player;
	/**
	 * The settings button
	 */
	private TextButton settings;
	/**
	 * The button that returns the user to the main game
	 */
	private TextButton toGame;
	/**
	 * The button that takes the user to the save and quit menu
	 */
	private TextButton saveQuit;
	/*
	 * * The button that saves the user's current game state
	 */
	private TextButton saveButton;
	/**
	 * The button that quits the game
	 */
	private TextButton quitButton;
	/**
	 * The button that takes the user to the menu with the character's information
	 */
	private TextButton character;
	/**
	 * The button that takes the user to the controls menu
	 */
	private TextButton controlButton;
	/**
	 * The font used in the menus
	 */
	private BitmapFont font;
	/**
	 * The slider for changing music settings
	 */
	private Slider musicSlider;
	/**
	 * The slider for changing brightness settings
	 */
	private Slider brightSlider;
	/**
	 * Determines whether or not the settings menu's buttons have been set up
	 */
	private boolean isSetUp;
	/**
	 * The label style for red text
	 */
	private Label.LabelStyle redLabel;
	/**
	 * The label style for white text
	 */
	private Label.LabelStyle whiteLabel;
	
	/**
	 * The check box for disabling sound
	 */
	private CheckBox disableSoundCheck;
	/**
	 * The select box for changing difficulties
	 */
	private SelectBox<String> difficultySelect;
	/**
	 * The table with twin dragon designs
	 */
	private Table twinDragonTable;
	/**
	 * The menu the MenuController listens to
	 */
	private Menu menu;
	/**
	 * The Viewport for the stage
	 */
	private Viewport viewport;
	/**
	 * Texture atlas for the transition stages buttons
	 */
	private TextureAtlas buttonAtlas1;
	/**
	 * Texture atlas for the settings men
	 */
	private TextureAtlas buttonAtlas2;
	
	/**
	 * Create the MenuController
	 * 
	 * @param menu
	 *            is the menu to control
	 */
	public MenuController(Menu menu) {
		this.menu = menu;
		this.viewport = menu.getViewPort();
		// set up the font
		this.font = new BitmapFont(Gdx.files.internal("MagicCardFont.fnt"), false);

		this.player = menu.getPlayer();

		// set the buttons for the menus
		this.setButtons();

		// the design aspects
		this.setDesignTables();

		// set the main menu stage up
		this.mainMenu();
		this.isSetUp = false;
	}

	/**
	 * Set up the main menu stage for the menu screen
	 */
	private void mainMenu() {
		Stage stage = new Stage(menu.getViewPort());
		this.setUpStage(stage);
		Gdx.input.setInputProcessor(stage);
		stage.addActor(twinDragonTable);
		this.font.getData().setScale(0.5f);

		// Get all of the labels for your table to render
		Label classLabel = new Label("Class:", redLabel);
		Label levelLabel = new Label("Level:", whiteLabel);
		Label expLabel = new Label("Current Experience:", whiteLabel);
		Label healthLabel = new Label("Health", whiteLabel);
		Label strLabel = new Label("Strength:", whiteLabel);
		Label intLabel = new Label("Intelligence:", whiteLabel);
		Label dexLabel = new Label("Dexterity:", whiteLabel);
		Label lckLabel = new Label("Luck:", whiteLabel);
		Label classVal = new Label(this.player.getCharClass(), redLabel);
		Label levelVal = new Label(String.valueOf(this.player.getLevel()), whiteLabel);
		Label healthVal = new Label(String.valueOf(this.player.getHealth()), whiteLabel);
		Label dexVal = new Label(String.valueOf(this.player.getDexterity()), whiteLabel);
		Label expVal = new Label(String.valueOf(this.player.getExperience()), whiteLabel);
		Label strVal = new Label(String.valueOf(this.player.getStrength()), new Label.LabelStyle(font, Color.WHITE));
		Label intVal = new Label(String.valueOf(this.player.getIntelligence()), whiteLabel);
		Label lckVal = new Label(String.valueOf(this.player.getLuck()), whiteLabel);

		// Fill the table with values
		Table table = new Table();
		table.setFillParent(true);
		table.padTop(10);
		table.center().top();
		table.add(classLabel).left().prefWidth(200);
		table.add(classVal).right().prefWidth(50);
		table.row();
		table.add(levelLabel).left();
		table.add(levelVal).right();
		table.row();
		table.add(healthLabel).left();
		table.add(healthVal).right();
		table.row();
		table.add(expLabel).left();
		table.add(expVal).right();
		table.row();
		table.add(strLabel).left();
		table.add(strVal).right();
		table.row();
		table.add(intLabel).left();
		table.add(intVal).right();
		table.row();
		table.add(dexLabel).left();
		table.add(dexVal).right();
		table.row();
		table.add(lckLabel).left();
		table.add(lckVal).right();
		table.row().height(100);
		stage.addActor(table);

		this.addMenuTransitionButtons(stage);
		menu.setStage(stage);
	}

	/**
	 * Set up the stage for the settings screen.
	 */
	private void settingsMenu() {
		Stage stage = new Stage(viewport);
		this.setUpStage(stage);
		Gdx.input.setInputProcessor(stage);
		stage.addActor(twinDragonTable);
		this.font.getData().setScale(0.4f);

		if (!isSetUp) {
			setUpSettingsButtons();
			isSetUp = true;
		}

		// Get all of the labels for your table to render
		Label settingsMenuLabel = new Label("Settings", redLabel);
		Label musicLabel = new Label("Music", whiteLabel);
		Label brightLabel = new Label("Brightness", whiteLabel);
		Label soundOnLabel = new Label("Disable Sound", whiteLabel);
		Label levelLabel = new Label("Difficulty", whiteLabel);

		// header for the settings menu
		Table heading = new Table();
		heading.setFillParent(true);
		heading.top();
		heading.add(settingsMenuLabel).padTop(5);
		stage.addActor(heading);

		// main table for the settings
		Table table = new Table();
		table.setFillParent(true);
		table.top().center();
		table.padTop(20);
		table.add(musicLabel).padRight(10).center().top();
		table.add(musicSlider);
		table.row();
		table.add(brightLabel).padRight(10).padTop(5).center();
		table.add(brightSlider).padTop(5);
		table.row();
		table.add(soundOnLabel).padRight(10).padTop(5).center().padBottom(10);
		table.add(disableSoundCheck).center().padBottom(10);
		table.row();
		table.add(levelLabel).padRight(10).padTop(5).center().padBottom(40);
		table.add(difficultySelect).padBottom(40);
		stage.addActor(table);

		this.addMenuTransitionButtons(stage);
		menu.setStage(stage);
	}

	/**
	 * Create the menu for the save Menu. Includes a label, a save button, and a
	 * quit button
	 */
	private void saveMenu() {
		Stage stage = new Stage(viewport);
		this.setUpStage(stage);
		stage.addActor(twinDragonTable);
		Gdx.input.setInputProcessor(stage);
		this.font.getData().setScale(0.6f);

		Label saveQuestionLabel = new Label("PathToTheNecromancer", redLabel);

		// Fill the table with values
		Table table = new Table();
		table.setFillParent(true);
		table.top().padTop(10);
		table.add(saveQuestionLabel).padBottom(30);
		table.row().height(60);
		table.add(saveButton).center().width(60).height(20).padBottom(30);
		table.row().height(60);
		table.add(quitButton).center().width(60).height(20);
		stage.addActor(table);

		this.addMenuTransitionButtons(stage);
		this.menu.setStage(stage);
	}

	/**
	 * The stage that displays all of the controls for the game.
	 */
	private void controlMenu() {
		Stage stage = new Stage(viewport);
		this.setUpStage(stage);
		Gdx.input.setInputProcessor(stage);
		stage.addActor(twinDragonTable);
		this.font.getData().setScale(0.4f);

		// Get all of the labels for your table to render
		Label title = new Label("Controls", redLabel);
		Label world = new Label("World", redLabel);
		Label combat = new Label("Combat", redLabel);
		Label movement = new Label("Movement", whiteLabel);
		Label movementControls = new Label("WASD or Arrow Keys", whiteLabel);
		Label run = new Label("Run", whiteLabel);
		Label runControls = new Label("Left Shift or Right Shift", whiteLabel);
		Label openMenu = new Label("Open Menu", whiteLabel);
		Label openMenuControl = new Label("Escape Key", whiteLabel);
		Label interact = new Label("Interact", whiteLabel);
		Label interactKey = new Label("Enter Key", whiteLabel);
		Label textMove = new Label("Skip text", whiteLabel);
		Label textMoveKey = new Label("Spacebar", whiteLabel);

		// Fill the table with values
		Table table = new Table();
		table.setFillParent(true);
		table.center().top();
		table.padTop(30);
		table.add(world).left().padLeft(20);
		table.add();
		table.row();
		table.add(movement).left().expandX().padLeft(20);
		table.add(movementControls).right().padRight(20);
		table.row();
		table.add(run).left().padLeft(20);
		table.add(runControls).right().padRight(20);
		table.row();
		table.add(openMenu).left().padLeft(20);
		table.add(openMenuControl).right().padRight(20);
		table.row();
		table.add(interact).left().padLeft(20);
		table.add(interactKey).right().padRight(20);
		table.row();
		table.add(textMove).left().padLeft(20);
		table.add(textMoveKey).right().padRight(20);
		table.row();
		
		table.add(combat).left().padLeft(20);
		table.add();

		table.row().height(100);
		stage.addActor(table);

		Table heading = new Table();
		heading.setFillParent(true);
		heading.center().top();
		heading.padTop(10);
		heading.add(title);
		stage.addActor(heading);

		this.addMenuTransitionButtons(stage);
		menu.setStage(stage);
	}

	/**
	 * Set up the buttons for the menu. These buttons are: <br>
	 * saveQuit: access the save and quit menu <br>
	 * settings: access the settings menu <br>
	 * character: access the main menu <br>
	 * toGame: return to the main game <br>
	 * save: save the game <br>
	 * quit: quit the game
	 */
	private void setButtons() {
		buttonAtlas1 = new TextureAtlas("Buttons/Buttons.pack");
		Skin skin = new Skin();
		skin.addRegions(buttonAtlas1);

		// Set up the labels
		redLabel = new Label.LabelStyle(font, Color.RED);
		whiteLabel = new Label.LabelStyle(font, Color.WHITE);

		// Set the button for returning to the game
		TextButtonStyle toGameStyle = new TextButtonStyle();
		toGameStyle.font = font;
		toGameStyle.up = skin.getDrawable("ReturntoGameButton");
		toGameStyle.down = skin.getDrawable("ReturntoGameButton");
		toGameStyle.checked = skin.getDrawable("ReturntoGameButton");
		toGame = new TextButton("", toGameStyle);
		toGame.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				menu.returnToGame();
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
			}
		});

		// Set the button for accessing the settings screen
		TextButtonStyle settingsStyle = new TextButtonStyle();
		settingsStyle.font = font;
		settingsStyle.up = skin.getDrawable("SettingsButton");
		settingsStyle.down = skin.getDrawable("SettingsButton");
		settingsStyle.checked = skin.getDrawable("SettingsButton");
		settings = new TextButton("", settingsStyle);
		settings.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				settingsMenu();
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
			}
		});

		// Set the button for accessing the save and quit screen
		TextButtonStyle saveQuitStyle = new TextButtonStyle();
		saveQuitStyle.font = font;
		saveQuitStyle.up = skin.getDrawable("Save&Quit");
		saveQuitStyle.down = skin.getDrawable("Save&Quit");
		saveQuitStyle.checked = skin.getDrawable("Save&Quit");
		saveQuit = new TextButton("", saveQuitStyle);
		saveQuit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				saveMenu();
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
			}
		});

		// Set the button for accessing the main screen
		TextButtonStyle characterStyle = new TextButtonStyle();
		characterStyle.font = font;
		characterStyle.up = skin.getDrawable("CharacterButton");
		characterStyle.down = skin.getDrawable("CharacterButton");
		characterStyle.checked = skin.getDrawable("CharacterButton");
		character = new TextButton("", characterStyle);
		character.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				mainMenu();
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
			}
		});

		// Set the button for saving the game
		TextButtonStyle saveButtonStyle = new TextButtonStyle();
		saveButtonStyle.font = font;
		saveButtonStyle.up = skin.getDrawable("SaveButton");
		saveButtonStyle.down = skin.getDrawable("SaveButton");
		saveButtonStyle.checked = skin.getDrawable("SaveButton");
		saveButton = new TextButton("", saveButtonStyle);
		saveButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Savestate save = menu.getSave();
				if(save == null) {
				save = new Savestate(player.getX(), player.getY(), menu.getMapName(),
						menu.getSettings(), player.isWarrior(), player.isRogue(), player.isMage(), player.getStats(),player.getStoryStats());
				} else {
					save.setplayerX(player.getX());
					save.setplayerY(player.getY());
					save.setMap(menu.getMapName());
					save.setSetting(menu.getSettings());
					save.setStat(player.getStats());
					save.setStory(player.getStoryStats());
				}
				try {
					SaveGame.saveGame(save);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				menu.storeSave(save);
				return true;
			}

		});

		// Set the button for quitting the game
		TextButtonStyle quitButtonStyle = new TextButtonStyle();
		quitButtonStyle.font = font;
		quitButtonStyle.up = skin.getDrawable("QuitGameButton");
		quitButtonStyle.down = skin.getDrawable("QuitGameButton");
		quitButtonStyle.checked = skin.getDrawable("QuitGameButton");
		quitButton = new TextButton("", quitButtonStyle);
		quitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
			}
		});

		// Set the button for going to the Controls menu
		TextButtonStyle controlStyle = new TextButtonStyle();
		controlStyle.font = font;
		controlStyle.up = skin.getDrawable("ControlButton");
		controlStyle.down = skin.getDrawable("ControlButton");
		controlStyle.checked = skin.getDrawable("ControlButton");
		controlButton = new TextButton("", controlStyle);
		controlButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				controlMenu();
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("Example", "touch done at (" + x + ", " + y + ")");
			}
		});

	}

	/**
	 * Add the menu transition buttons for the menu
	 * 
	 * @param stage
	 *            is the menu to put the buttons onto
	 */
	private void addMenuTransitionButtons(Stage stage) {
		// Create the top row of buttons
		Table buttons1 = new Table();
		buttons1.setFillParent(true);
		buttons1.center().bottom().padBottom(25);
		buttons1.add(settings).width(60).height(20).padRight(20);
		buttons1.add(character).width(60).height(20).padRight(20);
		buttons1.add(controlButton).width(60).height(20);
		stage.addActor(buttons1);

		// Create the bottom row of buttons
		Table buttons2 = new Table();
		buttons2.setFillParent(true);
		buttons2.center().bottom();
		buttons2.add(saveQuit).width(60).height(20).padRight(20);
		buttons2.add(toGame).width(60).height(20);
		stage.addActor(buttons2);
	}

	/**
	 * Set up the buttons and sliders used in the settings menu <br>
	 * musicSlider: the volume of the music <br>
	 * brightSlider: the screen's brightness <br>
	 * disableSoundCheck: if disable sound <br>
	 * difficultySelect: select the current difficulty
	 */
	private void setUpSettingsButtons() {
		Skin skin = new Skin();
		buttonAtlas2 = new TextureAtlas("Buttons/Skins/TracerButtons.pack");
		skin.addRegions(buttonAtlas2);

		Skin defaultSkin = new Skin(Gdx.files.internal("Buttons/Skins/Tracer_UI_Skin/tracerui/tracer-ui.json"));

		// set up the slider for changing the music volume
		musicSlider = new Slider(0, 100, 1, false, defaultSkin);
		musicSlider.setValue(menu.getSettings().getMusicSound());
		musicSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Slider slider = (Slider) actor;
				menu.getSettings().setMusicSound((int)Math.ceil(slider.getValue()));
				
			}
		});


		// set up the slider for changing the brightness
		brightSlider = new Slider(0, 100, 1, false, defaultSkin);
		brightSlider.setValue(menu.getSettings().getBrightness());
		brightSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Slider slider = (Slider) actor;
				menu.getSettings().setBrightness((int)Math.ceil(slider.getValue()));
			}
		});

		CheckBox.CheckBoxStyle checkStyle = new CheckBox.CheckBoxStyle(skin.getDrawable("checkbox"),
				skin.getDrawable("checkbox-on"), font, Color.WHITE);


		// set up the disable sound check button
		disableSoundCheck = new CheckBox("", checkStyle);
		disableSoundCheck.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(menu.getScreenManager().getMusicManager().getVolume() > 0) {
				menu.getScreenManager().getMusicManager().setPreviousVolume(menu.getSettings().getMusicSound());
				menu.getScreenManager().getMusicManager().setVolume(0);
				} else {
					menu.getScreenManager().getMusicManager().setVolume(menu.getSettings().getMusicSound());
				}
				
				return true;
			}
		});

		// set up the select difficulty drop-down
		String[] difficulties = { "Easy", "Medium", "Hard", "Insane" };
		SelectBoxStyle style = defaultSkin.get(SelectBoxStyle.class);
		style.font = font;
		style.listStyle.font = font;
		difficultySelect = new SelectBox<String>(style);

		difficultySelect.setItems(difficulties);
		difficultySelect.setSelected(menu.getSettings().getDifficulty().toString());
		difficultySelect.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
					menu.getSettings().setDifficulty(Difficulty.valueOf(difficultySelect.getSelected()));
			}
		});
	}
	/**
	 * Set up the stage initially
	 */
	private void setUpStage(Stage stage)
	{
		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				
				if(keycode == Keys.ESCAPE)
					menu.returnToGame();
				return true;
			}
		});
	}

	/**
	 * Set up tables for the background designs
	 */
	private void setDesignTables() {

		Texture dragonTexture = new Texture("Dragon Image.png");
		TextureRegion d1 = new TextureRegion(dragonTexture);
		TextureRegion d2 = new TextureRegion(dragonTexture);
		d2.flip(true, false);
		Image drake1 = new Image(d1);
		Image drake2 = new Image(d2);

		twinDragonTable = new Table();
		twinDragonTable.setFillParent(true);
		twinDragonTable.top().padTop(10);
		twinDragonTable.add(drake1).height(170);
		twinDragonTable.add(drake2).height(170);
	}

	/**
	 * Get rid of garbage
	 */
	public void dispose() {
		this.font.dispose();
		this.buttonAtlas1.dispose();
		if (this.buttonAtlas2 != null)
			this.buttonAtlas2.dispose();
	}
}
