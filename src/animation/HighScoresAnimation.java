package animation;

import biuoop.DrawSurface;
import score.HighScoresTable;

import java.awt.Color;
import java.io.File;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {

    /**
     * The Animation does one frame in the game.
     *
     * @param d  the DrawSurface
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(55, 50, "High Scores", 32);
        d.drawText(105, 120, "Player Name", 32);
        d.drawText(455, 120, "Score", 32);
        HighScoresTable highScores = HighScoresTable.loadFromFile(new File("highscores"));
        for (int i = 0; i < highScores.getHighScores().size(); i++) {
            d.drawText(105, 170 + 40 * i, highScores.getHighScores().get(i).getName(), 30);
            d.drawText(455, 170 + 40 * i, Integer.toString(highScores.getHighScores().get(i).getScore()), 30);
        }
    }

    /**
     * Should the Animation Stop.
     *
     * @return true if it should false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}

