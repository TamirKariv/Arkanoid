package sprite.backgrounds;

import biuoop.DrawSurface;
import sprite.Sprite;
import java.awt.Color;

/**
 * The type Level one background.
 */
public class LevelOneBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(20, 20, 800, 800);
        d.setColor(java.awt.Color.BLUE);
        d.drawCircle(400, 200, 100);
        d.drawCircle(400, 200, 80);
        d.drawCircle(400, 200, 60);
        d.drawCircle(400, 200, 40);
        d.drawLine(290, 200, 510, 200);
        d.drawLine(400, 90, 400, 310);
    }

    @Override
    public void timePassed(double dt) {
    }
}

