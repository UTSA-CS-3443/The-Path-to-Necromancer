package game.model.sprites.player;

import game.model.sprites.CharacterSprites;

/**
 * The player class used throughout the game by the user.
 * 
 * @author enigma-phi
 *
 */
public abstract class Player extends CharacterSprites {
    /**
     * The player's current level
     */
    private int level;
    /**
     * The player's current experience. After reaching a new level, the player's
     * experience will be reset to zero.
     */
    private int experience;

    /**
     * The player's strength for physical attack and defense
     */
    private int strength;

    /**
     * The player's intelligence for magic attack and defense
     */
    private int intelligence;

    /**
     * The player's dexterity for intiative and reflex
     */
    private int dexterity;

    /**
     * The player's luck for critical hits
     */
    private int luck;
}