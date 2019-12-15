package World.Tutorial;

import GamePieces.Holdable;
import Generator.WeaponGenerator;
import PlayerHandler.Player;

/**
 * A Tutorial NPC
 * <p>
 * Date Last Modified: 12/15/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class SouthernForce extends InfoGiver {
    /**
     * Constructor
     */
    public SouthernForce() {
        super("Great Southern Force");
        this.addLine("If you type 'dequip' then an item name, you can equip an item to use in combat.");
        this.addLine("To be honest, don't bother with that mallet.");
        this.addLine("It's the worst weapon I've ever seen!");
        this.addLine("I've given you a much better sword that I had lying around.");
        this.addLine("Once you're ready, head west.");
        this.npcMeetSomeoneListener = event -> {
            Player player = event.getSource().getRoom().getPlayers().get(0);
            boolean hasWeapon = false;
            for (Holdable holdable : player.getInventory()) {
                if (holdable.isValidName("decent sword of shocking normalcy")) {
                    hasWeapon = true;
                }
            }
            if (!hasWeapon) {
                player.getInventory().add(
                        new WeaponGenerator().weapon("Decent", "Sword", "Shocking", "Normalcy"));
                player.sendMessage("[Notice]: You have received a weapon.");
            }
        };
    }
}
