package CombatHandler.Weapons;

/**
 * The class handles the stats of the weapons 
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */

public class StatHandler {

    public enum Stats {
        brawn,          //Strength  
        spiffness,      //Dexterity 
        moxy,           // Charisma 
        smerts          // Intellect 
    }

    /**
     * Finds the stat used for defense against the attack of this stat type
     * @param stats the stat used for the attack
     * @return the corresponding stat
     */
    public static Stats findDefensiveStat(Stats stats) {
        if (stats == Stats.moxy) {
            return Stats.smerts;
        }
        return Stats.spiffness;
    }
}
