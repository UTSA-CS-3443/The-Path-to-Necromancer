package game.controller.story;

import com.badlogic.gdx.physics.box2d.World;

import game.model.sprites.player.Player;

/**
 * Classes that implement the Actor interface contain an act method that allows
 * them to perform some action unique to each class.
 * 
 * @author enigma-phi
 *
 */
public interface Actor {
	/**
	 * Set up the actor
	 * 
	 * @param world
	 *            is the world to set up the actor in
	 * @param player
	 *            is the player to base set up upon
	 */
	public void setUpActor(World world, Player player);

	/**
	 * Perform some action
	 * 
	 * @param is
	 *            the change in time since the last render
	 */
	public void act(float dt);

}
