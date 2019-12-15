package PlayerHandler;

import java.util.EventObject;
/**
 * This class makes a UpdateEvent
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Joe Teahen, Emma Smith
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class UpdateEvent extends EventObject {
    private Player source;// player that triggered this event

    /**
     * Constructs the Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public UpdateEvent(Player source) {
        super(source);
        this.source = source;
    }

    /**
     * Gets the source player
     *
     * @return the Player that needs the update
     */
    @Override
    public Player getSource() {
        return source;
    }
}
