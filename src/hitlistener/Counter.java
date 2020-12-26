package hitlistener;

/**
 * The type Counter.
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.count = 0;
    }


    /**
     * the function sums a number to current count.
     *
     * @param number the number to increase.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * the function subtract a number to current count.
     *
     * @param number the number to decrease
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * return the value of the count.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }
}