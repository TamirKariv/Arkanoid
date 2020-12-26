package iolevels;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Image parser and store.
 * this class is in charge of creating images from given string,
 * storing them in a map((to avoid reading every image from the file).
 * and parse a string to the image path.
 */
public class ImageParserAndStore {
    private Map<String, Image> imageMap = new TreeMap<>();


    /**
     * The function returns an image from a given image path.
     * if the image was already read from the file it will be returned.
     *
     * @param s the image path.
     * @return the image
     */
    public Image getImage(String s) {
        if (imageMap.containsKey(s)) {
            return imageMap.get(s);
        }
        try {
            Image image = ImageIO.read(new File(s));

            imageMap.put(s, image);
            return image;
        } catch (IOException e) {
            System.out.println("Error Finding Image");
        }
        return null;
    }

    /**
     * the function parses a string and returns an image path.
     *
     * @param s the string
     * @return the image path
     */
    public String imagePathFromString(String s) {
        if (s.contains("resources")) {
            return s;
        }
        if (s.contains("background_images")) {
            String r1 = s.replaceAll("\\(", "");
            String r2 = r1.replaceAll("\\)", "");
            String r3 = r2.replaceAll("background_images", "");
            String r4 = r3.replaceAll("image", "");
            String r5 = r4.replaceAll("\\/", "");
            return r5;
        }
        if (s.contains("block_images")) {
            String r1 = s.replaceAll("\\(", "");
            String r2 = r1.replaceAll("\\)", "");
            String r3 = r2.replaceFirst("image", "");
            String r4 = r3.replaceAll("imageblock", "block");
            return "resources/" + r4;
        }
        return null;


    }


}
