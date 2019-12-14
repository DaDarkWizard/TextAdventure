package CombatHandler.Weapons;

public class StatHandler {

    public enum Stats {
        brawn,
        spiffness,
        moxy,
        smerts
    }

    public static Stats findDefensiveStat(Stats stats) {
        if (stats == Stats.moxy) {
            return Stats.smerts;
        }
        return Stats.spiffness;
    }
}