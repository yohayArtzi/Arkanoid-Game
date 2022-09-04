// ID: 208061911

/**
 * @author Yohay Artzi
 * this interface represents collidable objects that in the game
 */
public interface Collidable {

    /**
     * @return collision shape of the block
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint - the collision point between collidable object and the ball trajectory
     * @param currentVelocity - currnet velocity of the ball
     * @param hitter - ball that makes the collision
     * @return new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
