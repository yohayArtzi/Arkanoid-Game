// ID: 208061911

/**
 * @author Yohay Artzi
 * this class represents a counter
 */
public class Counter {

    private int counter;

    /**
     * .
     * Constructor
     *
     * @param num - number to initialize the counter to
     */
    public Counter(int num) {
        counter = num;
    }

    /**
     * @param number - add number to current count
     */
    void increase(int number) {
        counter += number;
    }

    /**
     * @param number - decrease number from counter
     */
    void decrease(int number) {
        counter -= number;
    }

    /**
     * @return current count
     */
    int getValue() {
        return counter;
    }

}
