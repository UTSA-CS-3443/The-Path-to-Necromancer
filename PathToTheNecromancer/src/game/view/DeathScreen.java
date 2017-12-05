package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.controller.ScreenManager;

/**
 * The screen that renders when the player dies
 * @author enigma-phi
 *
 */
public class DeathScreen implements Screen{
	/**
	 * The Manager of the screens
	 */
	private ScreenManager screenManager;
	/**
	 * The viewport to render the stage on
	 */
	private Viewport viewport;
	/**
	 * The font for the game
	 */
	private BitmapFont font;
	/**
	 * The stage for the death screen
	 */
	private Stage stage;
	/**
	 * Constructor. Sets up values and creates the screen
	 * @param screenManager manages the screens
	 */
	public DeathScreen(ScreenManager screenManager) {
		this.screenManager = screenManager;
		this.viewport = this.screenManager.getViewport();
		this.font = new BitmapFont(Gdx.files.internal("MagicCardFont.fnt"), false);
		this.font.getData().setScale(1.2f);
		this.setStage();
		//this.screenManager.getMusicManager().setMusic((Music) Gdx.audio.newMusic(Gdx.files.internal("assets/ACT. 8. Unity (Chris Tilton) Assassin's Creed Unity.mp3")));
	}
	
	/**
	 * Set up the stage for the buttons to render on
	 */
	private void setStage() {
		this.stage = new Stage(this.viewport);
		Gdx.input.setInputProcessor(stage);
		
		// Set up the label
		Label.LabelStyle style = new Label.LabelStyle(this.font, Color.RED);
		Label diedLabel = new Label("YOU DIED", style);
		
		// Set the button for quitting the game
		TextureAtlas buttonAtlas1 = new TextureAtlas("Buttons/Buttons.pack");
		Skin skin = new Skin();
		skin.addRegions(buttonAtlas1);
		TextButtonStyle quitButtonStyle = new TextButtonStyle();
		quitButtonStyle.font = font;
		quitButtonStyle.up = skin.getDrawable("QuitGameButton");
		quitButtonStyle.down = skin.getDrawable("QuitGameButton");
		quitButtonStyle.checked = skin.getDrawable("QuitGameButton");
		TextButton quitButton = new TextButton("", quitButtonStyle);
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
		
		// The table for the stage
		Table table = new Table();
		table.setFillParent(true);
		table.setBackground(new TextureRegionDrawable(
						new TextureRegion(new Texture("assets/Necro Pictures/Lightning.png"))));
		table.center();
		table.add(diedLabel).padBottom(30);
		table.row();
		table.add(quitButton).width(80).height(30).padBottom(30);
		
		stage.addActor(table);
	}
	
	/**
	 * Free up garbage
	 */
	@Override
	public void dispose() {
		this.stage.dispose();
		this.font.dispose();
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
	public void render(float dt) {
		// clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}

	/**
	 * Resize the view port
	 */
	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height);
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
