// ID: 208061911

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yohay Artzi
 * this class represent a rectangle with upper left point, width and height
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * .
     * Constructor
     *
     * @param upperLeftP - the upper left point of the rectangle
     * @param widthR - width of the rectangle
     * @param heightR - height of the rectangle
     */
    public Rectangle(Point upperLeftP, double widthR, double heightR) {

        upperLeft = new Point(upperLeftP.getX(), upperLeftP.getY());
        width = widthR;
        height = heightR;
    }

    /**
     * .
     * Constructor
     *
     * @param upperLeftX - X value of the upper left point of the rectangle
     * @param upperLeftY - Y value of the upper left point of the rectangle
     * @param widthR - width of the rectangle
     * @param heightR - height of the rectangle
     */
    public Rectangle(double upperLeftX, double upperLeftY, double widthR, double heightR) {

        upperLeft = new Point(upperLeftX, upperLeftY);
        width = widthR;
        height = heightR;
    }

    /**
     * @param x - new x value of upper left point
     * @param y - new y value of upper left point
     */
    public void setUpperLeft(double x, double y) {
        upperLeft = new Point(x, y);
    }

    /**
     * @param line - line to calculate intersection point with rectangle
     * @return list of intersection points between the line and rectangle
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        List<Point> intersectionPoints = new ArrayList<>();
        Point p1 = upperLeft, p2 = new Point(upperLeft.getX() + width, upperLeft.getY()),
                p3 = new Point(upperLeft.getX() + width, upperLeft.getY() + height),
                p4 = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Line l1 = new Line(p1, p2), l2 = new Line(p2, p3), l3 = new Line(p3, p4), l4 = new Line(p4, p1);

        if (l1.isIntersecting(line)) {
            intersectionPoints.add(l1.intersectionWith(line));
        }

        List<Line> sides = new ArrayList<>();
        sides.add(l2);
        sides.add(l3);
        sides.add(l4);

        for (int i = 0; i < 3; i++) {
            if (sides.get(i).isIntersecting(line)) {
                intersectionPoints.add(sides.get(i).intersectionWith(line));
                }
        }
        for (int i = 1; i < intersectionPoints.size(); i++) {
            if (intersectionPoints.get(i).equalsApproximation(intersectionPoints.get(i - 1))) {
                intersectionPoints.remove(i);
                i--;
            }
        }
        if (intersectionPoints.size() != 0) {
            return intersectionPoints;
        }
        return null;
    }

    /**
     * @return width of rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return height of rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @param p - point to check if it's on rectangle sides
     * @return true if the point is on rectangle sides, false otherwise
     */
    public boolean isInSides(Point p) {
        Point p1 = upperLeft, p2 = new Point(upperLeft.getX() + width, upperLeft.getY()),
                p3 = new Point(upperLeft.getX() + width, upperLeft.getY() + height),
                p4 = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Line l1 = new Line(p1, p2), l2 = new Line(p2, p3), l3 = new Line(p3, p4), l4 = new Line(p4, p1);

        if (l1.isInLine(p) || l2.isInLine(p) || l3.isInLine(p) || l4.isInLine(p)) {
            return true;
        }
        return false;
    }

    /**
     * @param p - point to check which side it's on rectangle sides
     * @return the side which the point is on rectangle
     */
    public List<Line> whichSideIsOn(Point p) {

        if (!this.isInSides(p)) {
            return null;
        }

        Point p1 = upperLeft, p2 = new Point(upperLeft.getX() + width, upperLeft.getY()),
                p3 = new Point(upperLeft.getX() + width, upperLeft.getY() + height),
                p4 = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Line l1 = new Line(p1, p2), l2 = new Line(p2, p3), l3 = new Line(p3, p4), l4 = new Line(p4, p1);
        List<Line> sides = new ArrayList<>();
        sides.add(l1);
        sides.add(l2);
        sides.add(l3);
        sides.add(l4);

        int i = 0;
        while (i < sides.size()) {
            if (!sides.get(i).isInLine(p)) {
                sides.remove(i);
            } else {
                i++;
            }
        }
        return sides;
    }
}
