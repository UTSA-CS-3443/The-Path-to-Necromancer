package game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import game.model.sprites.player.Player;

/**
 * Controller for user input
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
     * Character's speed of motion
     */
    private final int VELOCITY = 50;

    /**
     * Construct the controller object
     * 
     * @param player
     *            is the user
     */
    public GameController(Player player) {
        this.player = player;
    }

    /**
     * Control the input for the character. Controls character motion.
     */
    public void handleInput() {

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
                xVel = VELOCITY;
            else
                xVel = -1 * VELOCITY;
        }
        // determine which y-direction key is pressed
        if (upDown) {
            if (up)
                yVel = VELOCITY;
            else
                yVel = -1 * VELOCITY;
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
     * @param gives an int corresponding to the key the player pressed.
     */
    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
        case Keys.ESCAPE:
            // render a menu
            break;
        default:
        }
        return true;
    }

    
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}
