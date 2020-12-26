package collidables;

import geometryprimitives.Point;

/**
 * The contents of the collidables.CollisionInfo.
 * the collidables.CollisionInfo contains information about the collision of the sprites.ball with a collidable object.
 *
 * @author Tamir Kariv
 */
public class CollisionInfo {


    //memebers
    private Point collisionPoint;
    private Collidable collisionObject;


    /**
     * constructor  - initialize the information about the collision.
     *
     * @param collisionPoint  - the collision geometryprimitives.Point.
     * @param collisionObject - the collidable.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }


    /**
     * the function returns the point at which the collision occurs.
     *
     * @return geometryprimitives.Point - collision geometryprimitives.Point.
     */
    public Point collisionPoint() {

        return this.collisionPoint;
    }

    // the collidable object involved in the collision.

    /**
     * the function returns the collidable object involved in the collision.
     *
     * @return collidables.Collidable - the collision Object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}