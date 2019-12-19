package Server.NPCHandler;

import java.util.EventListener;

/**
 * The interface for FindTargetListeners
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public interface NPCFindTargetListener extends EventListener {
    void handle(NPCFindTargetEvent event);
}
