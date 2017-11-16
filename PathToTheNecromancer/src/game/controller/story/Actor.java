package game.controller.story;

/**
 * Classes that implement the Actor interface contain an act method that allows
 * them to perform some action unique to each class.
 * 
 * @author enigma-phi
 *
 */
public interface Actor {
	/**
	 * Perform some action
	 * 
	 * @param is
	 *            the change in time since the last render
	 */
	public void act(float dt);

}
