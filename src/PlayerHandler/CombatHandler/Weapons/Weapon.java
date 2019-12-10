package PlayerHandler.CombatHandler.Weapons;

import PlayerHandler.CombatHandler.AttackCommands;
import PlayerHandler.CombatHandler.Combatant;
import GamePieces.Item;
import PlayerHandler.Player;

public abstract class Weapon extends Item {
    private AttackCommands attackCommand;
    private WeaponUseListener weaponUseListener;

    public Weapon(String shortDescription, String longDescription, AttackCommands attackCommand) {
        super(shortDescription, longDescription);
    }

    public AttackCommands getAttackCommand() {
        return this.attackCommand;
    }

    public String equip(Player player) {
        for (Weapon weapon : player.getEquipped()) {
            if (weapon.attackCommand == this.attackCommand) {
                return "You already have an item that can do that equipped.";
            }
        }
        player.getEquipped().add(this);
        return "You equip the " + this.getShortDescription() + ".";
    }

    public String dequip(Player player) {
        boolean equipped = false;
        for (Weapon weapon : player.getEquipped()) {
            if (weapon == this) {
                equipped = true;
                break;
            }
        }
        if (equipped) {
            player.getEquipped().remove(this);
            return "You dequip the " + this.getShortDescription() + ".";
        } else {
            return "You don't have the " + this.getShortDescription() + " equipped!";
        }
    }

    public String useWeapon(Combatant combatant) {
        WeaponUseEvent event = new WeaponUseEvent(combatant);
        if (weaponUseListener != null) {
            return weaponUseListener.handle(event);
        }
        return null;
    }

    public void setWeaponUseListener(WeaponUseListener listener) {
        this.weaponUseListener = listener;
    }
}
