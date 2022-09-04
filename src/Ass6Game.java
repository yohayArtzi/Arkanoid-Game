// ID: 208061911

import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yohay Artzi
 * this class runs the game, choosing levels according to arguments
 */
public class Ass6Game {

    private static final int WIDTH_OF_WINDOW = 800;
    private static final int HEIGHT_OF_WINDOW = 600;

    /**
     * @param args - numbers of levels to run, by the given order
     *             activate the game
     */
    public static void main(String[] args) {

        // create objects for the game
        GUI gui = new GUI("Game", WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        KeyboardSensor keyBoard = gui.getKeyboardSensor();
        List<LevelInformation> levels = new LinkedList<>();
        GameFlow game = new GameFlow(animationRunner, keyBoard);

        // create list of levels, by order
        LevelInformation lvl1 = new DestroyBlocks();
        LevelInformation lvl2 = new Level2();
        LevelInformation lvl3 = new Level3();
        LevelInformation lvl4 = new Level4();
        levels.add(lvl1);
        levels.add(lvl2);
        levels.add(lvl3);
        levels.add(lvl4);

        // organize levels by arguments
        List<LevelInformation> levelsByArgs = new LinkedList<>();
        for (String str : args) {
            int levelNum;
            try {
                levelNum = Integer.parseInt(str);
            } catch (NumberFormatException exception) {
                continue;
            }
            if (levelNum >= 1 && levelNum <= 4) {
                levelsByArgs.add(levels.get(levelNum - 1));
            }
        }
        if (levelsByArgs.isEmpty()) {
            levelsByArgs = levels;
        }

        // run game
        game.runLevels(levelsByArgs);
        gui.close();
    }
}