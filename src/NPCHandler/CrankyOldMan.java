package NPCHandler;

import PlayerHandler.Player;

public class CrankyOldMan extends DefaultNPC {

    public CrankyOldMan() {
        super("Cranky Old Man");
        this.npcMeetSomeoneListener = event -> {
            for (Player player : event.npc.getRoom().getPlayers()) {
                player.sendMessage("YURE A GOOON !!");
            }
        };
    }


}
