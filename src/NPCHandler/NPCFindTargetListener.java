package NPCHandler;

import java.util.EventListener;

public interface NPCFindTargetListener extends EventListener {
    void handle(NPCFindTargetEvent event);
}
