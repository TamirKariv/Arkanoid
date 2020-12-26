package iolevels;

import collidables.Block;

import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockFactory> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     *
     * @param spacerWidths  the spacer widths
     * @param blockCreators the block creators
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockFactory> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s the string
     * @return true if 's' is a valid space symbol/
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);

    }

    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s the s
     * @return true if 's' is a valid block symbol
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);

    }

    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s    the string
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */

    public Block getBlock(String s, int xpos, int ypos) {

        return this.blockCreators.get(s).create(xpos, ypos);

    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol..
     *
     * @param s the s
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}