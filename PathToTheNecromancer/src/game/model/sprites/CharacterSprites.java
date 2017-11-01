package game.model.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Abstract Class used to define the general character sprites for use
 * throughout the game.
 * 
 * @author enigma-phi
 *
 */
public abstract class CharacterSprites extends GameSprites {
    /**
     * Region for a standing Animation
     */
    private TextureRegion standingRegion;
    /**
     * Animation for moving the character up
     */
    private Animation<TextureRegion> moveUp;
    /**
     * Animation for moving the character left
     */
    private Animation<TextureRegion> moveLeft;
    /**
     * Animation for moving the character right
     */
    private Animation<TextureRegion> moveRight;
    /**
     * Animation for moving the character down
     */
    private Animation<TextureRegion> moveDown;
    /**
     * Animation for moving the character in the up-right diagonal
     */
    private Animation<TextureRegion> moveUpRight;
    /**
     * Animation for moving the character in the up-left diagonal
     */
    private Animation<TextureRegion> moveUpLeft;
    /**
     * Animation for moving the character in the down-right diagonal
     */
    private Animation<TextureRegion> moveDownRight;
    /**
     * Animation for moving the character in the down-left diagonal
     */
    private Animation<TextureRegion> moveDownLeft;
    /**
     * The character's current animation state
     */
    private State currentState;
    /**
     * The character's previous animation state
     */
    private State previousState;
    /**
     * Timer used for Animation
     */
    private float stateTimer;

    /**
     * An enum used to define different animation states
     */
    private enum State {
        UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, STANDING
    };

    /**
     * The region the character is currently on. This is used so that when the
     * character stops moving, the character remains in the current animation frame
     */
    private TextureRegion currentRegion;
    /**
     * The physics body for the character
     */
    private Body body;

    /**
     * Construct the character and initialize values.
     */
    public CharacterSprites() {
        super();
        this.stateTimer = 0;
        this.currentState = State.STANDING;
        this.previousState = State.STANDING;
    }

    public abstract void defineBody(World world, int x, int y);

    /**
     * Determine whether or not the character is moving and if so, in which
     * direction the character is moving in.
     * 
     * @return the direction the character is moving
     */
    public State getState() {
        if (this.body.getLinearVelocity().y > 0 && this.body.getLinearVelocity().x == 0)
            return State.UP;
        else if (this.body.getLinearVelocity().y < 0 && this.body.getLinearVelocity().x == 0)
            return State.DOWN;
        else if (this.body.getLinearVelocity().y == 0 && this.body.getLinearVelocity().x > 0)
            return State.RIGHT;
        else if (this.body.getLinearVelocity().y == 0 && this.body.getLinearVelocity().x < 0)
            return State.LEFT;
        else if (this.body.getLinearVelocity().y > 0 && this.body.getLinearVelocity().x > 0)
            return State.UPRIGHT;
        else if (this.body.getLinearVelocity().y > 0 && this.body.getLinearVelocity().x < 0)
            return State.UPLEFT;
        else if (this.body.getLinearVelocity().y < 0 && this.body.getLinearVelocity().x > 0)
            return State.DOWNRIGHT;
        else if (this.body.getLinearVelocity().y < 0 && this.body.getLinearVelocity().x < 0)
            return State.DOWNLEFT;
        else
            return State.STANDING;
    }

    /**
     * Get the current Animation frame the character is on for rendering
     * 
     * @param dt
     *            the time interval for determining which frame the character
     *            animation is on
     * @return the texture region to render
     */
    public TextureRegion getFrame(float dt) {
        // get the character's current state of motion
        currentState = getState();
        int animationSpeed = 4;
        
        if(this.body.getLinearVelocity().x > 50 || this.body.getLinearVelocity().y > 50)
            animationSpeed = 3;
        TextureRegion region;

        // set the current texture region for the animation
        switch (currentState) {
        case UP:
            region = this.moveUp.getKeyFrame(stateTimer, true);
            break;
        case DOWN:
            region = this.moveDown.getKeyFrame(stateTimer, true);
            break;
        case LEFT:
            region = this.moveLeft.getKeyFrame(stateTimer, true);
            break;
        case RIGHT:
            region = this.moveRight.getKeyFrame(stateTimer, true);
            break;
        case UPRIGHT:
            region = this.moveUpRight.getKeyFrame(stateTimer, true);
            break;
        case UPLEFT:
            region = this.moveUpLeft.getKeyFrame(stateTimer, true);
            break;
        case DOWNLEFT:
            region = this.moveDownLeft.getKeyFrame(stateTimer, true);
            break;
        case DOWNRIGHT:
            region = this.moveDownRight.getKeyFrame(stateTimer, true);
            break;
        case STANDING:
        default:
            // when the character starts moving, stay facing the same way in the same
            // animation
            if (this.currentRegion != null)
                return this.currentRegion;
            else
                region = this.standingRegion;
            break;
        }

        // move the animation forward
        stateTimer = currentState == previousState ? stateTimer + dt / animationSpeed : 0;
        previousState = currentState;
        this.currentRegion = region;
        return region;
    }

