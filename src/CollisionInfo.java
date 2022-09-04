// ID: 208061911

/**
 * @author Yohay Artzi
 * this class represent a collision information, means collision point between collidable
 * object and the ball, and the collidable object itself
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * .
     * Constructor
     *
     * @param c - the collidable object
     * @param trajectory - trajectory of the ball
     */
    public CollisionInfo(Collidable c, Line trajectory) {

        collidableObject = c;
        if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) == null) {
            collisionPoint = null;
            return;
        }
        Point collision = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
        collisionPoint = new Point(collision.getX(), collision.getY());
    }

    /**
     * .
     * Constructor
     *
     * @param c - the collidable object
     * @param p - collision point
     */
    public CollisionInfo(Collidable c, Point p) {
        collisionPoint = new Point(p.getX(), p.getY());
        collidableObject = c;
    }

    /**
     * @return collision point between collidable object and the ball
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return collision object which the ball is goint to hit
     */
    public Collidable collisionObject() {
        return collidableObject;
    }

}
