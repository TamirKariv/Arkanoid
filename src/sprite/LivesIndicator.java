package sprite;

import biuoop.DrawSurface;
import hitlistener.Counter;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {

    private Counter lives;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param lives the lives
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    @Override
    public void timePassed(double dt) {
        ;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(70, 20, "Lives:" + " " + this.lives.getValue(), 15);
    }
}

