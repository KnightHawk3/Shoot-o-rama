package Blue.Melody;

import org.newdawn.slick.Color;

import java.awt.*;

/**
 * Created by Melody on 1/18/2016.
 */

public abstract class Entity implements Collidable {
    private int posY;
    private int posX;
    private double velocityX;
    private double velocityY;

    public Rectangle getHitbox() {
        return new Rectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }

    public boolean willEnter(Collidable other) {
        if (this.getHitbox().intersects(other.getHitbox())) {
            Rectangle intersection = this.getHitbox().intersection(other.getHitbox());
            for (int x = 0; x < intersection.getWidth(); x++) {
                for (int y = 0; y < intersection.getWidth(); y++) {
                    if (this.getCurrentSprite().getColor((int) (intersection.getX() + x - this.getHitbox().getX()),
                                                         (int) (intersection.getY() + y - this.getHitbox().getY()))
                                                         != Color.transparent) {
                        return true;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public abstract void draw();
    public abstract void update(int delta);
}
