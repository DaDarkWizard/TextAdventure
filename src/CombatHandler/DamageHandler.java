package CombatHandler;

import CombatHandler.Weapons.Weapon;
import NPCHandler.NPC;
import PlayerHandler.Player;

/** 
 * This class handles all things damage related
 *
 * Date Last Modified: 12/14/2019
 * @author Daniel Masker, Ben Hodsdon, Emma Smith, Joseph Teahen
 *
 * CS1131, fall 2019
 * Lab Section 2
 */


public class DamageHandler {
    public static int rollDice(int sides) {
        if (sides < 1) {
            throw new IllegalArgumentException();
        }

        return (int) (Math.random() * (double) sides) + 1;
    }

    /**
     * Given a combatant and attack command, figures out what the attack does
     *
     * @param combatant the one using the attack command
     * @param command   the command being used
     * @return true if command is successfully calculated, false otherwise
     */
    
    //TOdo finish linking weapon
    public static boolean calcAttack(Combatant combatant, AttackCommands command) {
        if (combatant instanceof NPC) {
            ((NPC) combatant).findTarget();
            ((NPC) combatant).makeAttack();
        }
        if (combatant.getTarget() == null) {
            return false;
        }
        if (command == AttackCommands.hit) {
            int damage = hit(combatant, combatant.getTarget());
            combatant.getTarget().setPendingDamage(combatant.getTarget().getPendingDamage() + damage);
            return true;
        }
        if (combatant instanceof Player) {
            Player player = (Player) combatant;
            for (Weapon weapon : player.getEquipped()) {
                if (weapon.getAttackCommand() == command) {
                    weapon.useWeapon(player);
                }
            }
        }
        return false;
    }

    /**
     * Calculates damage for the hit attack command.
     *
     * @param hitter combatant hitting
     * @param hittee combatant getting hit
     * @return damage done
     */
    private static int hit(Combatant hitter, Combatant hittee) {
        if (hittee == null) {
            return 0;
        }

        int damage = hitter.getBrawn() - hittee.getSpiffness() + rollDice(4);
        if (damage < 0) {
            damage = 0;
        }

        return damage;
    }
}
