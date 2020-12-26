package sprite.backgrounds;

import biuoop.DrawSurface;
import sprite.Sprite;
import java.awt.Color;

/**
 * The type Level four background.
 */
public class LevelFourBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        d.fillRectangle(20, 20, 800, 800);
        d.setColor(java.awt.Color.WHITE);
        for (int i = 1; i < 14; i++) {
            d.drawLine(90 + 10 * i, 350, 140 + 10 * i, 600);
        }
        d.setColor(Color.darkGray);
        d.fillCircle(115, 350, 30);
        d.setColor(Color.gray);
        d.fillCircle(140, 330, 30);
        d.setColor(Color.lightGray);
        d.fillCircle(160, 350, 30);
        d.setColor(Color.gray);
        d.fillCircle(180, 330, 30);
        d.setColor(Color.darkGray);
        d.fillCircle(200, 350, 30);
        d.setColor(Color.white);
        d.setColor(java.awt.Color.WHITE);
        for (int i = 1; i < 14; i++) {
            d.drawLine(520 + 10 * i, 350, 470 + 10 * i, 600);
        }
        d.setColor(Color.darkGray);
        d.fillCircle(550, 350, 30);
        d.setColor(Color.gray);
        d.fillCircle(570, 330, 30);
        d.setColor(Color.lightGray);
        d.fillCircle(590, 350, 30);
        d.setColor(Color.gray);
        d.fillCircle(610, 330, 30);
        d.setColor(Color.darkGray);
        d.fillCircle(630, 350, 30);
        d.setColor(Color.white);
    }

    @Override
    public void timePassed(double dt) {
    }
}

