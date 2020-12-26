package sprite;

import geometryprimitives.Point;

/**
 * The contents of the sprite.Velocity Class.
 * sprite.Velocity specifies the change in position on the `x`(dx) and the `y`(dy) axes.
 *
 * @author Tamir Kariv
 */


public class Velocity {

    //members
    private double dx;
    private double dy;

    /**
     * constructor  - initialize the dx and dy values of the velocity.
     *
     * @param dx - the dx value.
     * @param dy - the dy value.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;

    }

    /**
     * the function returns the dy value the velocity.
     *
     * @return double - dy value of the velocity.
     */
    public double getdy() {
        return this.dy;
    }

    /**
     * the function returns the dx value the velocity.
     *
     * @return double - dx value of the velocity.
     */
    public double getdx() {
        return this.dx;
    }

    /**
     * the function takes a point with position (x,y) and return a new point
     * // with position (x+dx, y+dy).
     *
     * @param p - the point before the change.
     * @return geometryprimitives.Point - the new geometryprimitives.Point after the change.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * the function sets the velocity of a sprites.ball using it's speed and angle.
     *
     * @param angle - sprite.Ball's angle.
     * @param speed - sprite.Ball's speed.
     * @return sprite.Velocity - the sprite.Velocity of the sprites.ball.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -1 * (Math.cos(Math.toRadians(angle)) * speed);
        return new Velocity(dx, dy);
    }

    /**
     * the function calculates the sprites.ball's speed using it's dy and dx values.
     *
     * @param velX - sprite.Ball's dx value.
     * @param velY - sprite.Ball's dy value.
     * @return speed - the speed of the sprites.ball.
     */
    public double getSpeed(double velX, double velY) {

        return Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));

    }


}