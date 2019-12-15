package NPCHandler;

import CombatHandler.CombatGroup;
import CombatHandler.Combatant;
import CombatHandler.DamageHandler;
import CombatHandler.Weapons.Weapon;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Boss implements NPCTemplate{
    String name = "";
    protected NPCMeetSomeoneListener npcMeetSomeoneListener;
    protected NPCRunListener npcRunListener;
    protected NPCFindTargetListener npcFindTargetListener;
    protected NPCAttackListener npcAttackListener;
    int count = 0;

    public Boss(String name) {
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

    @Override
    public ArrayList<Object> getDataStorage() {
        return null;
    }

    @Override
    public NPCAttackListener getNPCAttackListener() {
        return this.npcAttackListener;
    }

    @Override
    public NPCFindTargetListener getNPCFindTargetListener() {
        return this.npcFindTargetListener;
    }

    @Override
    public int getMaxHitpoints() {
        return 100;
    }

    @Override
    public int getMoxy() {
        return 20;
    }

    @Override
    public int getBrawn() {
        return 20;
    }

    @Override
    public int getSpiffness() {
        return 20;
    }

    @Override
    public int getSmarts() {
        return 20;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Weapon> getWeapons() {
        return null;
    }

    @Override
    public ArrayList<String> getCombatWords() {
        return new ArrayList<>(Arrays.asList("Hit"));
    }

    @Override
    public CombatGroup.rpsChoice getRPSChoice() {
        return CombatGroup.rpsChoice.fight;
    }

    @Override
    public NPCMeetSomeoneListener getNPCMeetSomeoneListener() {
        return npcMeetSomeoneListener;
    }

    @Override
    public NPCRunListener getNPCRunListener() {
        return npcRunListener;
    }

    @Override
    public void increment() {
        count++;
    }
}
