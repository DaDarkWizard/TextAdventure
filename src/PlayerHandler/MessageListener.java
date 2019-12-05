package PlayerHandler;

import java.util.EventListener;

public interface MessageListener extends EventListener {
    void handle(MessageEvent e);
}
