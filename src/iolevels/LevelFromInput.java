package iolevels;

import collidables.Block;
import game.levels.LevelInformation;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level from input.
 */
public class LevelFromInput implements LevelInformation {

    private BlocksFromSymbolsFactory blocksFactory;
    private int numofBalls;
    private List<Velocity> velocityList;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private int numberOfBlocksToRemove;
    private int blockStartX;
    private int blockStartY;
    private boolean isImage;
    private String imagePath;
    private Sprite background;
    private List<String> layout;
    private ImageParserAndStore imageParserAndStore = new ImageParserAndStore();


    /**
     * Instantiates a new Level from input.
     *
     * @param blocksFactory the blocks factory
     */
    public LevelFromInput(BlocksFromSymbolsFactory blocksFactory) {
        this.blocksFactory = blocksFactory;
    }

    /**
     * the function returns the number of balls in the game.
     *
     * @return the numebr of balls.
     */
    @Override
    public int numberOfBalls() {
        return this.numofBalls;
    }

    /**
     * the function returns the ball velocities list.
     *
     * @return the list of velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }

    /**
     * the function returns paddle speed.
     *
     * @return the speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * the function returns the paddle width.
     *
     * @return the the paddle width
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * the function returns the level's name.
     *
     * @return the name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * the function returns the level's background.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        //if the background is an image
        if (this.isImage) {
            Block backgroundImage = new Block(new Rectangle(new Point(0, 0), 800, 600), null, 0);
            //parse and get image path
            this.imagePath = this.imageParserAndStore.imagePathFromString(this.imagePath);
            Image image = imageParserAndStore.getImage("resources/background_images/"
                    + imagePath);
            backgroundImage.setIsImage(true);
            backgroundImage.setImage(image);
            return backgroundImage;
        }
        //if it isn't an image return it
        return this.background;
    }

    /**
     * the function returns the level's the Blocks list of the level.
     * the layout is given from the levelspecification reader and the blocks from the block factory.
     *
     * @return the list of blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int spaces = 0;
        for (int i = 0; i < this.layout.size(); i++) {
            int j = 0;
            for (char c : this.layout.get(i).toCharArray()) {
                String str1 = Character.toString(c);
                if (blocksFactory.isSpaceSymbol((str1))) {
                    spaces += blocksFactory.getSpaceWidth(str1);
                }
                if (blocksFactory.isBlockSymbol(str1)) {
                    Block block = blocksFactory.getBlock(str1, this.blockStartX + j * 50 + spaces,
                            this.blockStartY + i * 25);
                    blocks.add(block);
                }
                j++;
            }
            spaces = 0;
        }
        return blocks;
    }

    /**
     * the function returns Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }


    /**
     * Sets numof balls.
     *
     * @param ballsNum the numof balls
     */
    public void setNumofBalls(int ballsNum) {
        this.numofBalls = ballsNum;
    }

    /**
     * Sets velocity list.
     *
     * @param velList the velocity list
     */
    public void setVelocityList(List<Velocity> velList) {
        this.velocityList = velList;
    }

    /**
     * Sets paddle speed.
     *
     * @param speed the paddle speed
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * Sets paddle width.
     *
     * @param width the paddle width
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * Sets level name.
     *
     * @param lvlName the level name
     */
    public void setLevelName(String lvlName) {
        this.levelName = lvlName;
    }

    /**
     * Sets number of blocks to remove.
     *
     * @param numofblocks the number of blocks to remove
     */
    public void setNumberOfBlocksToRemove(int numofblocks) {
        this.numberOfBlocksToRemove = numofblocks;
    }

    /**
     * Sets block start x.
     *
     * @param xBlockStart the block start x
     */
    public void setBlockStartX(int xBlockStart) {
        this.blockStartX = xBlockStart;
    }

    /**
     * Sets block start y.
     *
     * @param yBlockStart the block start y
     */
    public void setBlockStartY(int yBlockStart) {
        this.blockStartY = yBlockStart;
    }

    /**
     * Sets get layout.
     *
     * @param getLayout the get layout
     */
    public void setGetLayout(List<String> getLayout) {
        this.layout = getLayout;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(boolean image) {
        isImage = image;
    }

    /**
     * Sets background.
     *
     * @param bg the background
     */
    public void setBackground(Sprite bg) {
        this.background = bg;
    }

    /**
     * Sets image path.
     *
     * @param path the image path
     */
    public void setImagePath(String path) {
        this.imagePath = path;
    }
}
