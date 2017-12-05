package game.model.sprites.player;

import java.io.Serializable;

/**
 * The player's stats corresponding to story
 * 
 * @author enigma-phi
 *
 */
public class StoryStats implements Serializable {
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 1L;
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
	 * Whether or not the player has talked to the merchant before. If true, the
	 * player has not talked to the merchant before. If false, the player has talked
	 * to the merchant before.
	 */
	private boolean firstMerchantChat;
	/**
	 * Whether or not the player has talked to the knight yet
	 */
	private boolean knightEncounter;
	/**
	 * Determine actions in the finale
	 */
	private int finaleEncounter;
	/**
	 * Whether or not the player has the Necromancer's textures.
	 */
	private boolean hasNecroTextures;
	/**
	 * The player's interaction with the fighter
	 */
	private int fighterEncounter;

	/**
	 * Constructor for the StoryStats object. Initialize values as if it were a new
	 * game.
	 */
	public StoryStats() {
		this.necEncounters = 0;
		this.banditEncounters = 0;
		this.bookEncounters = true;
		this.villagerConversations = 0;
		this.firstMerchantChat = true;
		this.knightEncounter = true;
		this.finaleEncounter = 0;
		this.hasNecroTextures = false;
		this.fighterEncounter = 0;
	}

	/**
	 * Determine if the player has the Necromancer's textures
	 * @return whether or not the player has the Necromancer's textures
	 */
	public boolean isHasNecroTextures() {
		return hasNecroTextures;
	}
	/**
	 * Set whether or not the player has the Necromancer's textures
	 * @param hasNecroTextures whether or not the player has the Necromancer's texture
	 */
	public void setHasNecroTextures(boolean hasNecroTextures) {
		this.hasNecroTextures = hasNecroTextures;
	}

	/**
	 * Keep track of the player's Necromancer encounters
	 * @return the Necromancer's encounters
	 */
	public int getNecEncounters() {
		return necEncounters;
	}

	/**
	 * Set the player's progress in the Necromancer's encounters
	 * @param necEncounters is the player's progress
	 */
	public void setNecEncounters(int necEncounters) {
		this.necEncounters = necEncounters;
	}

	/**
	 * Get the player's progress in the Bandit encounter
	 * @return the player's progress
	 */
	public int getBanditEncounters() {
		return banditEncounters;
	}

	/**
	 * Set the player's progress in the Bandit's encounters
	 * @param banditEncounters is the player's progress
	 */
	public void setBanditEncounters(int banditEncounters) {
		this.banditEncounters = banditEncounters;
	}

	/**
	 * Get the player's progress in the Book encounter
	 * @return the player's progress
	 */
	public boolean isBookEncounters() {
		return bookEncounters;
	}

	/**
	 * Set the player's progress in the Book's encounters
	 * @param bookEncounters is the player's progress
	 */
	public void setBookEncounters(boolean bookEncounters) {
		this.bookEncounters = bookEncounters;
	}

	/**
	 * Get the player's progress in the Villager conversation
	 * @return the player's progress
	 */
	public int getVillagerConversations() {
		return villagerConversations;
	}

	/**
	 * Set the player's progress in the Villager's conversations
	 * @param villagerConversations is the player's progress
	 */
	public void setVillagerConversations(int villagerConversations) {
		this.villagerConversations = villagerConversations;
	}

	/**
	 * Get the player's progress in the Merchant chat
	 * @return the player's progress
	 */
	public boolean isFirstMerchantChat() {
		return firstMerchantChat;
	}

	/**
	 * Set the player's progress in the Merchant chat
	 * @param firstMerchantEncounters is the player's progress
	 */
	public void setFirstMerchantChat(boolean firstMerchantChat) {
		this.firstMerchantChat = firstMerchantChat;
	}

	/**
	 * Get the player's progress in the Fighter encounter
	 * @return the player's progress
	 */
	public int getFighterEncounter() {
		return fighterEncounter;
	}

	/**
	 * Set the player's progress in the Figher's encounters
	 * @param fighterEncounter is the player's progress
	 */
	public void setFighterEncounter(int fighterEncounter) {
		this.fighterEncounter = fighterEncounter;
	}

	/**
	 * Get the player's progress in the Knight encounter
	 * @return the player's progress
	 */
	public boolean isKnightEncounter() {
		return this.knightEncounter;
	}

	/**
	 * Set the player's progress in the Knight's encounter
	 * @param knightEncounter is the player's progress
	 */
	public void setKnightEncounter(boolean knightEncounter) {
		this.knightEncounter = knightEncounter;
	}

	/**
	 * Get the player's progress in the Finale encounter
	 * @return the player's progress
	 */
	public int getFinaleEncounter() {
		return finaleEncounter;
	}
	/**
	 * Set the player's progress in the Finale encounter
	 * @param finaleEncounters is the player's progress
	 */
	public void setFinaleEncounter(int finaleEncounter) {
		this.finaleEncounter = finaleEncounter;
	}
}
