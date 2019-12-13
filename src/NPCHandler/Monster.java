package NPCHandler;

import CombatHandler.CombatGroup;
import CombatHandler.Combatant;
import CombatHandler.Weapons.Weapon;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Monster implements NPCTemplate {
    String name = "";
    protected NPCMeetSomeoneListener npcMeetSomeoneListener;
    protected NPCRunListener npcRunListener;
    protected NPCFindTargetListener npcFindTargetListener;
    int count = 0;

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
        };
    }

    @Override
    public ArrayList<Object> getDataStorage() {
        return null;
    }

    @Override
    public NPCFindTargetListener getNPCFindTargetListener() {
        return this.npcFindTargetListener;
    }

    @Override
    public int getMaxHitpoints() {
        return 50;
    }

    @Override
    public int getMoxy() {
        return 10;
    }

    @Override
    public int getBrawn() {
        return 10;
    }

    @Override
    public int getSpiffness() {
        return 10;
    }

    @Override
    public int getSmarts() {
        return 10;
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
        return new ArrayList<>(Arrays.asList("hit", "hit", "hit", "hit"));
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
