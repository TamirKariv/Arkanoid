package game.levels;

import sprite.Velocity;
import collidables.Block;
import sprite.Sprite;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * the function returns the number of balls in the game.
     *
     * @return the numebr of balls.
     */
    int numberOfBalls();

    /**
     * the function returns the sprites.ball velocities list.
     *
     * @return the list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * the function returns paddle speed.
     *
     * @return the speed.
     */
    int paddleSpeed();

    /**
     * the function returns the paddle width.
     *
     * @return the the paddle width
     */
    int paddleWidth();

    /**
     * the function returns the level's name.
     *
     * @return the name.
     */
    String levelName();

    /**
     * the function returns the level's background.
     *
     * @return the background
     */
    Sprite getBackground();


    /**
     * the function returns the level's the Blocks list of the level.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * the function returns Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks
     */
    int numberOfBlocksToRemove();
}