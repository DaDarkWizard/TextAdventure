package Server.PlayerHandler.UI;

import Server.PlayerHandler.UpdateEvent;

import java.util.EventListener;

/**
 * The interface for the updateListener
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public interface UpdateListener extends EventListener {
    void handle(UpdateEvent event);
}
