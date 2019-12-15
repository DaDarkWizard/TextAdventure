package NPCHandler;

import CombatHandler.CombatGroup;
import CombatHandler.Combatant;
import CombatHandler.DamageHandler;
import CombatHandler.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * The class creates a Monster NPC Template
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class Monster implements NPCTemplate {
    String name = ""; //Monster name
    protected NPCMeetSomeoneListener npcMeetSomeoneListener; //MeetSomeoneListener for monster
    protected NPCRunListener npcRunListener; //NPCRunListener for monster
    protected NPCFindTargetListener npcFindTargetListener; //NPCFindTargetListener for monster
    protected NPCAttackListener npcAttackListener; //NPCAttackListener for monster
    protected NPCDeathListener npcDeathListener; //NPCDeathListener for monster
    int count = 0;

    /**
     * Monster constructor
     * @param name the name of the monster
     */
    Monster(String name){
        this.name = name;
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
                    event.getNPC().getTarget().getPendingDamage() + DamageHandler.rollDice(4)
            );
        };
    }

    /**
     * gets NPCDeathListener
     * @return the NPCDeathListener
     */
    @Override
    public NPCDeathListener getNPCDeathListener() {
        return this.npcDeathListener;
    }

    /**
     * Gets the data storage of monster
     * @return the data storage
     */
    @Override
    public ArrayList<Object> getDataStorage() {
        return null;
    }

    /**
     * Gets the NPCFindTargetListener
     * @return the NPCFindTargetListener
     */
    @Override
    public NPCFindTargetListener getNPCFindTargetListener() {
        return this.npcFindTargetListener;
    }

    /**
     * Gets the MaxHitPoints
     * @return the MaxHitPoints
     */
    @Override
    public int getMaxHitpoints() {
        return 50;
    }

    /**
     * Gets the Moxy of the monster
     * @return the moxy of the monster
     */
    @Override
    public int getMoxy() {
        return 10;
    }

    /**
     * Gets the Brawn of the monster
     * @return the brawn of the monster
     */
    @Override
    public int getBrawn() {
        return 10;
    }

    /**
     * Gets the Spiffness of the monster
     * @return the spiffness of the monster
     */
    @Override
    public int getSpiffness() {
        return 10;
    }

    /**
     * Gets the smerts of the monster
     * @return the smerts of the monster
     */
    @Override
    public int getSmarts() {
        return 10;
    }

    /**
     * gets the name of the monster
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the weapons of the monster
     * @return the weapons
     */
    @Override
    public ArrayList<Weapon> getWeapons() {
        return null;
    }

    /**
     * Gets the combat words of the monster
     * @return the combat words
     */
    @Override
    public ArrayList<String> getCombatWords() {
        return new ArrayList<>(Arrays.asList("hit", "hit", "hit", "hit"));
    }

    /**
     * Gets the RPS choice of the monster
     * @return the RPS choice
     */
    @Override
    public CombatGroup.rpsChoice getRPSChoice() {
        return CombatGroup.rpsChoice.fight;
    }

    /**
     * Gets the NPCMeetSomeoneListener
     * @return the NPCMeetSomeoneListener
     */
    @Override
    public NPCMeetSomeoneListener getNPCMeetSomeoneListener() {
        return npcMeetSomeoneListener;
    }

    /**
     * Gets the NPCRunListener
     * @return the NPCRunListener
     */
    @Override
    public NPCRunListener getNPCRunListener() {
        return npcRunListener;
    }

    /**
     * Gets the NPCAttackListener
     * @return the NPCAttackListener
     */
    @Override
    public NPCAttackListener getNPCAttackListener() {
        return this.npcAttackListener;
    }

    /**
     * Increments the template use
     */
    @Override
    public void increment() {
        count++;
    }
}
