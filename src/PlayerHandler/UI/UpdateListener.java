package PlayerHandler.UI;

import PlayerHandler.UpdateEvent;

import java.util.EventListener;

public interface UpdateListener extends EventListener {
    void handle(UpdateEvent event);
}
