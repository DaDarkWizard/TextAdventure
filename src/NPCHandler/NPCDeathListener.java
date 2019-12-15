package NPCHandler;

import java.util.EventListener;

public interface NPCDeathListener extends EventListener {
    void handle(NPCDeathEvent e);
}
