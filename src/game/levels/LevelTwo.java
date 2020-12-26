package game.levels;

import sprite.backgrounds.LevelTwoBackground;
import sprite.Velocity;
import collidables.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level two.
 */
public class LevelTwo implements LevelInformation {


    private List<Block> blockList;
    private List<Velocity> velocityList;

    /**
     * Instantiates a new Level two.
     */
    public LevelTwo() {
        this.blockList = new ArrayList<>();
        this.velocityList = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls(); i++) {
            this.velocityList.add(Velocity.fromAngleAndSpeed(30 + 5 * i, 300));
        }
        return this.velocityList;

    }

    @Override
    public int paddleSpeed() {
        return 1200;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new LevelTwoBackground();
    }

    @Override
    public List<Block> blocks() {
        Block block;
        Color color = null;
        for (int i = 0; i < 16; i++) {
            if (i <= 2) {
                color = Color.red;
            } else if (i <= 4) {
                color = Color.orange;
            } else if (i <= 6) {
                color = Color.yellow;
            } else if (i <= 8) {
                color = Color.green;
            } else if (i <= 10) {
                color = Color.blue;
            } else if (i <= 12) {
                color = Color.pink;
            } else {
                color = Color.cyan;
            }
            block = new Block(new Rectangle(new Point(20 + (double) 760 / 15 * i, 300), (double) 760 / 15
                    , 20), color, 1);
            this.blockList.add(block);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
