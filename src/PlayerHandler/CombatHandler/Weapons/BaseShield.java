package PlayerHandler.CombatHandler.Weapons;

import PlayerHandler.CombatHandler.AttackCommands;

public abstract class BaseShield extends Weapon {
    public BaseShield(String shortDescription, String longDescription) {
        super(shortDescription, longDescription, AttackCommands.block);
    }
}
