package game;

import animation.AnimationRunner;
import animation.Menu;
import animation.HighScoresAnimation;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import hitlistener.Counter;
import game.levels.LevelInformation;
import iolevels.SubMenuFromTypeFactory;
import iolevels.SubMenuReader;
import score.HighScoresTable;
import score.ScoreInfo;
import task.ExitGameTask;
import task.ShowHiScoresTask;
import task.StartGameTask;
import task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;
    private GUI gui;
    private List<LevelInformation> levelsOfGame;
    private HighScoresTable highScoresTable;
    private String[] args;


    /**
     * Instantiates a new Game flow.
     *
     * @param gui    the gui
     * @param levels game levels
     * @param args   args from user
     */
    public GameFlow(GUI gui, List<LevelInformation> levels, String[] args) {
        this.gui = gui;
        this.animationRunner = new AnimationRunner(gui);
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.score = new Counter();
        this.lives = new Counter();
        this.lives.increase(7);
        this.highScoresTable = new HighScoresTable(10);
        this.levelsOfGame = levels;
        this.args = args;


    }


    /**
     * the function runs the game.levels of the game from the list of game.levels.
     *
     * @param levels the levelsOfGame
     */
    public void runLevels(List<LevelInformation> levels) {



        //load the score if it exists else creat a new one
        File highscores = new File("highscores");
        if (!highscores.exists()) {
            try {
                this.highScoresTable.save(new File("highscores"));
            } catch (IOException e) {
                System.out.println("error creating new file");
                return;
            }
        } else {
            try {
                this.highScoresTable.load(highscores);
            } catch (IOException e) {
                System.out.println("error reading from file");
                return;
            }
        }
        boolean wonTheGame = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score
                    , this.lives);
            level.initialize();
            while (level.getNumberOfBlocks() != 0 && this.lives.getValue() != 0) {
                level.playOneTurn();
            }
            //player lost the game
            if (this.lives.getValue() == 0) {
                wonTheGame = false;
            }
        }
        //player won the game
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                "space", new EndScreen(wonTheGame, this.score.getValue())));
        if (checkPlayerScore()) {
            addPlayer();
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                "space", new HighScoresAnimation()));
    }


    /**
     * Get the game.levels of game.
     *
     * @return the game.levels of game
     */
    public List<LevelInformation> getLevelsOfGame() {
        return levelsOfGame;
    }

    /**
     * Check if the player's score is high enough to be put in the highscore table.
     *
     * @return true if it is ,false otherwise
     */
    public boolean checkPlayerScore() {
        return !(this.highScoresTable.getRank(this.score.getValue()) > this.highScoresTable.size());
    }

    /**
     * Add a a player's score to the highscore table.
     */
    public void addPlayer() {
        DialogManager dialog = this.gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        if (name != null) {
            this.highScoresTable.add(new ScoreInfo(name, this.score.getValue()));
            try {
                this.highScoresTable.save(new File("highscores"));
            } catch (IOException e) {
                System.out.println("error creating new file");
            }
        }
    }

    /**
     * Sets levels of game.
     *
     * @param levels the levels of game
     */
    public void setLevelsOfGame(List<LevelInformation> levels) {
        this.levelsOfGame = levels;
    }

    /**
     * Running the menu of the game, the game includes three difficulties of level sets which are loaded from a file.
     */
    public void runMenu() {
        //menu reader to get the level sets
        SubMenuReader subReader = new SubMenuReader();
        while (true) {
            try {
                //path for level sets file.
                    subReader.fromReader(new FileReader("resources" + "/level_sets.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("Error finding level_sets File");
            }
            //setting up the easy levels set
            List<LevelInformation> easyLevels =
                    new SubMenuFromTypeFactory(subReader.getKeys().get(0), subReader).buildLevelSet();
            //setting up the medium levels set
            List<LevelInformation> mediumLevels
                    = new SubMenuFromTypeFactory(subReader.getKeys().get(1), subReader).buildLevelSet();
            //setting up the hard levels set
            List<LevelInformation> hardLevels
                    = new SubMenuFromTypeFactory(subReader.getKeys().get(2), subReader).buildLevelSet();
            //main menu is set
            Menu<Task<Void>> menu = new MenuAnimation<>("Arknoid", this.keyboardSensor, this.animationRunner);
            //adding the submenu of choosing Difficulty"
            Menu<Task<Void>> levelSetsMenu = new MenuAnimation<>("Select Difficulty", this.keyboardSensor,
                    this.animationRunner);
            //adding the submenu of Startgame"
            menu.addSubMenu("s", "Start Game", levelSetsMenu);
            //level sets selections are added
            levelSetsMenu.addSelection(subReader.getKeys().get(0), subReader.getMessage().get(0),
                    new StartGameTask(new GameFlow(this.gui, easyLevels, this.args), easyLevels));
            levelSetsMenu.addSelection(subReader.getKeys().get(1), subReader.getMessage().get(1),
                    new StartGameTask(new GameFlow(this.gui, null, this.args), mediumLevels));
            levelSetsMenu.addSelection(subReader.getKeys().get(2), subReader.getMessage().get(2),
                    new StartGameTask(new GameFlow(this.gui, null, this.args), hardLevels));
            //highscores selection is added
            menu.addSelection("h", "HighScores.", new ShowHiScoresTask(this.animationRunner,
                    new KeyPressStoppableAnimation(this.keyboardSensor, "space", new HighScoresAnimation())));
            //Quit Game selection is added
            menu.addSelection("q", "Quit Game", new ExitGameTask(gui));
            //runing the menu
            this.animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            //menu rest after task is done
            menu.reset();
        }
    }
}

