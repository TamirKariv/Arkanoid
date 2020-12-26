package score;

import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> scoreInfos;
    private int maxSize;


    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified maxSize.
    // The maxSize means that the table holds up to maxSize top scores.
    public HighScoresTable(int size) {
        this.maxSize = size;
        this.scoreInfos = new ArrayList<>(this.maxSize);
    }

    /**
     * Adds a score to the table.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        //no space don't add
        if (this.getRank(score.getScore()) > this.maxSize) {
            return;
        //last element add to the end of the list
        } else if (this.getRank(score.getScore()) == this.maxSize) {
            this.scoreInfos.add(getHighScores().size(), score);
        } else {
            this.scoreInfos.add(this.getRank(score.getScore()) - 1, score);
        }
        //if the list is bigger then the max size remove the last element;
        if (this.scoreInfos.size() > this.maxSize) {
            this.scoreInfos.remove(this.scoreInfos.size() - 1);
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.maxSize;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.scoreInfos;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `maxSize` means the score will be lowest.
    // Rank > `maxSize` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        for (int i = 0; i < this.scoreInfos.size(); i++) {

            if (score > this.scoreInfos.get(i).getScore()) {
                return i + 1;
            }
        }
        //lowest score, check if there's space in the highscore table.
        if (this.maxSize == this.scoreInfos.size()) {
            return this.maxSize + 1;
        }
        return this.maxSize;
    }


    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        this.scoreInfos.clear();

    }


    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        this.clear();
        ObjectInputStream in = null;
        HighScoresTable highScoresTable = null;
        try {
            in = new ObjectInputStream(
                    new FileInputStream(filename));
            highScoresTable = (HighScoresTable) in.readObject();
            this.scoreInfos = highScoresTable.getHighScores();
            this.maxSize = highScoresTable.size();
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename.getName());
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(
                    new FileOutputStream(filename.getName()));
            out.writeObject(this);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable newHighScoreTable = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(
                    new FileInputStream(filename));
            newHighScoreTable = (HighScoresTable) in.readObject();
            return newHighScoreTable;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename.getName());
            return new HighScoresTable(10);
        } catch (IOException e) {
            return new HighScoresTable(10);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }


}






