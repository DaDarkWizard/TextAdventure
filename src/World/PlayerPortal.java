package World;

import GamePieces.InteractListener;
import GamePieces.Interactable;
import PlayerHandler.Commands;
import PlayerHandler.Player;

/**
 * Portal the player uses to go back to spawn
 * <p>
 * Date Last Modified: 12/13/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class PlayerPortal implements Interactable {

    private String shortDescription;    //Description displayed in room
    private String longDescription;     //Description displayed on look
    private String useDescription;      //Description given when used

    /**
     * Initialized the portal
     */
    public PlayerPortal() {
        this.shortDescription = "large rune circle in the center of the room";
        this.longDescription = "A circle made of runes, often used as an instant form of travel across unknown distances.\n" +
                "The tracings still glow faintly with residual magic from the teleportation spell used to get here.\n" +
                "While most of the formations are recognizable as often used words of power,\n" +
                "someone has left a message using the runes as a phonetic alphabet:\n\n" +
                "'DANYEL MAHSKER'  'EMAH SMIÞ'  'BYEN HOD'S SON' 'JO TIHYEN'\n";
        this.useDescription = "You stand in the center of the circle, tracing the runes in your mind and speaking\n" +
                "them under your breath. The ground feels as though it has disappeared from beneath your feet,\n" +
                "and your gut wrenches as everything goes black.";
    }


    /**
     * Gets the short description
     *
     * @return the short Description
     */
    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Gets the description when examined by the player
     *
     * @param player the player examining
     * @return just the longDescription
     */
    @Override
    public String examine(Player player) {
        return this.longDescription;
    }

    /**
     * Handles interaction with the object
     *
     * @param player  player interacting
     * @param command command used to interact
     * @return description of what happened
     */
    @Override
    public String interact(Player player, Commands command) {
        if (command == Commands.look) {
            return longDescription;
        } else if (command == Commands.use)
            player.setLocation(Spawn.spawn);
        return this.useDescription;
    }

    /**
     * Checks if a string can select this item
     *
     * @param name the string to check
     * @return whether it matches
     */
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

    /**
     * Sets this objects interact event
     *
     * @param listener the listener to be set
     */
    @Override
    public void setInteractEventListener(InteractListener listener) {

    }

    /**
     * Gets the long description for this object
     *
     * @return the long description
     */
    @Override
    public String getLongDescription() {
        return this.longDescription;
    }
}
