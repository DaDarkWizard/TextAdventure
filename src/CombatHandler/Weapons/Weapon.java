package CombatHandler.Weapons;

import CombatHandler.AttackCommands;
import CombatHandler.Combatant;
import GamePieces.Item;
import GamePieces.Room;
import PlayerHandler.Commands;
import PlayerHandler.Player;

import java.lang.reflect.Array;

public class Weapon extends Item {
    private AttackCommands attackCommand;
    private WeaponUseListener weaponUseListener;
    private int dice;
    private int modifier;


    public Weapon(String name, int dice, AttackCommands command, String grade, int modifier,
                  String adjective, WeaponUseListener listener, String verb) {

        super(grade + " " + name + " " + adjective + " " + verb,
                "",
                new String[]{grade + " " + name + " " + adjective + " " + verb, name, grade + " " + name});

        this.weaponUseListener = listener;
        this.dice = dice;
        this.modifier = modifier;
        this.attackCommand = command;
    }

    public int getDice() {
        return this.dice;
    }

    public int getModifier() {
        return this.modifier;
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

    public void useWeapon(Combatant combatant) {
        WeaponUseEvent event = new WeaponUseEvent(combatant, this);
        if (weaponUseListener != null) {
            weaponUseListener.handle(event);
        }
    }

    //Todo generate this
    public String generateLongDescription() {
        return null;
    }

    public void setWeaponUseListener(WeaponUseListener listener) {
        this.weaponUseListener = listener;
    }
}
