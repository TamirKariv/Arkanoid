package task;

import animation.Animation;
import animation.AnimationRunner;


/**
 * The type Show hi scores task.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner animationRunner;
    private Animation highScoresAnimation;


    /**
     * Instantiates a new Show hi scores task.
     *
     * @param runner              the runner
     * @param highScoresAnimation the high scores animation
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.animationRunner = runner;
        this.highScoresAnimation = highScoresAnimation;

    }

    /**
     * run the animations.
     *
     * @return null.
     */
    public Void run() {
        this.animationRunner.run(this.highScoresAnimation);
        return null;

    }
}