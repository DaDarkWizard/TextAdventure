package PlayerHandler;

import java.util.EventListener;
/**
 * Interface for InfoListeners
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Joe Teahen, Emma Smith, Ben Hodsdon
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public interface InfoListener extends EventListener {
    void handle(InfoEvent e);
}
