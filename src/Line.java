// ID: 208061911

import java.util.ArrayList;
import java.util.List;
import static java.lang.Double.max;
import static java.lang.Double.min;

/**
 * @author Yohay Artzi
 * this class represent line in 2D, defined between 2 points
 */
public class Line {

    private static final double EPSILON = 1.0E-4;

    private Point start;
    private Point end;

    /**
     * .
     * Constructor
     *
     * @param start - the point that the line starts with
     * @param end   -  the point that the line ends with
     */
    public Line(Point start, Point end) {

        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * .
     * Constructor
     *
     * @param x1 - the x coordinate of the starting point
     * @param y1 - the y coordinate of the starting point
     * @param x2 - the x coordinate of the ending point
     * @param y2 - the y coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {

        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return starting point
     */
    public Point start() {

        return this.start;
    }

    /**
     * @return ending point
     */
    public Point end() {

        return this.end;
    }

    /**
     * @return middle point between starting point and ending point
     */
    public Point middle() {

        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return slope of the line
     */
    public Double slope() {

        // if the line is vertical
        if (this.end().getX() == this.start().getX()) {
            return null;
        }
        return (this.end().getY() - this.start().getY()) / (this.end().getX() - this.start().getX());
    }

    /**
     * @param p - some point
     * @return true if the point is on the line, false otherwise
     */
    public boolean isInLine(Point p) {

        double x = this.start().getX(), y = this.start().getY();
        Double m = this.slope();

        if (m == null && p.getX() == start().getX() && p.getY() >= min(start().getY(), end().getY())
                && p.getY() <= max(start().getY(), end().getY())) {
            return true;
        } else if (m == null) {
            return false;
        }


        // if the point is on the infinite line and in the correct range of line
        if (((m * (p.getX() - x)) + y - p.getY() <= EPSILON
                || p.getY() - (m * (p.getX() - x)) + y <= EPSILON)
                && p.getX() >= min(x, this.end().getX()) && p.getX() <= max(x, this.end().getX())
                && p.getY() >= min(y, this.end().getY())
                && p.getY() <= max(y, this.end().getY())) {
            return true;
        }
        return false;
    }

    /**
     * @param other - another line
     * @return true if lines are intersecting if they were not limited to the points
     * and continue after them with the same slope, false otherwise
     */
    public boolean isIntersectingInInfinity(Line other) {

        Double thisM = this.slope(), otherM = other.slope();

        //if the lines are parallel or coincidental
        if (thisM == otherM) {
            return false;
        }
        return true;
    }

    /**
     * @param other - another line
     * @return intersecting point if lines are intersecting (if they were not limited to the points
     * and continue after them with the same slope), null otherwise
     */
    public Point intersectionPointInInfinity(Line other) {

        double thisX1 = this.start().getX(), thisY1 = this.start().getY(), otherX1 = other.start().getX(),
                otherY1 = other.start().getY();
        Double thisM = this.slope(), otherM = other.slope();

        if (!this.isIntersectingInInfinity(other)) {
            return null;
        }

        if (thisM == null) {
            return new Point(thisX1, (otherM * (thisX1 - otherX1)) + otherY1);
        }
        if (otherM == null) {
            return new Point(otherX1, (thisM * (otherX1 - thisX1)) + thisY1);
        }

        double x = ((thisM * thisX1) - thisY1 - (otherM * otherX1) + otherY1) / (thisM - otherM),
                y = thisM * (x - thisX1) + thisY1;
        return new Point(x, y);
    }

    /**
     * @param other - another line
     * @return true if lines are intersecting , false otherwise
     */
    public boolean isIntersecting(Line other) {

        // checks if the lines intersecting in infinity and their intersection point is on the current line
        if (this.isIntersectingInInfinity(other) && this.isInLine(this.intersectionPointInInfinity(other))
                && other.isInLine(other.intersectionPointInInfinity(this))) {
            return true;
        }
        return false;
    }

    /**
     * @param other - another line
     * @return intersecting point if lines are intersecting , null otherwise
     */
    public Point intersectionWith(Line other) {

        // checks if the lines intersecting in infinity and their intersection point is on the current line
        if (this.isIntersecting(other)) {
            return (this.intersectionPointInInfinity(other));
        }
        return null;
    }

    /**
     * @param other - another line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {

        double thisX1 = this.start().getX(), thisX2 = this.end().getX(), otherX1 = other.start().getX(),
                otherX2 = other.end().getX(), thisY1 = this.start().getY(), thisY2 = this.end().getY(),
                otherY1 = other.start().getY(), otherY2 = other.end().getY();

        if ((this.start().equals(other.start()) && this.end().equals(other.end()))
                || (this.start().equals(other.end()) && this.end().equals(other.start()))) {
            return true;
        }
        return false;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * @param rect - another line
     * @return true if the lines are equal, false otherwise
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        List<Point> intersections = new ArrayList<>();
        intersections = rect.intersectionPoints(this);

        if (intersections == null) {
            return null;
        }
        Point closestPoint = intersections.get(0);
        for (Point p : intersections) {
            if (p.distance(this.start) < closestPoint.distance(this.start)) {
                closestPoint = p;
            }
        }
        return closestPoint;
    }
}