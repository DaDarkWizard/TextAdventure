package NPCHandler;

import CombatHandler.CombatGroup;
import CombatHandler.Combatant;
import CombatHandler.DamageHandler;
import CombatHandler.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class creates a NPC Template for a Boss
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class Boss implements NPCTemplate{
    String name = ""; //the name of the boss
    protected NPCMeetSomeoneListener npcMeetSomeoneListener; //the npcMeetSomeoneListener
    protected NPCRunListener npcRunListener; //the npcRunListener
    protected NPCFindTargetListener npcFindTargetListener; //the npcFindTargetListener
    protected NPCAttackListener npcAttackListener; //the npcAttackListener
    protected NPCDeathListener npcDeathListener; //the npcDeathListener
    protected ArrayList<Object> dataStorage; //the dataStorage
    int count = 0; //the count of how many times this template is used

    /**
     * Constructor for Boss
     * @param name the name of the boss
     */
    public Boss(String name) {
        this.name = name;
        this.npcMeetSomeoneListener = event -> {
            new CombatGroup(event.getSource().getRoom().getCombatants(), event.getSource());
        };
        this.npcFindTargetListener = event -> {
            Combatant mostHealth = null;
            for (Combatant combatant : event.getSource().getCombatGroup().getCombatants(event.getSource())) {
                if (mostHealth == null) {
                    mostHealth = combatant;
                }
                if (combatant.getHitPoints() > mostHealth.getHitPoints()) {
                    mostHealth = combatant;
                }
            }
            event.getSource().setTarget(mostHealth);
        };
        this.npcAttackListener = event -> {
            if (event.getNPC().getTarget() == null) {
                return;

            }

            event.getNPC().getTarget().setPendingDamage(
                    event.getNPC().getPendingDamage() + DamageHandler.rollDice(10)
            );
        };
    }

    /**
     * Gets the NPCDeathListener
     * @return NPCDeathListener
     */
    @Override
    public NPCDeathListener getNPCDeathListener() {
        return this.npcDeathListener;
    }

    /**
     * Gets the data storage
     * @return data storage
     */
    //Todo update this method to return a copy of the ArrayList
    @Override
    public ArrayList<Object> getDataStorage() {
        return this.dataStorage;
    }

    /**
     * Gets the NPCAttackListener
     * @return NPCAttackListener
     */
    @Override
    public NPCAttackListener getNPCAttackListener() {
        return this.npcAttackListener;
    }

    /**
     * Gets the NPCFindTargetListener
     * @return NPCFindTargetListener
     */
    @Override
    public NPCFindTargetListener getNPCFindTargetListener() {
        return this.npcFindTargetListener;
    }

    /**
     * Gets the Max hit points
     * @return
     */
    @Override
    public int getMaxHitpoints() {
        return 100;
    }

    /**
     * Gets the moxy of the boss
     * @return moxy
     */
    @Override
    public int getMoxy() {
        return 20;
    }

    /**
     * Gets the brawn of the boss
     * @return brawn
     */
    @Override
    public int getBrawn() {
        return 20;
    }

    /**
     * Gets the spiffness of the boss
     * @return spiffness
     */
    @Override
    public int getSpiffness() {
        return 20;
    }

    /**
     * Gets the smerts of the boss
     * @return smerts
     */
    @Override
    public int getSmarts() {
        return 20;
    }

    /**
     * Gets the name of the boss
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the weapons of the boss
     * @return weapons
     */
    //Todo review and return an ArrayList if it should
    @Override
    public ArrayList<Weapon> getWeapons() {
        return null;
    }

    /**
     * Gets the combat words of the boss
     * @return combat words
     */
    @Override
    public ArrayList<String> getCombatWords() {
        return new ArrayList<>(Arrays.asList("Hit"));
    }

    /**
     * Gest the RPS choice of the boss
     * @return the RPS choice
     */
    @Override
    public CombatGroup.rpsChoice getRPSChoice() {
        return CombatGroup.rpsChoice.fight;
    }

    /**
     * Gets the NPCMeetSomeoneListener
     * @return NPCMeetSomeoneListener
     */
    @Override
    public NPCMeetSomeoneListener getNPCMeetSomeoneListener() {
        return npcMeetSomeoneListener;
    }

    /**
     * Gets the NPCRunListener
     * @return NPCRunListener
     */
    @Override
    public NPCRunListener getNPCRunListener() {
        return npcRunListener;
    }

    /**
     * Increments the use of the template
     */
    @Override
    public void increment() {
        count++;
    }
}
