// ID: 208061911

import java.util.List;
import java.util.ArrayList;

/**
 * @author Yohay Artzi
 * this class represent the game enviornment which includes collidable objects
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * .
     * Constructor
     */
    public GameEnvironment() {
        List<Collidable> collidable = new ArrayList<>();
        this.collidables = collidable;
    }

    /**.
     * add the given collidable to the environment
     * @param c - collidable object needed to be added to the game
     */
    public void addCollidable(Collidable c) {

        collidables.add(c);
    }

    /**.
     * remove the given collidable from the environment
     * @param c - collidable object needed to be removed from the game
     */
    public void removeCollidable(Collidable c) {

        collidables.remove(c);
    }

    /**.
     * add the given collidable to the environment
     * @param trajectory - the trajectory of the ball
     * @return return the information about the closest collision that is going to occur,
     * null if none is going to occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        List<CollisionInfo> possibleCollisions = new ArrayList<>();

        /*
         * going over the collidable objects in the game, and checks if the ball is going to hit them
         */
        for (Collidable c : collidables) {
            CollisionInfo collisionInfo = new CollisionInfo(c, trajectory);
            if (collisionInfo.collisionPoint() == null) {
                continue;
            }
            possibleCollisions.add(collisionInfo);
            if (possibleCollisions.size() < 2) {
                continue;
            }
            if (possibleCollisions.get(1).collisionPoint().distance(trajectory.start())
                    < possibleCollisions.get(0).collisionPoint().distance(trajectory.start())) {
                possibleCollisions.remove(0);
            } else {
                possibleCollisions.remove(1);
            }
        }

        if (possibleCollisions.size() == 0) {
            return null;
        }
        return possibleCollisions.get(0);
    }
}
