package CombatHandler.Weapons;

import java.util.EventListener;

/**
 * Interface for weapon events
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public interface WeaponUseListener extends EventListener {
    void handle(WeaponUseEvent e);
}
