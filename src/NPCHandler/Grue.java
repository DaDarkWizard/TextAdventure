package NPCHandler;

import CombatHandler.CombatGroup;

/**
 * Generated a gru monster
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class Grue extends Monster {
    public Grue(String name) {
        super(name);
        this.npcMeetSomeoneListener = event -> {
            CombatGroup combatGroup = new CombatGroup(event.npc.getRoom().getCombatants(), event.npc);
        };
    }
}
