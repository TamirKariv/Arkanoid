package collidables;

import iolevels.ColorsParser;
import iolevels.ImageParserAndStore;
import sprite.Velocity;
import sprite.Ball;
import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import hitlistener.HitListener;
import hitlistener.HitNotifier;

import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The contents of the collidables.Block Class.
 * the block is a colidable object used in the game.
 *
 * @author Tamir Kariv
 */
public class Block implements Collidable, Sprite, HitNotifier {


    //members
    private Rectangle rectangle;
    private java.awt.Color fillcolor;
    private java.awt.Color stroke;
    private int hitCounter;
    private List<HitListener> hitListeners;
    private Map<Integer, String> fillK;
    private boolean isImage = false;
    private Image image;
    //used to store the images and get their path.
    private ImageParserAndStore imageParserAndStore;


    /**
     * constructor  - initialize the shape of the block and it's fillcolor.
     *
     * @param rectangle  - the shape of the block.
     * @param fillcolor  - thc fillcolor of the block.
     * @param hitCounter - hit counter on the block.
     */
    public Block(Rectangle rectangle, Color fillcolor, int hitCounter) {

        this.rectangle = rectangle;
        this.fillcolor = fillcolor;
        this.hitCounter = hitCounter;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param rectangle  the rectangle
     * @param fillcolor  the fillcolor
     * @param stroke     the stroke
     * @param hitCounter the hit counter
     * @param fillK      the fill k
     */
    public Block(Rectangle rectangle, Color fillcolor, Color stroke, int hitCounter, Map<Integer, String> fillK) {
        this.rectangle = rectangle;
        this.fillcolor = fillcolor;
        this.stroke = stroke;
        this.hitCounter = hitCounter;
        this.fillK = fillK;
        this.hitListeners = new ArrayList<HitListener>();

    }


    /**
     * the function returns the "collision shape" of the block.
     *
     * @return "collision shape" of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * the function checks the location of the collision point of the sprites.ball with the collidable.
     * afterwards it returns the new velocity of the sprites.ball based on where it hit.
     *
     * @param collisionPoint  - the collision point of the sprites.ball with the collidable.
     * @param currentVelocity - the velocity of the sprites.ball before it hitCounter the collidable.
     * @param hitter          - the sprites.ball that hits
     * @return - new veolicity after hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //hit is counted.
        this.hitCounter--;
        this.notifyHit(hitter);
        //return negative dx and dy if the collision is on one of the blocks points
        if (collisionPoint.equals(this.rectangle.getUpperLeft())
                || collisionPoint.equals(this.rectangle.getBottomLeft())
                || collisionPoint.equals(this.rectangle.getUpperRight())
                || collisionPoint.equals(this.rectangle.getBottomRight())) {
            return new Velocity(-1 * currentVelocity.getdx(), -1 * currentVelocity.getdy());
        }
        //return negative dy if the collisionPoint point is on the width of the block
        if (collisionPoint.getX() > this.rectangle.getBottomLeft().getX()
                && collisionPoint.getX() < this.rectangle.getBottomRight().getX()) {
            return new Velocity(currentVelocity.getdx(), -1 * currentVelocity.getdy());

        }
        //return negative dx if the collisionPoint point is on the height of the block
        if (collisionPoint.getY() > this.rectangle.getUpperLeft().getY()
                && collisionPoint.getY() < this.rectangle.getBottomLeft().getY()
                ) {
            return new Velocity(-1 * currentVelocity.getdx(), currentVelocity.getdy());
        }

        //setting a default velocity if none of the conditions are met(they should).
        return currentVelocity;
    }

    /**
     * the function draws the block on a given DrawSurface.
     *
     * @param surface - the drawSurface.
     */
    public void drawOn(DrawSurface surface) {
        //if a fillk exist fill the block according to it's hitpoints
        if (fillK != null) {
            String fillk = this.fillK.get(this.hitCounter);
            if (fillk != null) {
                if (fillk.contains("color")) {
                    this.fillcolor = ColorsParser.colorFromString(fillk);
                    this.isImage = false;
                }
                if (fillk.contains("image")) {
                    this.fillcolor = null;
                    this.image = this.imageParserAndStore.getImage(this.imageParserAndStore.imagePathFromString(fillk));
                    this.isImage = true;
                }
            }
        }
        //default color if no values are given
        if (this.fillcolor == null && !this.isImage) {
            this.fillcolor = Color.black;
        }
        //if the block contains an image draw it
        if (isImage) {
            surface.drawImage((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
                    , this.image);
            //otherwise use the color and stroke to fill it.
        } else {
            surface.setColor(this.fillcolor);
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
                    , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
            if (stroke == null) {
                surface.setColor(Color.black);
            } else {
                surface.setColor(this.stroke);
            }
            surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
                    , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        }
    }

    /**
     * Set the image of the block.
     *
     * @param img the image.
     */
    public void setImage(Image img) {
        this.image = img;
    }


    /**
     * the function notifies the collidables.Block doing nothing in this instance.
     *
     * @param dt - delta time
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * the function removes the block from the game level.
     *
     * @param gameLevel the Game Level
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * the function Notifies all listeners about a hit event.
     *
     * @param hitter - the hitting sprites.ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * the function returns the hit points of the block.
     *
     * @return the int
     */
    public int getHitPoints() {
        return this.hitCounter;
    }


    /**
     * set the boleean paramtere if the block contains an image.
     *
     * @param isImg true if there's an image false otherwise.
     */
    public void setIsImage(boolean isImg) {
        this.isImage = isImg;
    }

    /**
     * Sets save and load image.
     *
     * @param imgParserAndStore the save and load image
     */
    public void setImageParserAndStore(ImageParserAndStore imgParserAndStore) {
        this.imageParserAndStore = imgParserAndStore;
    }
}





