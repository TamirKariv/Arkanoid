package collidables;

import sprite.Ball;
import sprite.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * The contents of the collidables.Collidable Interface.
 * The collidables.Collidable interface will be used by things that can be collided with.
 *
 * @author Tamir Kariv
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * Return the "collision shape" of the object.
     *
     * @return "collision shape" of the collidable.
     */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          - the sprites.ball that hits
     * @param collisionPoint  - the collision point
     * @param currentVelocity - the current velocity
     * @return the velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}