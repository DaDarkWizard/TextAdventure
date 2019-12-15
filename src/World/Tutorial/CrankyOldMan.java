package World.Tutorial;

import NPCHandler.DefaultNPC;
import PlayerHandler.Player;

import java.util.Arrays;

public class CrankyOldMan extends DefaultNPC {

    public CrankyOldMan() {
        super("Cranky Old Man");
        this.dataStorage.add((long) 0);
        boolean[] dialog = new boolean[5];
        Arrays.fill(dialog, false);
        this.dataStorage.add(dialog);
        this.npcFindTargetListener = event -> {
            event.getSource().setTarget(event.getSource().getCombatGroup().getCombatants(event.getSource()).get(0));
        };
        this.npcRunListener = event -> {
            long startTime = (long) event.getSource().getDataStorage().get(0);
            long timeElapsed = System.currentTimeMillis() - startTime;
            boolean[] said = (boolean[]) event.getSource().getDataStorage().get(1);
            Player player;

            if (event.getSource().getRoom().getPlayers().size() < 1) {
                return;
            } else if (!said[0]) {
                startTime = System.currentTimeMillis();
                said[0] = true;
                event.getSource().getDataStorage().set(1, said);
                event.getSource().getDataStorage().set(0, startTime);
            }

            player = event.getSource().getRoom().getPlayers().get(0);

            if (!said[4]) {

                if (timeElapsed < 1000) {

                } else if (timeElapsed < 2000) {
                    if (!said[1]) {
                        event.getSource().say("You stand in my dungeon!");
                        said[1] = true;
                        event.getSource().getDataStorage().set(1, said);
                    }
                } else if (timeElapsed < 3000) {
                    if (!said[2]) {
                        event.getSource().say("You are weak! I'd kill you if I could, but laws 'n stuff.");
                        said[2] = true;
                        event.getSource().getDataStorage().set(1, said);
                    }
                } else if (timeElapsed < 4000) {
                    if (!said[3]) {
                        event.getSource().say("So instead I'll be helpful.");
                        said[3] = true;
                        event.getSource().getDataStorage().set(1, said);
                    }
                } else if (timeElapsed < 10000) {
                    if (!said[4]) {
                        event.getSource().say("It's dangerous to go alone. Take this!");
                        said[4] = true;
                        event.getSource().getDataStorage().set(1, said);
                        player.getInventory().add(new UselessMallet().makeWeapon());
                        player.sendMessage("[Notice]: You have received a Ramshackle Mallet!");
                    }
                }
            }

        };
    }


}
