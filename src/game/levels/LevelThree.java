package game.levels;

import sprite.backgrounds.LevelThreeBackground;
import sprite.Velocity;
import collidables.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level three.
 */
public class LevelThree implements LevelInformation {


    private List<Block> blockList;
    private List<Velocity> velocityList;

    /**
     * Instantiates a new Level three.
     */
    public LevelThree() {
        this.blockList = new ArrayList<>();
        this.velocityList = new ArrayList<>();
    }

    @Override
    public int numberOfBalls() {
        return 2;
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new LevelThreeBackground();
    }

    @Override
    public List<Block> blocks() {
        Block block;
        Color color = null;
        int numOfRows = 6;
        int numOfBlocks = 10;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfBlocks; j++) {
                //the i value represents the rown number each row gets a diffrent color and hitpoints values.
                if (i == 0) {
                    color = Color.gray;
                } else if (i == 1) {
                    color = Color.red;
                } else if (i == 2) {
                    color = Color.yellow;
                } else if (i == 3) {
                    color = Color.blue;
                } else {
                    color = Color.white;
                }
                block = new Block(new Rectangle(new Point(720 - 60 * j, 150 + i * 20), 60, 20)
                        , color, 1);
                this.blockList.add(block);
            }
            numOfBlocks--;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
