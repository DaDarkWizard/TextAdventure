package Server.World.Tutorial;

import Server.NPCHandler.DefaultNPC;
import Server.PlayerHandler.Player;

import java.util.Arrays;

/**
 * Makes one of the Crankiest Men Alive
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class CrankyOldMan extends DefaultNPC {

    /**
     * Constructor for this goon
     */
    public CrankyOldMan() {
        super("Cranky Old Man");

        //Fill data storage
        this.dataStorage.add((long) 0);
        boolean[] dialog = new boolean[6];
        Arrays.fill(dialog, false);
        this.dataStorage.add(dialog);
        this.npcFindTargetListener = event -> {
            event.getSource().setTarget(event.getSource().getCombatGroup().getCombatants(event.getSource()).get(0));
        };

        //Dialogue made before InfoGiver
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

            //This hurts to see now that we have the info giver
            if (!said[5]) {

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
                } else if (timeElapsed < 5000) {
                    if (!said[4]) {
                        event.getSource().say("It's dangerous to go alone. Take this!");
                        said[4] = true;
                        event.getSource().getDataStorage().set(1, said);
                        //Give the player the most useless mallet
                        player.getInventory().add(new UselessMallet().makeWeapon());
                        player.sendMessage("[Notice]: You have received a Ramshackle Mallet!");
                    }
                } else if (timeElapsed < 6000) {
                    if (!said[5]) {
                        event.getSource().say("Now head east, fool!");
                        said[5] = true;
                        event.getSource().getDataStorage().set(1, said);
                    }
                }
            }

        };
    }


}
