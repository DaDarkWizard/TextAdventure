package Generator;

import GamePieces.Room;
import World.PlayerPortal;
import com.sun.imageio.stream.StreamFinalizer;
import sun.management.snmp.jvmmib.EnumJvmMemPoolCollectThreshdSupport;

public class RoomGenerator {

    RoomGenerator() {

    }

    Room generateRoom() {
        String shape = new RandomFileParser("Text/shape.txt");
        String


        StringBuilder description = new StringBuilder();
        description.append("You are in a ");
        description.append(new RandomFileParser("Text/shape.txt")).append(" ");
        description.append(new RandomFileParser("Text/color.txt")).append(" ");
        description.append("room with ");
        description.append(new RandomFileParser("Text/texture.txt"));
        description.append(" walls.\nIt smells vaguely of ");
        description.append(new RandomFileParser("Text/smell.txt")).append(".");



    }

    void makeEndRoom(Room room) {
        room.setName("Boss Room");
        clearRoom(room);

    }

    void makeStartRoom(Room room) {
        room.setName("Portal Room");
        clearRoom(room);
        room.addInteractable(new PlayerPortal());
    }

    private void clearRoom(Room room) {
        for (int i = 0; i < room.getNpcs().size(); i++) {
            room.removeNPC(room.getNpcs().get(i));
            i--;
        }
        for (int i = 0; i < room.getInteractables().size(); i++) {
            room.removeInteractable(room.getInteractables().get(i));
            i--;
        }
    }
}
