package sprite;

import biuoop.DrawSurface;
import hitlistener.Counter;

import java.awt.Color;


/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {

    private Counter score;


    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(350, 20, "Score:" + " " + this.score.getValue(), 15);
    }

    @Override
    public void timePassed(double dt) {
        ;
    }
}
