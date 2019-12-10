package NPCHandler;

import java.util.EventListener;

/**
 * Event listener for an npc finding more creatures
 * <p>
 * Date Last Modified: 12/10/2019
 *
 * @author Daniel Masker, Joe Teahen, Emma Smith, Ben Hodsdon
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public interface NPCMeetSomeoneListener extends EventListener {
    void handle(NPCMeetSomeoneEvent event);
}
