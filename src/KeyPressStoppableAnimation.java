// ID: 208061911

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yohay Artzi
 * this class taking care of key presses from the user
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop = false;
    private boolean isAlreadyPressed = true;

    /**
     * .
     * Constructor
     *
     * @param anim      - object to run the animations of the games on
     * @param sensor    - key board sensor
     * @param keyString - string of the key
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String keyString, Animation anim) {

        keyboard = sensor;
        key = keyString;
        animation = anim;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        animation.doOneFrame(d);
        if (keyboard.isPressed(key) && isAlreadyPressed) {
            return;
        } else if (keyboard.isPressed(key)) {
            stop = true;
        }
        isAlreadyPressed = false;
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
