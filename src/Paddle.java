//208061911

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * @author Yohay Artzi
 * this class represent the paddle that in the game
 */
public class Paddle implements Sprite, Collidable {

    private static final int WIDTH_OF_WINDOW = 800;
    private static final int WIDTH_OF_FRAME_BLOCK = 40;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private java.awt.Color color;
    private int speed;

    /**
     * .
     * Constructor
     *
     * @param keyboardSensor - the keyboard sensor for paddle
     */
    public Paddle(KeyboardSensor keyboardSensor) {

        paddle = new Rectangle(380.0, 540.0, 100.0, 20.0);
        color = Color.black;
        keyboard = keyboardSensor;
        speed = 10;
    }

    /**
     * .
     * Constructor
     *
     * @param paddleSpeed - speed of paddle
     * @param width - width of paddle
     * @param keyboardSensor - the keyboard sensor for paddle
     */
    public Paddle(KeyboardSensor keyboardSensor, int width, int paddleSpeed) {

        paddle = new Rectangle(380.0, 540.0, width, 20.0);
        color = Color.black;
        keyboard = keyboardSensor;
        speed = paddleSpeed;
    }

    /**
     * .
     * moving the paddle to the left
     */
    public void moveLeft() {

        if (paddle.getUpperLeft().getX() - speed <= WIDTH_OF_FRAME_BLOCK) {
            paddle.setUpperLeft(WIDTH_OF_FRAME_BLOCK, paddle.getUpperLeft().getY());
            return;
        }
        paddle.setUpperLeft(paddle.getUpperLeft().getX() - speed, paddle.getUpperLeft().getY());
    }

    /**
     * .
     * moving the paddle to the right
     */

    public void moveRight() {

        if (paddle.getUpperLeft().getX() + speed + paddle.getWidth()
                >= WIDTH_OF_WINDOW - WIDTH_OF_FRAME_BLOCK) {
            paddle.setUpperLeft(WIDTH_OF_WINDOW - WIDTH_OF_FRAME_BLOCK - paddle.getWidth(),
                    paddle.getUpperLeft().getY());
            return;
        }
        paddle.setUpperLeft(paddle.getUpperLeft().getX() + speed, paddle.getUpperLeft().getY());
    }

    @Override
    public void timePassed() {

        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }

    }

    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(this.color);
        d.fillRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY(),
                (int) paddle.getWidth(), (int) paddle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {

        return paddle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        List<Line> collisionSides = paddle.whichSideIsOn(collisionPoint);

        if (collisionSides == null) {
            throw new RuntimeException("point is not on sides of rectangle");
        }
        // if hits the corner
        if (collisionSides.size() > 1) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        Double slope = collisionSides.get(0).slope();

        // if hits paddle in right or left sides
        if (slope == null) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        double xValue = paddle.getUpperLeft().getX(), yValue = paddle.getUpperLeft().getY(),
                width = paddle.getWidth();
        Line l1 = new Line(paddle.getUpperLeft(), new Point(xValue + (width / 5.0), yValue));
        Line l2 = new Line(xValue + (width / 5.0), yValue, xValue + ((2 * width) / 5.0), yValue);
        Line l3 = new Line(xValue + ((2 * width) / 5.0), yValue, xValue + ((3 * width) / 5.0), yValue);
        Line l4 = new Line(xValue + ((3 * width) / 5.0), yValue, xValue + ((4 * width) / 5.0), yValue);
        Line l5 = new Line(xValue + ((4 * width) / 5.0), yValue, xValue + width, yValue);

        if (l3.isInLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (l1.isInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (l5.isInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        if (l2.isInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
    }


    /**
     * .
     * adding the paddle to the game
     *
     * @param gameLevel - the game the paddle is in
     */
    public void addToGame(GameLevel gameLevel) {

        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
}


