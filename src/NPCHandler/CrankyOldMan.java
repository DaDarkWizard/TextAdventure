package NPCHandler;

import PlayerHandler.Player;

public class CrankyOldMan extends DefaultNPC {

    public CrankyOldMan() {
        super("Cranky Old Man");
        this.npcMeetSomeoneListener = event -> {
            event.npc.say("You're a goon.");
        };
    }


}
