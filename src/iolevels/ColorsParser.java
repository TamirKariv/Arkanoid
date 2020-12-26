package iolevels;

import java.awt.Color;


/**
 * The type Colors parser.
 */
public class ColorsParser {


    /**
     *  parse color definition and return the specified color.
     *
     * @param s the string
     * @return the color
     */
    public static java.awt.Color colorFromString(String s) {
        if (s.startsWith("fillcolor")) {
            s = s.replace("fillcolor", "color");
        }

        if (s.startsWith("color(RGB")) {
            String r1 = s.replaceAll("\\(", "");
            String r2 = r1.replaceAll("\\)", "");
            String r3 = r2.replaceAll("colorRGB", "");
            // Creating the RGB values.
            int val1 = Integer.parseInt(r3.split(",")[0]);
            int val2 = Integer.parseInt(r3.split(",")[1]);
            int val3 = Integer.parseInt(r3.split(",")[2]);
            return new Color(val1, val2, val3);
        }
        if (s.contains("black")) {
            return Color.black;
        }
        if (s.contains("lightGray")) {
            return Color.lightGray;
        }
        if (s.contains("blue")) {
            return Color.blue;
        }
        if (s.contains("white")) {
            return Color.white;
        }
        if (s.contains("pink")) {
            return Color.pink;
        }
        if (s.contains("gray")) {
            return Color.gray;
        }
        if (s.contains("red")) {
            return Color.red;
        }
        if (s.contains("cyan")) {
            return Color.cyan;
        }
        if (s.contains("green")) {
            return Color.green;
        }
        if (s.contains("orange")) {
            return Color.orange;
        }
        if (s.contains("yellow")) {
            return Color.yellow;
        }
        return null;
    }
}






