package PlayerHandler;

import java.util.EventObject;

/**
 * This class makes a Message Event
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Joe Teahen, Emma Smith
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class MessageEvent extends EventObject {
    private Player receiver; //the message receiver
    private String message; //the message

    /**
     * Message Event Constructor
     * @param source the source of the message
     * @param receiver the receiver of the message
     * @param message the message to sent
     */
    public MessageEvent(Player source, Player receiver, String message) {
        super(source);
        this.source = source;
        this.receiver = receiver;
        this.message = message;
    }

    /**
     * Gets the receiver
     * @return the receiver
     */
    public Player getClient() {
        return this.receiver;
    }

    /**
     * Gets the message
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }
}
