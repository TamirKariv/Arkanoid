package collidables;

import sprite.Ball;
import sprite.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import sprite.Sprite;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;


/**
 * /
 * The contents of the paddle Class.
 * the paddle is a movable rectangle  via the keyboard.
 *
 * @author Tamir Kariv
 */
//members
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    private double rightLimit;
    private double leftLimit;
    private double speed;


    /**
     * constructor  - initialize the collidables.Paddle.
     *
     * @param rectangle  - the shape of the paddle.
     * @param color      - color of the paddle.
     * @param keyboard   - keyboard of the gui the user control the paddle with it.
     * @param rightLimit the right limit to where the paddle can move.
     * @param leftLimit  - the left limit to where the paddle can move.
     * @param speed      the speed
     */
    public Paddle(Rectangle rectangle, Color color, biuoop.KeyboardSensor keyboard, double rightLimit
            , double leftLimit, double speed) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.rightLimit = rightLimit;
        this.leftLimit = leftLimit;
        this.speed = speed;
    }

    /**
     * the function moves the paddle to the left.
     *
     * @param dt - delta time
     */
    public void moveLeft(double dt) {
        //check the left limit of the paddle
        if (this.rectangle.getUpperLeft().getX() - 6 <= this.leftLimit) {

            return;
        }
        //move the paddle
        Point newUpperLeft = new Point(this.rectangle.getUpperLeft().getX() - this.speed * dt
                , this.rectangle.getUpperLeft().getY());
        this.rectangle = new Rectangle(newUpperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());

    }

    /**
     * the function moves the paddle to the right.
     *
     * @param dt - delta time
     */
    public void moveRight(double dt) {
        //check the left limit of the paddle
        if (this.rectangle.getUpperRight().getX() + 6 >= this.rightLimit) {

            return;
        }
        //move the paddle
        Point newUpperLeft = new Point(this.rectangle.getUpperLeft().getX() + this.speed * dt
                , this.rectangle.getUpperLeft().getY());
        this.rectangle = new Rectangle(newUpperLeft, this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * the function checks if the user pressed the left or right key moving the paddle accordingly.
     *
     * @param dt -specifies the amount of seconds passed since the last call
     */
    public void timePassed(double dt) {
        //left
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        //right
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }

    }

    /**
     * the function draws the paddle on the DrawSurface.
     *
     * @param d - DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
                , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);

        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
                , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }


    /**
     * the function returns the "collision shape" of the paddle.
     *
     * @return "collision shape" of the paddle
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
     * @param hitter          - the sprites.ball
     * @return the new velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {


        //splitting the paddle into five parts (6 points) each part bounces the sprites.ball differently.
        double pointOne = this.rectangle.getUpperLeft().getX();
        double pointTwo = this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth() / 5);
        double pointThree = this.rectangle.getUpperLeft().getX() + (2 * this.rectangle.getWidth() / 5);
        double pointFour = this.rectangle.getUpperLeft().getX() + (3 * this.rectangle.getWidth() / 5);
        double pointFive = this.rectangle.getUpperLeft().getX() + (4 * this.rectangle.getWidth() / 5);
        double pointSix = this.rectangle.getUpperRight().getX();
        //calculating the speed of the sprites.ball
        double speedOfBall = currentVelocity.getSpeed(currentVelocity.getdx(), currentVelocity.getdy());

        //checking on which part the  point of the ball hit, each part bounces the sprites.ball with a different angle.
        if (collisionPoint.getX() >= pointOne
                && (collisionPoint.getX() <= pointTwo)) {
            return Velocity.fromAngleAndSpeed(300, speedOfBall);

        }
        if (collisionPoint.getX() > pointTwo
                && (collisionPoint.getX()) <= pointThree) {
            return Velocity.fromAngleAndSpeed(330, speedOfBall);

        }
        if (collisionPoint.getX() > pointThree
                && (collisionPoint.getX()) <= pointFour) {
            return new Velocity(currentVelocity.getdx(), -1 * currentVelocity.getdy());

        }
        if (collisionPoint.getX() > pointFour
                && (collisionPoint.getX() <= pointFive)) {
            return Velocity.fromAngleAndSpeed(30, speedOfBall);

        }
        if (collisionPoint.getX() > pointFive
                && collisionPoint.getX() <= pointSix) {
            return Velocity.fromAngleAndSpeed(60, speedOfBall);

        }
        //Y values are checked like the the block hit function.
        if (collisionPoint.getY() > this.rectangle.getUpperLeft().getY()
                && collisionPoint.getY() < this.rectangle.getBottomLeft().getY()) {


            return new Velocity(-1 * currentVelocity.getdx(), currentVelocity.getdy());
        }
        return currentVelocity;
    }

    /**
     * the function adds the paddle to the game.
     *
     * @param g - The game.GameLevel.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);

    }


}