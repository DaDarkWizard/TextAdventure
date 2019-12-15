package NPCHandler;

import java.util.EventListener;

public interface NPCAttackListener extends EventListener {
    void handle(NPCAttackEvent event);
}
