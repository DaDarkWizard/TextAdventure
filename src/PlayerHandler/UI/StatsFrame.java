package PlayerHandler.UI;

import CombatHandler.Weapons.Weapon;
import PlayerHandler.Player;
/**
 * The class creates a stats frame
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class StatsFrame extends SizableFrame {

    /**
     * Stats frame constructor
     * @param player the player
     */
    public StatsFrame(Player player) {

        super(11, 107);
        this.addLine("[Stats]:", true);
        this.newLine();
        this.addLine("Brawn: " + player.getBrawn(), true);
        this.addLine("Spiffness: " + player.getSpiffness(), true);
        this.addLine("Smerts: " + player.getSmarts(), true);
        this.addLine("Moxy: " + player.getMoxy(), true);
        this.addLine("Max Hit Points: " + player.getMaxHitpoints(), true);
        this.addLine("Current Hit Points " + player.getHitPoints(), true);
        this.newLine();
        this.addLine("Equipped Weapons: \n", true);
        if (player.getEquipped().size() < 1) {
            this.height++;
            this.addLine("You goon! Equip something!", true);
        } else {
            for (Weapon weapon : player.getEquipped()) {
                this.height++;
                this.addLine(weapon.getLongDescription(), true);
            }
        }
    }
}
