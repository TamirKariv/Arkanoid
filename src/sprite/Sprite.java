package sprite;

import biuoop.DrawSurface;


/**
 * The contents of the sprite.Sprite Interface.
 * The sprite.Sprite interface will be used by things that are considered sprites.
 * In computer graphics and games, a sprite.Sprite is a game object that can be drawn to the screen.
 *
 * @author Tamir Kariv
 */
public interface Sprite {


    /**
     * the function draws the sprite to the screen.
     *
     * @param d - the DrawSurface.
     */
    void drawOn(DrawSurface d);


    /**
     * the function notify the sprite that time has passed.
     * @param dt -specifies the amount of seconds passed since the last call
     */
    void timePassed(double dt);
}
