package PlayerHandler.UI;

import PlayerHandler.Player;

/**
 * Implements a frame for showing the player their inventory
 *
 * Date Last Modified: 12/10/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Joe Teahen, Emma Smith
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class InventoryFrame extends StandardFrame {

    /**
     * Constructs the frame
     *
     * @param player the player whose inventory to show
     */
    public InventoryFrame(Player player) {
        super();
        this.addLine("Your Inventory: ");
        this.newLine();
        if (player.getInventory().size() < 1) {
            this.addLine("Your inventory is empty! ¯\\_(ツ)_/¯ Sowwy.");
        } else {
            for (int i = 0; i < player.getInventory().size(); i++) {
                this.addLine(i + ". " + player.getInventory().get(i));
            }
        }
    }
}
