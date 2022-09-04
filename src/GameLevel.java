// ID: 208061911

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Yohay Artzi
 * this class represent the main components that creates the game
 */
public class GameLevel implements Animation {

    private static final int NUM_OF_SECONDS = 2;
    private static final int COUNT_FROM = 3;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInfo;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private KeyboardSensor keyboard;

    /**
     * .
     * Constructor
     *
     * @param animationRunner - object to run the animations of the games on
     * @param levelInformation - current level
     * @param k - key board sensor
     * @param scoreCounter - score counter
     */
    public GameLevel(LevelInformation levelInformation, Counter scoreCounter,
                     KeyboardSensor k, AnimationRunner animationRunner) {

        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        runner = animationRunner;
        levelInfo = levelInformation;
        remainingBlocks = new Counter(levelInfo.numberOfBlocksToRemove());
        remainingBalls = new Counter(levelInformation.numberOfBalls());
        score = scoreCounter;
        keyboard = k;
        running = true;
    }

    /**
     * @param c - collidable object needed to be added to the game
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * @param s - sprite object needed to be added to the game
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * @param c - collidable object needed to be removed from the game
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * @param s - sprite object needed to be removed from the game
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * initialize a new game: create the Blocks and Ball and Paddle
     * and add them to the game.
     */
    public void initialize() {

        generateFrame();

        // create paddle and add it to game
        Paddle paddle = new Paddle(keyboard, levelInfo.paddleWidth(), levelInfo.paddleSpeed());
        paddle.addToGame(this);

        // create score indicator and add it to game
        Sprite scoreIndicator = new ScoreIndicator(score);
        addSprite(scoreIndicator);

        // add listeners to blocks and add the blocks to the game
        ArrayList<Double> rowsHeights = new ArrayList<>();
        ArrayList<Counter> rows = new ArrayList<>();
        for (Block block : levelInfo.blocks()) {
            double heightOfBlock = block.getCollisionRectangle().getUpperLeft().getY();
            if (!rowsHeights.contains(heightOfBlock)) {
                rowsHeights.add(heightOfBlock);
                Counter remainingBlocksInRow = new Counter(0);
                rows.add(remainingBlocksInRow);
            }
            int index = rowsHeights.indexOf(heightOfBlock);
            rows.get(index).increase(1);
            HitListener scoreTracking = new ScoreTrackingListener(score, rows.get(index));
            block.addHitListener(scoreTracking);

            HitListener blockRemover = new BlockRemover(this, remainingBlocks);
            block.addHitListener(blockRemover);
            block.addToGame(this);
        }

        // create balls and add them to the game
        int i = 0;
        for (; i < levelInfo.numberOfBalls(); i++) {
            Velocity v = levelInfo.initialBallVelocities().get(i);
            Ball ball = new Ball(300 - (i * 50), 350 - (i * 50), 5, v, Color.red, this.environment);
            ball.addToGame(this);
        }
    }

    /**.
     * generates blocks for the frame of the window and add them to the game
     */
    private void generateFrame() {

        Block topBlock = new Block(new Rectangle(new Point(20, 0), 760, 40), Color.gray);
        Block downBlock = new Block(new Rectangle(new Point(20, 560), 760, 40), Color.WHITE);
        Block rightBlock = new Block(new Rectangle(new Point(0, 0), 40, 600), Color.gray);
        Block leftBlock = new Block(new Rectangle(new Point(760, 0), 40, 600), Color.gray);
        Block deathRegion = new Block(new Rectangle(new Point(40, 559.5), 720, 0.5), Color.WHITE);
        HitListener ballRemover = new BallRemover(this, remainingBalls);
        deathRegion.addHitListener(ballRemover);

        topBlock.addToGame(this);
        downBlock.addToGame(this);
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);
        deathRegion.addToGame(this);
    }

    /**
     * .
     * Run the game - starts the animation loop
     */
    public void run() {

        this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, sprites,
                runner.getFramesPerSecond(), levelInfo.getBackground(), levelInfo.levelName()));
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface drawSurface) {

        levelInfo.getBackground().drawOn(drawSurface);
        this.sprites.drawAllOn(drawSurface);
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawText(150, 18, "Level Name: " + levelInfo.levelName(), 15);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen();
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", pauseScreen));
        }
        if (remainingBalls.getValue() == 0) {
            running = false;
        }
        if (remainingBlocks.getValue() == 0) {
            running = false;
        }
    }

    @Override
    public boolean shouldStop() {

        return !running;
    }

    /**
     * @return false if there's no balls in the game, true otherwise
     */
    public boolean isLeftBalls() {
        if (remainingBalls.getValue() != 0) {
            return true;
        }
        return false;
    }
}