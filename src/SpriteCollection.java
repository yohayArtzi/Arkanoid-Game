//208061911

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author Yohay Artzi
 * this class represent the sprites objects
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * .
     * Constructor
     */
    public SpriteCollection() {

        List<Sprite> spritesList = new ArrayList<>();
        sprites = spritesList;
    }

    /**.
     * add the given sprite to the environment
     * @param s - sprite object needed to be added to the game
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**.
     * remove the given sprite from the environment
     * @param s - sprite object needed to be removed from the game
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**.
     * call timePassed() on all sprites
     */
    public void notifyAllTimePassed() {

        List<Sprite> tmpSprites = new ArrayList<>(sprites);
        for (Sprite s : tmpSprites) {
            s.timePassed();
        }
    }

    /**.
     * call drawOn(d) on all sprites
     * @param d - surface of the game
     */
    public void drawAllOn(DrawSurface d) {

        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
