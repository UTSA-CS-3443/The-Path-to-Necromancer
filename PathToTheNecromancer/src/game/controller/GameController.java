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
    private final int VELOCITY = 100;

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

        // If keys in opposite directions are pressed, they cancel each other out
        boolean leftRight = Gdx.input.isKeyPressed(Keys.LEFT) ^ Gdx.input.isKeyPressed(Keys.RIGHT);
        boolean upDown = Gdx.input.isKeyPressed(Keys.UP) ^ Gdx.input.isKeyPressed(Keys.DOWN);

        // determine which x-direction key is pressed
        if (leftRight) {
            if (Gdx.input.isKeyPressed(Keys.RIGHT))
                xVel = VELOCITY;
            else
                xVel = -1 * VELOCITY;
        }
        // determine which y-direction key is pressed
        if (upDown) {
            if (Gdx.input.isKeyPressed(Keys.UP))
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

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
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