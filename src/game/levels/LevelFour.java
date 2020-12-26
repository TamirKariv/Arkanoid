package game.levels;

import sprite.backgrounds.LevelFourBackground;
import sprite.Velocity;
import collidables.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level four.
 */
public class LevelFour implements LevelInformation {


    private List<Block> blockList;
    private List<Velocity> velocityList;

    /**
     * Instantiates a new Level four.
     */
    public LevelFour() {
        this.blockList = new ArrayList<>();
        this.velocityList = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(20, 420));
        }
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new LevelFourBackground();
    }

    @Override
    public List<Block> blocks() {
        Block block;
        Color color = null;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                if (i == 0) {
                    color = Color.gray;
                } else if (i == 1) {
                    color = Color.red;
                } else if (i == 2) {
                    color = Color.yellow;
                } else if (i == 3) {
                    color = Color.green;
                } else if (i == 4) {
                    color = Color.white;
                } else if (i == 5) {
                    color = Color.pink;
                } else {
                    color = Color.cyan;
                }
                block = new Block(new Rectangle(new Point(20 + (double) 760 / 15 * j, 100 + i * 20)
                        , (double) 760 / 15, 20), color, 1);
                this.blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
