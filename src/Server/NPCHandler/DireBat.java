package Server.NPCHandler;

import Server.CombatHandler.CombatGroup;
import Server.CombatHandler.DamageHandler;
import Server.GamePieces.Room;

import java.util.ArrayList;

/**
 * The class creates a NPC Template for a Dire Bat
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class DireBat extends DefaultNPC {
    /**
     * Constructor for the DireBat
     *
     * @param bounds the rooms it roams in
     */
    public DireBat(ArrayList<Room> bounds) {
        super("Dire Bat");
        this.maxHitpoints = 30;
        this.dataStorage.add((long) 0);
        this.dataStorage.add(true);
        this.dataStorage.add(bounds);
        this.npcMeetSomeoneListener = event -> {
            event.getSource().getDataStorage().set(1, false);
            new CombatGroup(event.getSource().getRoom().getCombatants(), event.getSource());
        };
        this.npcRunListener = event -> {
            if ((boolean) event.getSource().getDataStorage().get(1)) {
                long startTime = (long) event.getSource().getDataStorage().get(0);
                long timePassed = System.currentTimeMillis() - startTime;
                if (timePassed > 2000) {
                    Room newRoom = DireBat.findRandomRoom(event.getSource(), (ArrayList<Room>) event.getSource().getDataStorage().get(2));
                    event.getSource().getRoom().removeNPC(event.getSource());
                    newRoom.addNPC(event.getSource());
                    event.getSource().getDataStorage().set(0, System.currentTimeMillis());
                    System.out.println("Bat moved: " + newRoom.getName());
                }
            } else {
                if (event.getSource().getCombatGroup() == null && event.getSource().getRoom().getPlayers().size() > 0) {
                    new CombatGroup(event.getSource().getRoom().getCombatants(), event.getSource());
                }
            }
        };
        this.npcFindTargetListener = event -> {
            event.getSource().setTarget(
                    event.getSource().getCombatGroup().getCombatants(event.getSource()).get(
                            (int) (Math.random() * event.getSource().getCombatGroup().getCombatants(event.getSource()).size())
                    )
            );
        };
        this.npcAttackListener = event -> {
            if (event.getNPC().getTarget() != null) {
                event.getNPC().getTarget().setPendingDamage(
                        event.getNPC().getTarget().getPendingDamage() + DamageHandler.rollDice(5)
                );
            }
        };
    }

    /**
     * Finds a random room
     *
     * @param npc    the NPC looking
     * @param bounds the rooms that they will not end
     * @return The Room
     */
    public static Room findRandomRoom(NPC npc, ArrayList<Room> bounds) {
        ArrayList<Room> possibilities = new ArrayList<>();
        possibilities.add(npc.getRoom());
        if (npc.getRoom().getNorth() != null) {
            possibilities.add(npc.getRoom().getNorth());
        }
        if (npc.getRoom().getEast() != null) {
            possibilities.add(npc.getRoom().getEast());
        }
        if (npc.getRoom().getWest() != null) {
            possibilities.add(npc.getRoom().getWest());
        }
        if (npc.getRoom().getSouth() != null) {
            possibilities.add(npc.getRoom().getSouth());
        }

        for (int i = 0; i < possibilities.size(); i++) {
            if (bounds.contains(possibilities.get(i))) {
                possibilities.remove(possibilities.get(i));
                i--;
            }
        }

        return possibilities.get((int) (Math.random() * possibilities.size()));
    }
}
