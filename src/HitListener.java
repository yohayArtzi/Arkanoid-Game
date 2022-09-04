// ID: 208061911

/**
 * @author Yohay Artzi
 * this interface represents a litsener of hit event
 */
public interface HitListener {

    /**
     * @param beingHit - the block that being hit
     * @param hitter - the hitting ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
