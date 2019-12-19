package Server.NPCHandler;


import Server.CombatHandler.CombatGroup;
import Server.CombatHandler.Weapons.Weapon;

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
    /**
     * Gets the max hit points of the NPC
     *
     * @return the max hit points of the NPC
     */
    int getMaxHitpoints();

    /**
     * Gets the NPC stat for moxy
     *
     * @return the moxy stat
     */
    int getMoxy();

    /**
     * Gets the NPC stat for Brawn
     *
     * @return the brawn stat
     */
    int getBrawn();

    /**
     * Gets the NPC stat for Spiffness
     *
     * @return the spiffness stat
     */
    int getSpiffness();

    /**
     * Gets the NPC stat for Smerts
     *
     * @return the smerts stat
     */
    int getSmarts();

    /**
     * Gets name for the NPC
     *
     * @return the name for the NPC
     */
    String getName();

    /**
     * Gets weapons for the NPC
     *
     * @return arrayList of weapons
     */
    ArrayList<Weapon> getWeapons();

    /**
     * Gets the combat words for the NPC
     *
     * @return arrayList of combat words
     */
    ArrayList<String> getCombatWords();

    /**
     * Gets the RPS Choice for the NPC
     *
     * @return their RPS choice
     */
    CombatGroup.rpsChoice getRPSChoice();

    /**
     * Listener for when a NPC meets someone
     *
     * @return listener for meeting someone
     */
    NPCMeetSomeoneListener getNPCMeetSomeoneListener();

    /**
     * Listener for when a NPC is running around
     *
     * @return listener for running around
     */
    NPCRunListener getNPCRunListener();

    /**
     * Listener for when a NPC is finding a target
     *
     * @return listener for finding a target
     */
    NPCFindTargetListener getNPCFindTargetListener();

    /**
     * Gets the arrayList for data Storage
     *
     * @return arrayList for Data Storage
     */
    ArrayList<Object> getDataStorage();

    /**
     * Gets NPC attack listener
     *
     * @return listener for attacking
     */
    NPCAttackListener getNPCAttackListener();

    /**
     * Gets NPC death listener
     *
     * @return listener for death
     */
    NPCDeathListener getNPCDeathListener();

    /**
     * increment a count for how many time you use the template
     */
    void increment();
}
