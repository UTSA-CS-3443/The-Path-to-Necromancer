package game.controller.story;

/**
 * Holds an actor that it then acts upon.
 * 
 * @author enigma-phi
 *
 */
public class StoryActor {
	/**
	 * The actor that the story actor acts on
	 */
	private Actor actor;

	/**
	 * The Actor to set the story's actor to
	 * 
	 * @param actor
	 */
	public void actor(Actor actor) {
		this.actor = actor;
	}

	/**
	 * The actor perfoms its act action
	 * 
	 * @param is
	 *            the change in time since the last render
	 */
	public void act(float dt) {
		if (this.actor != null)
			this.actor.act(dt);
	}
}
