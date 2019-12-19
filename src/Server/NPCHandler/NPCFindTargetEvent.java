package Server.NPCHandler;

import java.util.EventObject;

/**
 * The class creates a NPCFindTargetEvent
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class NPCFindTargetEvent extends EventObject {
    NPC npc;

    /**
     * Constructs a NPCFindTargetEvent
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public NPCFindTargetEvent(NPC source) {
        super(source);
        this.npc = source;
    }

    /**
     * Gets this Events NPC
     *
     * @return the NPC
     */
    @Override
    public NPC getSource() {
        return npc;
    }
}
