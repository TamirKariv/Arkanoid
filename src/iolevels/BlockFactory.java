package iolevels;

import collidables.Block;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

import java.awt.Color;
import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Block factory.
 */
public class BlockFactory implements BlockCreator {
    private int height;
    private int width;
    private String stroke;
    private int hitpoints;
    private String bdefSymbol;
    private String fill;
    private Map<Integer, String> fillK = new TreeMap<>();
    private ImageParserAndStore imageParserAndStore;


    /**
     * Sets height.
     *
     * @param heightofBlock the height
     */
    public void setHeight(int heightofBlock) {
        this.height = heightofBlock;
    }

    /**
     * Sets width.
     *
     * @param widthBlock the width
     */
    public void setWidth(int widthBlock) {
        this.width = widthBlock;
    }

    /**
     * Sets bde fsymbol.
     *
     * @param bdefsymbol the bdefsymbol
     */
    public void setBDEFsymbol(String bdefsymbol) {
        this.bdefSymbol = bdefsymbol;
    }

    /**
     * Sets stroke.
     *
     * @param strokeBlock the stroke
     */
    public void setStroke(String strokeBlock) {
        this.stroke = strokeBlock;
    }

    /**
     * Sets hitpoints.
     *
     * @param hp the hitpoints
     */
    public void setHitpoints(int hp) {
        this.hitpoints = hp;
    }

    /**
     * Sets fill.
     *
     * @param fillBlock the fill
     */
    public void setFill(String fillBlock) {
        this.fill = fillBlock;

    }

    /**
     * Sets fill k.
     *
     * @param num       the num
     * @param fillBlock the fill
     */
    public void setFillK(int num, String fillBlock) {
        if (fillBlock == null || num == -1) {
            return;
        }
        this.fillK.put(num, fillBlock);
    }

    /**
     * creating blocks using parameters read from the file.
     *
     * @param xpos xpos of block
     * @param ypos ypos of block
     * @return a new block
     */
    public Block create(int xpos, int ypos) {
        //setting default for color and stroke if none given
        if (this.stroke == null) {
            this.stroke = "white";

        }
        if (this.fill == null && this.fillK.isEmpty()) {
            this.fill = "white";
        }
        if (this.fill == null) {
            int size = this.fillK.size();
            this.fill = this.fillK.get(size);
            this.fill = this.fill.replaceAll("fill", "");
        }

        boolean isImage = false;
        Color strokeColor = null;
        Color fillColor = null;
        Image image;
        //check if the blocks background is an image
        if (this.fill.contains("block_image")) {
            isImage = true;
        }
        //if it isn't set up it's color and stroke
        if (!isImage) {
            strokeColor = ColorsParser.colorFromString(stroke);
            fillColor = ColorsParser.colorFromString(this.fill);
        }
        //block is created
        Block block = new Block(new Rectangle(new Point(xpos, ypos)
                , this.width, this.height)
                , fillColor, strokeColor, this.hitpoints, this.fillK);
        //setting block's image if it's background is an image
        if (isImage) {
            block.setIsImage(true);
            if (this.fill.startsWith("image")) {
                this.fill = this.imageParserAndStore.imagePathFromString(this.fill);
            }
            image = imageParserAndStore.getImage(this.fill);
            block.setImage(image);

        }
        if (this.fillK != null) {
            for (int i = 1; i <= fillK.size(); i++) {
                if (fillK.get(i) == null) {
                    fillK.put(i, this.fill);
                }
            }
        }
        block.setImageParserAndStore(imageParserAndStore);

        //block is returned
        return block;
    }


    /**
     * Gets bdefsymbol.
     *
     * @return the bdefsymbol
     */
    public String getBdefsymbol() {
        return bdefSymbol;
    }

    /**
     * Sets image parser and store.
     *
     * @param imgeParserAndStore the image parser and store
     */
    public void setImageParserAndStore(ImageParserAndStore imgeParserAndStore) {
        this.imageParserAndStore = imgeParserAndStore;
    }


}
