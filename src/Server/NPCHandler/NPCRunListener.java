package Server.NPCHandler;

import java.util.EventListener;

/**
 * The Interface for the listener to handle an NPCRunEvent
 * <p>
 * Date Last Modified: 12/10/2019
 *
 * @author Daniel Masker, Joe Teahen, Emma Smith, Ben Hodsdon
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public interface NPCRunListener extends EventListener {
    void handle(NPCRunEvent event);
}
