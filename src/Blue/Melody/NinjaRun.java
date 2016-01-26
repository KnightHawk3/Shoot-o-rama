package Blue.Melody;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NinjaRun extends StateBasedGame {
    public enum STATES {
        //MAINMENU,
        GAME,
        GAME_WON,
        GAME_OVER
    }
    public static final int SCREEN_WIDTH = 1366;
    public static final int SCREEN_HEIGHT = 768;
    public static final int FPS = 60;
    public static final String VERSION = "0.0.1";

    public NinjaRun(String gamename) {
        super(gamename);
    }

    // Initialize your game states (calls init method of each gamestate, and set's the state ID)
    public void initStatesList(GameContainer gc) throws SlickException {
        // The first state added will be the one that is loaded first, when the application is launched
        this.addState(new Game(STATES.GAME.ordinal()));
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new NinjaRun("Simple Slick Game"));
            appgc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
            appgc.setShowFPS(true);
            appgc.setTargetFrameRate(FPS);
            appgc.setVSync(false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(NinjaRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
