package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.SpriteCollection;

import java.awt.Color;


/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection sprites;
    private Sleeper sleeper;
    private int showCount;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    where to count from
     * @param gameScreen   the sprites in the game
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.sprites = gameScreen;
        this.showCount = countFrom;
        this.sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        System.currentTimeMillis();
        //draw all the sprites first
        this.sprites.drawAllOn(d);
        d.setColor(Color.white);
        d.drawText(270, 69, "The Game Will Start In: " + showCount, 30);
        d.setColor(Color.black);
        d.drawText(269, 70, "The Game Will Start In: " + showCount, 30);
        long waitFor = (long) ((numOfSeconds / countFrom) * 1000);
        //sleeper is applied to the last 2 seconds (the numofseconds)
        if (this.showCount != this.countFrom) {
            sleeper.sleepFor(waitFor);
        }
        this.showCount--;
    }

    @Override
    public boolean shouldStop() {
        //stop counting when counting reaches 0.
        if (showCount < 0) {
            return true;
        }
        return false;
    }
}