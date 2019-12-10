package NPCHandler;

import java.util.EventObject;

public class NPCMeetSomeoneEvent extends EventObject {
    NPC npc;

    public NPCMeetSomeoneEvent(NPC npc) {
        super(npc);
        this.npc = npc;
    }

    @Override
    public NPC getSource() {
        return npc;
    }
}
