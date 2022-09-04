// ID: 208061911

import biuoop.DrawSurface;

/**
 * @author Yohay Artzi
 * this interface represent sprites which are game objects that effect the process of the game
 */
public interface Sprite {

    /**.
     * @param d - surface to draw on
     * draw the sprite to the screen
     */
    void drawOn(DrawSurface d);

    /**.
     * changing positions of sprites according to some rules
     */
    void timePassed();
}
