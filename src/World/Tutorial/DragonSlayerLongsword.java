package World.Tutorial;

import CombatHandler.AttackCommands;
import CombatHandler.Weapons.StatHandler;
import CombatHandler.Weapons.Weapon;

/**
 * Creates the ultimate sword for dragon slaying
 * <p>
 * Date Original Last Modified: 12/14/2019
 * Added sword long description: 12/16/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen, Michael Clinesmith
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class DragonSlayerLongsword extends Weapon {

    /**
     * Constructor for making the sword
     */
    public DragonSlayerLongsword() {
        super("Longsword", 0, AttackCommands.supermegagiantawesomeslash, StatHandler.Stats.brawn,
                "Dragon Slayer", 0, "Dragon",
                e -> {
                    //Only hurts dragons
                    if (e.getSource().getTarget() != null && e.getSource().getTarget().getName().equals("Dragon")) {
                        e.getSource().getTarget().setPendingDamage(Integer.MAX_VALUE / 4);
                    }
                },
                "Slaying");
        this.setLongDescription( "A sword no sharper than a butter knife but emits a faint aura.");  // adds long description
    }
}
