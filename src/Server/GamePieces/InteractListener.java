package Server.GamePieces;

import java.util.EventListener;

/**
 * This interface for interactlistener
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public interface InteractListener extends EventListener {
    String handle(InteractEvent e);
}
