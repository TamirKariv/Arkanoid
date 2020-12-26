package animation;

import biuoop.DrawSurface;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private boolean wonTheGame;
    private int score;

    /**
     * Instantiates a new End screen.
     *

     * @param wonTheGame boolean - true if the player won the game, false otherwise.
     * @param score      the score of the player
     */
    public EndScreen(boolean wonTheGame, int score) {
        this.wonTheGame = wonTheGame;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //if the player won
        if (wonTheGame) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is:" + " " + this.score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is:" + " " + this.score, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;

    }
}