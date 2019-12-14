package CombatHandler;

import CombatHandler.Weapons.Weapon;
import com.sun.org.glassfish.external.statistics.Stats;

import java.util.ArrayList;

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

    int getStatByReference(Stats stat);
}
