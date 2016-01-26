package Blue.Melody;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.awt.*;

/**
 * Created by Melody on 1/17/2016.
 */
public class Hero extends Entity implements Drawable {
    public static final double MOVESPEED = 0.1;
    public static final double GRAVITYSPEED = 0.09;

    private SpriteSheet spriteSheet;
    private int posX, posY;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean onGround = false;

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean getMovingLeft() {
        return movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean getMovingRight() {
        return movingRight;
    }

    @Override
    public int getWidth() {
        return this.getCurrentSprite().getWidth();
    }

    @Override
    public int getHeight() {
        return this.getCurrentSprite().getHeight();
    }

    /**
     * Returns the current sprite we are displaying for this character.
     * <p>
     *     Returns the current sprite we are displaying for this character by selecting it from the animations.
     * </p>
     */
    @Override
    public Image getCurrentSprite() {
        if (getMovingLeft()) {
            return runningAnimation.getCurrentFrame().getFlippedCopy(true, false);
        } else if (getMovingRight()) {
            return runningAnimation.getCurrentFrame();
        } else {
            return idleAnimation.getCurrentFrame();
        }
    }

    private Animation runningAnimation;
    private Animation idleAnimation;
    private Animation jumpingAnimation;


    public Hero() throws SlickException {
        this(0, 0);
    }

    public Hero(int posX, int posY) throws SlickException {
        this.posX = posX;
        this.posY = posY;

        spriteSheet = new SpriteSheet(new Image("Resources/Sprites/hero.png"), 16, 16, 0, 16);
        spriteSheet.setFilter(Image.FILTER_NEAREST);
        runningAnimation = new Animation(spriteSheet, 0, 1, 5, 1, true, 100, true);
        idleAnimation = new Animation(spriteSheet, 0, 0, 3, 0, true, 100, true);
        spriteSheet.setFilter(Image.FILTER_NEAREST);
    }

    public void gravity(double deltaTime) {
        if (!onGround) {
            this.setPosY((int) (this.getPosY() + GRAVITYSPEED * deltaTime));
        }
    }

    public void moveRight(double deltaTime) {
        this.setPosX((int) (this.getPosX() + MOVESPEED * deltaTime));
    }

    public void moveLeft(double deltaTime) {
        this.setPosX((int) (this.getPosX() - MOVESPEED * deltaTime));
    }

    public void draw() {
        this.getCurrentSprite().draw(this.getPosX(), this.getPosY());
    }

    public void update(int delta) {
        this.gravity(delta);
        if (getMovingLeft() || getMovingRight()) {
            runningAnimation.update(delta);
        } else {
            idleAnimation.update(delta);
        }
    }

    @Override
    public void collidedWith(Collidable other) {
        if (this.getHitbox().outcode(other.getHitbox().getCenterX(), other.getHitbox().getCenterY()) == Rectangle.OUT_BOTTOM) {
            onGround = true;
        }
    }
}
