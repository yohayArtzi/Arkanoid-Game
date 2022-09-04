// ID: 208061911

/**
 * @author Yohay Artzi
 * this class represent velocity as prograss in axis x, and axis y
 */
public class Velocity {

    private static final double EPSILON = 1.0E-4;

    private double dx;
    private double dy;

    /**
     * .
     * Constructor
     *
     * @param dx - progress of the ball in axis x
     * @param dy - progress of the ball in axis y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the progress of the ball in axis x
     */
    public double getDx() {

        return this.dx;
    }

    /**
     * @return the progress of the ball in axis y
     */
    public double getDy() {

        return this.dy;
    }

    /**
     * @param dx1 - the requested progress of the ball in axis x
     * @param dy1 - the requested progress of the ball in axis y
     */
    public void setVelocity(double dx1, double dy1) {
        this.dx = dx1;
        this.dy = dy1;
    }

    /**
     * @param dx1 - the requested progress of the ball in axis x
     *            change progress of the ball in axis x
     */
    public void setDx(double dx1) {

        this.dx = dx1;
    }

    /**
     * @param dy1 - the requested progress of the ball in axis y
     *            change progress of the ball in axis y
     */
    public void setDy(double dy1) {

        this.dy = dy;
    }

    /**
     * @param angle - the requested angle of the progress of the ball
     * @param speed - the requested speed of the ball
     * @return velocity object by requested speed and angle
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        double radianAngle = Math.toRadians(angle), rangedAngle = radianAngle % Math.PI, dx, dy;

        dx = Math.abs(speed * Math.sin(rangedAngle));
        dy = Math.abs(speed * Math.cos(rangedAngle));

        if (angle <= 90) {
            dy = -dy;
        } else if (angle <= 180) {
            return new Velocity(dx, dy);
        } else if (angle <= 270) {
            dx = -dx;
        } else {
            dx = -dx;
            dy = -dy;
        }
        return new Velocity(dx, dy);
    }

    /**
     * @param p     - the center point of the ball
     * @return next point of the ball according to his velocity
     */
    public Point applyToPoint(Point p) {

        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * @return the speed of the ball
     */
    public double getSpeed() {

        if (dy == 0.0) {
            return Math.abs(dx);
        }

        double angleInRadians = Math.atan(dx / dy);
        angleInRadians = angleInRadians % Math.PI;

        return Math.abs(dy / Math.cos(angleInRadians));
    }
}