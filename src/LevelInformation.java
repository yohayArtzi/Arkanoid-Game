// ID: 208061911

import java.util.List;

/**
 * @author Yohay Artzi
 * this interface represents a level in the game
 */
public interface LevelInformation {

    /**
     * @return - number of balls in the level
     */
    int numberOfBalls();

    /**
     * @return - list of velocities of balls in the level
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return - paddle speed
     */
    int paddleSpeed();

    /**
     * @return - paddle width
     */
    int paddleWidth();

    /**
     * @return - level name
     */
    String levelName();

    /**
     * @return - sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return - list of blocks in the level
     */
    List<Block> blocks();

    /**
     * @return - number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
