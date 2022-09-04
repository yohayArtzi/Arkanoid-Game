// ID: 208061911

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Yohay Artzi
 * this class represent a count down before the game starts
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection sprites;
    private boolean stop = false;
    private int framesCounter;
    private double framesPerSecondes;
    private int figuresCounter;
    private String text;
    private Sprite background;
    private String levelName;

    /**
     * .
     * Constructor
     *
     * @param seconds - time for count down
     * @param countStart - number to count from
     * @param gameScreen - sprites to display the screen in the start of the game
     * @param frames - number frames per seconds
     * @param backgroundSprite - sprite for background
     * @param lvlName - level name
     */
    public CountdownAnimation(double seconds, int countStart, SpriteCollection gameScreen,
                              int frames, Sprite backgroundSprite, String lvlName) {
        numOfSeconds = seconds;
        countFrom = countStart;
        sprites = gameScreen;
        framesPerSecondes = frames;
        framesCounter = 0;
        figuresCounter = 0;
        text = String.valueOf(countFrom);
        background = backgroundSprite;
        levelName = lvlName;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        framesCounter++;
        double usedTime = framesCounter * (1.0 / framesPerSecondes), figureTime = numOfSeconds / countFrom;

        // drawing the sprites and the count down
        background.drawOn(d);
        this.sprites.drawAllOn(d);
        d.setColor(Color.BLACK);
        d.drawText(150, 18, "Level Name: " + levelName, 15);
        d.drawText(400, d.getHeight() / 2, text, 150);

        if (usedTime > figureTime) {
            figuresCounter++;
            if (figuresCounter == countFrom) {
                stop = true;
                return;
            }
            framesCounter = 0;
            text = String.valueOf(Integer.parseInt(text) - 1);
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
