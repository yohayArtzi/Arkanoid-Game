// ID: 208061911

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Yohay Artzi
 * this class represent an object that runs animations on the game
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * .
     * Constructor
     *
     * @param gui - the gui object that display the game
     */
    public AnimationRunner(GUI gui) {

        this.gui = gui;
        framesPerSecond = 60;

    }


    /**
     * @return number frames per seconds
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }


    /**
     * @param animation - animation to run on the game
     */
    public void run(Animation animation) {

        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (animation.shouldStop()) {
            return;
        }
        gui.close();
    }
}
