package NPCHandler;

import CombatHandler.CombatGroup;

public class Dragon extends Boss {
    public Dragon(String name) {
        super(name);
        this.npcMeetSomeoneListener = event -> {
            CombatGroup combatGroup = new CombatGroup(event.npc.getRoom().getCombatants(), event.npc);
        };
    }
}
