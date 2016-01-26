package Blue.Melody;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;

/**
 * Created by Melody on 1/20/2016.
 */
public class Level extends TiledMap implements Collidable {
    Rectangle[][] collisionArray;

    public Level(String ref, String tileSetsLocation) throws SlickException {
        super(ref, tileSetsLocation);
        this.collisionArray = new Rectangle[this.getWidth()][this.getHeight()];
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                this.collisionArray[x][y] = new Rectangle(x * this.getTileWidth(),
                                                          y * this.getTileHeight(),
                                                          this.getTileWidth(),
                                                          this.getTileHeight());
            }
        }
    }

    @Override
    public int getPosX() {
        return 0;
    }

    @Override
    public int getPosY() {
        return 0;
    }

    @Override
    public boolean willEnter(Collidable other) {
        Rectangle otherHitbox = other.getHitbox();
        if (this.getTileId((int) otherHitbox.getX() / this.getTileWidth(),
                                          (int) otherHitbox.getY() / this.getTileHeight(), 0) != 0) {
            if (otherHitbox.intersects(
                this.collisionArray[(int) otherHitbox.getX() / this.getTileWidth()]
                                   [(int) otherHitbox.getY() / this.getTileHeight()])) {
                System.out.println("Something is entering the map");
                return true;
            }
        }
        return false;
    }

    @Override
    public void collidedWith(Collidable other) {

    }

    @Override
    public Rectangle getHitbox() {
        return null;
    }

    @Override
    public Image getCurrentSprite() {
        return null;
    }
}
