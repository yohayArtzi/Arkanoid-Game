// ID: 208061911

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yohay Artzi
 * this class represent the end screen of the end of the game, in winning situation
 */
public class YouWin implements Animation {

    private int finalScore;

    /**
     * @param score - score counter
     */
    public YouWin(Counter score) {
        finalScore = score.getValue();
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(Color.BLACK);
        d.drawText(270, d.getHeight() / 2 - 70, "You Win!", 70);
        d.drawText(250, d.getHeight() / 2 + 30, "Your score is " + finalScore, 40);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}


