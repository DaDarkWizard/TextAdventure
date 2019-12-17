package CombatHandler.Weapons;

import CombatHandler.AttackCommands;
import CombatHandler.Combatant;
import GamePieces.Item;
import PlayerHandler.Player;

/**
 * Handles weapon methods and equip/dequip mechanics
 *
 * Date Original Last Modified: 12/14/2019
 * Added setters for weapon descriptions: 12/16/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen, Michael Clinesmith
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
     * @param name name of weapon
     * @param dice dice sides
     * @param command command used by weapon
     * @param stat stat used by weapon
     * @param grade grade for weapon
     * @param modifier modifier by grade
     * @param adjective adjective
     * @param listener listener for special affect
     * @param verb flavor verb
     */
    public Weapon(String name, int dice, AttackCommands command, StatHandler.Stats stat, String grade, int modifier,
                  String adjective, WeaponUseListener listener, String verb) {

        super(grade + " " + name + " of " + adjective + " " + verb,
                new DescriptionMaker().getNameDescription(name),
                new String[]{(grade + " " + name + " of " + adjective + " " + verb).toLowerCase(),
                        name.toLowerCase(), (grade + " " + name).toLowerCase()});

        this.weaponUseListener = listener;
        this.dice = dice;
        this.modifier = modifier;
        this.attackCommand = command;
        this.stat = stat;
    }

    /**
     * constructor for potions
     * @param name the name
     */
    public Weapon(String name){
        super(name, name + " of greater healing", new String[]{"potion", name.trim().toLowerCase()});
        this.attackCommand = AttackCommands.potion;
        this.setInteractEventListener(e -> {
            int healing = (int) (Math.random() * 40);
            int hurt = e.getPlayer().getMaxHitpoints() - e.getPlayer().getHitPoints();
            if (healing > hurt) {
                healing = hurt;
            }

            e.getPlayer().modifyHitpoints(healing);

            return "The potion healed you for " + healing + " health!";
        });
        this.weaponUseListener = e -> {
            e.getSource().setPendingHeal(e.getSource().getPendingHeal() + (int) (Math.random() * 40));
            if (e.getSource() instanceof Player) {
                ((Player) e.getSource()).getEquipped().remove(e.getWeapon());
                ((Player) e.getSource()).getInventory().remove(e.getWeapon());
            }
        };
    }

    /**
     * returns the stat for the weapon
     * @return the stat
     */
    public StatHandler.Stats getStat() {
        return this.stat;
    }

    /**
     * returns the sides of the dice
     * @return the sides of the dice
     */
    public int getDice() {
        return this.dice;
    }

    /**
     * Rolls the dice of the weapon
     * @return the damage for the dice
     */
    public int rollDice() {
        return ((int) (Math.random() * dice) + 1);
    }

    /**
     * Gets the modifier
     * @return the modifier
     */
    public int getModifier() {
        return this.modifier;
    }

    /**
     * Gets the attack commands
     * @return the attack commands
     */
    public AttackCommands getAttackCommand() {
        return this.attackCommand;
    }

    /**
     * Handles equip mechanics
     * @param player the player
     * @return string for feedback
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
     * @param player the player
     * @return string feedback
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

    /**
     * Gets the long description
     * @return the long description
     */
    @Override
    public String getLongDescription() {
        return this.longDescription;
    }

    /**
     * Setter allowing changes to the shortDescription
     * @param shortDescription String: the new short description
     */
    @Override
    public void setShortDescription( String shortDescription )
    {
        this.shortDescription = shortDescription;
    }

    /**
     * Setter allowing changes to the longDescription
     * @param longDescription String: the new long description
     */
    @Override
    public void setLongDescription( String longDescription )
    {
        this.longDescription = longDescription;
    }
}
