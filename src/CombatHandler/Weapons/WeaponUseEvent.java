package CombatHandler.Weapons;

import CombatHandler.Combatant;

import java.util.EventObject;

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
