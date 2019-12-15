package NPCHandler;

import CombatHandler.CombatGroup;
import CombatHandler.DamageHandler;
import GamePieces.Room;

import java.util.ArrayList;

public class DireBat extends DefaultNPC {
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
