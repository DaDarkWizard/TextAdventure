package Generator;

import CombatHandler.AttackCommands;
import CombatHandler.Weapons.Weapon;

/**
 * Randomly generates name for potions
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */
public class RandomPotion {
    /**
     * Makes a random potion
     * @return the potion
     */
    public Weapon potion() {
        String color = RandomFileParser.RandomString("Text/color.txt");
        return new Weapon(color + " Potion");
    }
}
