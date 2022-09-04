// ID: 208061911

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author Yohay Artzi
 * this class represent a Block which in the game consider as a colliadble object
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle block;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * .
     * Constructor
     *
     * @param rec - the rectangle
     * @param blockColor - color of the block
     */
    public Block(Rectangle rec, java.awt.Color blockColor) {
        block = rec;
        color = blockColor;
        hitListeners = new ArrayList<>();
    }

    /**
     * .
     * Constructor
     *
     * @param upperLeft - the upper left point of the rectangle
     * @param width - width of the rectangle
     * @param height - height of the rectangle
     * @param blockColor - color of the block
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color blockColor) {

        block = new Rectangle(upperLeft, width, height);
        color = blockColor;
        hitListeners = new ArrayList<>();
    }

    /**
     * .
     * Constructor
     *
     * @param upperLeftX - X value of the upper left point of the rectangle
     * @param upperLeftY - Y value of the upper left point of the rectangle
     * @param width - width of the rectangle
     * @param height - height of the rectangle
     * @param blockColor - color of the block
     */
    public Block(double upperLeftX, double upperLeftY, double width, double height, java.awt.Color blockColor) {

        block = new Rectangle(upperLeftX, upperLeftY, width, height);
        color = blockColor;
        hitListeners = new ArrayList<>();
    }

    /**
     * @param c - new color of the block
     */
    public void setColor(Color c) {
        color = c;
    }

    @Override
    public Rectangle getCollisionRectangle() {

        return block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        List<Line> collisionSides = block.whichSideIsOn(collisionPoint);
        this.notifyHit(hitter);

        // if hits corner
        if (collisionSides.size() > 1) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        Double slope = collisionSides.get(0).slope();
        if (slope == null) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    @Override
    public void drawOn(DrawSurface d) {

        if (this.color == Color.WHITE) {
            return;
        }

        d.setColor(this.color);
        d.fillRectangle((int) block.getUpperLeft().getX(), (int) block.getUpperLeft().getY(),
                (int) block.getWidth(), (int) block.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) block.getUpperLeft().getX(), (int) block.getUpperLeft().getY(),
                (int) block.getWidth(), (int) block.getHeight());
    }

    @Override
    public void timePassed() { }

    /**
     * @param gameLevel - the game the block is in
     * add the current block to the game
     */
    public void addToGame(GameLevel gameLevel) {

        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * @param gameLevel - the game the block is in
     * remove the current block from the game
     */
    public void removeFromGame(GameLevel gameLevel) {

        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * @param hitter - the ball that hit the current block
     * notify all listeners that a hit occurs
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
