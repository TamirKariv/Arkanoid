package hitlistener;

import sprite.Ball;
import collidables.Block;
import game.GameLevel;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel      the game level
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Balls that hit the death-region block are removed from the game.
     *
     * @param beingHit - the block
     * @param hitter   - the sprites.ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        this.gameLevel.removeSprite(hitter);

    }
}

