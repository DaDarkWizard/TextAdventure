package PlayerHandler.CombatHandler.Weapons;

import java.util.EventListener;

public interface WeaponUseListener extends EventListener {
    String handle(WeaponUseEvent e);
}
