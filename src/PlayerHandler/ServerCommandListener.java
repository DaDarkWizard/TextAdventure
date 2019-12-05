package PlayerHandler;

import java.util.EventListener;

public interface ServerCommandListener extends EventListener {
    void handle(ServerCommandEvent e);
}
