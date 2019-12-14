package World.Tutorial;

import CombatHandler.Weapons.Weapon;
import Generator.WeaponGenerator;

public class UselessMallet {
    public Weapon makeWeapon() {
        return new WeaponGenerator().weapon("Ramshackle", "Mallet", "Suspicious", "Uselessness");
    }
}
