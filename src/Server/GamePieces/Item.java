package Server.GamePieces;

import Server.PlayerHandler.Commands;
import Server.PlayerHandler.Player;

/**
 * This class handles Items
 * <p>
 * <p>
 * Date Original Last Modified: 12/14/2019
 * Added setters for item descriptions: 12/16/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen, Michael Clinesmith
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class Item implements Holdable {
    protected String shortDescription = ""; //short description
    protected String longDescription = ""; //long description
    String[] validNames; //valid names
    InteractListener interactListener; //interactListener

    /**
     * Constructor for Item
     *
     * @param shortDescription the short description of the item
     * @param longDescription  the long description of the item
     * @param validNames       the valid names of the item
     */
    public Item(String shortDescription, String longDescription, String[] validNames) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.validNames = validNames;
    }


    /**
     * Getter for short description
     *
     * @return short description
     */
    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Examines the items
     *
     * @param player the player examining
     * @return feedback
     */
    @Override
    public String examine(Player player) {
        return longDescription;
    }

    /**
     * Interacts with the items
     *
     * @param player  the player interacting
     * @param command the command to do so
     * @return feedback
     */
    @Override
    public String interact(Player player, Commands command) {
        InteractEvent event = new InteractEvent(player, command);
        if (interactListener == null) {
            return null;
        }
        return interactListener.handle(event);
    }

    /**
     * sets the interact event listener
     *
     * @param listener the listener to be set
     */
    public void setInteractEventListener(InteractListener listener) {
        this.interactListener = listener;
    }

    /**
     * Checks to see if a string is a valid name
     *
     * @param name the string to check
     * @return if it is
     */
    @Override
    public boolean isValidName(String name) {
        //Binary search wasn't up to it
        for (int i = 0; i < validNames.length; i++) {
            if (validNames[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Picks up the item
     *
     * @param player the player picking it up
     * @return feedback
     */
    @Override
    public String pickup(Player player) {
        player.getInventory().add(this);
        player.getLocation().removeInteractable(this);
        return "You picked up " + this.shortDescription;
    }

    /**
     * Drops the item
     *
     * @param player the player dropping it
     * @return feedback
     */
    @Override
    public String drop(Player player) {
        player.getInventory().remove(this);
        player.getLocation().addInteractable(this);
        return "You dropped " + this.shortDescription;
    }

    /**
     * Gets the long description
     *
     * @return feedback
     */
    @Override
    public String getLongDescription() {
        return this.longDescription;
    }

    /**
     * Setter allowing changes to the shortDescription
     *
     * @param shortDescription String: the new short description
     */

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Setter allowing changes to the longDescription
     *
     * @param longDescription String: the new long description
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
