package GamePieces;

import java.util.EventListener;

public interface InteractListener extends EventListener {
    String handle(InteractEvent e);
}
