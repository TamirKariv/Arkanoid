package geometryprimitives;

/**
 * The contents of the geometryprimitives.Point Class.
 * The point is defined by it's x and y values.
 *
 * @author Tamir Kariv
 */
public class Point {
    //members
    private double x;
    private double y;

    /**
     * the function calculates the distance between two points.
     *
     * @param other - the other point given to the function.
     * @return double, distance between two points.
     */
    public double distance(Point other) {

        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * constructor  - initialize the x and y values to the point.
     *
     * @param x - x value of the point.
     * @param y -y vlaue of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the function checks if two points are equal.
     *
     * @param other - the other point given to the function.
     * @return boolean - if equal true,otherwise false,
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }


    /**
     * the function returns the x value of a point.
     *
     * @return double - x value of a point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * the function returns the y value of a point.
     *
     * @return double - y value of a point.
     */
    public double getY() {
        return this.y;
    }
}




