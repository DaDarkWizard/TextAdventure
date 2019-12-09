package PlayerHandler.CombatHandler;

public class DamageHandler {
    private static int rollDice(int sides) {
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
    public static boolean calcAttack(Combatant combatant, AttackCommands command) {
        if (command == AttackCommands.hit) {
            int damage = hit(combatant, combatant.getTarget());
            combatant.getTarget().setPendingDamage(combatant.getTarget().getPendingDamage() + damage);
            return true;
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
