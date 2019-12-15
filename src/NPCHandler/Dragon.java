package NPCHandler;

import CombatHandler.CombatGroup;
import GamePieces.Room;

import java.util.ArrayList;

public class Dragon extends Boss {
    public Dragon(ArrayList<Room> batBounds, Room batSpawn, NPC deleteNPC, NPC addNPC) {
        super("Dragon");
        this.dataStorage.add(batBounds);
        this.dataStorage.add(batSpawn);
        this.dataStorage.add(deleteNPC);
        this.dataStorage.add(addNPC);
        this.npcMeetSomeoneListener = event -> {
            CombatGroup combatGroup = new CombatGroup(event.npc.getRoom().getCombatants(), event.npc);
        };
        this.npcDeathListener = e -> {
            DireBat bat = new DireBat((ArrayList<Room>) e.getNPC().getDataStorage().get(0));
            ((Room) e.getNPC().getDataStorage().get(1)).addNPC(new NPC(bat));
            ((NPC) e.getNPC().getDataStorage().get(2)).getRoom().addNPC((NPC) e.getNPC().getDataStorage().get(3));
            ((NPC) e.getNPC().getDataStorage().get(2)).getRoom().removeNPC((NPC) e.getNPC().getDataStorage().get(2));
        };
    }
}
