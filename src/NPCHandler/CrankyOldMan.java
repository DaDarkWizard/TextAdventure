package NPCHandler;

import PlayerHandler.Player;

public class CrankyOldMan extends DefaultNPC {

    public CrankyOldMan() {
        super("Cranky Old Man");
        this.npcMeetSomeoneListener = event -> {
            event.npc.say("You're a goon.");
        };
        this.npcFindTargetListener = event -> {
            event.getSource().setTarget(event.getSource().getCombatGroup().getCombatants(event.getSource()).get(0));
        };
    }


}
