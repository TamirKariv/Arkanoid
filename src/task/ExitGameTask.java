package task;

import biuoop.GUI;

/**
 * The type Exit game task.
 */
public class ExitGameTask implements Task<Void> {

    private GUI gui;

    /**
     * Instantiates a new Exit game task.
     *
     * @param gui the gui
     */
    public ExitGameTask(GUI gui) {
        this.gui = gui;
    }

    @Override
    public Void run() {
        this.gui.close();
        return null;
    }

}
