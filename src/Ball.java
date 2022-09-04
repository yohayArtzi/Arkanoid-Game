// ID: 208061911

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Yohay Artzi
 * this class represent a ball with a radius, color, and speed
 */
public class Ball implements Sprite {

    private static final double DEFAULT_VELOCITY = 5.0;

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameE;

    /**
     * .
     * Constructor
     *
     * @param center - the point in the center of the ball
     * @param r -  the radius of the ball
     * @param color - the color of the ball
     * @param v - velocity of the ball
     * @param gameEnvironment - the game environment the ball is in
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity v, GameEnvironment gameEnvironment) {

        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(v.getDx(), v.getDy());
        gameE = gameEnvironment;
    }

    /**
     * .
     * Constructor
     *
     * @param center - the point in the center of the ball
     * @param r -  the radius of the ball
     * @param color - the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {

        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(DEFAULT_VELOCITY, DEFAULT_VELOCITY);
        gameE = null;
    }

    /**
     * .
     * Constructor
     *
     * @param x - the x coordinate of the center point
     * @param y - the y coordinate of the center point
     * @param r -  the radius of the ball
     * @param color - the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {

        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(DEFAULT_VELOCITY, DEFAULT_VELOCITY);
        gameE = null;
    }

    /**
     * .
     * Constructor
     *
     * @param x - the x coordinate of the center point
     * @param y - the y coordinate of the center point
     * @param r -  the radius of the ball
     * @param color - the color of the ball
     * @param gameEnvironment - the game environment the ball is in
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {

        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(DEFAULT_VELOCITY, DEFAULT_VELOCITY);
        gameE = gameEnvironment;
    }

    /**
     * .
     * Constructor
     *
     * @param x - the x coordinate of the center point
     * @param y - the y coordinate of the center point
     * @param r -  the radius of the ball
     * @param color - the color of the ball
     * @param v - velocity of the ball
     * @param gameEnvironment - the game environment the ball is in
     */
    public Ball(double x, double y, int r, Velocity v, java.awt.Color color, GameEnvironment gameEnvironment) {

        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = v;
        gameE = gameEnvironment;
    }

    /**
     * @return the x coordinate of the center point
     */
    public int getX() {

        return (int) this.center.getX();
    }

    /**
     * @return the y coordinate of the center point
     */
    public int getY() {

        return (int) this.center.getY();
    }

    /**
     * @return center point of the ball
     */
    public Point getCenter() {

        return center;
    }

    /**
     * @return the circle area of the ball
     */
    public double circleArea() {

        return Math.PI * Math.pow(r, 2.0);
    }

    /**
     * @return the radius of the ball
     */
    public int getSize() {

        return r;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {

        return this.color;
    }

    /**
     * @param c - new color of the ball
     */
    public void setColor(Color c) {
        color = c;
    }

    /**
     * @param v - the requested velocity of the ball
     */
    public void setVelocity(Velocity v) {

        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * @param dx - the requested progress of the ball in axis x
     * @param dy - the requested progress of the ball in axis y
     */
    public void setVelocity(double dx, double dy) {

        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {

        return this.velocity;
    }

    /**.
     * this method moving the current ball one step according to velocity
     */
    public void moveOneStep() {

        Line trajectory = new Line(center, this.getVelocity().applyToPoint(center));
        CollisionInfo collision = this.gameE.getClosestCollision(trajectory);

        // if there's no collision
        if (collision == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            this.setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), getVelocity()));
        }
    }


    @Override
    public void drawOn(DrawSurface surface) {

        if (color == Color.WHITE) {
            return;
        }
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    @Override
    public void timePassed() {

       this.moveOneStep();
    }

    /**
     * @param gameLevel - the game that the ball is in
     * adding ball to game objects
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * @param gameLevel - the game that the ball is in
     * removing the ball from the game objects
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}