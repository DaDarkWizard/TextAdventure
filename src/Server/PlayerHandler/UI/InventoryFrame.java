package Server.PlayerHandler.UI;

import Server.PlayerHandler.Player;

/**
 * Implements a frame for showing the player their inventory
 * <p>
 * Date Last Modified: 12/10/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Joe Teahen, Emma Smith
 * <p>
 * CS 1131, Fall 2019
 * Lab Section 2
 */
public class InventoryFrame extends SizableFrame {

    /**
     * Constructs the frame
     *
     * @param player the player whose inventory to show
     */
    public InventoryFrame(Player player) {
        super(player.getInventory().size() + 4, 107);
        this.addLine("[Inventory]:", true);
        this.newLine();
        if (player.getInventory().size() < 1) {
            this.addLine("Your inventory is empty! ¯\\_(ツ)_/¯ Sowwy.", true);
        } else {
            for (int i = 0; i < player.getInventory().size(); i++) {
                this.addLine((i + 1) + ". " +
                        (player.getEquipped().contains(player.getInventory().get(i)) ? "[E] " : "")
                        + player.getInventory().get(i).getShortDescription());
            }
        }
    }
}
