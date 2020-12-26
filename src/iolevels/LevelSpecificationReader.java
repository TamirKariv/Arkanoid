package iolevels;

import collidables.Block;
import game.levels.LevelInformation;
import sprite.Sprite;
import sprite.Velocity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import java.util.List;

import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {
    private String levelName;
    private List<Velocity> velocityList;
    private int paddleSpeed;
    private int paddleWidth;
    private int numOfBlocks;
    private Sprite background;
    private boolean isImage;
    private String imagePath;
    private int blockStartX;
    private int blockStartY;
    private String blockDefinitionsPath;
    private List<String> layout;
    private List<LevelInformation> levels = new ArrayList<>();


    /**
     * The function Reads from the level specification file and assigns the values of the level accordingly.
     * it then creates a level using the levelFrominput class which creates levels based on given values.
     * the level is then added to a list of levels.
     *
     * @param reader the reader
     * @return the list of levels
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader br = new BufferedReader(reader);
        String line;
        TreeMap<String, String> levelInfoMap = new TreeMap<>();
        try {
       //condition was: (while ((line = br.readLine()) != null)) but check style gave an error for Inner assignments
            while ((true)) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                try {
                    //add the level information untill the blocks layout
                    while (true) {
                        line = br.readLine();
                        if (line.equals("START_BLOCKS")) {
                            break;
                        }
                        if ((!"".equals(line)) && (!line.startsWith("#") && line.contains(":"))) {
                            String[] parts = line.split(":");
                            String leftPart = parts[0];
                            String rightPart = parts[1];
                            levelInfoMap.put(leftPart, rightPart);
                        }
                    }
                    //add blocks layout
                    this.layout = new ArrayList<>();
                    while (true) {
                        line = br.readLine();
                        if (line.equals("END_LEVEL")) {
                            break;
                        }
                        if ((!"".equals(line)) && (!line.startsWith("#")) && !onlySpaces(line)) {
                            this.layout.add(line);
                        }
                    }
                    //check if all fields of level were given
                    if (fieldsEmpty(levelInfoMap)) {
                        System.out.println("Error: one or more fields for the level are empty");
                        System.exit(0);
                    }
                    //assign the values given from the file
                    this.levelName = levelInfoMap.get("level_name");
                    this.paddleSpeed = Integer.parseInt(levelInfoMap.get("paddle_speed"));
                    this.paddleWidth = Integer.parseInt(levelInfoMap.get("paddle_width"));
                    this.numOfBlocks = Integer.parseInt(levelInfoMap.get("num_blocks"));
                    this.blockStartX = Integer.parseInt(levelInfoMap.get("blocks_start_x"));
                    this.blockStartY = Integer.parseInt(levelInfoMap.get("blocks_start_y"));
                    this.blockDefinitionsPath = "resources/" + levelInfoMap.get("block_definitions");
                    String backgroundStr = levelInfoMap.get("background");
                    this.velocityList = new ArrayList<>();
                    String[] velArray = levelInfoMap.get("ball_velocities").split(" ");
                    for (String vel : velArray) {
                        String[] parts = vel.split(",");
                        String angel = parts[0];
                        String speed = parts[1];
                        velocityList.add(Velocity.fromAngleAndSpeed(Double.parseDouble(angel)
                                , Double.parseDouble(speed)));
                    }
                    //check if the background is an image or a color and assign them accordingly
                    this.isImage = false;
                    this.imagePath = null;
                    java.awt.Color color;
                    if (backgroundStr.startsWith("color") || backgroundStr.startsWith("fillcolor")) {
                        color = ColorsParser.colorFromString(backgroundStr);
                        this.background = new Block(new Rectangle(new Point(0, 0), 800, 600), color, 0);

                    } else if (backgroundStr.startsWith("image")) {
                        this.isImage = true;
                        this.imagePath = backgroundStr;
                    }
                    levelInfoMap.clear();
                    //using a BlocksDefinitionReader to get the definitions of the block and creating a block factory
                    BlocksDefinitionReader blockReader = new BlocksDefinitionReader();
                    BlocksFromSymbolsFactory factory = null;
                    try {
                        // block factory is created
                        factory = blockReader.fromReader(new FileReader(blockDefinitionsPath));
                    } catch (FileNotFoundException e) {
                        System.out.println("Error reading file");
                    }
                    //crating a level information and assigning with it's values with setters.
                    LevelFromInput levelFromInput = new LevelFromInput(factory);
                    levelFromInput.setBlockStartX(this.blockStartX);
                    levelFromInput.setBlockStartY(this.blockStartY);
                    levelFromInput.setGetLayout(layout);
                    levelFromInput.setLevelName(levelName);
                    levelFromInput.setNumberOfBlocksToRemove(numOfBlocks);
                    levelFromInput.setPaddleSpeed(paddleSpeed);
                    levelFromInput.setPaddleWidth(paddleWidth);
                    levelFromInput.setVelocityList(velocityList);
                    levelFromInput.setNumofBalls(velocityList.size());
                    levelFromInput.setImage(isImage);
                    levelFromInput.setBackground(background);
                    levelFromInput.setImagePath(imagePath);
                    this.levels.add(levelFromInput);
                } catch (IOException e) {
                    System.out.println("Error Reading file");
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading file");

        }
        //levels are returned
        return this.levels;

    }

    /**
     * check if one or more of the fields is empty.
     *
     * @param levelinfomap the levelinfomap
     * @return the true if it is empty.
     */
    public boolean fieldsEmpty(TreeMap<String, String> levelinfomap) {
        return !(levelinfomap.containsKey("level_name")
                && levelinfomap.containsKey("paddle_speed")
                && levelinfomap.containsKey("paddle_width")
                && levelinfomap.containsKey("num_blocks")
                && levelinfomap.containsKey("blocks_start_x")
                && levelinfomap.containsKey("blocks_start_y")
                && levelinfomap.containsKey("block_definitions")
                && levelinfomap.containsKey("ball_velocities"))
                && levelinfomap.containsKey("background");
    }

    /**
     * check if line only contains spaces.
     *
     * @param str the str
     * @return the boolean
     */
    public boolean onlySpaces(String str) {
        return (str.replaceAll(" ", "").length() == 0);
    }


}
