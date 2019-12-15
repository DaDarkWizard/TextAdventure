package CombatHandler.Weapons;

import CombatHandler.AttackCommands;
import CombatHandler.Combatant;
import GamePieces.Item;
import PlayerHandler.Player;

/**
 * Handles weapon methods and equip/dequip mechanics
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */
public class Weapon extends Item {
    private AttackCommands attackCommand;
    private WeaponUseListener weaponUseListener;
    private int dice;
    private int modifier;
    private StatHandler.Stats stat;

    /**
     * Assigns methods to establish the stats and characteristics of weapons
     *
     * @param name
     * @param dice
     * @param command
     * @param stat
     * @param grade
     * @param modifier
     * @param adjective
     * @param listener
     * @param verb
     */
    public Weapon(String name, int dice, AttackCommands command, StatHandler.Stats stat, String grade, int modifier,
                  String adjective, WeaponUseListener listener, String verb) {

        super(grade + " " + name + " of " + adjective + " " + verb,
                "",
                new String[]{(grade + " " + name + " of " + adjective + " " + verb).toLowerCase(),
                        name.toLowerCase(), (grade + " " + name).toLowerCase()});

        this.weaponUseListener = listener;
        this.dice = dice;
        this.modifier = modifier;
        this.attackCommand = command;
        this.stat = stat;
    }

    public Weapon(String name){
        super(name, name, new String[]{"potion", name});
    }   // Assigns weapon name

    public StatHandler.Stats getStat() {
        return this.stat;
    }                          // Assigns weapon stats

    public int getDice() {
        return this.dice;
    }                                        // Assigns damage modifiers

    public int rollDice() {
        return ((int) (Math.random() * dice) + 1);
    }              // Modifies damage modifier

    public int getModifier() {
        return this.modifier;
    }                                // Returns modded total

    public AttackCommands getAttackCommand() {
        return this.attackCommand;
    }           // Assigns combat commands

    /**
     * Handles equip mechanics
     * @param player
     * @return
     */
    public String equip(Player player) {
        for (Weapon weapon : player.getEquipped()) {
            if (weapon.attackCommand == this.attackCommand) {
                return "You already have an item that can do that equipped.";
            }
        }
        player.getEquipped().add(this);
        return "You equip the " + this.getShortDescription() + ".";
    }

    /**
     * Handles dequip mechanics
     * @param player
     * @return
     */
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

    /**
     * Allows players to use weapons if able
     * @param combatant
     */
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

    @Override
    public String getLongDescription() {
        return this.longDescription;
    }

    public void setWeaponUseListener(WeaponUseListener listener) {
        this.weaponUseListener = listener;
    }
}
