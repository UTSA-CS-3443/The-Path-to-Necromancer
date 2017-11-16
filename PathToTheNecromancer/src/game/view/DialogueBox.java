package game.view;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import game.controller.ScreenManager;
import game.controller.story.Interaction;

/**
 * The dialogue box for rendering text from in-game characters
 * 
 * @author enigma-phi
 *
 */
public class DialogueBox {
	/**
	 * The Stage Dialogue is rendered on
	 */
	private Stage dialogueStage;
	/**
	 * The text that the stage will render. We render one character at a time.
	 */
	private Stack<Character> textToRender;
	/**
	 * The text that is currently being rendered.
	 */
	private String currentText;
	/**
	 * The current time since the last update of currentText
	 */
	private float time;
	/**
	 * Whether or not the current text to be rendered has been completely rendered
	 */
	private boolean completed;
	/**
	 * The Label for the current panel
	 */
	private Label label;
	/**
	 * The BitmapFont used in the text
	 */
	private BitmapFont font;
	/**
	 * The table that contains the panels
	 */
	private Table table;
	/**
	 * The number of buttons in the table
	 */
	private int buttonCount;
	/**
	 * Whether or not the dialogue box is awaiting user input for the buttons
	 */
	private boolean buttonsWaiting;
	/**
	 * The screen manager for the whole game
	 */
	private ScreenManager manager;
	/**
	 * The time since the space bar was last hit to skip to the end the line
	 */
	private float timeSinceEndLine;
	/**
	 * The time since the space bar was last hit to move to the next line
	 */
	private float timeSinceNextLine;
	/**
	 * Whether or not the space button was pressed previously.
	 */
	private boolean spaced;
	/**
	 * The interaction to work with
	 */
	private Interaction interaction;

	/**
	 * Constructor
	 * 
	 * @param manager
	 *            for managing contact listeners and screen values
	 */
	public DialogueBox(ScreenManager manager) {
		this.manager = manager;
		this.dialogueStage = new Stage(manager.getViewport());
		Gdx.input.setInputProcessor(this.dialogueStage);
		this.timeSinceEndLine = 0;
		this.spaced = false;

		font = new BitmapFont(Gdx.files.internal("MagicCardFont.fnt"), false);
		font.getData().setScale(.4f);
		font.getData().setLineHeight(40f);

		// create the texture background
		TextureRegionDrawable draw = new TextureRegionDrawable(
				new TextureRegion(new Texture("assets/Buttons/TextBackground2.png")));

		// set up the table
		table = new Table();
		table.left();
		table.bottom();
		table.setHeight(55);
		table.setWidth(300);
		table.setBackground(draw);
		this.dialogueStage.addActor(table);

	}

	/**
	 * Add text to the dialogue stage
	 * 
	 * @param text
	 *            is the text to set it to
	 */
	public void setText(String text) {
		this.buttonsWaiting = false;

		this.textToRender = new Stack<Character>();
		for (int i = text.length() - 1; i >= 0; i--) {
			textToRender.push(text.charAt(i));
		}
		this.currentText = "";
		this.label = new Label(this.currentText, new Label.LabelStyle(this.font, Color.BLACK));
		this.completed = false;
		label.setWrap(true);

		// add the label to the label
		table.clearChildren();
		table.add(label).top().padRight(5).padLeft(5).width(290).padTop(5).expandY();
	}

	/**
	 * Update the text that is currently being rendered based off of the change in
	 * time
	 * 
	 * @param dt
	 *            is the change in time
	 */
	public void update(float dt) {
		this.timeSinceNextLine += dt;
		if (this.spaced) {
			this.timeSinceEndLine += dt;
		}

		// if there is no more text to render do not update the text
		if (textToRender.isEmpty())
			completed = true;
		if (completed)
			return;

		// update the text based off of the change in time
		this.time += dt;
		if (this.time >= 0.05) {
			this.currentText += textToRender.pop();
			this.time = 0;
			if (textToRender.isEmpty()) {
				completed = true;
				this.spaced = true;
			}
		}

		// if the user hits the space button, finish rendering text
		if (Gdx.input.isKeyPressed(Keys.SPACE) && this.timeSinceNextLine > 0.25) {
			while (!textToRender.isEmpty())
				this.currentText += textToRender.pop();
			completed = true;
			this.spaced = true;
			this.timeSinceEndLine = 0;
		}

		// set the current text to use
		this.label.setText(this.currentText);
	}

	/**
	 * First, update your values based on the change in time. Then render.
	 * 
	 * @param dt
	 *            is the change in time since the last render
	 */
	public void render(float dt) {
		// If the user hits space after the text has completed rendering,
		// move on to the next interaction
		if (Gdx.input.isKeyPressed(Keys.SPACE) && completed && this.timeSinceEndLine >= 0.25) {
			this.timeSinceEndLine = 0;
			this.timeSinceNextLine = 0;
			this.interaction.act(this.manager.getPlayer(), this.manager.getMapManager());
			this.interaction.getNextInteraction();
			this.spaced = false;
		}

		// The user can simply exit from dialogue
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			this.endDialogue();

		// If you are waiting for a button, don't update the text because there
		// is nothing to update.
		if (!this.buttonsWaiting)
			this.update(dt);

		this.dialogueStage.act();
		this.dialogueStage.draw();
	}

	/**
	 * Set the table up to start adding buttons
	 */
	public void buttonsStart() {
		this.table.clearChildren();
		this.buttonCount = 0;
		this.completed = false;
	}

	/**
	 * Add a single button
	 * 
	 * @param buttonText
	 *            is the text on the button
	 */
	public void addButton(String buttonText) {
		TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
		style.font = this.font;

		// create the button
		TextButton button = new TextButton("\"" + buttonText + "\"", style);
		button.getLabel().setWrap(true);
		button.getLabel().setColor(Color.BLACK);

		int count = buttonCount;
		buttonCount++;

		// add an input listener
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				interaction.setDialogueInput(count, manager.getMapManager());
				return true;
			}
		});

		// add the button to the table
		table.add(button).left().padRight(5).padLeft(5).width(290).expandY();
		table.row();
	}

	/**
	 * Finish adding the buttons
	 */
	public void buttonsEnd() {
		this.buttonsWaiting = true;
	}

	/**
	 * End the dialogue sequence and reset
	 */
	public void endDialogue() {
		this.manager.resetInput();
		this.manager.setDialogue(false);
		this.interaction.resetDialogue();
	}

	/**
	 * Set the interaction to render
	 * 
	 * @param interaction
	 *            is the interaction to render
	 */
	public void setInteraction(Interaction interaction) {
		this.interaction = interaction;
		this.interaction.getFirstInteraction();
	}

	/**
	 * Set the stage up to be the input processor for the screen
	 */
	public void setInput() {
		Gdx.input.setInputProcessor(this.dialogueStage);
	}
}
