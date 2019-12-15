package GamePieces;

import PlayerHandler.Commands;
import PlayerHandler.Player;

import java.util.ArrayList;

/**
 * This class creates interactables that can't stored in an inventory called furniture
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class Furniture implements Interactable {
    private String shortDescription; //a short description of the furniture
    private String longDescription; //a longer description of the furniture
    protected ArrayList<String> validNames = new ArrayList<>(); //valid names for the furniture
    private InteractListener interactListener; //interactListener

    /**
     * Constructor
     * @param shortDescription short description of the furniture
     * @param longDescription long description of the furniture
     * @param interactListener InteractListener
     */
    public Furniture(String shortDescription, String longDescription, InteractListener interactListener) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.interactListener = interactListener;
        validNames.add(shortDescription.toLowerCase());
    }

    /**
     * Gets the long description
     * @return the long description
     */
    @Override
    public String getLongDescription() {
        return this.longDescription;
    }

    /**
     * Gets the short description
     * @return the short description
     */
    @Override
    public String getShortDescription() {
        return this.shortDescription;
    }

    /**
     * Gives the long description to the player
     * @param player the player
     * @return the long description
     */
    @Override
    public String examine(Player player) {
        return this.longDescription;
    }

    /**
     * Used for the player to interact with the furniture
     * @param player the player
     * @param command the command word
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
     * Checks to see if a name is a valid name
     * @param name the name to be checked
     * @return if it is a valid name
     */
    @Override
    public boolean isValidName(String name) {
        return validNames.contains(name.trim().toLowerCase());
    }

    /**
     * HAS TO BE HERE FOR THE INTERFACE
     * it would set the interactEventListener
     * @param listener the Listener
     */
    @Override
    public void setInteractEventListener(InteractListener listener) {

    }
}
