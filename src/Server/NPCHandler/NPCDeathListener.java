package Server.NPCHandler;

import java.util.EventListener;

/**
 * The interface for NPCDeathListener
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public interface NPCDeathListener extends EventListener {
    void handle(NPCDeathEvent e);
}
