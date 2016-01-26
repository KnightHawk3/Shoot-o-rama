package Blue.Melody;

import org.newdawn.slick.*;
import org.newdawn.slick.command.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Game extends BasicGameState implements InputProviderListener {
    private int ID;
    private Map<String,LinkedList<Drawable>> entities = new HashMap<>();
    private InputProvider provider;
    private Command moveRight = new BasicCommand("moveRight");
    private Command moveLeft = new BasicCommand("moveLeft");
    private Level map;

    public Game(int ID) throws SlickException {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        InputProvider provider = new InputProvider(container.getInput());
        provider.addListener(this);
        provider.bindCommand(new KeyControl(Input.KEY_LEFT), moveLeft);
        provider.bindCommand(new KeyControl(Input.KEY_A), moveLeft);
        provider.bindCommand(new KeyControl(Input.KEY_RIGHT), moveRight);
        provider.bindCommand(new KeyControl(Input.KEY_D), moveRight);
        LinkedList<Drawable> players = new LinkedList<>();
        players.add(new Hero());
        entities.put("player", players);

        map = new Level("Resources/Levels/Fantasy.tmx", "Resources/Sprites/");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.scale(2,2);
        g.setBackground(Color.blue);
        map.render(0,0);
        // Draw it all.
        for (Map.Entry<String, LinkedList<Drawable>> drawables : entities.entrySet()) {
            drawables.getValue().forEach(Drawable::draw);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Hero player = (Hero) entities.get("player").getFirst();
        player.update(delta);
        if (player.getMovingRight()) {
            player.moveRight(delta);
        }
        if (player.getMovingLeft()) {
            player.moveLeft(delta);
        }
        if (map.willEnter(player)) {
            player.setPosY(player.getPosY() - 1);
        }
    }

    @Override
    public void controlPressed(Command command) {
        Hero player = (Hero) entities.get("player").getFirst();
        if (command.equals(moveRight)) {
            player.setMovingRight(true);
        } else if (command.equals(moveLeft)) {
            player.setMovingLeft(true);
        }
    }

    @Override
    public void controlReleased(Command command) {
        Hero player = (Hero) entities.get("player").getFirst();
        if (command.equals(moveRight)) {
            player.setMovingRight(false);
        } else if (command.equals(moveLeft)) {
            player.setMovingLeft(false);
        }
    }
}