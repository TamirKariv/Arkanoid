package sprite;

import biuoop.DrawSurface;
import collidables.Collidable;
import game.GameEnvironment;
import geometryprimitives.Line;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * The contents of the sprite.Ball Class.
 * The sprite.Ball is defined by it's center(location), radius(size) and color.
 *
 * @author Tamir Kariv
 */
public class Ball implements Sprite {

    //members
    private Point center;
    private int r;
    private java.awt.Color color;
    //default velocity if none is givens
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment gameEnvironment;


    /**
     * constructor  - initialize the center of the sprites.ball, it's radius and it's color.
     *
     * @param center          - the color of the sprites.ball.
     * @param r               - the radius of the sprites.ball.
     * @param color           - thc color of the sprites.ball.
     * @param gameEnvironment the game environment of the sprites.ball.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {

        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }


    /**
     * the function returns the x value the center of the sprites.ball.
     *
     * @return int - x value of the center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * the function returns the y value the center of the sprites.ball.
     *
     * @return int - y value of the center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * the function returns the size of the radius of the sprites.ball.
     *
     * @return int - size of the radius.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * the function returns the color of the sprites.ball.
     *
     * @return - the color of the sprites.ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * the function draws the sprites.ball on a given DrawSurface.
     *
     * @param surface - the drawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), getY(), getSize());
    }

    /**
     * the function sets the velocity of the sprites.ball.
     *
     * @param v - the velocity of the sprites.ball.
     */
    public void setVelocity(Velocity v) {

        this.velocity = v;
    }

    /**
     * the function returns the game environment of the sprites.ball.
     *
     * @return gameEnvironment - the game environment of the sprites.ball.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * the function sets the velocity of the sprites.ball.
     *
     * @param dx - the dx value of the velocity.
     * @param dy = the dy value of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        Velocity setVel = new Velocity(dx, dy);
        this.velocity = setVel;
    }

    /**
     * the function sets the frame limits of the sprites.ball.
     *
     * @param frameRightLimit - the right limit of the frame.
     * @param frameDownLimit  = the bottom limit of the frame.
     * @param frameLeftLimit  - the left limit of the width.
     * @param frameUpperLimit - the bottom limit of the height.
     */


    /**
     * the function returns the velocity of the sprites.ball.
     *
     * @return sprite.Velocity - the velocity of the sprites.ball.
     */
    public Velocity getVelocity() {

        return this.velocity;
    }

    /**
     * the function moves the sprites.ball inside the draw surface by alerting it's position.
     * if a sprites.ball collision with a collidable is detected the function changes the velocity of the sprites.ball
     * and changes it's position accordingly.
     *
     * @param dt - delta time
     */
    public void moveOneStep(double dt) {

        Velocity dtVel = new Velocity(this.velocity.getdx() * dt, this.velocity.getdy() * dt);
        /*setting the trajactory of the sprites.ball, the start point is the balls current position
        and the end point is the position after the velocity is applied to the sprites.ball*/
        Line trajectory = new Line(this.center, dtVel.applyToPoint(this.center));
        //checking if there is collision between the balls trajectory and the collidables.
        if (this.gameEnvironment.getClosestCollision(trajectory) == null) {
            //if there isn't the sprites.ball continues in it's current velocity.
            this.center = dtVel.applyToPoint(this.center);
            //if there is a collision
        } else {
            Double epsilonX;
            Double epsilonY;
            //checking the collisionPoint of the sprites.ball and the collidables.
            Point collisionPoint = this.gameEnvironment.getClosestCollision(trajectory).collisionPoint();
            //using an epsilon variable to move the sprites.ball close to the coalition point.
            //checking if the epsilon is positive or negative depending on the balls location.
            if (this.getX() >= collisionPoint.getX()) {
                epsilonX = 0.005;
            } else {
                epsilonX = -0.005;
            }
            if (this.getY() >= collisionPoint.getY()) {
                epsilonY = 0.005;
            } else {
                epsilonY = -0.005;
            }
            //creating a new center close to the collation point.
            Point newCenter = new Point(collisionPoint.getX() + epsilonX, collisionPoint.getY() + epsilonY);
            //getting the collidable shape to check if the sprites.ball got stuck in it.
            Collidable collidable = this.gameEnvironment.getClosestCollision(trajectory).collisionObject();
            //checking if the sprites.ball got stuck in the block or the paddle.
            if (this.checkIfBallStuck(newCenter
                    , collidable.getCollisionRectangle())) {


                //if it is return the sprites.ball to it's start point.
                Point ballsStartPoint = new Point(400, 400);
                this.center = ballsStartPoint;
                //otherwise set the position of the sprites.ball to the new center.
            } else {
                this.center = newCenter;
            }
            //set the velocity of the sprites.ball
            Velocity newVelocity = this.gameEnvironment.getClosestCollision(trajectory).collisionObject()
                    .hit(this, collisionPoint, this.velocity);
            this.setVelocity(newVelocity);
        }
    }


    /**
     * the function checks if the sprites.ball is stuck inside a block or a paddle.
     *
     * @param ballCenter - balls location after setting the volcity
     * @param collidable - the paddle
     * @return - true if sprites.ball is stuck, false otherwise
     */
    public boolean checkIfBallStuck(Point ballCenter, Rectangle collidable) {
        return (ballCenter.getX() >= collidable.getUpperLeft().getX()
                && ballCenter.getX() <= collidable.getUpperRight().getX()
                && ballCenter.getY() >= collidable.getUpperLeft().getY()
                && ballCenter.getY() <= collidable.getBottomLeft().getY());
    }


    @Override
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }


}