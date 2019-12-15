package CombatHandler.Weapons;

import CombatHandler.Combatant;

import java.util.EventObject;

/**
 * Triggers weapon use events
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class WeaponUseEvent extends EventObject {
    Combatant source;
    Weapon weapon;

    public WeaponUseEvent(Combatant source, Weapon weapon) {
        super(source);
        this.source = source;
        this.weapon = weapon;
    }

    @Override
    public Combatant getSource() {
        return this.source;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }
}
