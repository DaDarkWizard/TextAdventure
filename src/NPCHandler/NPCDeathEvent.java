package NPCHandler;

import java.util.EventObject;
/**
 * The class creates a NPCDeathEvent
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class NPCDeathEvent extends EventObject {

    private NPC npc; //the npc

    /**
     * The constructor
     * @param source the source of the Event
     */
    public NPCDeathEvent(NPC source) {
        super(source);
        this.npc = source;
    }

    /**
     * Gets the source
     * @return the source
     */
    @Override
    public NPC getSource() {
        return this.npc;
    }

    /**
     * Gets the NPC
     * @return the NPC
     */
    public NPC getNPC() {
        return this.npc;
    }
}
