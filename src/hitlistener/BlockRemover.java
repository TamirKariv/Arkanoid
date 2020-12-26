package hitlistener;

import sprite.Ball;
import collidables.Block;
import game.GameLevel;

/**
 * The type Block remover.
 * a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;


    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel       the game level
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;


    }

    /**
     * Blocks that are hit and reach 0 hit-points are removed
     * from the gameLevel as well as their hit listener.
     *
     * @param beingHit - the block
     * @param hitter   - the sprites.ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(gameLevel);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}