package geometryprimitives;

import java.util.ArrayList;

/**
 * The contents of the Rectangle Class.
 * the rectangle is a shape used to be darwed on the DrawSurface.
 *
 * @author Tamir Kariv
 */
public class Rectangle {

    //members
    private Point upperLeft;
    private Point bottomLeft;
    private Point upperRight;
    private Point bottomRight;
    private double width;
    private double height;


    // Create a new rectangle with location and width/height.

    /**
     * constructor  - initialize the shape of the block and it's color.
     *
     * @param upperLeft - the upper left point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        //defining the other points of rectangle.
        this.bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.upperRight = new Point(this.getUpperLeft().getX() + this.width, this.upperLeft.getY());
        this.bottomRight = new Point(this.upperRight.getX(), this.upperRight.getY() + this.height);



    }


    /**
     * the function checks intersection points between a line and a rectangle
     * it then returns a (possibly empty) List of the intersection points.
     *
     * @param line - the line given.
     * @return intersection Points
     */
    public ArrayList<Point> intersectionPoints(Line line) {
        //defining the lines of the rectangle.
        Line upperLine = new Line(this.upperLeft, this.upperRight);
        Line bottomLine = new Line(this.bottomLeft, this.bottomRight);
        Line rightLine = new Line(this.upperRight, this.bottomRight);
        Line leftLine = new Line(this.upperLeft, this.bottomLeft);
        //setting up the list of the intersection points.
        ArrayList<Point> intersectionPointsList = new ArrayList<Point>();

        //checking if the line given intersects with any of the lines of the rectangle if it is, it's add to the list.
        if (line.isIntersecting(upperLine)) {
            intersectionPointsList.add(line.intersectionWith(upperLine));
        }
        if (line.isIntersecting(bottomLine)) {
            intersectionPointsList.add(line.intersectionWith(bottomLine));
        }
        if (line.isIntersecting(rightLine)) {
            intersectionPointsList.add(line.intersectionWith(rightLine));
        }
        if (line.isIntersecting(leftLine)) {
            intersectionPointsList.add(line.intersectionWith(leftLine));
        }
        //returning the list of intersection points(it could be empty)
        return intersectionPointsList;
    }


    /**
     * the function returns the width of the rectangle.
     *
     * @return double - width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * the function returns the height of the rectangle.
     *
     * @return double - height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * the function returns the upper Left point of the rectangle.
     *
     * @return double - upper Left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * the function returns the upper right point of the rectangle.
     *
     * @return double - upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * the function returns the bottom Left point of the rectangle.
     *
     * @return double - upper bottom point of the rectangle.
     */
    public Point getBottomLeft() {
        return this.bottomLeft;
    }

    /**
     * the function returns the bottom right point of the rectangle.
     * right
     *
     * @return double - upper bottom right of the rectangle.
     */
    public Point getBottomRight() {
        return this.bottomRight;
    }


}
