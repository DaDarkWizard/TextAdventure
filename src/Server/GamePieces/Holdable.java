package Server.GamePieces;

import Server.PlayerHandler.Player;

/**
 * This interface is for holdable interactable
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public interface Holdable extends Interactable {
    /**
     * Picks up and item
     *
     * @param player the player picking it up
     * @return feedback
     */
    String pickup(Player player);

    /**
     * Drops the item
     *
     * @param player the player dropping it
     * @return feedback
     */
    String drop(Player player);
}
