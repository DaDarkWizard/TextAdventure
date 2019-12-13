package NPCHandler;

import PlayerHandler.Player;

public class CrankyOldMan extends DefaultNPC {

    public CrankyOldMan() {
        super("Cranky Old Man");
        this.npcMeetSomeoneListener = event -> {
            event.npc.say("You're a goon.");
            event.npc.getDataStorage().set(0, System.currentTimeMillis());
        };
        this.npcFindTargetListener = event -> {
            event.npc.setTarget(event.getSource().getCombatGroup().getCombatants(event.npc).get(0));
        };
        this.npcRunListener = event -> {
            long startTime = (long) event.npc.getDataStorage().get(0);
            long timeElapsed = System.currentTimeMillis() - startTime;

            if (event.npc.getRoom().getPlayers().size() < 1) {
                return;
            }
            if (timeElapsed < 2000) {

            } else if (timeElapsed < 4000) {

            }
        };
    }


}
