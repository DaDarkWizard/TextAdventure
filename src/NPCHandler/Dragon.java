package NPCHandler;

import CombatHandler.CombatGroup;
import GamePieces.Room;

import java.util.ArrayList;

public class Dragon extends Boss {
    public Dragon(NPCTemplate bat, Room batSpawn, NPC deleteNPC, NPCTemplate addNPC, ArrayList<NPC> deletableNPCS) {
        super("Dragon");
        this.dataStorage = new ArrayList<>();
        this.dataStorage.add(bat);
        this.dataStorage.add(batSpawn);
        this.dataStorage.add(deleteNPC);
        this.dataStorage.add(addNPC);
        this.dataStorage.add(deletableNPCS);
        this.npcMeetSomeoneListener = event -> {
            CombatGroup combatGroup = new CombatGroup(event.npc.getRoom().getCombatants(), event.npc);
        };
        this.npcDeathListener = e -> {
            NPCTemplate newBat = (NPCTemplate) e.getNPC().getDataStorage().get(0);
            NPC newNPC = new NPC((NPCTemplate) e.getNPC().getDataStorage().get(3));
            ((Room) e.getNPC().getDataStorage().get(1)).addNPC(new NPC(newBat));
            ((NPC) e.getNPC().getDataStorage().get(2)).getRoom().addNPC(newNPC);
            ((NPC) e.getNPC().getDataStorage().get(2)).getRoom().removeNPC((NPC) e.getNPC().getDataStorage().get(2));


            ArrayList<NPC> deletable = null;
            //Can't fix this :(
            deletable = (ArrayList<NPC>) e.getNPC().getDataStorage().get(4);
        };
    }
}
