package Generator;

import GamePieces.Room;
import NPCHandler.NPC;
import World.PlayerPortal;

public class RoomGenerator {

    RoomGenerator() {

    }

    Room generateRoom() {
        String shape = RandomFileParser.RandomString("Text/shape.txt");
        String color = RandomFileParser.RandomString("Text/color.txt");


        StringBuilder description = new StringBuilder();
        String lookDescription = "It looks like it's " + color;
        description.append("You are in a ");
        description.append(new RandomFileParser("Text/shape.txt")).append(" ");
        description.append(new RandomFileParser("Text/color.txt")).append(" ");
        description.append("room with ");
        description.append(new RandomFileParser("Text/texture.txt"));
        description.append(" walls.\nIt smells vaguely of ");
        description.append(new RandomFileParser("Text/smell.txt")).append(".");

        Room newRoom = new Room(shape + " " + color, description.toString(), lookDescription);

        if(Math.random() < 0.3){
            newRoom.addInteractable(new WeaponGenerator().weapon());
        }
        if(Math.random() < 0.5){
            newRoom.addInteractable(new RandomPotion().potion());
        }
        return newRoom;
    }

    void makeEndRoom(Room room) {
        room.setName("Boss Room");
        clearRoom(room);
        room.addNPC(new NPC(new RandomBoss().boss()));
        room.addInteractable(new RandomPotion().potion());
        room.addInteractable(new WeaponGenerator().weapon());
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
