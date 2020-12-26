package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {

    private List<String> menuKeys;
    private List<String> menuMessages;
    private List<T> menuReturnValues;
    private T status;
    private String menuTitle;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private boolean stop;
    private List<Boolean> isSubMenuList;
    private List<Menu<T>> subMenuList;

    /**
     * Instantiates a new Menu animation.
     *
     * @param menuTitle       the menu title
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the animation runner
     */
    public MenuAnimation(String menuTitle, KeyboardSensor keyboardSensor, AnimationRunner animationRunner) {
        this.menuTitle = menuTitle;
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
        this.menuKeys = new ArrayList<>();
        this.menuMessages = new ArrayList<>();
        this.menuReturnValues = new ArrayList<>();
        this.isSubMenuList = new ArrayList<>();
        this.subMenuList = new ArrayList<>();
        this.reset();
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.menuKeys.add(key);
        this.menuMessages.add(message);
        this.menuReturnValues.add(returnVal);
        this.isSubMenuList.add(false);
        this.subMenuList.add(null);
    }

    @Override
    public T getStatus() {
        return this.status;
    }


    /**
     * The Animation does one frame in the game.
     *
     * @param d  the DrawSurface
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.YELLOW);
        //drawing menu titles and messages
        d.drawText(50, 50, this.menuTitle, 30);
        for (int i = 0; i < this.menuMessages.size(); i++) {
            d.drawText(100, 120 + 50 * i, "(" + this.menuKeys.get(i) + ")" + this.menuMessages.get(i), 24);
        }
        //check for key presses to stop menu
        for (int i = 0; i < this.menuReturnValues.size(); i++) {
            if (this.keyboardSensor.isPressed((this.menuKeys.get(i)))) {
                if (!isSubMenuList.get(i)) {
                    this.status = this.menuReturnValues.get(i);
                    this.stop = true;
                    break;
                }
                //running the submenu
                this.animationRunner.run(subMenuList.get(i));
                this.status = subMenuList.get(i).getStatus();
                this.stop = true;
                subMenuList.get(i).reset();
                break;
            }
        }
    }

    /**
     * Reset the menu.
     */
    public void reset() {
        this.status = null;
        this.stop = false;

    }

    /**
     * Should the Animation Stop.
     *
     * @return true if it should false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return status != null;
    }

    /**
     * Adding the submenu.
     *
     * @param key     the keyboard
     * @param message the   message
     * @param subMenu the submenu
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.menuKeys.add(key);
        this.menuMessages.add(message);
        this.menuReturnValues.add(null);
        this.isSubMenuList.add(true);
        this.subMenuList.add(subMenu);
    }

}
