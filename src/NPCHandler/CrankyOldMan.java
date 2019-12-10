package NPCHandler;

import PlayerHandler.Player;

public class CrankyOldMan extends DefaultNPC {

    public CrankyOldMan() {
        super("Cranky Old Man");
        this.npcMeetSomeoneListener = new NPCMeetSomeoneListener() {
            @Override
            public void handle(NPCMeetSomeoneEvent event) {
                for (Player player : event.npc.getRoom().getPlayers()) {
                    player.sendMessage("Yur a gooooooon!");
                }
            }
        };
    }


}
