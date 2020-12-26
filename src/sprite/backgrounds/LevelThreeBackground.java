package sprite.backgrounds;

import biuoop.DrawSurface;
import sprite.Sprite;
import java.awt.Color;

/**
 * The type Level three background.
 */
public class LevelThreeBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN.darker());
        d.fillRectangle(20, 20, 800, 800);
        d.setColor(Color.black);
        d.fillRectangle(30, 300, 150, 600);
        d.setColor(Color.white);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(35 + 30 * j, 570 - 40 * i, 15, 30);
            }
        }
        d.setColor(Color.gray.darker());
        d.fillRectangle(80, 230, 50, 70);
        d.setColor(Color.gray);
        d.fillRectangle(100, 120, 15, 110);
        d.setColor(Color.yellow);
        d.fillCircle(107, 115, 15);
        d.setColor(Color.red);
        d.fillCircle(107, 115, 10);
        d.setColor(Color.white);
        d.fillCircle(107, 115, 5);
    }

    @Override
    public void timePassed(double dt) {
    }
}

