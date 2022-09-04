// ID: 208061911

/**
 * @author Yohay Artzi
 * this class represent point in 2D: every object has an X value and an Y value.
 */
public class Point {

    private static final double EPSILON = 1.0E-4;

    private double x;
    private double y;

    /**
     * .
     * Constructor
     *
     * @param x - the x coordinate of the point
     * @param y - the y coordinate of the point
     */
    public Point(double x, double y) {

        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate of the point
     */
    public double getX() {

        return this.x;
    }

    /**
     * @return the y coordinate of the point
     */
    public double getY() {

        return this.y;
    }

    /**
     * @param newX - new value for x
     */
    public void setX(double newX) {

        x = newX;
    }

    /**
     * @param newY - new value for y
     */
    public void setY(double newY) {

        y = newY;
    }

    /**
     * @param other - another point
     * @return true if the points are equals, false otherwise
     */
    public boolean equals(Point other) {

        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * @param other - another point
     * @return the distance between the points
     */
    public double distance(Point other) {

        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
    }

    /**
     * @param other - another point
     * @return true if the points are equal in approximation, false otherwise
     */
    public boolean equalsApproximation(Point other) {

        if (Math.abs(x - other.getX()) < EPSILON && Math.abs(y - other.getY()) < EPSILON) {
            return true;
        }
        return false;
    }
}