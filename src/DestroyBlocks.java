// ID: 208061911

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yohay Artzi
 * this class represent a level in the game
 */
public class DestroyBlocks implements LevelInformation {

    private static final double BLOCK_WIDTH = 45.0;
    private static final double BLOCK_HEIGHT = 25.0;
    private static final int NUM_OF_ROWS = 6;


    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        int i = 0;
        for (; i < numberOfBalls(); i++) {
            Velocity v = new Velocity(5, 5);
            velocities.add(v);
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Destroy Blocks";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0),
                800, 600), Color.DARK_GRAY);
    }

    @Override
    public List<Block> blocks() {

        List<Block> blocks = new LinkedList<>();
        ArrayList<Color> colors = generateColors();
        Block lastBlock = new Block(new Point(175, 150), BLOCK_WIDTH, BLOCK_HEIGHT, colors.get(0));
        int i, j, numOfBlocks = 12;

        /*
         * for rows of the blocks
         */
        for (i = 0; i < NUM_OF_ROWS; i++) {

            Counter remainingBlocksInRow = new Counter(0);
            /*
             * generate blocks to specific row
             */
            for (j = 0; j < numOfBlocks; j++) {
                double upperLeftXValue = lastBlock.getCollisionRectangle().getUpperLeft().getX(),
                        upperLeftYValue = lastBlock.getCollisionRectangle().getUpperLeft().getY();
                Point newUpperLeft = new Point(upperLeftXValue + BLOCK_WIDTH, upperLeftYValue);
                Block block = new Block(newUpperLeft, BLOCK_WIDTH, BLOCK_HEIGHT, colors.get(i));
                lastBlock = new Block(newUpperLeft, BLOCK_WIDTH, BLOCK_HEIGHT, colors.get(i));
                blocks.add(block);
            }
            double upperLeftXValue = lastBlock.getCollisionRectangle().getUpperLeft().getX(),
                    upperLeftYValue = lastBlock.getCollisionRectangle().getUpperLeft().getY();
            lastBlock.getCollisionRectangle().setUpperLeft(upperLeftXValue - (j - 1) * BLOCK_WIDTH,
                    upperLeftYValue + BLOCK_HEIGHT);
            numOfBlocks--;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }


    /**
     * .
     * generates colors for the blocks
     *
     * @return list of colors
     */
    private ArrayList<Color> generateColors() {

        Color color1 = new Color(221, 65, 36);
        Color color2 = new Color(214, 80, 118);
        Color color3 = new Color(68, 184, 172);
        Color color4 = new Color(239, 192, 80);
        Color color5 = new Color(91, 94, 166);
        Color color6 = new Color(155, 35, 53);

        ArrayList<Color> colors = new ArrayList<>();
        colors.add(color1);
        colors.add(color2);
        colors.add(color3);
        colors.add(color4);
        colors.add(color5);
        colors.add(color6);

        return colors;
    }
}
