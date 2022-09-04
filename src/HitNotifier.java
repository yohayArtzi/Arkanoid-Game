// ID: 208061911

/**
 * @author Yohay Artzi
 * this interface represents a notifier of hit event
 */
public interface HitNotifier {

    /**
     * @param hl - HitListener object needed to be added to the list of listeners to hit events
     */
    void addHitListener(HitListener hl);

    /**
     * @param hl - HitListener object needed to be removed from list of listeners to hit events
     */
    void removeHitListener(HitListener hl);

}
