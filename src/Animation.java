// ID: 208061911

import biuoop.DrawSurface;

/**
 * @author Yohay Artzi
 * this interface represents animation to run in the game
 */
public interface Animation {

    /**
     * @param d - DrawSurface object to draw on
     *          display one frame of the animation
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if animation should stop, false otherwise
     */
    boolean shouldStop();
}
