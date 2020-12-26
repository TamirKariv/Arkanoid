

import biuoop.GUI;
import game.GameFlow;


/**
 * The type Ass 6 game.
 * @author Tamir Kariv
 */
public class Ass6Game {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow game = new GameFlow(gui, null, args);
        game.runMenu();
    }
}

