package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {


    private KeyboardSensor k;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;



    /**
     * Instantiates a new KeyPressStoppableAnimation.
     *
     * @param sensor the KeyboardSensor
     * @param key    the key to stop the animation
     * @param animation   the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.k = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * The Animation does one frame in the game.
     *
     * @param d  the DrawSurface
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.k.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!k.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Should the Animation Stop.
     *
     * @return true if it should false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
