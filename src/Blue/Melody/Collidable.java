package Blue.Melody;

import org.newdawn.slick.Image;

import java.awt.*;

/**
 * Created by Melody on 1/19/2016.
 */
public interface Collidable {
    int getWidth();
    int getHeight();
    int getPosX();
    int getPosY();
    /* This returns the location it needs to be to not enter the object */
    boolean willEnter(Collidable other);
    /* This handles what happens when we enter the object */
    void collidedWith(Collidable other);
    Rectangle getHitbox();
    Image getCurrentSprite();
}
