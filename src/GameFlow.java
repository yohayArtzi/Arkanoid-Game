// ID: 208061911

import biuoop.KeyboardSensor;
import java.util.List;

/**
 * @author Yohay Artzi
 * this class represent the game process
 */
public class GameFlow {

    private KeyboardSensor keyboard;
    private AnimationRunner animationRunner;
    private Counter score = new Counter(0);

    /**
     * .
     * Constructor
     *
     * @param animation - object to run the animations of the games on
     * @param ks - key board sensor
     */
    public GameFlow(AnimationRunner animation, KeyboardSensor ks) {

        animationRunner = animation;
        keyboard = ks;
    }

    /**
     * @param levels - the levels of the game
     */
    public void runLevels(List<LevelInformation> levels) {

        // create levels and add them to a list
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, score, this.keyboard, this.animationRunner);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (!level.isLeftBalls()) {
                Animation gameOver = new GameOver(score);
                Animation gameOverWrapper = new KeyPressStoppableAnimation(keyboard, "space", gameOver);
                animationRunner.run(gameOverWrapper);
                return;
            }
        }
        Animation youWin = new YouWin(score);
        Animation youWinWrapper = new KeyPressStoppableAnimation(keyboard, "space", youWin);
        animationRunner.run(youWinWrapper);
    }

}
