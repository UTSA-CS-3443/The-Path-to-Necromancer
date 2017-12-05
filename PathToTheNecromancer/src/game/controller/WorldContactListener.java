package game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import game.model.sprites.CharacterSprites;
import game.model.sprites.player.Player;
import game.model.maps.GameMaps;

/**
 * Look for user contact with different map objects and other fixtures
 * 
 * @author enigma-phi
 *
 */
public class WorldContactListener implements ContactListener {

    /**
     * When the contact happens, perform different actions
     * 
     * @param contact
     *            that occurs
     */
    @Override
    public void beginContact(Contact contact) {
        // Separate the fixtures
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // If one of the fixtures in contact was the player
        if (fixA.getUserData() instanceof Player || fixB.getUserData() instanceof Player) {
            // determine which fixture was the player
            Fixture player = fixA.getUserData() instanceof Player ? fixA : fixB;
            Fixture object = player == fixA ? fixB : fixA;

            if (object.getUserData() != null) {
                // If the user collided with a map object
                if (object.getUserData() instanceof GameMaps) {
                    ((GameMaps) object.getUserData()).transitionAreas(object.getFilterData().categoryBits);
                }

            }
        }

        // If an NPC collides with a wall, cease motion. If an NPC moves off the map, remove the sprite animation
        if (fixA.getUserData() instanceof CharacterSprites && !(fixA.getUserData() instanceof Player)
                || fixB.getUserData() instanceof CharacterSprites && !(fixB.getUserData() instanceof Player)) {

            Fixture character = (fixA.getUserData() instanceof CharacterSprites
                    && !(fixA.getUserData() instanceof Player)) ? fixA : fixB;
            Fixture object = character == fixA ? fixB : fixA;
            if (object.getUserData() != null) {
                if (object.getUserData() instanceof GameMaps) {
                    ((GameMaps) object.getUserData()).remove((CharacterSprites) character.getUserData());
                }
            } else {
                ((CharacterSprites) character.getUserData()).setVelocity(new Vector2(0, 0));
            }
        }

    }

    /**
     * Unimplemented
     */
    @Override
    public void endContact(Contact arg0) {
        // TODO Auto-generated method stub
    }

    /**
     * Unimplemented
     */
    @Override
    public void postSolve(Contact arg0, ContactImpulse arg1) {
        // TODO Auto-generated method stub
    }

    /**
     * Unimplemented
     */
    @Override
    public void preSolve(Contact arg0, Manifold arg1) {
        // TODO Auto-generated method stub
    }

}
