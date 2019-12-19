package Server.Generator;

import Server.GamePieces.Room;
import Server.NPCHandler.NPC;
import Server.World.PlayerPortal;

/**
 * Randomly describes rooms as they are initialized
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class RoomGenerator {
    /**
     * Generates a random room
     *
     * @return the room
     */
    Room generateRoom() {
        String shape = RandomFileParser.RandomString("Text/shape.txt");
        String color = RandomFileParser.RandomString("Text/color.txt");


        StringBuilder description = new StringBuilder();
        String lookDescription = "It looks like it's " + color;
        description.append("You are in a ");
        description.append(shape).append(" ");
        description.append(color).append(" ");
        description.append("room with ");
        description.append(RandomFileParser.RandomString("Text/texture.txt"));
        description.append(" walls.\nIt smells vaguely of ");
        description.append(RandomFileParser.RandomString("Text/smell.txt")).append(".");

        Room newRoom = new Room(shape + " " + color, description.toString(), lookDescription);

        if (Math.random() < 0.3) {
            newRoom.addInteractable(new WeaponGenerator().weapon());
        }
        if (Math.random() < 0.5) {
            newRoom.addInteractable(new RandomPotion().potion());
        }
        return newRoom;
    }

    /**
     * Generated dead-end
     *
     * @param room
     */
    void makeEndRoom(Room room) {
        room.setName("Boss Room");
        clearRoom(room);
        room.addNPC(new NPC(new RandomBoss().boss()));
        room.addInteractable(new RandomPotion().potion());
        room.addInteractable(new WeaponGenerator().weapon());
    }

    /**
     * Generates spawn room
     *
     * @param room
     */
    void makeStartRoom(Room room) {
        room.setName("Portal Room");
        clearRoom(room);
        room.addInteractable(new PlayerPortal());
    }

    /**
     * Clears room of NPCs and interactables
     *
     * @param room
     */
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
