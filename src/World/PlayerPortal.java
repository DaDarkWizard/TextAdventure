package World;

import GamePieces.InteractListener;
import GamePieces.Interactable;
import PlayerHandler.Commands;
import PlayerHandler.Player;

public class PlayerPortal implements Interactable {

    String shortDescription;
    String longDescription;

    public PlayerPortal() {
        this.shortDescription = "A large rune circle in the center of the room";
        this.longDescription = "A circle made of runes, often used as an instant form of travel across unknown distances.\n" +
                "The tracings still glow faintly with residual magic from the teleportation spell used to get here.\n" +
                "While most of the formations are recognizable as often used words of power,\n" +
                "someone has left a message using the runes as a phonetic alphabet:\n\n" +
                "'DANYEL MAHSKER'  'EMAH SMIÞ'  'BYEN HOD'S SON' 'JO TIHYEN'\n";
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

            return null;
    }

    @Override
    public boolean isValidName(String name) {
        return false;
    }

    @Override
    public void setInteractEventListener(InteractListener listener) {

    }
}
