package Blue.Melody;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Melody on 1/16/2016.
 */
public class GameOver extends BasicGameState {
    private int ID;

    public GameOver(int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // TODO: Save game or something?
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // TODO: Render you lost
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // TODO: Something?
    }
}
