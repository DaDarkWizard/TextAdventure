package NPCHandler;

import CombatHandler.CombatGroup;
import CombatHandler.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Arrays;

public class BigOldBadGuy implements NPCTemplate{
    String name = "";
    protected NPCMeetSomeoneListener npcMeetSomeoneListener;
    protected NPCRunListener npcRunListener;
    int count = 0;

    BigOldBadGuy(String name){
        this.name = name;
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
