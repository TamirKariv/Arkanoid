package task;

import game.GameFlow;
import game.levels.LevelInformation;

import java.util.List;

/**
 * The type Start game task.
 */
public class StartGameTask implements Task<Void> {
    private GameFlow gameFlow;
    private List<LevelInformation> levels;

    /**
     * Instantiates a new Start game task.
     *
     * @param gameFlow the game flow
     * @param levels   the levels
     */
    public StartGameTask(GameFlow gameFlow, List<LevelInformation> levels) {
        this.gameFlow = gameFlow;
        this.levels = levels;
    }

    @Override
    public Void run() {
        this.gameFlow.runLevels(this.levels);
        return null;
    }
}
