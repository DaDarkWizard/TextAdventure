package World;

import GamePieces.InteractListener;
import GamePieces.Interactable;
import PlayerHandler.Commands;
import PlayerHandler.Player;

public class PlayerPortal implements Interactable {

    private String shortDescription;
    private String longDescription;
    private String useDescription;

    public PlayerPortal() {
        this.shortDescription = "A large rune circle in the center of the room";
        this.longDescription = "A circle made of runes, often used as an instant form of travel across unknown distances.\n" +
                "The tracings still glow faintly with residual magic from the teleportation spell used to get here.\n" +
                "While most of the formations are recognizable as often used words of power,\n" +
                "someone has left a message using the runes as a phonetic alphabet:\n\n" +
                "'DANYEL MAHSKER'  'EMAH SMIÞ'  'BYEN HOD'S SON' 'JO TIHYEN'\n";
        this.useDescription = "You stand in the center of the circle, tracing the runes in your mind and speaking\n" +
                "them under your breath. The ground feels as though it has disappeared from beneath your feet,\n" +
                "and your gut wrenches as everything goes black.";
    }


    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String examine(Player player) {
        return null;
    }

    @Override
    public String interact(Player player, Commands command) {
        if (command == Commands.look) {
            return longDescription;
        } else if (command == Commands.use)
            player.setLocation(Spawn.spawn);
        return this.useDescription;
    }

    @Override
    public boolean isValidName(String name) {
        name = name.trim().toLowerCase();

        switch (name) {
            case "circle":
            case "rune circle":
            case "runes":
            case "rune":
            case "large rune circle":
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setInteractEventListener(InteractListener listener) {

    }
}
