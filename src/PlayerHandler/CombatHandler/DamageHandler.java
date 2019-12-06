package PlayerHandler.CombatHandler;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class DamageHandler {
    public static int rollDice(int sides) {
        if (sides < 1) {
            throw new IllegalArgumentException();
        }

        return (int) (Math.random() * (double) sides) + 1;
    }

    public static int getDamage() {
        return -1;
    }
}
