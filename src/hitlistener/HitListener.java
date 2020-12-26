package hitlistener;

import sprite.Ball;
import collidables.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the Block
     * @param hitter   the Ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}