    /**
     * Set the current velocity of the character
     */
    public void setVelocity(Vector2 vector) {
        this.body.setLinearVelocity(vector);
    }

    /**
     * Update the character's position
     * 
     * @param dt
     */
    public void update(float dt) {
        setPosition(this.body.getPosition().x - getWidth() / 2, this.body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    /**
     * Set the texture values associated with the character animations.
     */
    public abstract void setTextureValues();

    /**
     * Get the moveUp animation
     * 
     * @return the moveUp animation
     */
    public Animation<TextureRegion> getMoveUp() {
        return moveUp;
    }

    /**
     * Set the moveUp animation
     * 
     * @param the
     *            new moveUp animation to set to
     */
    public void setMoveUp(Animation<TextureRegion> moveUp) {
        this.moveUp = moveUp;
    }

    /**
     * Get the moveLeft animation
     * 
     * @return the moveLeft animation
     */
    public Animation<TextureRegion> getMoveLeft() {
        return moveLeft;
    }

    /**
     * Set the moveLeft animation
     * 
     * @param the
     *            new moveLeft animation to set to
     */
    public void setMoveLeft(Animation<TextureRegion> moveLeft) {
        this.moveLeft = moveLeft;
    }

    /**
     * Get the moveRight animation
     * 
     * @return the moveRight animation
     */
    public Animation<TextureRegion> getMoveRight() {
        return moveRight;
    }

    /**
     * Set the moveRight animation
     * 
     * @param the
     *            new moveRight animation to set to
     */
    public void setMoveRight(Animation<TextureRegion> moveRight) {
        this.moveRight = moveRight;
    }

    /**
     * Get the moveDown animation
     * 
     * @return the moveDown animation
     */
    public Animation<TextureRegion> getMoveDown() {
        return moveDown;
    }

    /**
     * Set the moveDown animation
     * 
     * @param the
     *            new moveDown animation to set to
     */
    public void setMoveDown(Animation<TextureRegion> moveDown) {
        this.moveDown = moveDown;
    }

    /**
     * Get the moveUpRight animation
     * 
     * @return the moveUpRight animation
     */
    public Animation<TextureRegion> getMoveUpRight() {
        return moveUpRight;
    }

    /**
     * Set the moveUpRight animation
     * 
     * @param the
     *            new moveUpRight animation to set to
     */
    public void setMoveUpRight(Animation<TextureRegion> moveUpRight) {
        this.moveUpRight = moveUpRight;
    }

    /**
     * Get the moveUpLeft animation
     * 
     * @return the moveUpLeft animation
     */
    public Animation<TextureRegion> getMoveUpLeft() {
        return moveUpLeft;
    }

    /**
     * Set the moveUpLeft animation
     * 
     * @param the
     *            new moveUpLeft animation to set to
     */
    public void setMoveUpLeft(Animation<TextureRegion> moveUpLeft) {
        this.moveUpLeft = moveUpLeft;
    }

    /**
     * Get the moveDownRight animation
     * 
     * @return the moveDownRight animation
     */
    public Animation<TextureRegion> getMoveDownRight() {
        return moveDownRight;
    }

    /**
     * Set the moveDownRight animation
     * 
     * @param the
     *            new moveDownRight animation to set to
     */
    public void setMoveDownRight(Animation<TextureRegion> moveDownRight) {
        this.moveDownRight = moveDownRight;
    }

    /**
     * Get the moveDownLeft animation
     * 
     * @return the moveDownLeft animation
     */
    public Animation<TextureRegion> getMoveDownLeft() {
        return moveDownLeft;
    }

    /**
     * Set the moveDownLeft animation
     * 
     * @param the
     *            new moveDownLeft animation to set to
     */
    public void setMoveDownLeft(Animation<TextureRegion> moveDownLeft) {
        this.moveDownLeft = moveDownLeft;
    }

    /**
     * Get the standing texture region
     * 
     * @return the standing texture region
     */
    public TextureRegion getStandingRegion() {
        return standingRegion;
    }

    /**
     * Set the standing texture region
     * 
     * @param the
     *            new standing texture region to set to
     */
    public void setStandingRegion(TextureRegion standingRegion) {
        this.standingRegion = standingRegion;
    }

    /**
     * Set the sprite's body
     * 
     * @param the
     *            new sprite body to set to
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * Get the sprite's body
     * 
     * @return the sprite's body
     */
    public Body getBody() {
        return this.body;
    }

}
