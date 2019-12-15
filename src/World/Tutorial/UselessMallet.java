package World.Tutorial;

import CombatHandler.Weapons.Weapon;
import Generator.WeaponGenerator;

/**
 * Creates one of the most useless weapons in the game.
 * This weapon actually equates to putting pillows over your fists.
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, Fall 2019
 * Lab Section 2
 */
public class UselessMallet {

    /**
     * Generates the weapon
     *
     * @return the generated mallet
     */
    public Weapon makeWeapon() {
        return new WeaponGenerator().weapon("Ramshackle", "Mallet", "Suspicious", "Uselessness");
    }
}
