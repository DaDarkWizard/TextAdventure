package Server;

import java.util.EventObject;

/**
 * Server.FrameEvent for updating player
 * <p>
 * Date Last Modified: 12/5/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class FrameEvent extends EventObject {
    /**
     * Constructor
     *
     * @param source object that initialized this
     */
    public FrameEvent(Object source) {
        super(source);
    }
}
