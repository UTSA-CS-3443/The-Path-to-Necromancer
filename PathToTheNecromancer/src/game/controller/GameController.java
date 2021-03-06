package game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.utils.Array;

import game.controller.story.Interaction;
import game.model.sprites.InteractionSprites;
import game.model.sprites.player.Player;
import game.view.PlayScreen;

/**
 * Controller for user input in the main game screen.
 * 
 * @author enigma-phi
 *
 */
public class GameController implements InputProcessor {
	/**
	 * The player's character
	 */
	private Player player;
	/**
	 * The screen of the current game
	 */
	private PlayScreen screen;
	/**
	 * Character's speed of motion
	 */
	private int velocity = 50;

	/**
	 * Construct the controller object
	 * 
	 * @param player
	 *            is the user
	 */
	public GameController(PlayScreen screen) {
		this.player = screen.getPlayer();
		this.screen = screen;
	}

	/**
	 * Control the input for the character. Controls character motion.
	 */
	public void handleInput() {

		// set the player's s
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT))
			this.velocity = 100;
		else
			this.velocity = 50;

		// Control 8-way motion
		int xVel = 0; // not moving
		int yVel = 0; // not moving

		// Set motion = to true if any of the different direction keys is working
		boolean up = Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W);
		boolean down = Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S);
		boolean left = Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A);
		boolean right = Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D);

		// If keys in opposite directions are pressed, they cancel each other out
		boolean leftRight = left ^ right;
		boolean upDown = up ^ down;

		// determine which x-direction key is pressed
		if (leftRight) {
			if (right)
				xVel = velocity;
			else
				xVel = -1 * velocity;
		}
		// determine which y-direction key is pressed
		if (upDown) {
			if (up)
				yVel = velocity;
			else
				yVel = -1 * velocity;
		}

		// set the player's velocity
		player.setVelocity(new Vector2(xVel, yVel));

	}

	/**
	 * Set the position of the camera to the position of the player
	 * 
	 * @param gameCam
	 *            is the camera to modify.
	 */
	public void updateCamera(OrthographicCamera gameCam) {
		gameCam.position.x = (player.getBody()).getPosition().x;
		gameCam.position.y = (player.getBody()).getPosition().y;
		gameCam.update();
	}

	/**
	 * Set the user's character
	 * 
	 * @param player
	 *            is the user's character
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Respond to player input.
	 * 
	 * @param gives
	 *            an integer corresponding to the key the player pressed.
	 */
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.ESCAPE:
			this.screen.setMenu();
			break;
		case Keys.ENTER:
			Array<Contact> contacts = this.screen.getWorld().getContactList();

			// Determine if the user is trying to interact with somebody
			for (Contact contact : contacts) {
				if (contact.getFixtureA().getUserData() == "Interaction"
						&& contact.getFixtureB().getUserData() instanceof InteractionSprites) {
					screen.startChat();
					Interaction interact = new Interaction(
							((InteractionSprites) contact.getFixtureB().getUserData()).getDialogue(this.player),
							screen.getDialogueBox(), player);
					screen.getDialogueBox().setInteraction(interact);
					break;
				} else if (contact.getFixtureB().getUserData() == "Interaction"
						&& contact.getFixtureA().getUserData() instanceof InteractionSprites) {
					screen.startChat();
					Interaction interact = new Interaction(
							((InteractionSprites) contact.getFixtureA().getUserData()).getDialogue(this.player),
							screen.getDialogueBox(), player);
					screen.getDialogueBox().setInteraction(interact);
					break;
				}
			}
			break;
		case Keys.NUM_1:
			Gdx.app.log("X", String.valueOf(player.getX()));
			Gdx.app.log("Y", String.valueOf(player.getY()));
		default:
		}
		return true;
	}

	/**
	 * Unimplemented
	 */
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * Unimplemented
	 */
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Unimplemented
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Unimplemented
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Unimplemented
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Unimplemented
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Unimplemented
	 */
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
