package CombatHandler.Weapons;

import CombatHandler.Combatant;

import java.util.EventObject;

public class WeaponUseEvent extends EventObject {
    Combatant source;

    public WeaponUseEvent(Combatant source) {
        super(source);
        this.source = source;
    }

    @Override
    public Combatant getSource() {
        return this.source;
    }
}
