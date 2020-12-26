package iolevels;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import game.levels.LevelInformation;


/**
 * The type Sub menu SubMenuFromTypeFactory.
 * the class creates the levels using a LevelSpecificationReader with a given file path.
 */
public class SubMenuFromTypeFactory {
    private String levelType;
    private List<LevelInformation> levels = new ArrayList<>();
    private SubMenuReader submenu;


    /**
     * Instantiates a new Sub menu definition reader.
     *
     * @param levelType the level type
     * @param subMenu   the sub menu
     */
    public SubMenuFromTypeFactory(String levelType, SubMenuReader subMenu) {
        this.levelType = levelType;
        this.submenu = subMenu;
    }

    /**
     * the functions builds a level set based on the info .
     *
     * @return the list
     */
    public List<LevelInformation> buildLevelSet() {
        LevelSpecificationReader lsr1 = new LevelSpecificationReader();
        LevelSpecificationReader lsr2 = new LevelSpecificationReader();
        LevelSpecificationReader lsr3 = new LevelSpecificationReader();
        LevelSpecificationReader lsr4 = new LevelSpecificationReader();
        LevelSpecificationReader lsr5 = new LevelSpecificationReader();
        LevelSpecificationReader lsr6 = new LevelSpecificationReader();


        //Easy Difficulty levels are set
        if (levelType.equals(submenu.getKeys().get(0))) {
            try {
                levels.addAll(lsr1.fromReader(new FileReader(this.submenu.getPaths().get(0))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                levels.addAll(lsr2.fromReader(new FileReader(this.submenu.getPaths().get(1))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return levels;
        }
        //Medium Difficulty levels are set
        if (levelType.equals(submenu.getKeys().get(1))) {
            try {
                levels.addAll(lsr3.fromReader(new FileReader(this.submenu.getPaths().get(2))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                levels.addAll(lsr4.fromReader(new FileReader(this.submenu.getPaths().get(3))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return levels;
        }
        //Hard Difficulty levels are set
        if (levelType.equals(submenu.getKeys().get(2))) {
            try {
                levels.addAll(lsr5.fromReader(new FileReader(this.submenu.getPaths().get(4))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                levels.addAll(lsr6.fromReader(new FileReader(this.submenu.getPaths().get(5))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return levels;
        }
        return null;
    }
}