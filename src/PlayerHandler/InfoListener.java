package PlayerHandler;

import java.util.EventListener;

public interface InfoListener extends EventListener {
    void handle(InfoEvent e);
}
