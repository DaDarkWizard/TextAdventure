package NPCHandler;

import CombatHandler.CombatGroup;

public class Grue extends Monster {
    public Grue(String name) {
        super(name);
        this.npcMeetSomeoneListener = event -> {
            CombatGroup combatGroup = new CombatGroup(event.npc.getRoom().getCombatants(), event.npc);
        };
    }
}
