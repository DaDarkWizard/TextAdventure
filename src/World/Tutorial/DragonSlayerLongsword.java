package World.Tutorial;

import CombatHandler.AttackCommands;
import CombatHandler.Weapons.StatHandler;
import CombatHandler.Weapons.Weapon;
import CombatHandler.Weapons.WeaponUseListener;

public class DragonSlayerLongsword extends Weapon {

    public DragonSlayerLongsword() {
        super("Longsword", 0, AttackCommands.supermegagiantawesomeslash, StatHandler.Stats.brawn,
                "Dragon Slayer", 0, "Dragon",
                e -> {
                    if (e.getSource().getTarget() != null && e.getSource().getTarget().getName().equals("Dragon")) {
                        e.getSource().getTarget().setPendingDamage(Integer.MAX_VALUE / 4);
                    }
                },
                "Slaying");
    }
}
