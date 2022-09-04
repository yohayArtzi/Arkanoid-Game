// ID: 208061911

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Yohay Artzi
 * this class represent the end screen of the end of the game, in failure situation
 */
public class GameOver implements Animation {

    private int finalScore;

    /**
     * @param score - score counter
     */
    public GameOver(Counter score) {
        finalScore = score.getValue();

    }

    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(Color.BLACK);
        d.drawText(215, d.getHeight() / 2 - 70, "Game Over", 70);
        d.drawText(250, d.getHeight() / 2 + 30, "Your score is " + finalScore, 40);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}

