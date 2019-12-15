package PlayerHandler;

import java.util.EventListener;
/**
 * Interface for severcommandlistener
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Joe Teahen, Emma Smith
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public interface ServerCommandListener extends EventListener {
    void handle(ServerCommandEvent e);
}
