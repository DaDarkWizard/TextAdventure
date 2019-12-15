import java.util.EventListener;

/**
 * Listens for frame updates
 * <p>
 * Date Last Modified: 12/10/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public interface FrameListener extends EventListener {
    /**
     * Handle the FrameEvent
     *
     * @param e the FrameEvent in question
     */
    void handle(FrameEvent e);
}
