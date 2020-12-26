package iolevels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * The type Sub menu reader.
 */
public class SubMenuReader {
    private List<String> keys = new ArrayList<>();

    private List<String> levelPaths = new ArrayList<>();

    private List<String> message = new ArrayList<>();


    /**
     * From reader read the level_sets file and assign the level with their keys and path.
     *
     * @param reader the reader
     */
    public void fromReader(java.io.Reader reader) {
        BufferedReader br = new BufferedReader(reader);
        String line;
        // Reading the file line by line.
        try {
            while ((line = br.readLine()) != null) {
                if (line.contains(":")) {
                    String key = line.split(":")[0];
                    if (!this.keys.contains(key)) {
                        this.keys.add(key);
                    }
                }
                if (line.contains(".txt")) {
                    this.levelPaths.add(line);
                    String tempMessage = line.split("/")[2];
                    if (!this.message.contains(tempMessage)) {
                        this.message.add(tempMessage);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error Reading Files");
        }
    }


    /**
     * Gets keys.
     *
     * @return the keys
     */
    public List<String> getKeys() {
        return this.keys;
    }


    /**
     * Gets paths.
     *
     * @return the paths
     */
    public List<String> getPaths() {
        return this.levelPaths;
    }


    /**
     * Gets message.
     *
     * @return the message
     */
    public List<String> getMessage() {
        return this.message;
    }
}