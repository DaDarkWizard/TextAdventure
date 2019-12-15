package NPCHandler;

import java.util.EventObject;

public class NPCAttackEvent extends EventObject {
    private NPC source;

    public NPCAttackEvent(NPC source) {
        super(source);
        this.source = source;
    }

    public NPC getNPC() {
        return this.source;
    }

    @Override
    public NPC getSource() {
        return this.source;
    }
}
