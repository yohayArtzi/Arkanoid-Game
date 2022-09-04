// ID: 208061911

import java.awt.Color;

/**
 * @author Yohay Artzi
 * this class represents a listener that is in charge of removing blocks from the game, as well as keeping count
 * // of the number of blocks that remain
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * .
     * Constructor
     *
     * @param g - the game
     * @param c - counter of remaining blocks
     */
    public BlockRemover(GameLevel g, Counter c) {

        gameLevel = g;
        remainingBlocks = c;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
        beingHit.setColor(Color.WHITE);
    }
}