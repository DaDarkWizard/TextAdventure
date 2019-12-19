package Server.NPCHandler;

import java.util.EventObject;

/**
 * The class creates a NPCAttackEvent
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class NPCAttackEvent extends EventObject {
    private NPC source; //the NPC

    /**
     * the constructor
     *
     * @param source the NPC
     */
    public NPCAttackEvent(NPC source) {
        super(source);
        this.source = source;
    }

    /**
     * Gets the NPC
     *
     * @return the NPC
     */
    public NPC getNPC() {
        return this.source;
    }

    /**
     * Gets the source
     *
     * @return the source
     */
    @Override
    public NPC getSource() {
        return this.source;
    }
}
