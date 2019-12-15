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

    public static Stats findDefensiveStat(Stats stats) {
        if (stats == Stats.moxy) {
            return Stats.smerts;
        }
        return Stats.spiffness;
    }
}
