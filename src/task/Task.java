package task;

/**
 * The interface Task.
 *
 * @param <T> the type parameter
 */
public interface Task<T> {
    /**
     * Run the task.
     *
     * @return the t
     */
    T run();
}