package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {


    /**
     * The Animation does one frame in the game.
     *
     * @param d  the DrawSurface
     * @param dt specifies the amount of seconds passed since the last call.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Should the Animation Stop.
     *
     * @return true if it should false otherwise.
     */
    boolean shouldStop();
}