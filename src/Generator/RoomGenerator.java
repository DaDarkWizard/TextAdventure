package Generator;

import GamePieces.Room;
import sun.management.snmp.jvmmib.EnumJvmMemPoolCollectThreshdSupport;

public class RoomGenerator {

    RoomGenerator() {

    }

    Room generateRoom() {
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
        room.setName("Portal Room");
        for (int i = 0; i < room.getNpcs().size(); i++) {
            room.removeNPC(room.getNpcs().get(i));
            i--;
        }
    }

    void makeStartRoom(Room room) {

    }
}
