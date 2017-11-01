package game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import game.model.PathToNecromancer;
import game.model.sprites.EnemySprites;

public class CombatScreen implements Screen {
    private PlayScreen playscreen;
    private EnemySprites enemy;
    private PathToNecromancer game;

    public CombatScreen(PlayScreen playscreen, PathToNecromancer game) {
        // You use the playscreen to set the screen and gain information,
        // like the player and mapManage, both of which you may need
        // this.playscreen = playscreen;
        // The only reason for the game is to set the screen
        // this.game = game;
        // You need an enemy right? I need to know which enemies go on
        // which maps
        // this.enemy = this.playscreen.getMapManager().getEnemy();
        // You can set an input processor (like E = escape) or whatever
        // You have to set some sort of input processor to override the
        // current one.
        // Gdx.input.setInputProcessor(new CombatInputProcessor());

        // You may need to use a camera or a
        // fitscreen, look at playscreen mostly for that
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    /**
     * Update the combat
     * 
     * @param dt
     *            is the used to update the character sprite (see CharacterSprites)
     */
    public void update(float dt) {

    }

    /**
     * Render the game's combat
     */
    @Override
    public void render(float delta) {
        // update the combat
        this.update(delta);

        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render the combat from bottom up (background first)

        // this.batch.begin();
        // for each sprite, render it (just the sprites)
        // this.batch.end();

    }

    /**
     * Perform operations that end the combat and return to the PlayScreen
     */
    public void endCombat() {
        // This is where to perform some end combat operations

        // change the screen and the input processor
        this.game.setScreen(this.playscreen);
        Gdx.input.setInputProcessor(this.playscreen.getInputProcessor());

    }

    @Override
    public void resize(int width, int height) {
        this.playscreen.resize(width, height);
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

}
