package CombatHandler.Weapons;

import java.util.EventListener;

public interface WeaponUseListener extends EventListener {
    void handle(WeaponUseEvent e);
}
