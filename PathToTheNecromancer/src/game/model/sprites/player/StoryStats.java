package game.model.sprites.player;

/**
 * The player's stats corresponding to story
 * @author enigma-phi
 *
 */
public class StoryStats {
	/**
	 * The Necromancer Encounters
	 */
	private int necEncounters;
	/**
	 * The Bandit Encounters
	 */
	private int banditEncounters;
	/**
	 * The Book Encounters
	 */
	private boolean bookEncounters;
	/**
	 * Keep track of the player's conversations with villagers 
	 */
	private int villagerConversations;
	
	/**
	 * Constructor for the StoryStats object. Initialize values as if 
	 * it were a new game.
	 */
	public StoryStats() {
		this.necEncounters = 0;
		this.banditEncounters = 0;
		this.bookEncounters = true;
		this.villagerConversations = 0;
	}
	
	public int getNecEncounters() {
		return necEncounters;
	}
	public void setNecEncounters(int necEncounters) {
		this.necEncounters = necEncounters;
	}
	public int getBanditEncounters() {
		return banditEncounters;
	}
	public void setBanditEncounters(int banditEncounters) {
		this.banditEncounters = banditEncounters;
	}
	public boolean isBookEncounters() {
		return bookEncounters;
	}
	public void setBookEncounters(boolean bookEncounters) {
		this.bookEncounters = bookEncounters;
	}
	public int getVillagerConversations() {
		return villagerConversations;
	}
	public void setVillagerConversations(int villagerConversations) {
		this.villagerConversations = villagerConversations;
	}
}
