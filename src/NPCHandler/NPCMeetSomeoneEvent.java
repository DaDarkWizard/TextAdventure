package NPCHandler;

import java.util.EventObject;

/**
 * Event for when an NPC meets another creature
 * <p>
 * Date Last Modified: 12/10/2019
 *
 * @author Daniel Masker, Joe Teahen, Emma Smith, Ben Hodsdon
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */

public class NPCMeetSomeoneEvent extends EventObject {
    NPC npc;    //The npc that had this happen

    /**
     * Create the event
     *
     * @param npc the one this event is happening to
     */
    public NPCMeetSomeoneEvent(NPC npc) {
        super(npc);
        this.npc = npc;
    }

    /**
     * Gets the npc this event is happening to
     *
     * @return npc this event is happening to
     */
    @Override
    public NPC getSource() {
        return npc;
    }
}
