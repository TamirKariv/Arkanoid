package iolevels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {


    private int defaultHeight = 0;
    private int defaultWidth = 0;
    private String defaultStroke = null;
    private int defaultHitPoints = -1;
    private String defaultFill = null;
    private int fillKnum = -1;
    private String fillKColor = null;
    private ImageParserAndStore imageParserAndStore = new ImageParserAndStore();


    /**
     *The function Reads from the Block definitions file and assigns the values of the block accordingly.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockFactory> blockCreators = new TreeMap<>();
        BufferedReader br = new BufferedReader(reader);
        String line;
        try {
            //setting up the default values of the block
            while ((line = br.readLine()) != null) {
                if (line.startsWith("default height")) {
                    String newStr = line.replace("default ", "");
                    String[] strArray = newStr.split(" ");
                    for (int i = 0; i < strArray.length; i++) {
                        this.addDefaultValues(strArray[i]);
                    }
                }
                //setting up the the values of the blocks according to their bdef symbol
                if (line.startsWith("bdef")) {
                    BlockFactory blockFactory = new BlockFactory();
                    String newStr = line.replaceAll("bdef ", "");
                    this.setDefaults(blockFactory);
                    String[] strArray = newStr.split(" ");
                    for (int i = 0; i < strArray.length; i++) {
                        this.addBlockValues(strArray[i], blockFactory);
                    }

                    blockCreators.put(blockFactory.getBdefsymbol(), blockFactory);
                }
                //setting up the the values of the blocks space width according to their sdef symbol
                if (line.startsWith("sdef")) {
                    String newStr = line.replaceAll("sdef", "");
                    String sdefSymbol = (newStr.split(" ")[1]).split(":")[1];
                    int sdefValue = Integer.parseInt(newStr.split(" ")[2].split(":")[1]);
                    spacerWidths.put(sdefSymbol, sdefValue);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        //returns a new block factory which blocks are created from their assigned values.
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    /**
     * Add default values of a block.
     *
     * @param value the value
     */
    public void addDefaultValues(String value) {
        if (value.contains(":")) {
            if (value.startsWith("height")) {
                this.defaultHeight = (Integer.parseInt(value.split(":")[1]));
            }
            if (value.startsWith("width")) {
                this.defaultWidth = (Integer.parseInt(value.split(":")[1]));
            }
            if (value.startsWith("hit_points")) {
                this.defaultHitPoints = (Integer.parseInt(value.split(":")[1]));
            }
            if (value.startsWith("stroke")) {
                this.defaultStroke = (value.split(":")[1]);
            }
            if (value.startsWith("fill-")) {
                String secondeSplit = (value.split(":")[0]).split("-")[1];
                this.fillKnum = Integer.parseInt(secondeSplit);
                this.fillKColor = (value.split(":")[1]);
            } else if (value.startsWith("fill")) {
                this.defaultFill = value.split(":")[1];
            }
        }
    }

    /**
     * Sets defaults values of a block.
     *
     * @param blockFactory the block factory
     */
    public void setDefaults(BlockFactory blockFactory) {
        blockFactory.setHeight(this.defaultHeight);
        blockFactory.setWidth(this.defaultWidth);
        blockFactory.setHitpoints(this.defaultHitPoints);
        blockFactory.setFill(this.defaultFill);
        blockFactory.setFillK(this.fillKnum, this.fillKColor);
        blockFactory.setStroke(this.defaultStroke);
        blockFactory.setImageParserAndStore(imageParserAndStore);
    }


    /**
     * Add block values for bdef.
     *
     * @param value        the value
     * @param blockFactory the block factory
     */
     //same function like default values had to do it twice to avoid mixing default values with new ones
    public void addBlockValues(String value, BlockFactory blockFactory) {
        if (value.contains(":")) {
            if (value.startsWith("symbol")) {
                blockFactory.setBDEFsymbol(value.split(":")[1]);
            }
            if (value.startsWith("height")) {
                blockFactory.setHeight((Integer.parseInt(value.split(":")[1])));
            }
            if (value.startsWith("width")) {
                blockFactory.setWidth((Integer.parseInt(value.split(":")[1])));
            }
            if (value.startsWith("hit_points")) {
                blockFactory.setHitpoints((Integer.parseInt(value.split(":")[1])));
            }
            if (value.startsWith("stroke")) {
                this.defaultStroke = (value.split(":")[1]);
            }
            if (value.startsWith("fill-")) {
                String secondeSplit = (value.split(":")[0]).split("-")[1];
                int fillNum = Integer.parseInt(secondeSplit);
                String fillColor = (value.split(":")[1]);
                blockFactory.setFillK(fillNum, fillColor);
            } else if (value.startsWith("fill")) {
                blockFactory.setFill(value.split(":")[1]);
            }
        }
    }
}
