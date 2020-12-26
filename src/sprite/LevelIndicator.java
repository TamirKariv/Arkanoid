package sprite;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level indicator.
 */
public class LevelIndicator implements Sprite {
    private String levelName;


    /**
     * Instantiates a new Level indicator.
     *
     * @param levelName the level
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        String title = "Level Name: " + this.levelName;
        d.setColor(Color.black);
        d.drawText(530, 20, title, 15);
    }


    @Override
    public void timePassed(double dt) {
    }
}
