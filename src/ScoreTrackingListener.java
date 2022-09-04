// ID: 208061911

/**
 * @author Yohay Artzi
 * this class compute the score the player gain during the game
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;
    private Counter remainingBlocksInRow;

    /**
     * .
     * Constructor
     *
     * @param scoreCounter - the score object of the game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
        this.remainingBlocksInRow = new Counter(0);
    }

    /**
     * @param scoreCounter - the score object of the game
     * @param blocksInRowCounter - counter of remaining blocks in the row
     */
    public ScoreTrackingListener(Counter scoreCounter, Counter blocksInRowCounter) {
        this.currentScore = scoreCounter;
        this.remainingBlocksInRow = blocksInRowCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

       currentScore.increase(5);
       remainingBlocksInRow.decrease(1);
       if (remainingBlocksInRow.getValue() == 0) {
           currentScore.increase(100);
       }
    }
}