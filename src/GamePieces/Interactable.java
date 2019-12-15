package GamePieces;

import PlayerHandler.Player;
import PlayerHandler.Commands;
/**
 * This interface sets up objects that the player can interact with
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public interface Interactable {
    /**
     * Gets the short description of the Interactable
     * @return the short description
     */
    String getShortDescription();

    /**
     * Gets the long description of the Interactable
     * @return the long description
     */
    String getLongDescription();

    /**
     * Examines the Interactable
     * @param player the player examining
     * @return feedback
     */
    String examine(Player player);

    /**
     * Interacts with the Interactable
     * @param player the player interacting
     * @param command the command to do so
     * @return feedback
     */
    String interact(Player player, Commands command);

    /**
     * Checks to see if a string is a valid name
     * @param name the string to check
     * @return if it is or not
     */
    boolean isValidName(String name);

    /**
     * Sets the interact Event Listener
     * @param listener the listener to be set
     */
    void setInteractEventListener(InteractListener listener);
}
