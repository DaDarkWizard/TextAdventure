package NPCHandler;

import java.util.EventObject;

public class NPCDeathEvent extends EventObject {

    private NPC npc;

    public NPCDeathEvent(NPC source) {
        super(source);
        this.npc = source;
    }

    @Override
    public NPC getSource() {
        return this.npc;
    }

    public NPC getNPC() {
        return this.npc;
    }
}
