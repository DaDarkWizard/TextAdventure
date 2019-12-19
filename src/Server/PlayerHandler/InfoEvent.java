package Server.PlayerHandler;

import java.util.EventObject;

/**
 * Event for sending messages to the player
 * <p>
 * Date Last Modified: 12/10/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Joe Teahen, Emma Smith
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class InfoEvent extends EventObject {

    private String message; //Message to send the player
    private Player source;  //Player the message is being sent to

    /**
     * Constructor for the InfoEvent
     *
     * @param source  Player to send the message to
     * @param message Message to send the player
     */
    public InfoEvent(Player source, String message) {
        super(source);
        this.source = source;
        this.message = message;
    }

    @Override
    public Player getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }
}
