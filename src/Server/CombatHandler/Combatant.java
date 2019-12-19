package Server.CombatHandler;

import Server.CombatHandler.Weapons.StatHandler;
import Server.CombatHandler.Weapons.Weapon;

import java.util.ArrayList;

/**
 * This class is the interface for combatant
 * <p>
 * Date Last Modified: 12/14/2019
 *
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 * <p>
 * CS1131, fall 2019
 * Lab Section 2
 */


public interface Combatant {
    String getName();

    boolean isUnconscious();

    int modifyHitpoints(int amount);

    int getHitPoints();

    int getMaxHitpoints();

    void setMaxHitpoints(int amount);

    int getBrawn();

    void setBrawn(int amount);

    int getSpiffness();

    void setSpiffness(int amount);

    int getSmarts();

    void setSmarts(int amount);

    int getMoxy();

    void setMoxy(int amount);

    void setPendingBlock(int amount);

    int getPendingBlock();

    void setPendingDamage(int amount);

    int getPendingDamage();

    void setPendingHeal(int amount);

    int getPendingHeal();

    ArrayList<Weapon> getWeapons();

    Combatant getTarget();

    void setTarget(Combatant target);

    CombatGroup getCombatGroup();

    void setCombatGroup(CombatGroup group);

    ArrayList<String> getWords();

    CombatGroup.rpsChoice getCombatDecision();

    void setCombatDecision(CombatGroup.rpsChoice decision);

    int getStatByReference(StatHandler.Stats stat);
}
