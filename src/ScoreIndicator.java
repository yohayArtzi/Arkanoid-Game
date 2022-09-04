// ID: 208061911

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Yohay Artzi
 * this class represents a score indicator of the score the player gain during the game
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreIndicator;
    private Rectangle rec;
    private Color color;

    /**
     * .
     * Constructor
     *
     * @param score - the score object of the game
     */
    public ScoreIndicator(Counter score) {
        scoreIndicator = score;
        rec = new Rectangle(new Point(0, 0), 800, 25);
        color = Color.WHITE;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
        d.drawText(550, 18, "Score: " + scoreIndicator.getValue(), 15);
    }

    @Override
    public void timePassed() { }
}
