package game;

import collidables.Collidable;
import collidables.CollisionInfo;
import geometryprimitives.Line;
import geometryprimitives.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The contents of the game.GameEnvironment class.
 * the GameEnvironment contains the collidable objects of the game.
 *
 * @author Tamir Kariv
 */
public class GameEnvironment {


    //members
    private List<Collidable> collidables;


    /**
     * constructor  - initialize the collidables of the game.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }


    /**
     * the function adds a given collidable to the environment.
     *
     * @param c - the collidables.Collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);

    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.


    /**
     * the function checks if the trjactory of the sprites.ball collides with any of the collidable objects
     * if it does it returns the the collidables.CollisionInfo. otherwise it returns null.
     *
     * @param trajectory - the trajectory of the sprites.ball.
     * @return the collidables.CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //going through all the coldiables
        for (int i = 0; i < this.collidables.size(); i++) {

            //checking if there is an collision point.
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(
                    this.collidables.get(i).getCollisionRectangle());
            //if there is return the collidables.CollisionInfo.
            if (collisionPoint != null) {

                return new CollisionInfo(collisionPoint, this.collidables.get(i));
            }
        }
        //if there isn't return null.
        return null;
    }

    /**
     * Remove collidable.
     *
     * @param c the colliadble
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

}


