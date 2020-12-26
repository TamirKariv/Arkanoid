package game.levels;

import sprite.backgrounds.LevelOneBackground;
import sprite.Velocity;
import collidables.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import sprite.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level one.
 */
public class LevelOne implements LevelInformation {


    private List<Block> blockList;
    private List<Velocity> velocityList;

    /**
     * Instantiates a new Level one.
     */
    public LevelOne() {
        this.blockList = new ArrayList<>();
        this.velocityList = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override

    public List<Velocity> initialBallVelocities() {
        velocityList.add(Velocity.fromAngleAndSpeed(360, 300));
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 1200;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Block theme = new Block(new Rectangle(new Point(20, 20), 800, 600), Color.blue, 3);
        return new LevelOneBackground();
    }

    @Override
    public List<Block> blocks() {
        Block block = new Block(new Rectangle(new Point(390, 190), 20, 20), java.awt.Color.RED.brighter(), 1);
        this.blockList.add(block);
        return blockList;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
