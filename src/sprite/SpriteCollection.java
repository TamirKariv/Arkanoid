package sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The contents of the sprite.SpriteCollection class.
 * the sprite.SpriteCollection contains the sprites objects of the game.
 *
 * @author Tamir Kariv
 */
public class SpriteCollection {
    //members
    private List<Sprite> sprites;


    /**
     * constructor  - initialize the sprites of the game.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();

    }

    /**
     * the function adds a given sprite to the collection.
     *
     * @param s - the sprite.
     */
    public void addSprite(sprite.Sprite s) {
        this.sprites.add(s);
    }


    /**
     * the function calls timePassed() on all sprites.
     *
     * @param dt - delta time
     */
    public void notifyAllTimePassed(double dt) {

        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed(dt);
        }
    }


    /**
     * the function calls drawOn(d) on all sprites.
     *
     * @param d -the DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);

        }
    }

    /**
     * the function removes a given sprite to the collection.
     *
     * @param s - the sprite.
     */
    public void removeSprite(sprite.Sprite s) {
        this.sprites.remove(s);

    }


}