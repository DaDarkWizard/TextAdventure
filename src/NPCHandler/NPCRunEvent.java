package NPCHandler;

import java.util.EventObject;

/**
 * The event for when an NPC need to run
 * <p>
 * Date Last Modified: 12/10/2019
 *
 * @author Daniel Masker, Joe Teahen, Emma Smith, Ben Hodsdon
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class NPCRunEvent extends EventObject {
    NPC npc;

    /**
     * Constructs a NPCRunEvent
     *
     * @param source The object on which the Event initially occurred
     */
    public NPCRunEvent(NPC source) {
        super(source);
        this.npc = source;
    }

    /**
     * Returns the npc who is running
     *
     * @return the npc
     */
    public NPC getSource() {
        return this.npc;
    }
}
