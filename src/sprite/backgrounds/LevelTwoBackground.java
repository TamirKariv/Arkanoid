package sprite.backgrounds;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Level two background.
 */
public class LevelTwoBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.cyan);
        d.fillRectangle(20, 20, 800, 800);
        d.setColor(java.awt.Color.ORANGE);
        for (int i = 0; i < 50; i++) {
            d.drawLine(125, 125, i * 18, 300);
        }
        d.fillCircle(150, 125, 70);
        d.setColor(java.awt.Color.YELLOW);
        d.fillCircle(150, 125, 50);
    }

    @Override
    public void timePassed(double dt) {
    }
}

