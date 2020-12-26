package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import sprite.Ball;
import biuoop.KeyboardSensor;
import hitlistener.BallRemover;
import biuoop.DrawSurface;
import collidables.Block;
import collidables.Collidable;
import collidables.Paddle;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import hitlistener.BlockRemover;
import hitlistener.Counter;
import hitlistener.HitListener;
import hitlistener.ScoreTrackingListener;
import game.levels.LevelInformation;
import sprite.LevelIndicator;
import sprite.LivesIndicator;
import sprite.ScoreIndicator;
import sprite.SpriteCollection;
import sprite.Sprite;

import java.awt.Color;

/**
 * The contents of the game.GameLevel class.
 * the  game.GameLevel class that will hold the sprites and the collidables, and will be in charge of the animation.
 *
 * @author Tamir Kariv
 */
public class GameLevel implements Animation {

    //constants
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    //members
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter lives;
    private biuoop.KeyboardSensor keyboard;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;


    /**
     * constructor  - initialize the game.GameEnvironment and the sprite.SpriteCollection.
     *
     * @param levelInformation the level information
     * @param keyboard         the keyboard
     * @param runner           the runner
     * @param score            the score
     * @param lives            the lives
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner runner, Counter score
            , Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.score = score;
        this.lives = lives;
        this.keyboard = keyboard;
        this.runner = runner;
        this.levelInformation = levelInformation;
    }


    /**
     * the function adds a given collidable to the environment.
     *
     * @param c - the collidables.Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);

    }

    /**
     * the function adds a given sprite to the collection.
     *
     * @param s - the sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);

    }


    /**
     * the function Initializes a new game: create the Blocks and sprite.Ball (and collidables.Paddle)
     * and adds them to the game.
     */
    public void initialize() {

        //adding the background of the level first
        this.addSprite(this.levelInformation.getBackground());

        //setting up the hitlisteners
        HitListener blockRemover = new BlockRemover(this, this.blockCounter);
        HitListener ballRemover = new BallRemover(this, this.ballCounter);
        HitListener scoreTracker = new ScoreTrackingListener(this.score);

        //adding the frame blocks and the sprites.ball remover block
        Block ballRemoverBlock = new Block(new Rectangle(new Point(20, FRAME_HEIGHT), FRAME_WIDTH, 20)
                , Color.gray, 0);
        ballRemoverBlock.addHitListener(ballRemover);
        Block upperBlock = new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, 35), Color.gray, 0);
        Block leftBlock = new Block(new Rectangle(new Point(0, 35), 20, FRAME_HEIGHT - 20), Color.gray
                , 0);
        Block rightBlock = new Block(new Rectangle(new Point(FRAME_WIDTH - 20, 35), 20
                , FRAME_HEIGHT - 20), Color.gray, 0);
        this.addCollidable(ballRemoverBlock);
        this.addSprite(ballRemoverBlock);
        this.addCollidable(upperBlock);
        this.addSprite(upperBlock);
        this.addCollidable(leftBlock);
        this.addSprite(leftBlock);
        this.addCollidable(rightBlock);
        this.addSprite(rightBlock);

        //adding the score,lives and level indicators
        Sprite levelIndicator = new LevelIndicator(this.levelInformation.levelName());
        Sprite scoreIndicator = new ScoreIndicator(this.score);
        Sprite livesIndicator = new LivesIndicator(this.lives);
        this.addSprite(scoreIndicator);
        this.addSprite(levelIndicator);
        this.addSprite(livesIndicator);

        //adding the blocks from the level information.
        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove(); i++) {
            Block block = this.levelInformation.blocks().get(i);
            this.addSprite(block);
            this.addCollidable(block);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            this.blockCounter.increase(1);
        }
    }


    /**
     * the function runs the game and starts the animation loop.
     */
    public void playOneTurn() {
        //balls are created
        createBalls();
        //paddle is created
        createPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * doOneFrame is in charge of the logic of the game.
     *
     * @param d  the DrawSurface
     * @param dt - delta time
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }

        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.lives.decrease(1);
            this.removeCollidable(paddle);
            this.removeSprite(paddle);
            this.running = false;
        }
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Create balls.
     */
    public void createBalls() {
        int xVal = 200;
        if (this.levelInformation.levelName().equals("Direct Hit")) {
            xVal = 400;
        }
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(xVal + 20 * i, 480), 4, Color.white, environment);
            this.addSprite(ball);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            this.ballCounter.increase(1);
        }
    }

    /**
     * Create paddle.
     */
    public void createPaddle() {
        Point paddleStartPoint = null;
        if (this.levelInformation.levelName().equals("Wide Easy")) {
            paddleStartPoint = new Point(240, 560);
        } else {
            paddleStartPoint = new Point(340, 560);
        }
        //setting up the paddle;
        Rectangle rectanglePaddle = new Rectangle(paddleStartPoint, this.levelInformation.paddleWidth(), 15);
        double paddleLeftLimit = 20;
        double paddleRightlimit = 780;
        this.paddle = new Paddle(rectanglePaddle, Color.orange, this.keyboard, paddleRightlimit, paddleLeftLimit
                , this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * Gets number of blocks.
     *
     * @return the number of blocks
     */
    public int getNumberOfBlocks() {
        return this.blockCounter.getValue();
    }


}