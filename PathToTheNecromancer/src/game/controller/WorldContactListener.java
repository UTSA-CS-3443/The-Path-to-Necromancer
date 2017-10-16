package game.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

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
     * @param contact that occurs
     */
    @Override
    public void beginContact(Contact contact) {
        // Separate the fixtures
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // If one of the fixtures in contact was the player
        if (fixA.getUserData() == "Player" || fixB.getUserData() == "Player") {
            // determine which fixture was the player
            Fixture player = fixA.getUserData() == "Player" ? fixA : fixB;
            Fixture object = player == fixA ? fixB : fixA;

            if (object.getUserData() != null) {
                // If the user collided with a map object
                if (object.getUserData() instanceof GameMaps) {
                    ((GameMaps) object.getUserData()).transitionAreas(object.getFilterData().categoryBits);
                }
                // if there was a different type of player-object collision.
            }
        }

    }

    @Override
    public void endContact(Contact arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void postSolve(Contact arg0, ContactImpulse arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void preSolve(Contact arg0, Manifold arg1) {
        // TODO Auto-generated method stub

    }

}
