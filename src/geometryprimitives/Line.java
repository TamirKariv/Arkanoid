package geometryprimitives;

import java.util.List;

/**
 * The contents of the geometryprimitives.Line Class.
 * The geometryprimitives.Line is defined by 2 points a start point and an end point.
 *
 * @author Tamir Kariv
 */
public class Line {
    //members
    private Point start;
    private Point end;


    /**
     * constructor  - initialize the start and end points values of the line.
     *
     * @param start - the start point of the line.
     * @param end   - the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        //if the start and the end equal the lines is a geometryprimitives.Point.
        if (this.start == this.end) {
            throw new RuntimeException("geometryprimitives.Line must have two different set of points");
        }
    }

    /**
     * constructor  - initialize the x and y values to the start and end points of the line.
     *
     * @param x1 - x value of the start of the line.
     * @param y1 - y value of the start of the line.
     * @param x2 - x value of the end of the line.
     * @param y2 - y value of the end of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        ////if the start and the end equal the lines is a geometryprimitives.Point.
        if (this.start == this.end) {
            throw new RuntimeException("geometryprimitives.Line must have two different set of points");
        }
    }

    /**
     * the function calculates the length of a line.
     *
     * @return double, the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * the function returns the the middle point of the line.
     *
     * @return geometryprimitives.Point, Returns the middle point of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);

    }

    /**
     * the function returns the start point of the line.
     *
     * @return geometryprimitives.Point, Returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * the function returns the end point of the line.
     *
     * @return geometryprimitives.Point, Returns the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * the function calculates the Slope of a line.
     *
     * @param other - line given to the function.
     * @return double, the Slope of the line.
     */
    public double calculateSlope(Line other) {
        return (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
    }

    /**
     * the function returns true if the lines intersect, false otherwise.
     *
     * @param other - line given to the function.
     * @return boolean, true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {

        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * the function Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other - line given to the function.
     * @return geometryprimitives.Point, point of intersection.(null if the point doesn't exist)
     */
    public Point intersectionWith(Line other) {
        Point intersectionPoint;
        // if same line was given or a line with the same coordinates.
        if (this.equals(other) || this.start.equals(this.end) || other.start.equals(other.end)) {
            return null;
        }
        //if both lines are vertical
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            //if they have the same x value.
            if (this.start.getX() == other.start.getX()) {
                //checking if they intersect
                return this.specialCasesOfIntersection(other);
            }
            // not the same x value or overlap.
            return null;
            // if one of the lines is vertical
        } else if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()
                || this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
            //checking which one is vertical and checking the intersection
            if (this.start.getX() == this.end.getX()) {
                intersectionPoint = this.compareEquationsOfLines(other);
            } else {
                intersectionPoint = other.compareEquationsOfLines(this);
            }
            //if there's an intersection point compare it's values with the other two lines.
            if (this.compareValues(other, intersectionPoint)) {
                return intersectionPoint;
                //if there
            } else {
                return null;
            }
            //if both are parallel and have the same y value.
        } else if (this.start.getY() == this.end.getY() && other.start.getY() == other.end.getY()) {
            //if they have the same value
            if (this.start.getY() == other.start.getY()) {
                //check if they intersect
                return this.specialCasesOfIntersection(other);
            }
            //not the same y value and don't intersect.
            return null;
            //if they are parallel but their slope isn't 0.
        } else if (calculateSlope(this) == calculateSlope(other)) {
            // checking the b value in y=ax+b if it's the same value in both equations go to the special case.
            if (this.start.getY() - calculateSlope(this) * this.start.getX()
                    == other.start.getY() - calculateSlope(other) * other.start.getX()) {
                // checking if they intersect.
                return this.specialCasesOfIntersection(other);

            }
            //if they overlap or the b value is different.
            return null;
            //any other case check the intersectionPoint then compare it with the values of the two lines.
        } else {
            intersectionPoint = this.compareEquationsOfLines(other);
            if (this.compareValues(other, intersectionPoint)) {
                return intersectionPoint;
            }
        }
        return null;
    }

    /**
     * the function return true is the lines are equal, false otherwise.
     *
     * @param other - line given to the function.
     * @return boolean, true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.start == other.start && this.end == other.end || this.start == other.end && this.end == other.start) {
            return true;
        }
        return false;
    }

    /**
     * .
     * the functions checks three special cases of lines intersection:
     * 1.between two vertical lines with the same x values.
     * 2.between two parallel lines with the same y values.
     * 3.between two parallel lines with the same slope that it different then 0(y=ax+b).
     *
     * @param other - the other line.
     * @return geometryprimitives.Point, the Intersection point,null if it doesn't exist.
     */
    public Point specialCasesOfIntersection(Line other) {

        /*using temp variables to compare the values of the lines,the start of the lines will get the minimum values
        of either x or y and the end of the lines will get the maximum values
        (other and this are names of the two lines)*/
        double tempStartThis;
        double tempEndThis;
        double tempStartOther;
        double tempEndOther;
        int flag = 0;
        //case 1 for vertical lines the y values are stored in the temp variables.
        if (this.start.getX() == this.end.getX()) {
            //start gets min value
            tempStartThis = Math.min(this.start.getY(), this.end.getY());
            tempStartOther = Math.min(other.start.getY(), other.end.getY());
            //end gets max value
            tempEndThis = Math.max(this.start.getY(), this.end.getY());
            tempEndOther = Math.max(other.start.getY(), other.end.getY());
            //flag to show which case was used
            flag = 1;
            //case 2 for parallel lines the x values are stored in the temp variables.
        } else if (calculateSlope(other) == 0) {
            tempStartThis = Math.min(this.start.getX(), this.end.getX());
            tempStartOther = Math.min(other.start.getX(), other.end.getX());
            tempEndThis = Math.max(this.start.getX(), this.end.getX());
            tempEndOther = Math.max(other.start.getX(), other.end.getX());
            flag = 2;
            //case 3 for the other lines the x values are used and we will get the y value later using the line equation
        } else {
            tempStartThis = Math.min(this.start.getX(), this.end.getX());
            tempStartOther = Math.min(other.start.getX(), other.end.getX());
            tempEndThis = Math.max(this.start.getX(), this.end.getX());
            tempEndOther = Math.max(other.start.getX(), other.end.getX());
            flag = 3;
        }
        //comparing the start and end of the lines if they are equal then they intersect.
        if (tempStartThis == tempEndOther) {
            // vertical lines
            if (flag == 1) {
                return new Point(this.start.getX(), tempStartThis);
                // parallel lines
            }
            if (flag == 2) {
                return new Point(tempStartThis, this.start.getY());
            }
            //same slope that is different then 0, using the equation of the line to find the y value.
            if (flag == 3) {
                double a = calculateSlope(this);
                double b = this.start.getY() - a * this.start.getX();
                double x = tempStartThis;
                return new Point(x, a * x + b);
            }
        }
        //same case only with the start of the other line and the end of the this line
        if (tempStartOther == tempEndThis) {
            if (flag == 1) {
                return new Point(this.start.getX(), tempStartOther);
            }
            if (flag == 2) {
                return new Point(tempStartOther, other.start.getX());
            }
            if (flag == 3) {
                double a = calculateSlope(this);
                double b = this.start.getY() - a * this.start.getX();
                double x = tempStartOther;
                return new Point(x, a * x + b);
            }
        }
        //if they don't intersect
        return null;
    }

    /**
     * the function returns a possible intersection geometryprimitives.Point of two lines by comparing their equations,
     * the point is later checked.
     *
     * @param other - line given to the function.
     * @return geometryprimitives.Point, possible intersection geometryprimitives.Point of two lines.
     */
    public Point compareEquationsOfLines(Line other) {

        //if one of the lines is vertical(x=b) find it's y value.
        if (this.start.getX() == this.end.getX()) {
            double a = calculateSlope(other);
            double b = other.start.getY() - a * other.start.getX();
            double x = this.start.getX();
            return new Point(x, Math.round(a * x + b));
        }
        //finding the intersection point using the equations of the two lines y=ax+b y=mx+d.
        double a = calculateSlope(this);
        double m = calculateSlope(other);
        double b = this.start.getY() - a * this.start.getX();
        double d = other.start.getY() - m * other.start.getX();
        //bug duo to lack of precision in y=0, returning 0 in the 0 y value.

        if (this.start.getY() == 0 && this.end.getY() == 0 || other.start.getY() == 0 && other.end.getY() == 0) {
            return new Point((d - b) / (a - m), 0);
        }
        // possible intersection geometryprimitives.Point is returned
        return new Point((d - b) / (a - m), Math.round(((a * (d - b)) / (a - m)) + b));
    }

    /**
     * the function checks a possible Intersection point of two lines
     * by comparing ir's x and y values to the other lines.
     *
     * @param other          - line given to the function.
     * @param intersectPoint - possible intersect geometryprimitives.Point.
     * @return geometryprimitives.Point-
     * possible intersection geometryprimitives.Point of two lines.(or null if it isn't)
     */
    public boolean compareValues(Line other, Point intersectPoint) {


        //checking if the x values and y values of the intersection point is within of the two lines.

        if (Math.min(this.start.getX(), this.end.getX()) <= (intersectPoint.getX())
                && intersectPoint.getX() <= Math.max(this.start.getX(), this.end.getX())
                && Math.min(other.start.getX(), other.end.getX()) <= intersectPoint.getX()
                && intersectPoint.getX() <= Math.max(other.start.getX(), other.end.getX())
                && Math.min(this.start.getY(), this.end.getY()) <= intersectPoint.getY()
                && intersectPoint.getY() <= Math.max(this.start.getY(), this.end.getY())
                && Math.min(other.start.getY(), other.end.getY()) <= intersectPoint.getY()
                && intersectPoint.getY() <= Math.max(other.start.getY(), other.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * the function checkes if line intersect with the rectangle, if it does return the closest intersection
     * point to the start of the line .
     * // Otherwise, return it returns null;
     *
     * @param rect - rect given to the function.
     * @return closest Intersection To Start Of geometryprimitives.Line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestIntersectionPoint = null;
        //getting a list of possible intersections
        List<Point> intersectionPointsList = rect.intersectionPoints(this);
        int flag = 0;
        //if list is empty(nothing to compare with) return null.
        if (intersectionPointsList.isEmpty()) {
            return null;
        }
        //going through the intersectionPointsLis
        for (int i = 0; i < intersectionPointsList.size(); i++) {
            //if flag is 0 the the first intersection point will be the closest one
            if (flag == 0) {
                closestIntersectionPoint = intersectionPointsList.get(i);
                flag = 1;
                continue;
            }
            //otherwise compare both of their distances to the start point the one who's closer is the closest one.
            if (intersectionPointsList.get(i).distance(this.start) < closestIntersectionPoint.distance(this.start)) {
                closestIntersectionPoint = intersectionPointsList.get(i);
            }
        }
        //return the closest point
        return closestIntersectionPoint;


    }


}








