package NPCHandler;


import PlayerHandler.CombatHandler.CombatGroup;
import PlayerHandler.CombatHandler.Weapons.Weapon;

import java.util.ArrayList;

/**
 * Interface to allow easy creation of multiple npcs with little variation
 * <p>
 * Date Last Modified: 12/9/2019
 *
 * @author Daniel Masker
 * <p>
 * CS 1131, Fall 2019
 * <p>
 * Lab Section 2
 */
public interface NPCTemplate {
    int getMaxHitpoints();

    int getMoxy();

    int getBrawn();

    int getSpiffness();

    int getSmarts();

    String getName();

    ArrayList<Weapon> getWeapons();

    ArrayList<String> getCombatWords();

    CombatGroup.rpsChoice getRPSChoice();

    void increment();
}
