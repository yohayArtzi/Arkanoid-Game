// ID: 208061911

import java.awt.Color;

/**
 * @author Yohay Artzi
 * this class represents a listener that removes a ball when it hit the bottom of the screen
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * .
     * Constructor
     *
     * @param g - the game
     * @param c - counter of remaining balls
     */
    public BallRemover(GameLevel g, Counter c) {

        gameLevel = g;
        remainingBalls = c;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        hitter.setColor(Color.WHITE);
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);

    }
}
